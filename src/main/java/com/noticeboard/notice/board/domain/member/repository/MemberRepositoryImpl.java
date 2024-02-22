package com.noticeboard.notice.board.domain.member.repository;

import com.noticeboard.notice.board.domain.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl{

    protected final EntityManager em;

    /**
     * 회원 가입
     */
    public void signupMember(Member member) {
        em.persist(member);
    }

    /**
     * id값으로 회원 조회
     */
    public Member findMemberById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    /**
     * 회원 아이디로 회원 조회
     */
    public Member findMemberByMemberId(String memberId) {
        return em.find(Member.class, memberId);
    }

    /**
     * 회원 삭제
     */
    public void deleteMember(Member member) {
        em.remove(member);
    }
}
