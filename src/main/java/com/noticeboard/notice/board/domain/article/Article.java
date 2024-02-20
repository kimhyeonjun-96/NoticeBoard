package com.noticeboard.notice.board.domain.article;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Article {
    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private Timestamp date;

}
