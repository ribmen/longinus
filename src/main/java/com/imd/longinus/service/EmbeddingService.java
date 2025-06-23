package com.imd.longinus.service;

import java.util.List;

import com.imd.longinus.domain.model.Nr12PdfKnowledge;
import com.imd.longinus.domain.repository.EmbeddingRepository;
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

    public void save(Nr12PdfKnowledge nr12PdfKnowledge){
        List<Double> documentEmbedding = getTextEmbedding(nr12PdfKnowledge.getContent());
        nr12PdfKnowledge.setEmbedding(documentEmbedding);
    }

    public List<Nr12PdfKnowledge> getByVectorSearch(String prompt){
        List<Double> promptEmbedding = getTextEmbedding(prompt);
        return embeddingRepository.searchSimilarEmbedding(promptEmbedding);

    }
}