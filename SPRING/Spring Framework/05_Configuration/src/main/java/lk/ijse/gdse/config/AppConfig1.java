package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.SpringBeanOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {
    @Bean
    public SpringBeanOne springBeanOne(){
        return new SpringBeanOne();
    }
}
