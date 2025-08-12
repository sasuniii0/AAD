package lk.ijse.gdse.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// This bean will be created as a new instance each time it is requested
public class TestBean1 {
    TestBean1(){
        System.out.println("TestBean1 Constructor Called");
    }
}
