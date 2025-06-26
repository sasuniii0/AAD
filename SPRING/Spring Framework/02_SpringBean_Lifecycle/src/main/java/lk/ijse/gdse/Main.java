package lk.ijse.gdse;

import lk.ijse.gdse.bean.MyConnection;
import lk.ijse.gdse.bean.TestBean3;
import lk.ijse.gdse.bean.TestBean4;
import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        /*TestBean3 bean = context.getBean(TestBean3.class);
        System.out.println(bean);
        TestBean4 bean2 = context.getBean(TestBean4.class);
        System.out.println(bean2);*/

        MyConnection myConnection1 = context.getBean(MyConnection.class);
        System.out.println(myConnection1);

        System.out.println("=====================================");

        MyConnection myConnection2 = context.getBean(MyConnection.class);
        System.out.println(myConnection2);

        context.registerShutdownHook();
    }
}