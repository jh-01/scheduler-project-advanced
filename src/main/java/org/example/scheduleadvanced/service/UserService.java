package org.example.scheduleadvanced.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.UserResponseDto;
import org.example.scheduleadvanced.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.example.scheduleadvanced.entity.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        User user = optionalUser.get();
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname());
    }
}
