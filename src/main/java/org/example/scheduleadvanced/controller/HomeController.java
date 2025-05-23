package org.example.scheduleadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.MemberResponseDto;
import org.example.scheduleadvanced.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;

    @GetMapping("/home")
    public String home(
            @CookieValue(name = "email", required = false) String email,
            Model model
    ){
        System.out.println("쿠키 email = " + email);
        // 쿠키에 값이 없으면 로그인 페이지로 이동
        if(email == null) {
            System.out.println("email 쿠키 없음 → 로그인 페이지로 이동");
            return "login";
        }

        // 일치하는 회원정보가 아닌 경우 로그인 페이지로 이동
        MemberResponseDto loginUser = memberService.findUserByEmail(email);
        if(loginUser == null) {
            return "login";
        }

        // 정상적으로 로그인 된 사람이라면 View에서 사용할 데이터를 model 객체에 데이터 임시 저장
        model.addAttribute("loginUser", loginUser);
        // home 화면으로 이동
        return "home";
    }
}
