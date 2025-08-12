package lk.ijse.gdse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({"lk.ijse.gdse.bean","lk.ijse.gdse.controller"})
@EnableWebMvc
// This annotation is used to enable Spring MVC features
public class WebAppConfig implements WebMvcConfigurer {


    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/views/");
        // views folder wala path eka api dann one prefix eka widihta
        viewResolver.setSuffix(".jsp");
        // html and jsp file wala suffix eka api dann one
        viewResolver.setOrder(2);
        return viewResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("views/**")
                .addResourceLocations("/views/");
    }
}
