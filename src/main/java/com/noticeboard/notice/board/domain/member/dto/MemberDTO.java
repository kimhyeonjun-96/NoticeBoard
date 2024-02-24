package com.noticeboard.notice.board.domain.member.dto;

import com.noticeboard.notice.board.domain.article.Article;
import com.noticeboard.notice.board.domain.article.dto.ArticleDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
public class MemberDTO implements UserDetails {

    @NotEmpty
    private Long id;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberPhone;
    private String city;
    private String street;
    private String zipcode;

    List<ArticleDTO> articles = new ArrayList<>();

    public MemberDTO() {}

    public MemberDTO(Long id, String memberId, String memberPwd, String memberName, String memberPhone, String city, String street, String zipcode) {
        this.id = id;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public MemberDTO(Long id, String memberId, String memberPwd, String memberName, String memberPhone, String city, String street, String zipcode, List<ArticleDTO> articles) {
        this.id = id;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("member"));
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
