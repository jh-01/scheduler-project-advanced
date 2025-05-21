package org.example.scheduleadvanced.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.scheduleadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(long id);
    Optional<User> findUserByNickname(String nickname);
    Optional<User> findUserByEmail(String email);
    User findIdByEmailAndPassword(@NotBlank String email, @NotNull String password);
}
