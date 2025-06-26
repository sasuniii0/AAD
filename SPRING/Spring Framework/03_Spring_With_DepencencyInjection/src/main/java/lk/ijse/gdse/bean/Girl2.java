package lk.ijse.gdse.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Girl2 implements Aggreement{
    public Girl2() {
        System.out.println("Girl2 constructor called");
    }
    @Override
    public void chat() {
        System.out.println("Girl2 chat");
    }
}
