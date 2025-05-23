package org.example.scheduleadvanced.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.scheduleadvanced.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberById(long id);
    Optional<Member> findMemberByNickname(String nickname);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberByEmailAndPassword(@NotBlank String email, @NotNull String password);
}
