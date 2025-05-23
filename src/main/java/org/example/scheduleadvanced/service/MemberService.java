package org.example.scheduleadvanced.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.LoginResponseDto;
import org.example.scheduleadvanced.dto.MemberResponseDto;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserRepository userRepository;

    public MemberResponseDto signup(String email, String nickname, String password){
        Member user = new Member(email, password, nickname);
        userRepository.save(user);
        return MemberResponseDto.toDto(user);
    }

    public List<MemberResponseDto> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    public MemberResponseDto findUserByEmail(String email){
        Optional<Member> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        Member user = optionalUser.get();
        return new MemberResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public MemberResponseDto findUserByNickname(String nickname){
        Optional<Member> optionalUser = userRepository.findUserByNickname(nickname);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        Member user = optionalUser.get();
        return new MemberResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public MemberResponseDto findUserById(Long id){
        Optional<Member> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        Member user = optionalUser.get();
        return new MemberResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Transactional
    public MemberResponseDto modifyUserEmail(Long id, String oldEmail, String newEmail){
        Member user = userRepository.findUserById(id);
        if (!user.getEmail().equals(oldEmail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 일치하지 않습니다.");
        }
        user.updateEmail(newEmail);
        return MemberResponseDto.toDto(user);
    }

    @Transactional
    public MemberResponseDto modifyUserNickname(Long id, String oldNickname, String newNickname){
        Member user = userRepository.findUserById(id);
        if (!user.getNickname().equals(oldNickname)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "닉네임이 일치하지 않습니다.");
        }
        user.updateNickname(newNickname);
        return MemberResponseDto.toDto(user);
    }

    @Transactional
    public MemberResponseDto modifyUserPassword(Long id, String oldPassword, String newPassword){
        Member user = userRepository.findUserById(id);
        if (!user.getEmail().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(newPassword);
        return MemberResponseDto.toDto(user);
    }

    public void DeleteUser(Long id){
        userRepository.delete(userRepository.findUserById(id));
    }

    public LoginResponseDto login(@NotBlank String email, @NotNull String password) {
        // 입력받은 userName, password와 일치하는 Database 조회
        Member user = userRepository.findIdByEmailAndPassword(email, password);
        return new LoginResponseDto(user.getId());
    }
}
