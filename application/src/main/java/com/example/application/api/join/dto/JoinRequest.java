package com.example.application.api.join.dto;

import com.example.domain.member.dto.JoinCommand;

public class JoinRequest {

    private final String email;
    private final String password;
    private final String role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public JoinRequest(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public JoinCommand of() {
        return new JoinCommand(email, password, role);
    }
}
