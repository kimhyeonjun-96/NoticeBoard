package com.noticeboard.notice.board.domain.member.service;

import com.noticeboard.notice.board.domain.member.Member;
import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import com.noticeboard.notice.board.domain.member.dto.SignupMemberRequest;
import com.noticeboard.notice.board.domain.member.repository.MemberRepositoryImpl;
import com.noticeboard.notice.board.global.util.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return (MemberDTO) authentication.getPrincipal();
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
        Address address = new Address(memberDTO.getCity(), memberDTO.getStreet(), memberDTO.getZipcode()); // 주소 변경
        String encode = bCryptPasswordEncoder.encode(memberDTO.getMemberPwd()); // 패스워드 변경
        member.updateMember(encode, memberDTO.getMemberPhone(), address); // 변경된 회원 저장

        // SecurityContext를 업데이트
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(member, currentAuthentication.getCredentials(), currentAuthentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
    }
}

