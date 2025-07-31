package lk.ijse.gdse.o13_auth_bn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String role;
}
