package com.noticeboard.notice.board.domain.member.service;

import com.noticeboard.notice.board.domain.member.Member;
import com.noticeboard.notice.board.domain.member.dto.MemberDto;
import com.noticeboard.notice.board.global.util.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    /**
     * 회원가입 로직
     */
    @Transactional
    public Long signup(MemberDto memberDto) {
        // 아이디 중복 검사
        // 계정 저장
        // 회원 pk id 리턴
        Address address = new Address(memberDto.getCity(), memberDto.getStreet(), memberDto.getZipcode());
        Member signupMember = new Member(memberDto.getMember_id(), memberDto.getMember_pwd(), memberDto.getMember_name(), memberDto.getMember_phone(), address);

        return signupMember.getId();
    }

}
