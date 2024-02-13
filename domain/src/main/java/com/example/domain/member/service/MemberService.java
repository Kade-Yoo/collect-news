package com.example.domain.member.service;

import com.example.domain.common.ErrorCode;
import com.example.domain.common.InvalidInputException;
import com.example.domain.member.dto.JoinCommand;
import com.example.domain.member.dto.MemberResult;
import com.example.domain.member.entity.Member;
import com.example.domain.member.entity.MemberRole;
import com.example.domain.member.repository.MemberRepository;
import com.example.domain.member.repository.MemberRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository, MemberRoleRepository memberRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
    }


    @Transactional
    public void join(JoinCommand command) {
        existsEmail(command);
        if (command.getRole() == null) {
            throw new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE_ERROR);
        }

        Member member = Member.create(
                command.getUsername(),
                passwordEncoder.encode(command.getPassword())
        );

        memberRepository.save(member);

        MemberRole memberRole = MemberRole.create(member, command.getRole());
        memberRoleRepository.save(memberRole);
    }

    private void existsEmail(JoinCommand command) {
        Optional<Member> findByEmail = this.findByEmail(command.getUsername());
        if (findByEmail.isPresent()) {
            throw new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE_ERROR);
        }
    }

    public void modify(JoinCommand command) {
        Member member = this.findByEmail(command.getUsername()).orElseThrow(() ->
                new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE_ERROR)
        );
        member.update(command.getUsername(), passwordEncoder.encode(command.getPassword()));
    }

    public void withdraw(JoinCommand command) {
        memberRepository.deleteByUsername(command.getUsername());
    }

    public List<MemberResult> getMembers() {
        List<Member> findAll = this.findAll();
        return findAll.stream().map(MemberResult::new).toList();
    }

    private List<Member> findAll() {
        return memberRepository.findAll();
    }

    public MemberResult getMemberByEmail(String username) {
        return new MemberResult(this.findByEmail(username).orElseThrow(() -> new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE_ERROR)));
    }

    public MemberResult getMemberById(Long id)  {
        Member member = this.findById(id).orElseThrow(() -> new InvalidInputException(ErrorCode.INVALID_INPUT_VALUE_ERROR));
        return new MemberResult(member);
    }

    private Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    private Optional<Member> findByEmail(String username) {
        return memberRepository.findByUsername(username);
    }
}
