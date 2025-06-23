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

//    @Override
//    public void save(Nr12PdfKnowledge nr12PdfKnowledge) {
//
//        jdbcClient.sql("""
//        INSERT INTO documents (content, embedding)
//        VALUES (:content, :embedding)
//    """)
//                .param("content", documentDto.text())
//                .param("embedding", promptEmbedding.toString())
//                .update();
//    }

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

//    @Override
//    public List<Nr12PdfKnowledge> searchSimilar(List<Double> promptEmbedding) {
//        // Converte a lista de Double para o formato que o pgvector entende
//        PGvector promptVector = new PGvector(
//          promptEmbedding.stream().map(Double::floatValue).collect(
//            () -> new float[promptEmbedding.size()],
//            (a, v) -> a[0] = v, // Simplificação para demonstração, ajuste conforme necessário
//            (a1, a2) -> {}
//          )
//        );
//
//        // SQL CORRIGIDO E MAIS SEGURO
//        String sql = """
//            SELECT
//                id, document_source, document_version, section_id, section_title, content, page_number,
//                1 - (embedding <=> :prompt_vector) as similarity
//            FROM
//                nr12_knowledge_base
//            WHERE
//                1 - (embedding <=> :prompt_vector) > :match_threshold
//            ORDER BY
//                similarity DESC
//            LIMIT :match_cnt
//        """;
//
//        return jdbcClient.sql(sql)
//          .param("prompt_vector", promptVector)
//          .param("match_threshold", MATCH_THRESHOLD)
//          .param("match_cnt", MATCH_CNT)
//          .query(Nr12PdfKnowledge.class) // Mapeia o resultado para a sua entidade
//          .list();
//    }
}
