package com.imd.longinus.service;

import java.util.List;

import com.imd.longinus.domain.dto.DocumentDto;
import com.imd.longinus.repository.EmbeddingRepository;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.stereotype.Service;


@Service
public class EmbeddingService{
    private EmbeddingRepository embeddingRepository;
    private EmbeddingClient embeddingClient;

    public EmbeddingService(EmbeddingRepository embeddingRepository, EmbeddingClient embeddingClient) {
        this.embeddingRepository = embeddingRepository;
        this.embeddingClient = embeddingClient;
    }

    private List<Double> getTextEmbedding(String text)  {
        return embeddingClient.embed(text);
    }

    public void save(DocumentDto documentDto){
        List<Double> documentEmbedding = getTextEmbedding(documentDto.text());
        embeddingRepository.save(documentDto, documentEmbedding);
    }

    public List<DocumentDto> getByVectorSearch(String prompt){
        List<Double> promptEmbedding = getTextEmbedding(prompt);
        return embeddingRepository.searchDocument(prompt, promptEmbedding);

    }
}