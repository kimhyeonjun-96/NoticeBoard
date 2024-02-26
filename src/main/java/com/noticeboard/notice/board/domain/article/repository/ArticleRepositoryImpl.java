package com.noticeboard.notice.board.domain.article.repository;

import com.noticeboard.notice.board.domain.article.Article;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl {

    private final EntityManager em;

    /**
     * 글 저장
     */
    public void saveArticle(Article article) {
        em.persist(article);
    }

    /**
     * 글 id로 글 조회
     */
    public Article findByArticleId(Long articleId) {
        return em.find(Article.class, articleId);
    }

    /**
     * 회원의 선택 된 글 삭제
     */
    public void removeArticle(Article article) {
        em.remove(article);
    }
}
