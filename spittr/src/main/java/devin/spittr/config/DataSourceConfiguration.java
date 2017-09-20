package devin.spittr.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * 数据源的配置文件
 * @author devin
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:app.properties")    // Spring.Environment-1
public class DataSourceConfiguration {

    /** 日志打印 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

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

        LOGGER.info("创建了嵌入式数据源, 用于开发环境.");
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

        // Spring.Environment-3
        // Environment 的相关方法
        /*LOGGER.info(env.getProperty("mysql.driver", "no driver"));
        LOGGER.info(env.getProperty("mysql.initial.size", Integer.class, 0));
        LOGGER.info(env.getRequiredProperty("mysql.url"));
        LOGGER.info(Stream.of(env.getActiveProfiles()).reduce(String::concat));
        LOGGER.info(Stream.of(env.getDefaultProfiles()).reduce(String::concat));*/

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(mysqlDriver);
        basicDataSource.setUrl(mysqlUrl);
        basicDataSource.setUsername(mysqlUsername);
        basicDataSource.setPassword(mysqlPassword);
        basicDataSource.setInitialSize(env.getProperty("mysql.initial.size", Integer.class, 0));

        LOGGER.info("创建 dbcp 数据源, 用于测试环境.");
        return basicDataSource;

        // 配置 JDBC 驱动的数据源 (与连接池数据源的区别在于没有连接池的功能)

        // 1. SingleConnectionDataSource: 在每个连接请求时都会返回同一个的连接 (不支持多线程)
        // 2. SimpleDriverDataSource: 对每个请求返回新的连接, 但是它直接使用 JDBC 驱动, 来解决在特定环境下
        // 的类加载问题, 这样的环境包括 OSGi 容器
        // 3. DriverManagerDataSource: 对每个请求返回新的连接
        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriver);
        dataSource.setUrl(mysqlUrl);
        dataSource.setUsername(mysqlUsername);
        dataSource.setPassword(mysqlPassword);
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
        LOGGER.info("创建了 jndi 数据源, 用于生产环境.");
        return new JndiDataSourceLookup().getDataSource(jndiName);
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

    // 获取外部值(配置文件等)
    // 1. 使用 Spring 的 Environment
    // 1) @PropertySource(path) 将属性文件加载到 Spring 的 Environment 中.
    // 2) env.getProperty(key, defaultVlaue) 获取属性值
    @Autowired
    private Environment env;    // Spring.Environment-2

    // 2. 解析属性占位符    属性占位符-1
    // 1). xml: <context: property-placeholder />;
    // javaConfig: PropertySourcesPlaceholderConfigurer/PropertyPlaceholderConfigurer
    // 推荐 PropertySourcesPlaceholderConfigurer, 因为它能基于 Spring Environment 及其属性源来解析占位符
    // 这个好像不是必须的, 类上面有 PropertySource 也可以.
    // 2) xml: ${key}; javaConfig: @Value("${key}")

    // 3. SpringEL
    // #{systemProperties["key"]}    // 引用系统属性
    // #{object.field}    // 引用对象或对象属性
    // #{object.method()?.toUpperCase()}    // ?. 先判空
    // #{T(java.lang.Math).PI}    // T() 产生 Class 对象
    // SpringEL 能使用各种运算符, lt = <, gt = >, eq = ==, le = <=, ge = >=, and, or, not, |, matches
    // SpringEL 支持集合操作, [] 按索引获取集合, 数组或字符串的元素
    // .?[] 过滤集合的元素, .^[] / .$[] 查询第一或最后一个匹配项, .![] 将集合的某个属性映射到另一个集合

    // 属性占位符-3
    @Value("${mysql.driver}")
    private String mysqlDriver;

    @Value("${mysql.url}")
    private String mysqlUrl;

    @Value("${mysql.username}")
    private String mysqlUsername;

    @Value("${mysql.password}")
    private String mysqlPassword;

    @Value("${jndi.name}")
    private String jndiName;

    // 属性占位符-2
    // 注意 static
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("app.properties"));
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return placeholderConfigurer;
    }*/
}
