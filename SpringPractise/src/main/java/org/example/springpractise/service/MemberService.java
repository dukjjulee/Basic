package org.example.springpractise.service;

import org.example.springpractise.dto.MemberRequestDto;
import org.example.springpractise.dto.MemberResponseDto;
import org.example.springpractise.entity.Member;
import org.example.springpractise.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto memberRequestDto){
        Member savedMember = memberRepository.save(new Member(memberRequestDto.getName()));
        return new MemberResponseDto(savedMember.getId(), savedMember.getName());
    }
    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMember() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> dtos = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(
                    member.getId(),
                    member.getName()
            );
            dtos.add(memberResponseDto);
        }
        return dtos;
    }
    @Transactional
    public MemberResponseDto update(Long memberId, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memberID가 없습니다")
        );
        member.updateName(memberRequestDto.getName());
        return new MemberResponseDto(
                member.getId(),
                member.getName()
        );
    }

    @Transactional
    public void deleteMember(Long memberId) {
        boolean b = memberRepository.existsById(memberId);
        if(!b) {
            throw new IllegalArgumentException("해당하는 memberID가 없습니다");
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memberID사 없습니다")
        );

        return new MemberResponseDto(member.getId(), member.getName());
    }
}
