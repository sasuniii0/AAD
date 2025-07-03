package lk.ijse.gdse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wildcard")
public class WildCardMappingController {
    @GetMapping("/map")
    public String handleWildcard() {
        return "Wildcard mapping matched!";
    }
    @GetMapping("/test/**")
    public String handleWildcardTest() {
        return "Wildcard mapping matched!";
    }
}
