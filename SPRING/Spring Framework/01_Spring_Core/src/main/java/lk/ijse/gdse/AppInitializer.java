package lk.ijse.gdse;

import lk.ijse.gdse.bean.SpringBean;
import lk.ijse.gdse.bean.TestBean1;
import lk.ijse.gdse.bean.TestBean2;
import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        SpringBean springBean1 = context.getBean(SpringBean.class);
        springBean1.test();
        System.out.println(springBean1);

        TestBean1 testBean1 = context.getBean(TestBean1.class);
        System.out.println(testBean1);

        TestBean2 testBean2 = context.getBean(TestBean2.class);
        System.out.println(testBean2);

        context.close();
    }
}