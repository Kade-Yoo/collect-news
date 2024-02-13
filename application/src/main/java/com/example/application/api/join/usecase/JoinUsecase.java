package com.example.application.api.join.usecase;

import com.example.application.api.join.dto.JoinRequest;
import com.example.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinUsecase {

    private final MemberService memberService;

    @Autowired
    public JoinUsecase(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입
     */
    public void join(JoinRequest joinRequest) {
        memberService.join(joinRequest.of());
    }

    /**
     * 회원 정보 수정
     */
    public void modify(JoinRequest joinRequest) {
        memberService.modify(joinRequest.of());
    }

    /**
     * 회원 탈퇴
     */
    public void withdraw(JoinRequest joinRequest) {
        memberService.withdraw(joinRequest.of());
    }
}
