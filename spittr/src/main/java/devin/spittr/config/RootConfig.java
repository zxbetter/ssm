package devin.spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 用于配置 {@link org.springframework.web.context.ContextLoaderListener} 应用上下文中的 bean
 * 通常是驱动应用后端的中间层和数据层组件 bean
 * @author devin
 */
@Configuration
@ComponentScan(basePackages = {"devin.spittr"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}
