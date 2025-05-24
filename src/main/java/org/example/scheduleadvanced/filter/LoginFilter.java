package org.example.scheduleadvanced.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/api/members/signup", "/login", "/logout"};

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestUrl = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        log.info("로그인 필터 로직 실행");

        // 로그인 체크하는 URL인지 검사
        if(!isWhiteList(requestUrl)){
            HttpSession session = httpServletRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey값") == null){
                throw new RuntimeException("로그인해주세요");
            }

            log.info("로그인 성공했습니다!");
        }
        // List에 있는 URL 요청일 경우 : chain.doFilter() 호출
        // List에 없는 URL 요청일 경우 : 필터 로직 통과 후 chain.doFilter() 다음 필터나 servlet 호추

        // filterChain.doFilter : 다음 필터가 없으면 servlet -> Controller가 요청되게끔 만들어줌
        // 다음 필터가 있으면 다음 필터 호출
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean isWhiteList(String requestUrl) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestUrl);
    }
}
