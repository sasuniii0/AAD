package lk.ijse.gdse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanOne implements InitializingBean {
    @Value("Sasuni-Wijerathne")
    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Spring Bean One with name: " + name);
    }


   /* @Autowired(required = false)
    public SpringBeanOne(@Value("Sasuni-Wijerathne") String name,
    @Value("21") int age,@Value("true") boolean isActive) {
        System.out.println("Spring Bean One with name: " + name);
        System.out.println("Spring Bean One with age: " + age);
        System.out.println("Spring Bean One with isActive: " + isActive);
    }



    @Autowired(required = false)
    public SpringBeanOne(@Value("Sasuni-Wijerathne") String name,@Value("21") int age) {
        System.out.println("Spring Bean One with name: " + name);
        System.out.println("Spring Bean One with age: " + age);
    }*/
}
