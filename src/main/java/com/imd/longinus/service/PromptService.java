package com.imd.longinus.service;

import com.imd.longinus.domain.model.Nr12PdfKnowledge;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        String context = relevantDocuments.stream()
          .map(Nr12PdfKnowledge::toRagContext)
          .collect(Collectors.joining("\n\n"));


        PromptTemplate template = new PromptTemplate(
        """
        Você é o Longinus, um assistente de IA especialista na Norma Regulamentadora NR-12 (Segurança no Trabalho em Máquinas e Equipamentos). Sua principal missão é fornecer respostas precisas, confiáveis e úteis, agindo como um engenheiro de segurança sênior.
               \s
        Siga estas regras RIGOROSAMENTE:
               \s
        1.  *BASE EXCLUSIVA NO CONTEXTO:* Responda utilizando APENAS a informação contida nos trechos da NR-12 fornecidos no prompt do usuário. Não utilize nenhum conhecimento prévio ou externo.
        2.  *CITAÇÃO OBRIGATÓRIA:* Esta é a sua regra mais importante. Para CADA afirmação que fizer, você DEVE citar a fonte. Use o formato [fonte: NR-12, item X, pág. Y] ao final da frase ou do parágrafo. Os metadados necessários (section_id, page_number) estarão junto com cada trecho de contexto.
        3.  *SEJA DIRETO E SINTETIZE:* Forneça uma resposta direta e clara para a pergunta do usuário. Sintetize a informação dos vários trechos de contexto em uma resposta coesa. Não se limite a listar ou repetir os trechos fornecidos.
        4.  *TOM PROFISSIONAL:* Mantenha sempre um tom profissional, técnico e formal. Sua linguagem deve ser descritiva e precisa.
        5.  *QUANDO NÃO SOUBER, DIGA:* Se a informação necessária para responder à pergunta não estiver no contexto fornecido, declare explicitamente: "A informação solicitada não foi encontrada nos trechos fornecidos da NR-12." Não tente adivinhar.
        6.  *FORMATE PARA CLAREZA:* Se a pergunta pedir por procedimentos, listas ou comparações, formate sua resposta usando checklists (com marcadores) ou subtítulos para facilitar a leitura e o uso prático da informação.

        Contexto:
        {context}

        Pergunta:
        {question}
   \s""");

        Map<String, Object> variables = Map.of(
          "context", context,
          "question", message
        );

        Prompt chatPrompt = template.create(variables);

        return aiClient.call(chatPrompt).getResult().getOutput().toString();
    }

}
