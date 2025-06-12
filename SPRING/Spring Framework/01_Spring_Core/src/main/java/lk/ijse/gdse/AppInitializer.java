package lk.ijse.gdse;

import lk.ijse.gdse.bean.MyConnection;
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

        //long way
        /*Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                context.close();
                // okkoma iwara unata passe thamai api context eka close krnn one ..... eka  nisa mehma dnwa.. hook ekkk athule..
            }
        });*/

        /*//class eken
        SpringBean springBean2 = context.getBean(SpringBean.class);
        System.out.println(springBean2);

        // id eken
        SpringBean springBean3 = (SpringBean) context.getBean("springbean");
        System.out.println(springBean3);

        //class and id eken
        SpringBean springBean4 = (SpringBean) context.getBean("springbean",SpringBean.class);
        System.out.println(springBean4);*/

        MyConnection myConnection = context.getBean(MyConnection.class);
        System.out.println(myConnection);
        MyConnection myConnection1 = (MyConnection) context.getBean("myConnection");
        System.out.println(myConnection1);

        // short way
        context.registerShutdownHook();
    }
}