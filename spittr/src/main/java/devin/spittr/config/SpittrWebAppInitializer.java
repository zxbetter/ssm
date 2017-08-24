package devin.spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Servlet 3.0 中, 容器会在类路径中查找实现 ${@link javax.servlet.ServletContainerInitializer} 接口的类,
 * 并用其配置 Servlet 容器。
 *
 * <p> Spring 提供了改接口的实现即 {@link org.springframework.web.SpringServletContainerInitializer}, 这个类
 * 反过来会查找实现 ${@link org.springframework.web.WebApplicationInitializer} 的类并将配置任务交给他们完成。
 *
 * <p> Spring 3.2 引入了 ${@link AbstractAnnotationConfigDispatcherServletInitializer} 对
 * ${@link org.springframework.web.WebApplicationInitializer} 进行了基础实现。
 *
 * 这里创建 ${@link SpittrWebAppInitializer} 继承 ${@link AbstractAnnotationConfigDispatcherServletInitializer}
 * 当部署到 Servlet 容器时, 容器会自动发现它, 并用它来配置 Servlet 的上下文。
 * @author devin
 * @since 1.0.0
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取带有 {@link org.springframework.context.annotation.Configuration} 注解的类用来配置
     * ${@link org.springframework.web.context.ContextLoaderListener} 应用上下文中的 bean
     * @return
     * @since 1.0.0
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class};
    }

    /**
     * 获取带有 {@link org.springframework.context.annotation.Configuration} 注解的类用来配置
     * {@link org.springframework.web.servlet.DispatcherServlet} 应用上下文中的 bean
     * @return
     * @since 1.0.0
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    /**
     * 将一个或多个路径映射到 ${@link org.springframework.web.servlet.DispatcherServlet} 上。
     * 这里映射所有的请求。
     * @return 路径的字符串数组
     * @since 1.0.0
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /**
     * 设置容器的初始化参数
     * @param servletContext
     * @throws ServletException
     * @since 1.0.0
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // 激活 Profile
        // 依赖两个独立的属性 spring.profiles.default 和 spring.profiles.active
        // 可以设置多个, 并用逗号隔开
        // 激活方式
        // 1. 作为 DispatcherServlet 的初始化参数
        // 2. 作为 Web 应用的上下文参数
        // 3. 作为 JNDI 条目
        // 4. 作为环境变量
        // 5. 作为 JVM 的系统属性
        // 6. 在集成测试类上，使用 @ActiveProfiles 注解设置

        // 设置容器上下文的初始化参数, 等价于 web.xml 中的 <context-param></context-param>
        servletContext.setInitParameter("spring.profiles.default", "prod");
        // servletContext.setInitParameter("spring.profiles.active", activeValue);
    }
}
