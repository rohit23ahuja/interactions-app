package dev.rohitahuja.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorSearchService {

    private final VectorStore vectorStore;

    public VectorSearchService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public String similaritySearch(String text) {
        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder()
                .query(text)
                .topK(2)
                .build());
        List<String> contentList = results.stream().map(Document::getFormattedContent).toList();
        return String.join("\n", contentList);
    }

}
