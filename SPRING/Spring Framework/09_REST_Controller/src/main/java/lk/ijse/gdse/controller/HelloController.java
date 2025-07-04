package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hello")
@RestController
public class HelloController {
    @GetMapping("post/one")
    public String post(){
        return "POST";
    }
    @GetMapping("2")
    public String get2(){
        return "GET2";
    }
    @GetMapping("get")
    public String get(){
        return "GET";
    }
    @GetMapping("put/one")
    public String put(){
        return "PUT";
    }
    @GetMapping("delete/one")
    public String delete(){
        return "DELETE";
    }
    @GetMapping("get/one")
    public String getOne(){
        return "GET";
    }
    @GetMapping("patch/one")
    public String patch(){
        return "PATCH";
    }

}
