package com.noticeboard.notice.board.domain.article;

import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;
    @Lob
    private String content;
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public Article(Long id, String title, String content, Timestamp date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    // 회원 정보 추가
    public void updateMember(Member member) {
        this.member = member;
    }

    // ArticleDTO로 변환하여 반환
    public ArticleDTO convertToArticleDTO() {
        return new ArticleDTO(
                this.id,
                this.title,
                this.content,
                this.date,
                this.member.convertToMemberDTO()
        );
    }

    // Member 삭제
    public void removeMember(){
        this.member = null;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", member=" + member +
                '}';
    }

    // 글 수정
    public void updateArticle(String content, Timestamp updateDate) {
        this.content = content;
        this.date = updateDate;
    }
}
