package dev.rohitahuja.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RagService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    @Value("classpath:/prompts/trade-surveillance.st")
    private Resource sbPromptTemplate;
    private static final Logger _log = LoggerFactory.getLogger(RagService.class);

    public RagService(ChatClient.Builder builder,
                      VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build())
                .build();
        this.vectorStore = vectorStore;
    }

    public Flux<String> chat(String message) {
        PromptTemplate promptTemplate = new PromptTemplate(sbPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));


        return chatClient.prompt(promptTemplate.create(promptParameters))
                .stream()
                .content();
    }

    private List<String> findSimilarDocuments(String message){
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.builder()
                .query(message)
                .topK(3).build());
        return similarDocuments.stream().map(Document::getFormattedContent).toList();

    }
}