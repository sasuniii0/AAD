package lk.ijse.gdse.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBean3 {
    public SpringBean3() {
        System.out.println("Spring Bean 3");
    }

    // didn't satisfy the interbean dependency
    // inject normal java objects not beans
    // light mode
    // we use light mode because we this is Spring bean

    @Bean
    public SpringBean1 springBean1(){
        SpringBean2 springBeanOne = new SpringBean2();
        SpringBean2 springBeanTwo = new SpringBean2();
        // this creates normal java objects not beans

        System.out.println(springBeanOne);
        System.out.println(springBeanTwo);

        return new SpringBean1();

    }
    @Bean
    public SpringBean2 springBean2(){
        return new SpringBean2();
    }
}
