package lk.ijse.gdse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController // wenama ui eka hadnwa nam api use krnna oni  me annotation eka..
@Controller
@RequestMapping("hello")
// view return krnnn puluwn... me project ekem ui hdnwa nam @Controller annotation thamai use krnna one...
public class HelloController {
    public HelloController(){
        System.out.println("Hello Controller");
    }

    // ekama controller eke multiple mappings thiyagnna puluwan...

    @GetMapping("index")
    public String hello(){
        return "index";
    }
    @GetMapping("customer")
    public String customer(){
        return "customer";
    }

    @GetMapping("one")
    public String test1(){
        return "Test1 Spring";
    }

    @GetMapping("two")
    public String test2(){
        return "Test2 Spring";
    }
}
