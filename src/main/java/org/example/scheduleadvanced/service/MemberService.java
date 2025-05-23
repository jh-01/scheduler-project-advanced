package org.example.scheduleadvanced.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.LoginResponseDto;
import org.example.scheduleadvanced.dto.MemberResponseDto;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.exception.LoginFailedException;
import org.example.scheduleadvanced.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto signup(String email, String nickname, String password){
        Member user = new Member(email, password, nickname);
        memberRepository.save(user);
        return MemberResponseDto.toDto(user);
    }

    public List<MemberResponseDto> findAllUsers(){
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    public MemberResponseDto findUserByEmail(String email){
        Optional<Member> optionalUser = memberRepository.findMemberByEmail(email);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        Member user = optionalUser.get();
        return new MemberResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public MemberResponseDto findUserById(Long id){
        Optional<Member> optionalUser = memberRepository.findById(id);
        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저 없음");
        Member user = optionalUser.get();
        return new MemberResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Transactional
    public MemberResponseDto modifyMemberEmail(Long id, String oldEmail, String newEmail){
        Member user = memberRepository.findMemberById(id);
        if (!user.getEmail().equals(oldEmail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 일치하지 않습니다.");
        }
        user.updateEmail(newEmail);
        return MemberResponseDto.toDto(user);
    }

    @Transactional
    public MemberResponseDto modifyMemberNickname(Long id, String oldNickname, String newNickname){
        Member user = memberRepository.findMemberById(id);
        if (!user.getNickname().equals(oldNickname)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "닉네임이 일치하지 않습니다.");
        }
        user.updateNickname(newNickname);
        return MemberResponseDto.toDto(user);
    }

    @Transactional
    public void modifyMemberPassword(Long id, String oldPassword, String newPassword){
        Member member = memberRepository.findMemberById(id);
        if (!member.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        member.updatePassword(newPassword);
    }

    public void DeleteUser(Long id){
        memberRepository.delete(memberRepository.findMemberById(id));
    }

    public LoginResponseDto login(@NotBlank String email, @NotNull String password) throws LoginFailedException {
        // 입력받은 userName, password와 일치하는 Database 조회
        Optional<Member> optionalMember = memberRepository.findMemberByEmailAndPassword(email, password);
        if(optionalMember.isEmpty()) throw new LoginFailedException("이메일 혹은 비밀번호 오류입니다.");
        return new LoginResponseDto(optionalMember.get().getId());
    }
}
