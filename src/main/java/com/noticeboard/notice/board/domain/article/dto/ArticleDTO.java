package com.noticeboard.notice.board.domain.article.dto;

import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class ArticleDTO {

    private Long articleId;
    private String title;
    private String content;
    private Timestamp createDate;

    private MemberDTO member;

    public ArticleDTO(MemberDTO member) {
        this.member = member;
    }

    public ArticleDTO(String title, String content, Timestamp createDate, MemberDTO member) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.member = member;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + articleId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", member=" + member +
                '}';
    }
}
