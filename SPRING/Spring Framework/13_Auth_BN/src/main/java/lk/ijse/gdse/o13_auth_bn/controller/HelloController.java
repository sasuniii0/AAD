package lk.ijse.gdse.o13_auth_bn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
