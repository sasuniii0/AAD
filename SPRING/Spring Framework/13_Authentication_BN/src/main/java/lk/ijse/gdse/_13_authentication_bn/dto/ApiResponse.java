package lk.ijse.gdse._13_authentication_bn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
}
