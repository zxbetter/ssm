package devin.spittr.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

/**
 * 数据源的配置文件
 * @author devin
 * @since 1.0.0
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 配置嵌入式数据源 (开发环境)
     *
     * @return
     * @since 1.0.0
     */
    @Profile("dev")
    // 如果不指定 bean 的 ID, 默认为方法名
    @Bean("dataSource")
    public DataSource embeddedDataSource() {

        // 可以通过 addScript() 添加 SQL 脚本文件
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }


    /**
     * 配置连接池 (这里是 dbcp) 数据源 (测试环境)
     *
     * @return
     * @since 1.0.0
     */
    @Profile("qa")
    @Bean("dataSource")
    public DataSource dbcpDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/ssm_spittr?characterEncoding=utf-8");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        basicDataSource.setInitialSize(5);

        return basicDataSource;

        // 配置 JDBC 驱动的数据源 (与连接池数据源的区别在于没有连接池的功能)

        // 1. SingleConnectionDataSource: 在每个连接请求时都会返回同一个的连接 (不支持多线程)
        // 2. SimpleDriverDataSource: 对每个请求返回新的连接, 但是它直接使用 JDBC 驱动, 来解决在特定环境下
        // 的类加载问题, 这样的环境包括 OSGi 容器
        // 3. DriverManagerDataSource: 对每个请求返回新的连接
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ssm_spittr?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;*/
    }

    /**
     * 设置 jndi 数据源 (生产环境)
     *
     * @return
     * @since 1.0.0
     */
    @Profile("prod")
    @Bean("dataSource")
    public DataSource jndiDataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/spittrDS");

        // 如果应用程序运行在 Java 应用服务器中, 需要设置 resource-ref 为 true
        // 这样给定的 jndi-name 将会自动添加 "java:" 前缀.
        jndiObjectFactoryBean.setResourceRef(true);

        jndiObjectFactoryBean.setProxyInterface(DataSource.class);

        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    /**
     * 配置 jdbc 模板, jdbc 模板承担了资源管理和异常处理的工作, 从而简化了 jdbc 代码
     * <p> 1. JdbcTemplate: 最基本的 Spring JDBC 模板, 支持简单的 JDBC 数据库访问功能以及基于索引参数的查询
     * <p> 2. NamedParameterJdbcTemplate: 支持将值以命名参数的形式绑定到 SQL 中.
     * <p> 3. SimpleJdbcTemplate: Spring 3.1 已经废弃.
     *
     * @param dataSource
     * @return
     * @since 1.0.0
     */
    @Bean("jdbcOperations")
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {

        // 借助 JavaConfig 实现注入

        // 1. jndiDataSource() 被 @Bean 注解, 所以 Spring 会拦截所有对它的调用,
        // 确保返回的是 Spring 创建的对象, 也就是 Spring 本身调用它时创建的对象,
        // 而不是每次都对其进行实际的调用.
        // return new NamedParameterJdbcTemplate(jndiDataSource());

        // 2. 通过给 jdbcTemplate 传递参数是最佳的选择,
        // 不要求被注入的 bean (dataSource) 声明在同一个配置类中, 甚至不用在 JavaConfig
        // 中声明(组件扫描/xml)
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
