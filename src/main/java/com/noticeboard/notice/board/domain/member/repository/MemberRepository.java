package com.noticeboard.notice.board.domain.member.repository;

import com.noticeboard.notice.board.domain.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    protected final EntityManager em;

    /**
     * 회원 가입
     */
    public void signupMember(Member member) {
        em.persist(member);
    }
}
