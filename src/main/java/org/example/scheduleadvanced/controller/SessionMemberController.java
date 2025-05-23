package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.LoginRequestDto;
import org.example.scheduleadvanced.dto.LoginResponseDto;
import org.example.scheduleadvanced.dto.MemberResponseDto;
import org.example.scheduleadvanced.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.login.LoginException;

@Controller
@RequiredArgsConstructor
public class SessionMemberController {

    private final MemberService memberService;

    public abstract static class Const {
        public static final String LOGIN_USER = "loginUser";
    }

    @PostMapping("/session-login")
    public String login(
            @Valid @ModelAttribute LoginRequestDto dto,
            HttpServletRequest request
    ) throws LoginException {

        LoginResponseDto responseDto = memberService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        if (userId == null) {
            return "session-login";
        }

        HttpSession session = request.getSession();

        MemberResponseDto loginUser = memberService.findUserById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);

        return "redirect:/session-home";
    }

    @PostMapping("/session-logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        return "redirect:/session-home";
    }
}