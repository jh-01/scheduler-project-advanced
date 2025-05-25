package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.*;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.exception.UnauthorizedException;
import org.example.scheduleadvanced.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> signUp(
            @Validated @RequestBody SignupRequestDto signupRequestDto
    ) {
        MemberResponseDto memberResponseDto =
                memberService.signup(
                        signupRequestDto.getEmail(),
                        signupRequestDto.getNickname(),
                        signupRequestDto.getPassword()
                );
        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllUsers(){
        List<MemberResponseDto> userList = memberService.findAllMembers();
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getUserById(
            @NotNull @PathVariable Long id
    ){
        MemberResponseDto user = memberService.findMemberById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<MemberResponseDto>> modifyMember(
            @Validated @RequestBody MemberModifyRequestDto modifyDto,
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        if(modifyDto.isEmailUpdate()){
            MemberResponseDto memberResponseDto = memberService.modifyMemberEmail(member.getId(), modifyDto.getOldEmail(), modifyDto.getEmail());
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, true, memberResponseDto, null, "이메일 수정이 완료되었습니다."));
        } else if(modifyDto.isNicknameUpdate()){
            MemberResponseDto memberResponseDto = memberService.modifyMemberNickname(member.getId(), modifyDto.getOldNickname(), modifyDto.getNickname());
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, true, memberResponseDto, null, "닉네임 수정이 완료되었습니다."));
        } else if(modifyDto.isPasswordUpdate()){
            memberService.modifyMemberPassword(member.getId(), modifyDto.getOldPassword(), modifyDto.getPassword());
            return ResponseEntity.ok().body(new ApiResponse<>(HttpStatus.OK, true, null, null, "비밀번호 수정이 완료되었습니다."));
        } else {
            throw new IllegalArgumentException("수정할 내용을 입력해주세요.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        // 유저 삭제 후 세션도 삭제
        memberService.DeleteMember(member.getId());
        session.invalidate();

        return ResponseEntity.ok("유저 삭제 완료했습니다.");
    }
}
