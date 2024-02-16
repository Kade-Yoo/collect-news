package com.example.application.api.member.controller;

import com.example.application.api.member.dto.MemberResponse;
import com.example.application.api.member.usecase.MemberUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 회원 관련 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberUsecase memberUsecase;

    /**
     * 전체 회원 정보 조회
     *
     * @return 회원 정보
     */
    @GetMapping("/list")
    public ResponseEntity<List<MemberResponse>> getMembers() {
        return ResponseEntity.ok(memberUsecase.getMembers());
    }

    /**
     * 선택한 회원 정보 조회
     *
     * @param id 회원 id
     */
    @GetMapping
    @Async
    public void getMember(@RequestParam("id")Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication principal = " + authentication.getPrincipal());
        memberUsecase.getMember(id);
//        return ResponseEntity.ok().build();
    }
}
