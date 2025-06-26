package lk.ijse.gdse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:8application.properties")
@ComponentScan("lk.ijse.gdse.bean")
public class AppConfig {

}
