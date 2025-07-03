package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("param")
public class ParamController {

    @GetMapping(params = {"id"})
    public String get(@RequestParam("id") String name){
        return "Hello "+name;
    }

    @GetMapping(path = "test/{code}", params = {"id","name"})
    public String get(@PathVariable("code") String code, @RequestParam("id") String id, @RequestParam("name") String name){
        return code + " Hello "+id+" "+name;
    }
}
