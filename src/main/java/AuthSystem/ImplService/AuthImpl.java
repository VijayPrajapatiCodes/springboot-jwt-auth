package AuthSystem.ImplService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import AuthSystem.DTO.SignupRequestDTO;
import AuthSystem.DTO.UserResponseDTO;
import AuthSystem.Entity.User;
import AuthSystem.Mapper.UserMapper;
import AuthSystem.Repository.UserRepository;
import AuthSystem.Service.Auth;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthImpl implements Auth {

    private final UserRepository repo;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserResponseDTO signup(SignupRequestDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = mapper.toEntity(dto);
        user.setPassword(encoder.encode(dto.getPassword()));

        return mapper.toResponse(repo.save(user));
    }

    @Override
    public UserResponseDTO getById(Long id) {

        return mapper.toResponse(
                repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"))
        );
    }
}