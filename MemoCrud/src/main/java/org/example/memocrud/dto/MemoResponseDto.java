package org.example.memocrud.dto;

import lombok.Getter;

@Getter
public class MemoResponseDto {

    private final Long id;
    private final String name;

    public MemoResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
