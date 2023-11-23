package com.study.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 기본 페이지 요청 메서드 (그냥 / 로 많이 표현함)
    @GetMapping("/")
    public String index() {
        // 기본 요청이 왔을 때 index 라는 html 을 띄우겠다는 뜻!
        return "index"; // templates 폴더의 index.html을 찾아감
    }
}
