package com.noticeboard.notice.board.domain.article.repository;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * 특정 회원의 글 리스트 조회
     */
    Page<Article> findALlByMember(Member member, Pageable pageable);

    /**
     * 모든 회원의 글 리스트 조회
     */
    Page<Article> findAll(Pageable pageable);
}
