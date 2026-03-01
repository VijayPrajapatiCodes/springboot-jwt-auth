package AuthSystem.Controller;

import org.springframework.web.bind.annotation.*;

import AuthSystem.Config.JwtUtil;
import AuthSystem.DTO.LoginRequestDTO;
import AuthSystem.DTO.SignupRequestDTO;
import AuthSystem.DTO.UserResponseDTO;
import AuthSystem.Entity.User;
import AuthSystem.Repository.UserRepository;
import AuthSystem.Service.Auth;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final Auth authService;
    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    // ✅ Signup
    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody SignupRequestDTO dto) {
        return authService.signup(dto);
    }

    // ✅ Get User By Id
    @GetMapping("/user/{id}")
    public UserResponseDTO getById(@PathVariable Long id) {
        return authService.getById(id);
    }

    // ✅ Login (JWT Return Karega)
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {

        User user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole(),
                user.getName()
        );
    }
}