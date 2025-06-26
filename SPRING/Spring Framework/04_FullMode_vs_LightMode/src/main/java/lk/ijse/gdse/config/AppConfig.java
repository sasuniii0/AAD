package lk.ijse.gdse.config;

import lk.ijse.gdse.bean.SpringBean1;
import lk.ijse.gdse.bean.SpringBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("lk.ijse.gdse.bean")
public class AppConfig {

    // inject Spring beans
    //satisfy the interbean dependency
    // use full mode because we use appConfig class

    /*@Bean
    public SpringBean1 springBean1(){
        SpringBean2 springBeanOne = springBean2(); // it called as interbean dependency
        SpringBean2 springBeanTwo = springBean2(); // it called as  interbean dependency

        System.out.println(springBeanOne);
        System.out.println(springBeanTwo);

        return new SpringBean1();

    }
    @Bean
    public SpringBean2 springBean2(){
        return new SpringBean2();
    }*/
}
