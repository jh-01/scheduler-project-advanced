package org.example.scheduleadvanced.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.scheduleadvanced.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Member findUserById(long id);
    Optional<Member> findUserByNickname(String nickname);
    Optional<Member> findUserByEmail(String email);
    Member findIdByEmailAndPassword(@NotBlank String email, @NotNull String password);
}
