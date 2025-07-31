package lk.ijse.gdse.o13_auth_bn.service;

import lk.ijse.gdse.o13_auth_bn.dto.AuthDTO;
import lk.ijse.gdse.o13_auth_bn.dto.AuthResponseDTO;
import lk.ijse.gdse.o13_auth_bn.dto.UserDTO;
import lk.ijse.gdse.o13_auth_bn.entity.Role;
import lk.ijse.gdse.o13_auth_bn.entity.User;
import lk.ijse.gdse.o13_auth_bn.repository.UserRepository;
import lk.ijse.gdse.o13_auth_bn.util.JWTUtil;
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
        User user = userRepository.findByUserName(authDTO.getUserName()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(authDTO.getUserName());
        return new AuthResponseDTO(token);
    }

    public String register(UserDTO userDTO) {
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user  = User.builder()
                .userName(userDTO.getUserName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.valueOf(userDTO.getRole()))
                .build();
        userRepository.save(user);
        return "User registered successfully";
    }
}
