package org.example.memocrud.service;

import org.example.memocrud.dto.MemoRequestDto;
import org.example.memocrud.dto.MemoResponseDto;
import org.example.memocrud.entity.Memo;
import org.example.memocrud.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto memoRequestDto){
        Memo savedMemo = memoRepository.save(new Memo(memoRequestDto.getName()));
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getName());
    }
    @Transactional(readOnly = true)
    public List<MemoResponseDto> findMemo() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponseDto> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            MemoResponseDto memoResponseDto = new MemoResponseDto(
                    memo.getId(),
                    memo.getName()
            );
            dtos.add(memoResponseDto);
        }
        return dtos;
    }
    @Transactional
    public MemoResponseDto update(Long memberId, MemoRequestDto memoRequestDto) {
        Memo memo = memoRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memoID가 없습니다")
        );
        memo.updateName(memoRequestDto.getName());
        return new MemoResponseDto(
                memo.getId(),
                memo.getName()
        );
    }

    @Transactional
    public void deleteMemo(Long memoId) {
        boolean b = memoRepository.existsById(memoId);
        if(!b) {
            throw new IllegalArgumentException("해당하는 memoID가 없습니다");
        }
        memoRepository.deleteById(memoId);
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findMemo(Long memoId) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memoID가 없습니다")
        );

        return new MemoResponseDto(memo.getId(), memo.getName());
    }
}
