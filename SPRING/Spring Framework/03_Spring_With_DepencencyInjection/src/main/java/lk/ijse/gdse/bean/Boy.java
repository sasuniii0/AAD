package lk.ijse.gdse.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {
/*
    @Autowired
*/


    @Qualifier("girl2")
    // Specify which implementation of Agreement to use


    Aggreement girl;

    public Boy(){
        System.out.println("Boy Constructor Called");
    }

    public void chatWithGirl(){
        girl.chat();
    }
}
