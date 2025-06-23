package com.imd.longinus.service;

import com.imd.longinus.domain.model.Nr12PdfKnowledge;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptService {

    private EmbeddingService embeddingService;
    private ChatClient aiClient;

    public PromptService(EmbeddingService embeddingService, ChatClient aiClient) {
        this.embeddingService = embeddingService;
        this.aiClient = aiClient;
    }

    public String prompt(String message) {
        List<Nr12PdfKnowledge> relevantDocuments = embeddingService.getByVectorSearch(message);

        String ragPrompt = ""; //todo: montar o prompt direito

        Prompt chatPrompt = new Prompt(List.of(new UserMessage(ragPrompt))); //todo: montar o prompt direito

        return aiClient.call(chatPrompt).getResult().getOutput().toString();
    }

}
