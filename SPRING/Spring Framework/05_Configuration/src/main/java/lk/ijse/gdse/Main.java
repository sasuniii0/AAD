package lk.ijse.gdse;

import lk.ijse.gdse.bean.SpringBeanOne;
import lk.ijse.gdse.bean.SpringBeanTwo;
import lk.ijse.gdse.config.AppConfig1;
import lk.ijse.gdse.config.AppConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig1.class);
        context.register(AppConfig2.class);
        context.refresh();

        SpringBeanOne beanOne = context.getBean(SpringBeanOne.class);
        System.out.println(beanOne);

        System.out.println("=====================================");

        SpringBeanTwo beanTwo = context.getBean(SpringBeanTwo.class);
        System.out.println(beanTwo);

        context.registerShutdownHook();

    }
}