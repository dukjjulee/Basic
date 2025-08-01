package org.example.springpractise.controllar;

import org.example.springpractise.dto.MemberRequestDto;
import org.example.springpractise.dto.MemberResponseDto;
import org.example.springpractise.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto createMember(
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/members/{memberId}")
    public MemberResponseDto getMember(
            @PathVariable Long memberId
    ) {
       return memberService.findMember(memberId);
    }

    @PutMapping("/members/{memberId")
    public MemberResponseDto updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto memberRequestDto
    ) {
        return memberService.update(memberId, memberRequestDto);
    }

    @DeleteMapping("/members/{memberId")
    public void deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMember(memberId);
    }
}
