package com.imd.longinus.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequestDto(
  @NotBlank(message = "A pergunta não pode estar em branco.")
  String question
){}
