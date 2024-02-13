package com.example.application.config.jwt;

public class Jwt {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;

    public String getGrantType() {
        return this.grantType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public Jwt(String grantType, String accessToken, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}