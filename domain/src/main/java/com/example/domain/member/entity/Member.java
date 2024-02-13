package com.example.domain.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Member {
    public Long getMemberId() {
        return memberId;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public String getusername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned", nullable = false)
    private Long memberId;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "member")
    private MemberRole memberRole;

    @Column(columnDefinition = "varchar", nullable = false)
    private String username;

    @Column(columnDefinition = "varchar", nullable = false)
    private String password;

    @Column(columnDefinition = "bool", nullable = false)
    private boolean enabled;

    public void update(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member() {}

    public Member(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public static Member create(String username, String password) {
        return new Member(username, password, true);
    }
}
