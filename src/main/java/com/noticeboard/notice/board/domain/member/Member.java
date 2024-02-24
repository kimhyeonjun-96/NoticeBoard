package com.noticeboard.notice.board.domain.member;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import com.noticeboard.notice.board.domain.member.dto.MemberDTO;
import com.noticeboard.notice.board.global.util.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "diff_member_id", unique = true)
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberPhone;

    @Embedded
    Address address;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Article> articles = new ArrayList<>();

    public Member(String memberId, String memberPwd, String memberName, String memberPhone, Address address) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.address = address;
    }

    // id값 반환 메서드
    public Long getId() {
        return this.id;
    }

    public String getMemberPwd(){
        return this.memberPwd;
    }

    public List<Article> getArticles() {
        return articles;
    }

    // MemberDTO로 변환하면 반환 메서드
    public MemberDTO convertToMemberDTO() {
        return new MemberDTO(
                this.id,
                this.memberId,
                this.memberPwd,
                this.memberName,
                this.memberPhone,
                this.address.getCity(),
                this.address.getStreet(),
                this.address.getZipcode()
        );
    }
    
    // articles를 MemberDTO로 반환 메서드
    public List<ArticleDTO> convertToArticlesMemberDTO(){
        List<ArticleDTO> articlesMemberDto = new ArrayList<>();
        for (Article article : this.articles) {
            articlesMemberDto.add(article.convertToArticleDTO());
        }
        return articlesMemberDto;
    }

    // 회원 정보 업데이트 메서드
    public void updateMember(String memberPwd, String memberPhone, Address address) {
        this.memberPwd = memberPwd;
        this.memberPhone = memberPhone;
        this.address = address;
    }

    // 회원이 작성한 글 추가
    public void addArticle(Article article) {
        this.articles.add(article);
        article.updateMember(this);
    }

    // 회원이 작성한 글 삭제
    public void removeArticle(Long articleId) {
        this.articles.remove(articleId);
    }
}
