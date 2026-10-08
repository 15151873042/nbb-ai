package com.nbb.ai.mcp.client.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatConfig {


    @Bean
    public ChatClient chatClient(
            ChatClient.Builder chatClientBuilder,
            List<ToolCallbackProvider> toolCallbackProviders) {
        // 远程mcp-server为SyncMcpToolCallbackProvider
        // 本地定义的工具为MethodToolCallbackProvider
        return chatClientBuilder
                .defaultTools(toolCallbackProviders)
                .build();
    }
}
