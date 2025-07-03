package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exact")
public class ExactMappingController {
    @GetMapping
    public String hello() {
        return "get";
    }

    @GetMapping("hello")
    public String hello2() {
        return "hello2";
    }

    @GetMapping("hello/three")
    public String hello3() {
        return "hello3";
    }
    @GetMapping("hello/three/four")
    public String hello4() {
        return "hello4";
 }

}
