package com.imd.longinus.domain.repository;

import com.imd.longinus.domain.model.Nr12PdfKnowledge;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmbeddingRepositoryImpl implements CustomEmbeddingRepository {

    private static final float MATCH_THRESHOLD = 0.7f;
    private static final int MATCH_CNT = 3;

    private JdbcClient jdbcClient;

    public EmbeddingRepositoryImpl(JdbcClient jdbcClient, EmbeddingClient embeddingClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Nr12PdfKnowledge> searchSimilarEmbedding(List<Double> promptEmbedding) {
        JdbcClient.StatementSpec query = jdbcClient.sql(
            "SELECT sectionNumber, section_number, section_title, page_number 1 - (description_embedding <=> :user_promt::vector) as similarity " +
              "FROM public.embedding_data 1 - (embedding <=> :user_promt::vector) > :match_threshold "
              +
              "ORDER BY embedding <=> :user_promt::vector LIMIT :match_cnt")
          .param("user_promt", promptEmbedding.toString())
          .param("match_threshold", MATCH_THRESHOLD)
          .param("match_cnt", MATCH_CNT);

        return query.query(Nr12PdfKnowledge.class).list();
    }
}
