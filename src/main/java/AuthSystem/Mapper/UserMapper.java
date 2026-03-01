package AuthSystem.Mapper;

import org.springframework.stereotype.Component;

import AuthSystem.DTO.SignupRequestDTO;
import AuthSystem.DTO.UserResponseDTO;
import AuthSystem.Entity.User;
@Component
public class UserMapper {
    public User toEntity(SignupRequestDTO dto){
        User user=new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return user;
    }
    public UserResponseDTO toResponse(User user){
        UserResponseDTO dto=new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
