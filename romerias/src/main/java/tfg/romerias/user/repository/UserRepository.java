package tfg.romerias.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.romerias.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
