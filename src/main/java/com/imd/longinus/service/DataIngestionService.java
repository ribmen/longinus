package com.imd.longinus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataIngestionService {

  private final VectorStore vectorStore;
  private final EmbeddingClient embeddingClient; // Necessário para criar o embedding antes de salvar

  // Injetamos o VectorStore, nosso "repositório"
  public DataIngestionService(VectorStore vectorStore, EmbeddingClient embeddingClient) {
    this.vectorStore = vectorStore;
    this.embeddingClient = embeddingClient;
  }

  public void ingestText(String text) {
    var springAiDocument = new org.springframework.ai.document.Document(text);

  }
}
