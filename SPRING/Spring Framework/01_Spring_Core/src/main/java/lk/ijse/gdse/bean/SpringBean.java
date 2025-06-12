package lk.ijse.gdse.bean;

import org.springframework.stereotype.Component;

@Component
public class SpringBean {
    SpringBean() {
        System.out.println("Spring Bean Constructor Called");
    }
    public void test(){
        System.out.println("Spring Bean Test Method Called");
    }
}
