package com.imd.longinus.domain.repository;

import com.imd.longinus.domain.model.Nr12PdfKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EmbeddingRepository extends JpaRepository<Nr12PdfKnowledge, UUID>, CustomEmbeddingRepository {
}
