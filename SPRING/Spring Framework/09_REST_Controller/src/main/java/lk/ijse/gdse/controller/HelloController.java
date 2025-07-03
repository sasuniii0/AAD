package lk.ijse.gdse.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hello")
@RestController
public class HelloController {
    @RequestMapping("post/one")
    public String post(){
        return "POST";
    }
    @RequestMapping("2")
    public String get2(){
        return "GET2";
    }
    @RequestMapping("get")
    public String get(){
        return "GET";
    }
    @RequestMapping("put/one")
    public String put(){
        return "PUT";
    }
    @RequestMapping("delete/one")
    public String delete(){
        return "DELETE";
    }
    @RequestMapping("get/one")
    public String getOne(){
        return "GET";
    }
    @RequestMapping("patch/one")
    public String patch(){
        return "PATCH";
    }

}
