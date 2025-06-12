package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.bean")
public class AppConfig {
    @Bean
    public MyConnection myConnection(){
        return new MyConnection();
    }
}
