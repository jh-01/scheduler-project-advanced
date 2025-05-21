package org.example.scheduleadvanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.UserResponseDto;
import org.example.scheduleadvanced.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.scheduleadvanced.entity.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signup(String email, String nickname, String password){
        User user = new User(email, password, nickname);
        userRepository.save(user);
        return UserResponseDto.toDto(user);
    }

    public List<UserResponseDto> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toDto)
                .toList();
    }

    public UserResponseDto findUserByEmail(String email){
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        User user = optionalUser.get();
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserResponseDto findUserByNickname(String nickname){
        Optional<User> optionalUser = userRepository.findUserByNickname(nickname);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        User user = optionalUser.get();
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserResponseDto findUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        User user = optionalUser.get();
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Transactional
    public UserResponseDto modifyUserEmail(Long id, String oldEmail, String newEmail){
        User user = userRepository.findUserById(id);
        if (!user.getEmail().equals(oldEmail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 일치하지 않습니다.");
        }
        user.updateEmail(newEmail);
        return UserResponseDto.toDto(user);
    }

    @Transactional
    public UserResponseDto modifyUserNickname(Long id, String oldNickname, String newNickname){
        User user = userRepository.findUserById(id);
        if (!user.getNickname().equals(oldNickname)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "닉네임이 일치하지 않습니다.");
        }
        user.updateNickname(newNickname);
        return UserResponseDto.toDto(user);
    }

    @Transactional
    public UserResponseDto modifyUserPassword(Long id, String oldPassword, String newPassword){
        User user = userRepository.findUserById(id);
        if (!user.getEmail().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(newPassword);
        return UserResponseDto.toDto(user);
    }

    public void DeleteUser(Long id){
        userRepository.delete(userRepository.findUserById(id));
    }
}
