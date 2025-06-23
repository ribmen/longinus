package com.imd.longinus.domain.repository;

import com.imd.longinus.domain.model.Nr12PdfKnowledge;

import java.util.List;

public interface CustomEmbeddingRepository {
  List<Nr12PdfKnowledge> searchSimilarEmbedding(List<Double> promptEmbedding);
}
