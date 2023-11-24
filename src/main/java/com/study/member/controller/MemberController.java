package com.study.member.controller;

import com.study.member.dto.MemberDTO;
import com.study.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    // index.html 에서 링크를 클릭해서 오는건 거의 GetMapping
    @GetMapping("/member/save")
    public String saveForm() {
        System.out.println("MemberController.saveForm");
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        // 잘못된 정보이면 loginResult에 안들어갈테니까 null인지 확인하는 것
        if (loginResult != null) {
            // login success
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // login fail
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model){
        // dto를 실어나르는 객체 = model
        // html로 가져갈 데이터가 있다면 model 사용
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);

        return "list";
    }
}
