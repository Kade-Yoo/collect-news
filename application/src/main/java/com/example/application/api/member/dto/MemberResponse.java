package com.example.application.api.member.dto;

import com.example.domain.member.dto.MemberResult;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final String username;
//    @JsonIgnore
    private final String password;
    private final String role;

    public MemberResponse(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static MemberResponse of(MemberResult memberResult) {
        return new MemberResponse(memberResult.getUsername(), memberResult.getPassword(), memberResult.getRole());
    }
}
