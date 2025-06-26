package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("lk.ijse.gdse.bean")
public class AppConfig {

    @Bean
    @Scope("prototype")
    public MyConnection getMyConnection(){
        return new MyConnection();
    }

}
