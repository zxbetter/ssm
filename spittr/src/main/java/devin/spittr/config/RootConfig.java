package devin.spittr.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 用于配置 {@link org.springframework.web.context.ContextLoaderListener} 应用上下文中的 bean
 * 通常是驱动应用后端的中间层和数据层组件 bean
 * @author devin
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackages = {"devin.spittr"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})

// 通过 @Import 注解可以把其他 java 配置类合并过来, @ImportResource 能合并 xml 配置文件
@Import(DataSourceConfiguration.class)
public class RootConfig {
}
