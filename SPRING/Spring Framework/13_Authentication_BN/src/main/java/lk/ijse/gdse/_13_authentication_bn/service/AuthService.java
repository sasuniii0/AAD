package lk.ijse.gdse._13_authentication_bn.service;

import lk.ijse.gdse._13_authentication_bn.dto.AuthDTO;
import lk.ijse.gdse._13_authentication_bn.dto.AuthResponseDTO;
import lk.ijse.gdse._13_authentication_bn.dto.UserDTO;
import lk.ijse.gdse._13_authentication_bn.entity.Role;
import lk.ijse.gdse._13_authentication_bn.entity.User;
import lk.ijse.gdse._13_authentication_bn.repository.UserRepository;
import lk.ijse.gdse._13_authentication_bn.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        User user = userRepository.findByUsername(authDTO.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(authDTO.getUsername());
        String role = user.getRole().name(); // Get the user's role
        String username = user.getUsername(); // Get the user's username

        return new AuthResponseDTO(token, role,username);
    }

    public String register(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user  = User.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }
}
