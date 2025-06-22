package com.imd.longinus.repository;

import com.imd.longinus.domain.dto.DocumentDto;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmbeddingRepositoryImpl implements EmbeddingRepository {

    private static final float MATCH_THRESHOLD = 0.7f;
    private static final int MATCH_CNT = 3;

    private JdbcClient jdbcClient;

    public EmbeddingRepositoryImpl(JdbcClient jdbcClient, EmbeddingClient embeddingClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void save(DocumentDto documentDto, List<Double> promptEmbedding) {

        jdbcClient.sql("""
        INSERT INTO documents (content, embedding) 
        VALUES (:content, :embedding)
    """)
                .param("content", documentDto.text())
                .param("embedding", promptEmbedding.toString())
                .update();
    }

    public List<DocumentDto> searchDocument(String prompt, List<Double> promptEmbedding) {
        JdbcClient.StatementSpec query = jdbcClient.sql(
                        "SELECT name, description, price, 1 - (description_embedding <=> :user_promt::vector) as similarity " +
                                "FROM airbnb_listing WHERE 1 - (description_embedding <=> :user_promt::vector) > :match_threshold "
                                +
                                "ORDER BY description_embedding <=> :user_promt::vector LIMIT :match_cnt")
                .param("user_promt", promptEmbedding.toString())
                .param("match_threshold", MATCH_THRESHOLD)
                .param("match_cnt", MATCH_CNT);

        return query.query(DocumentDto.class).list();
    }
}
