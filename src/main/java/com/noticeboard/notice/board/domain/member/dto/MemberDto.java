package com.noticeboard.notice.board.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberDto {

    private String member_id;
    private String member_pwd;
    private String member_name;
    private String member_phone;

    private String city;
    private String street;
    private String zipcode;
}
