package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.MyConnection;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.bean")
public class AppConfig {
    @Bean
/*
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
*/
    @Scope("prototype")
    // This bean will be created as a new instance
    // each time it is requested
    public MyConnection myConnection(){
        return new MyConnection();
    }
}
