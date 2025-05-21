package org.example.scheduleadvanced.repository;

import org.example.scheduleadvanced.dto.UserResponseDto;
import org.example.scheduleadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(long id);
}
