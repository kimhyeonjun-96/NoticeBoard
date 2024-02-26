package com.noticeboard.notice.board.domain.article.service;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.article.repository.ArticleRepository;
import com.noticeboard.notice.board.domain.article.repository.ArticleRepositoryImpl;
import com.noticeboard.notice.board.domain.member.Member;
import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import com.noticeboard.notice.board.domain.member.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleRepositoryImpl articleRepositoryImpl;
    private final MemberRepositoryImpl memberRepository;

    /**
     * 새로운 글 작성
     */
    @Transactional
    public void saveNewArticle(Long memberId, ArticleDTO articleDTO) {

        Article newArticle = new Article(articleDTO.getArticleId(), articleDTO.getTitle(), articleDTO.getContent(), new Timestamp(System.currentTimeMillis()));
        Member member = memberRepository.findMemberById(memberId);
        articleRepositoryImpl.saveArticle(newArticle);
        member.addArticle(newArticle);
    }


    /**
     * 특정 회원 글 페이징 처리
     */
    public Page<ArticleDTO> getPageable(MemberDTO memberDTO, Pageable pageable) {

        Member member = memberRepository.findMemberById(memberDTO.getId());

        // 작성일자를 기준으로 내림차순 정렬
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Article> articlePage = articleRepository.findALlByMember(member, pageable);

        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (Article article : articlePage) {
            articleDTOList.add(article.convertToArticleDTO());
        }
        return new PageImpl<>(articleDTOList, pageable, articlePage.getTotalElements());
    }

    @Transactional
    public void removeArticle(Long memberId, Long articleId) {

        Member member = memberRepository.findMemberById(memberId);
        Article findArticle = articleRepositoryImpl.findByArticleId(articleId);
        articleRepositoryImpl.removeArticle(findArticle);
        member.removeArticle(articleId);

    }

    public ArticleDTO findArticleDTOById(Long articleId) {

        Article article = articleRepositoryImpl.findByArticleId(articleId);
        return article.convertToArticleDTO();
    }

    @Transactional
    public void updateArtile(ArticleDTO articleDTO) {

        Article article = articleRepositoryImpl.findByArticleId(articleDTO.getArticleId());
        article.updateArticle(articleDTO.getContent(), new Timestamp(System.currentTimeMillis())); // 변경된 글 저장
        articleDTO = article.convertToArticleDTO();
    }
}
