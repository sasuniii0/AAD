package lk.ijse.gdse.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // This bean will be created as a new instance each time it is requested
public class TestBean3 {
    TestBean3() {
        System.out.println("TestBean3 Constructor Called");
    }
}
