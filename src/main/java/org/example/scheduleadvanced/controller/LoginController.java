package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.*;
import org.example.scheduleadvanced.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // login.html 템플릿 보여주기
    }

    @PostMapping("login")
    public String login(
            @Validated @ModelAttribute LoginRequestDto request,
            HttpServletResponse response // 쿠키값 세팅에 필요
    ) throws LoginException {
        // 로그인 유저 조회
        LoginResponseDto responseDto = memberService.login(request.getEmail(), request.getPassword());

        if (responseDto == null) {
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
