package com.noticeboard.notice.board.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupMemberRequest {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberPhone;

    private String city;
    private String street;
    private String zipcode;

    @Override
    public String toString() {
        return "SignupMemberRequest{" +
                "memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
