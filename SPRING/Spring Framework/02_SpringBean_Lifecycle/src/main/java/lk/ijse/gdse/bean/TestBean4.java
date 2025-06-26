package lk.ijse.gdse.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestBean4 {
    TestBean4() {
        System.out.println("TestBean4 Constructor Called");
    }

}
