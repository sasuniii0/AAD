package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class PathVariableController {
    @GetMapping("{id}")
    // request mapping eke path ekai ... path variable annotation eke path ekai samana wenn one
    public String examplePathVariable(@PathVariable ("id") String name) {
        return "Path variable received: " + name;
    }

    @GetMapping("{name}/{age}")
    public String examplePathVariableWithAge(@PathVariable("name") String name, @PathVariable("age") int age) {
        return name +" "+ age;
    }

    @GetMapping("customer/{id:[I][0-9]{3}}")
    public String examplePathVariableWithRegex(@PathVariable("id") String id) {
        return id;
    }
    // [I] - starts with I
    // [0-9]{3} - followed by 3 digits
    // [0-9] - any digit from 0 to 9
    @GetMapping("item/{code}/{branch}/{city}")
    public String examplePathVariableWithRegex(@PathVariable("code") String code, @PathVariable("branch") String branch, @PathVariable("city") String city) {
        return code +" "+ branch +" "+ city;
    }

    @GetMapping("items/{code:[I][0-9]{3}}/{branch}/{city}")
    public String examplePathVariableWithRegex2(@PathVariable("code") String code, @PathVariable("branch") String branch, @PathVariable("city") String city) {
        return code +" "+ branch +" "+ city;
    }
}
