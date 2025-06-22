package com.imd.longinus.repository;

import com.imd.longinus.domain.dto.DocumentDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbeddingRepository {
    public void save(DocumentDto documentDto, List<Double> promptEmbedding);
    public List<DocumentDto> searchDocument(String prompt, List<Double> promptEmbedding);
}
