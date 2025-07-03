package lk.ijse.gdse.controller;

import lk.ijse.gdse.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/formurl")
public class FormURLDataController {
    @PostMapping("save")
    public String save(@ModelAttribute UserDTO userDTO) {
        System.out.println("UserDTO: " + userDTO);
        return "User data saved successfully!";
    }
}
