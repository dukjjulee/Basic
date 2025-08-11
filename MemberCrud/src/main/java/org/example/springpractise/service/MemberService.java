package org.example.springpractise.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractise.dto.MemberResponseDto;
import org.example.springpractise.dto.MemberRequestDto;
import org.example.springpractise.entity.Member;
import org.example.springpractise.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;




    @Transactional
    public MemberResponseDto save(MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto.getName());
        Member saveMember = memberRepository.save(member);
        return new MemberResponseDto(
                saveMember.getId(),
                saveMember.getName()
        );
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMembers() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(new MemberResponseDto(
                    member.getId(),
                    member.getName()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("안돼요")
        );
        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    @Transactional
    public MemberResponseDto updateMember(Long memberId, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("안돼요")
        );
        member.updateName(memberRequestDto.getName());
        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("안돼요")
        );
        memberRepository.deleteById(memberId);
    }
}
