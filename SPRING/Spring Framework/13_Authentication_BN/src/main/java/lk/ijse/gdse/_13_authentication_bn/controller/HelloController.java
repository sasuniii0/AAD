package lk.ijse.gdse._13_authentication_bn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:63342",
        allowCredentials = "true")

@RestController
@RequestMapping("/hello")


public class HelloController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    // This endpoint can be accessed by  ADMIN  roles
    public String helloAdmin() {
        return "Hello, Admin!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    // This endpoint can be accessed by USER roles
    public String helloUser() {
        return "Hello, User!";
    }
}
