package com.imd.longinus.controller;

import com.imd.longinus.domain.dto.DocumentDto;
import com.imd.longinus.service.EmbeddingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/context")
public class ContextController {

    private EmbeddingService embeddingService;

    public ContextController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody DocumentDto documentDto) {
        embeddingService.save(documentDto);
        return ResponseEntity.ok().build();
    }
}
