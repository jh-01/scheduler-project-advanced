package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.*;
import org.example.scheduleadvanced.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // login.html 템플릿 보여주기
    }

    @PostMapping("login")
    public String login(
            @Valid @ModelAttribute LoginRequestDto request,
            HttpServletResponse response // 쿠키값 세팅에 필요
    ){
        // 로그인 유저 조회
        LoginResponseDto responseDto = userService.login(request.getEmail(), request.getPassword());

        if (request.getEmail() == null) {
            return "login";
        }

        Cookie cookie = new Cookie("email", request.getEmail());
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        cookie.setHttpOnly(false);
        // 디버깅 로그
        System.out.println("쿠키 저장: email = " + request.getEmail());
        response.addCookie(cookie);

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(
            HttpServletResponse response
    ){
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/login";
    }
}
