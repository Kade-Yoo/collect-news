package com.example.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class MemberRole {

    public String getRole() {
        return role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "int unsigned", nullable = false)
    private Long memberRoleId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String role;

    public MemberRole() {}
    public MemberRole(Member member, String role) {
        this.member = member;
        this.role = role;
    }

    public static MemberRole create(Member member, String role) {
        return new MemberRole(member, role);
    }
}
