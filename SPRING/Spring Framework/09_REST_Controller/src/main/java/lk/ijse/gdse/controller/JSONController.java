package lk.ijse.gdse.controller;

import lk.ijse.gdse.dto.CityDTO;
import lk.ijse.gdse.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/json")
public class JSONController {
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return "User data saved successfully!";
    }
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO get() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUId("U001");
        userDTO.setUName("Kamal");
        userDTO.setUAddress("Colombo");
        userDTO.setUCity(new CityDTO("Colombo 5", "11300"));
        return userDTO;
    }
}
