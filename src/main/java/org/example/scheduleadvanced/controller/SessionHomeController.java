package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.LoginResponseDto;
import org.example.scheduleadvanced.dto.UserResponseDto;
import org.example.scheduleadvanced.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class SessionHomeController {

    private final UserService userService;

    @GetMapping("/session-home")
    public String home(
            HttpServletRequest request,
            Model model
    ) {
        HttpSession session = request.getSession(false);

        if(session == null) {
            return "session-login";
        }

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(SessionUserController.Const.LOGIN_USER);

        if (loginUser == null) {
            return "session-login";
        }

        model.addAttribute("loginUser", loginUser);
        // home 화면으로 이동
        return "session-home";

    }

    @GetMapping("/v2/session-home")
    public String homeV2(
            @SessionAttribute(name = SessionUserController.Const.LOGIN_USER, required = false) LoginResponseDto loginUser,
            Model model
            ){
        if(loginUser == null) return "session-login";

        model.addAttribute(("loginUser"), loginUser);
        return "session-home";
    }
}