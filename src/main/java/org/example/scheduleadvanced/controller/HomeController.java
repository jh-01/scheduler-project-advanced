package org.example.scheduleadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.UserResponseDto;
import org.example.scheduleadvanced.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @RequestMapping("/home")
    public String home(
            @CookieValue(name = "userId", required = false) Long userId,
            Model model
    ){
        // 쿠키에 값이 없으면 로그인 페이지로 이동
        if(userId == null) {
            return "signin";
        }

        // 일치하는 회원정보가 아닌 경우 로그인 페이지로 이동
        UserResponseDto loginUser = userService.findUserById(userId);
        if(loginUser == null) {
            return "signin";
        }

        // 정상적으로 로그인 된 사람이라면 View에서 사용할 데이터를 model 객체에 데이터 임시 저장
        model.addAttribute("loginUser", loginUser);
        // home 화면으로 이동
        return "home";
    }
}
