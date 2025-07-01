package dev.rohitahuja.tools;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ToolsService {

    private final ChatClient chatClient;

    public ToolsService(ChatClient.Builder builder,
                        WeatherConfigProperties weatherConfigProperties,
                        EmailConfigProperties emailConfigProperties) {
        this.chatClient = builder
                .defaultSystem("You are a helpful AI Assistant that answers based on knowledge available but also utilizes the tools that have been made available.")
                .defaultTools(new WeatherTools(weatherConfigProperties), new EmailTools(emailConfigProperties), new DateTimeTools())
                .build();
    }

    public Flux<String> callTool(String message) {
        return chatClient
                .prompt()
                .user(message)
                .stream()
                .content();
    }
}
