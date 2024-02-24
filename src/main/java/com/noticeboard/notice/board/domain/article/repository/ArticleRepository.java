package com.noticeboard.notice.board.domain.article.repository;

import com.noticeboard.notice.board.domain.article.Article;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    /**
     * 글 저장
     */
    public void saveArticle(Article article) {
        em.persist(article);
    }


}
