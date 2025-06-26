package lk.ijse.gdse.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Girl1 implements Aggreement{
    public Girl1() {
        System.out.println("Girl1 constructor called");
    }

    @Override
    public void chat() {
        System.out.println("Girl chat");
    }
}
