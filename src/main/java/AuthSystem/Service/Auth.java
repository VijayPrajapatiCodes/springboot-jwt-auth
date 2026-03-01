package AuthSystem.Service;


import AuthSystem.DTO.SignupRequestDTO;
import AuthSystem.DTO.UserResponseDTO;

public interface Auth {

    UserResponseDTO signup(SignupRequestDTO dto);

    UserResponseDTO getById(Long id);
}
