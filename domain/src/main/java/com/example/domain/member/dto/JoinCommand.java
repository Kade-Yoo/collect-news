package com.example.domain.member.dto;

public class JoinCommand {

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

    public JoinCommand(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
