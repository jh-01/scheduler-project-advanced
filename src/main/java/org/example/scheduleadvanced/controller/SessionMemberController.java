package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.LoginRequestDto;
import org.example.scheduleadvanced.dto.LoginResponseDto;
import org.example.scheduleadvanced.dto.MemberResponseDto;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.login.LoginException;

@Controller
@RequiredArgsConstructor
public class SessionMemberController {

    private final MemberService memberService;

    public abstract static class Const {
        public static final String LOGIN_USER = "loginMember";
    }

    @PostMapping("/session-login")
    public String login(
            @Validated @ModelAttribute LoginRequestDto dto,
            HttpServletRequest request
    ) throws LoginException {

        LoginResponseDto member = memberService.login(dto.getEmail(), dto.getPassword());
        Long memberId = member.getId();

        if (memberId == null) {
            return "session-login";
        }

        HttpSession session = request.getSession();
        // 세션에 유저 저장
        session.setAttribute(Const.LOGIN_USER, member);

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