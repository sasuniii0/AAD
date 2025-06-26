package lk.ijse.gdse;

import lk.ijse.gdse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        //System variables...
        Map<String, String> getenv = System.getenv();
        for (String key : getenv.keySet()) {
            //System.out.println(key + " : " + getenv.get(key));
        }
        /*String os = System.getenv("OS");
        System.out.println(os);*/

        //Java variables
        //System.getProperties().list(System.out);

        context.registerShutdownHook();
    }
}