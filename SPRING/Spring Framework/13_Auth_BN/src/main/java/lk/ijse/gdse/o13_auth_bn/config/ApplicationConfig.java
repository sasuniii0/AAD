package lk.ijse.gdse.o13_auth_bn.config;

import lk.ijse.gdse.o13_auth_bn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {
    private final UserRepository userRepository;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUserName(username)
                .map(user -> new
                        org.springframework.security.core.userdetails.User(
                                user.getUserName(),
                                user.getPassword(),
                                List.of(new SimpleGrantedAuthority(
                                        "ROLE_" + user.getRole().name()
                                        )
                                )

                        )).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
