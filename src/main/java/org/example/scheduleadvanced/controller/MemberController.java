package org.example.scheduleadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.*;
import org.example.scheduleadvanced.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> signUp(
            @RequestBody SignupRequestDto signupRequestDto
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
        List<MemberResponseDto> userList = memberService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getUserById(
            @PathVariable Long id
    ){
        MemberResponseDto user = memberService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> modifyUserEmail(
            @PathVariable Long id,
            @RequestBody ModelModifyRequestDto modifyDto
            ){
            modifyDto.setId(id);
            MemberResponseDto responseDto = null;

            if(modifyDto.isEmailUpdate()){
                responseDto = memberService.modifyUserEmail(modifyDto.getId(), modifyDto.getOldEmail(), modifyDto.getEmail());
            } else if(modifyDto.isNicknameUpdate()){
                responseDto = memberService.modifyUserNickname(modifyDto.getId(), modifyDto.getOldNickname(), modifyDto.getNickname());
            } else if(modifyDto.isPasswordUpdate()){
                responseDto = memberService.modifyUserPassword(modifyDto.getId(), modifyDto.getOldPassword(), modifyDto.getPassword());
            } else {
                throw new IllegalArgumentException("수정할 내용을 입력해주세요.");
            }

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable long id
    ){
        memberService.DeleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
