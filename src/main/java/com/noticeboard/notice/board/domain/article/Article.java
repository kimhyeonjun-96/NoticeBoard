package com.noticeboard.notice.board.domain.article;

import com.noticeboard.notice.board.domain.member.Member;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Article {

    @Id @GeneratedValue
    @Column(name = "ARTICLE_ID")
    private Long id;

    private String title;
    @Lob
    private String content;
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "ID")
    private Member member;

}
