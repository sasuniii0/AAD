package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/char")
public class CharacterMappingController {
    //mokakma hri characters 3k tiynn one question marks walta...

    @GetMapping("item/i???")
    public String getItem() {
        return "Item";
    }

    @GetMapping("????/search")
    public String hello() {
        return "Hello";
    }
}
