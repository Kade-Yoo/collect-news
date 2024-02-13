package com.example.application.api.join.controller;

import com.example.application.api.join.dto.JoinRequest;
import com.example.application.api.join.usecase.JoinUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {

    private final JoinUsecase joinUsecase;

    /**
     * 회원 가입
     *
     * @param request 가입 정보
     */
    @PostMapping
    public ResponseEntity<Void> join(@RequestBody JoinRequest request) {
        joinUsecase.join(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 정보 수정
     *
     * @param request 가입 정보
     */
    @PutMapping
    public ResponseEntity<Void> modify(@RequestBody JoinRequest request) {
        joinUsecase.modify(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 탈퇴
     *
     * @param request 가입 정보
     */
    @DeleteMapping
    public ResponseEntity<Void> withdraw(@RequestBody JoinRequest request) {
        joinUsecase.withdraw(request);
        return ResponseEntity.ok().build();
    }
}
