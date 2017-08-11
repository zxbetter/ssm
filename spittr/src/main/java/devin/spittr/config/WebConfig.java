package devin.spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 用于配置 ${@link org.springframework.web.servlet.DispatcherServlet} 应用上下文中的 bean
 * 通常是 Web 组件的 bean, 如控制器、视图解析器以及处理器映射
 * @author devin
 * @since 1.0.0
 */
@Configuration
@EnableWebMvc
@ComponentScan("devin.spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter{
     /** 视图的前缀 **/
    private static final String RESOLVER_PREFIX = "/WEB-INF/views/";

    /** 视图的后缀 **/
    private static final String RESOLVER_SUFFIX = ".jsp";

    /**
     * 默认的视图解析器是 {@link org.springframework.web.servlet.view.BeanNameViewResolver}
     * 这个解析器会查找 ID 与视图名称匹配的 bean, 并且查找的 bean 要实现 ${@link org.springframework.web.servlet.View} 接口
     *
     * <p> 这里添加 {@link InternalResourceViewResolver} 视图解析器用来解析 JSP 文件。
     * @return
     * @since 1.0.0
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(RESOLVER_PREFIX);
        resolver.setSuffix(RESOLVER_SUFFIX);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     * 通过在重写该方法的过程中调用 <code>enbale()</code> 使得 {@link org.springframework.web.servlet.DispatcherServlet}
     * 对静态资源的请求转发到 Servlet 容器中默认的 servlet 上进行处理。
     * @param configurer
     * @since 1.0.0
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
