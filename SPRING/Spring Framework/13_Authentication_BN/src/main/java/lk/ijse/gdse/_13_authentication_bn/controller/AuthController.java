package lk.ijse.gdse._13_authentication_bn.controller;

import lk.ijse.gdse._13_authentication_bn.dto.ApiResponse;
import lk.ijse.gdse._13_authentication_bn.dto.AuthDTO;
import lk.ijse.gdse._13_authentication_bn.dto.UserDTO;
import lk.ijse.gdse._13_authentication_bn.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ApiResponse(
                200,
                "User registered successfully",
                authService.register(userDTO)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthDTO authDTO) {
        System.out.println("Received login request: " + authDTO.getUsername() + " / " + authDTO.getPassword());

        return ResponseEntity.ok(new ApiResponse(
                200,
                "User authenticated successfully",
                authService.authenticate(authDTO)
        ));
    }
}
