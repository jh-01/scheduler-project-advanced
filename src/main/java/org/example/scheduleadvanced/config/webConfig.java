package org.example.scheduleadvanced.config;

import jakarta.servlet.Filter;
import org.example.scheduleadvanced.filter.CustomFilter;
import org.example.scheduleadvanced.filter.LoginFilter;
import org.hibernate.metamodel.mapping.FilterRestrictable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean customFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 필터 등록
        filterRegistrationBean.setFilter(new CustomFilter());
        // 실행될 필터들 중 순서 설정
        filterRegistrationBean.setOrder(1);
        // 필터를 적용할 URL 패턴/ 이 경우 모든 Request 해당
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
