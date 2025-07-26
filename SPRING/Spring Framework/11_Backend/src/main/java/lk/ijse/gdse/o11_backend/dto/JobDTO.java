package lk.ijse.gdse.o11_backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobDTO {
    private Integer id;
    @NotBlank (message = "Job title cannot be empty")
    private String jobTitle;
/*
    @NotBlank (message = "Company name cannot be empty")
*/
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Company name should contain only letters and spaces")
    private String company;
    private String location;
    @NotNull (message = "Job type cannot be empty")
    private String type;
    @Size(min = 10, max = 1000, message = "Job description should be between 100 and 1000 characters")
    private String jobDescription;
    @NotEmpty (message = "Status cannot be empty")
    private String status;
}
