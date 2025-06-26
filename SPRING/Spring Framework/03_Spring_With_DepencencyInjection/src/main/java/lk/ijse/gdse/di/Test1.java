package lk.ijse.gdse.di;

import org.springframework.stereotype.Component;

@Component
public class Test1 implements DI {
    public Test1(){
        System.out.println("Test1 Constructor Called");
    }
    @Override
    public void sayHello() {
        System.out.println("Hello");
    }
}
