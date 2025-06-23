package com.imd.longinus.controller;

import com.imd.longinus.domain.dto.Nr12PdfKnowledgeSnippetDto;
import com.imd.longinus.domain.model.Nr12PdfKnowledge;
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
    public ResponseEntity<Void> save(@RequestBody Nr12PdfKnowledgeSnippetDto nr12PdfKnowledgeSnippetDto) {
        Nr12PdfKnowledge nr12PdfKnowledge = new Nr12PdfKnowledge();
        nr12PdfKnowledge.setContent(nr12PdfKnowledgeSnippetDto.content());
        nr12PdfKnowledge.setSectionTitle(nr12PdfKnowledgeSnippetDto.sectionTitle());
        nr12PdfKnowledge.setSectionNumber(nr12PdfKnowledgeSnippetDto.sectionNumber());
        nr12PdfKnowledge.setPageNumber(nr12PdfKnowledgeSnippetDto.pageNumber());
        embeddingService.save(nr12PdfKnowledge);
        return ResponseEntity.ok().build();
    }
}
