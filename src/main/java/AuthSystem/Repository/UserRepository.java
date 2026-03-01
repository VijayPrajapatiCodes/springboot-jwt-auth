package AuthSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import AuthSystem.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
      Optional<User>findByEmail(String email);
}
