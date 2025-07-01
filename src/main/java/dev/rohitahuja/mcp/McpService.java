package dev.rohitahuja.mcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class McpService {

    private final ChatClient chatClient;

    public McpService(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a helpful AI Assistant that answers based on knowledge available but also utilizes the tools that have been made available.
                        After receiving a tool's response, transform the raw data into a natural, conversational response. 
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build())
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    public Flux<String> mcp(String query) {
        return chatClient
                .prompt()
                .user(query)
                .stream()
                .content();
    }
}
