package AuthSystem.DTO;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String name;
    private String email;
    private String password;
    private String role;
    
}
