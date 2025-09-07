package com.groupName.practice.controller;

import com.groupName.practice.dto.MemberDTO;
import com.groupName.practice.entity.MemberEntity;
import com.groupName.practice.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // constructor 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력요청
    @GetMapping("/member/signup")
    public String showSignup() {
        return "signup";
    }

    @PostMapping("/member/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO) {
        System.out.printf("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "index";
    }

    // 로그인 페이지 출력요청
    @GetMapping("/member/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/member/login") // session: 로그인 유지
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            // success login
            session.setAttribute("loginUid", loginResult.getUid());
            return "main";
        }
        else {
            // fail login
            return "login";
        }
    }

    // 회원목록 페이지 출력요청
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // model에 memberDTOList 담아서 list.html로 내보냄
        model.addAttribute("userList", memberDTOList);
        return "list";
    }

    // 특정회원 검색 페이지 출력요청
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // @PathVariable은 URL 경로의 {}에 들어 있는 값을 변수로 매핑
        MemberDTO memberDTO = memberService.findById(id);
        // login처럼 값에 따라 분류 가능
        model.addAttribute("user", memberDTO);
        return "detail";
    }

    // 삭제요청
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        // list 로 쓰면 껍데기만 보여지니까 redirect:
        return "redirect:/member/";
    }
}
