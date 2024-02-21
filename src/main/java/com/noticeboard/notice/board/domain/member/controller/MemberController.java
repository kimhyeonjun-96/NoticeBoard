package com.noticeboard.notice.board.domain.member.controller;


import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import com.noticeboard.notice.board.domain.member.dto.SignupMemberRequest;
import com.noticeboard.notice.board.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼으로 이동
     */
    @GetMapping("/signup")
    public String createSignupForm(Model model) {

        model.addAttribute("memberDto", new SignupMemberRequest());
        return "members/signupMemberForm";
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public String signup(SignupMemberRequest memberDto, BindingResult result) {

        if (result.hasErrors()) {
            return "members/signupMemberForm";
        }
        memberService.signup(memberDto);
        return "redirect:/login";
    }

    /**
     * 로그인 폼으로 이동
     */
    @GetMapping("/login")
    public String createLoginForm(Model model) {

        model.addAttribute("memberDto", new SignupMemberRequest());
        return "members/loginForm";
    }

    /**
     * 로그인
     * 해보니 이건 안해도 작동을 한다...
     */
//    @PostMapping("/login")
//    public String login(@Valid SignupMemberRequest memberDto, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "members/loginForm";
//        }
//        System.out.println("controller memberDto : " + memberDto.toString());
//        return "redirect:/";
//    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    /**
     * 마이페이지
     */
    @GetMapping("/members/mypage")
    public String mypage(Model model) {

        MemberDTO memberDTO = memberService.findByOneMember(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("memberDto", memberDTO);
        return "members/mypage";
    }

    /**
     * 회원 정보 변경 폼으로 이동
     */
    @GetMapping("/members/{id}/update")
    public String createMemberUpdateForm(@PathVariable("id") Long memberId, Model model) {

//        System.out.println("Controller-createMemberUpdateForm memberId : " + memberId );
        MemberDTO memberDTO = memberService.findMemberDTOById(memberId);
//        System.out.println("Controller-createMemberUpdateForm MemberDTO : " + memberDTO.toString());
        model.addAttribute("memberDto", memberDTO);
        return "members/updateMemberForm";
    }

    /**
     * 회원 정보 변경
     */
    @PostMapping("/members/{id}/update")
    public String updateMemberProfile(@ModelAttribute("memberDto")MemberDTO memberDTO, BindingResult result) {

        System.out.println("Controller-updateMemberProfile MemberDTO : " + memberDTO.toString());
//        memberService.updateMember(memberDTO);
        return "redirect:/";
    }
}

