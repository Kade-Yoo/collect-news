package com.example.domain.member.dto;

import com.example.domain.member.entity.Member;

public class MemberResult {

    private final Long id;
    private final String username;
    private final String password;
    private final String role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public MemberResult(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public MemberResult(Member member) {
        this.id = member.getMemberId();
        this.username = member.getusername();
        this.password = member.getPassword();
        this.role = member.getMemberRole().getRole();
    }
}
