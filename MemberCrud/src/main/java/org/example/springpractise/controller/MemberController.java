package org.example.springpractise.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractise.dto.MemberResponseDto;
import org.example.springpractise.dto.MemberRequestDto;
import org.example.springpractise.repository.MemberRepository;
import org.example.springpractise.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public MemberResponseDto createMember(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> getMembers() {
        return memberService.findMembers();
    }

    @GetMapping("/members/{memberId")
    public MemberResponseDto getMember(
            @PathVariable Long memberId
    ) {
        return memberService.findMember(memberId);
    }

    @PutMapping("/members/memberId")
    public MemberResponseDto updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.updateMember(memberId,memberRequestDto);
    }

    @DeleteMapping("/members/memberId")
    public void deleteMember(
            @PathVariable Long memberId
    ){
        MemberService.deleteMember(memberId);
    }

}
