package com.imd.longinus.domain.dto;

public record ChatResponseDto(String answer) {

    public ChatResponseDto {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Não foi possível obter resposta");
        }
    }
}
