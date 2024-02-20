package com.noticeboard.notice.board.domain.member.controller;

import com.noticeboard.notice.board.domain.member.dto.MemberDto;
import com.noticeboard.notice.board.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼으로 이동
     */
    @GetMapping("/signup")
    public String createSignupForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "members/signupMemberForm";
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public String signup(@Validated MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            return "members/signupMemberForm";
        }
        memberService.signup(memberDto);
        return "redirect:/";
    }


    /**
     * 로그인
     */
    @GetMapping("/login")
    public String createLoginForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "members/loginForm";
    }
}
