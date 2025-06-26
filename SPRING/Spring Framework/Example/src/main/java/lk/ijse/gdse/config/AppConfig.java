package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.E;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan("lk.ijse.gdse.bean")
@Import({AppConfig1.class, AppConfig2.class})
@Configuration
public class AppConfig {
    /*@Bean
    public E e (){
        return new E();
    }

    @Bean
    public AppConfig1 appConfig1(){
        return new AppConfig1();
    }

    @Bean
    public AppConfig2 appConfig2(){
        return new AppConfig2();
    }*/

}
