package com.noticeboard.notice.board.domain.member.service;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.member.Member;
import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import com.noticeboard.notice.board.domain.member.dto.SignupMemberRequest;
import com.noticeboard.notice.board.domain.member.repository.MemberRepositoryImpl;
import com.noticeboard.notice.board.global.util.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepositoryImpl memberRepositoryImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입 로직
     */
    @Transactional
    public Long signup(SignupMemberRequest memberDto) {
        // 아이디 중복 검사
        // 계정 저장
        // 회원 pk id 리턴
        Address address = new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        // 패스워드 암호화
        String encode = bCryptPasswordEncoder.encode(memberDto.getMemberPwd());
        // 가입회원 생성
        Member signupMember = new Member(memberDto.getMemberId(), encode, memberDto.getMemberName(), memberDto.getMemberPhone(), address);

        // 회원 가입
        memberRepositoryImpl.signupMember(signupMember);
        return signupMember.getId();
    }

    /**
     *
     * authentication을 통한 로그인한 회원 정보 반환 로직
     */
    public MemberDTO findByOneMember(Authentication authentication){

        MemberDTO details = (MemberDTO) authentication.getPrincipal();
        List<ArticleDTO> articleDTOList = getMemberArticles(details.getId());
        details.setArticles(articleDTOList);
        return details;
    }

    /**
     * 회원 정보 수정을 위한 id 값으로 회원 조회 반환 로직
     */
    public MemberDTO findMemberDTOById(Long memberId) {
        return memberRepositoryImpl.findMemberById(memberId).convertToMemberDTO();
    }

    /**
     * 회원 정보 변경 로직
     */
    @Transactional
    public void updateMember(MemberDTO memberDTO) {

        Member member = memberRepositoryImpl.findMemberById(memberDTO.getId()); // 기존 회원 조회
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Address address = new Address(memberDTO.getCity(), memberDTO.getStreet(), memberDTO.getZipcode()); // 주소 변경
        String encode = bCryptPasswordEncoder.encode(memberDTO.getMemberPwd()); // 패스워드 변경
        member.updateMember(encode, memberDTO.getMemberPhone(), address); // 변경된 회원 저장

        MemberDTO updateMemberDto = member.convertToMemberDTO();

        // SecurityContext를 업데이트
        Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(updateMemberDto, currentAuthentication.getCredentials(), currentAuthentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
    }

    /**
     * 호원 탈퇴
     */
    @Transactional
    public String withdrawal(MemberDTO memberDTO) {

        Member member = memberRepositoryImpl.findMemberById(memberDTO.getId());

//        System.out.println("Service-withdrawal member password : "+ member.getMemberPwd());
//        System.out.println("Service-withdrawal memberDTO password : "+ memberDTO.getPassword());

        if (bCryptPasswordEncoder.matches(memberDTO.getPassword(), member.getMemberPwd())) {
            memberRepositoryImpl.deleteMember(member);
            return "redirect:/logout";
        } else{
            System.out.println("패스워드가 일치하지 않습니다.");
            return "redirect:/members/mypage";
        }
    }

    /**
     * 회원이 작성한 글 목록 가져오기 로직
     */
    private List<ArticleDTO> getMemberArticles(Long memberId) {

        Member member = memberRepositoryImpl.findMemberById(memberId);
        List<ArticleDTO> articleDTOs = new ArrayList<>();
        for (Article newArticle : member.getArticles()) {
            articleDTOs.add(newArticle.convertToArticleDTO());
        }
        return articleDTOs;
    }

}

