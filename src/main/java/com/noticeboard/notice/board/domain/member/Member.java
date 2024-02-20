package com.noticeboard.notice.board.domain.member;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.global.util.Address;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_phone;

    @Embedded
    Address address;
    @OneToMany
    List<Article> articles = new ArrayList<>();

    // 생성자 메서드
    protected Member() {}
    public Member(String member_id, String member_pwd, String member_name, String member_phone, Address address) {
        this.member_id = member_id;
        this.member_pwd = member_pwd;
        this.member_name = member_name;
        this.member_phone = member_phone;
        this.address = address;
    }

    // 회원 가입 후 id값 반환 메서드
    public Long getId() {
        return this.id;
    }
}
