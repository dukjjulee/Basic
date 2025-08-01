package org.example.memocrud.controller;

import org.example.memocrud.dto.MemoRequestDto;
import org.example.memocrud.dto.MemoResponseDto;
import org.example.memocrud.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memo")
    public MemoResponseDto createMemo(
            @RequestBody MemoRequestDto memoRequestDto
    ) {
        return memoService.save(memoRequestDto);
    }

    @GetMapping("/memo/{memoId}")
    public MemoResponseDto getMemo(
            @PathVariable Long memoId
    ) {
        return memoService.findMemo(memoId);
    }

    @PutMapping("/memo/{memoId}")
    public MemoResponseDto updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto memoRequestDto
    ) {
        return memoService.update(memoId, memoRequestDto);
    }

    @DeleteMapping("/memo/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ) {
        memoService.deleteMemo(memoId);
    }
}
