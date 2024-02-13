package com.example.application.api.member.usecase;

import com.example.application.api.member.dto.MemberResponse;
import com.example.domain.member.dto.MemberResult;
import com.example.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberUsecase {

    private final MemberService memberService;

    @Autowired
    public MemberUsecase(MemberService memberService) {
        this.memberService = memberService;
    }

    public List<MemberResponse> getMembers() {
        List<MemberResult> members = memberService.getMembers();
        return members.stream().map(MemberResponse::of).toList();
    }

    public MemberResponse getMember(Long id) {
        return MemberResponse.of(memberService.getMemberById(id));
    }
}
