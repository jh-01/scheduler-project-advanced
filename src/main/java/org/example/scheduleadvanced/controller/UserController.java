package org.example.scheduleadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.*;
import org.example.scheduleadvanced.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(
            @RequestBody UserRequestDto userRequestDto
    ) {
        UserResponseDto userResponseDto =
                userService.signup(
                        userRequestDto.getEmail(),
                        userRequestDto.getNickname(),
                        userRequestDto.getPassword()
                );
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userList = userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long id
    ){
        UserResponseDto user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PatchMapping("/modify/email")
    public ResponseEntity<UserResponseDto> modifyUserEmail(
            @RequestBody ModifyUserEmailDto modifyUserEmailDto
            ){
            UserResponseDto userResponseDto = userService.modifyUserEmail(
                    modifyUserEmailDto.getId(),
                    modifyUserEmailDto.getOldEmail(),
                    modifyUserEmailDto.getNewEmail()
            );
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/modify/nickname")
    public ResponseEntity<UserResponseDto> modifyUserNickname(
            @RequestBody ModifyUserNicknameDto modifyUserNicknameDto
    ){
        UserResponseDto userResponseDto = userService.modifyUserNickname(
                modifyUserNicknameDto.getId(),
                modifyUserNicknameDto.getOldNickname(),
                modifyUserNicknameDto.getNewNickname()
        );
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/modify/password")
    public ResponseEntity<UserResponseDto> modifyUserPassword(
            @RequestBody ModifyUserPasswordDto modifyUserPasswordDto
    ){
        UserResponseDto userResponseDto = userService.modifyUserPassword(
                modifyUserPasswordDto.getId(),
                modifyUserPasswordDto.getOldPassword(),
                modifyUserPasswordDto.getNewPassword()
        );
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable long id
    ){
        userService.DeleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
