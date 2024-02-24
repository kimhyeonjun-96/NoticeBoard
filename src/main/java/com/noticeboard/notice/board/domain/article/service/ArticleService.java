package com.noticeboard.notice.board.domain.article.service;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.article.repository.ArticleRepository;
import com.noticeboard.notice.board.domain.member.Member;
import com.noticeboard.notice.board.domain.member.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepositoryImpl memberRepository;

    /**
     * 새로운 글 작성
     */
    @Transactional
    public void saveNewArticle(Long memberId, ArticleDTO articleDTO) {

        Article newArticle = new Article(articleDTO.getArticleId(), articleDTO.getTitle(), articleDTO.getContent(), new Timestamp(System.currentTimeMillis()));
        Member member = memberRepository.findMemberById(memberId);
        articleRepository.saveArticle(newArticle);
        member.addArticle(newArticle);
    }

}
