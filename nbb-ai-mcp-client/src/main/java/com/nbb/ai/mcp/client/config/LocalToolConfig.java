package com.nbb.ai.mcp.client.config;

import com.nbb.ai.mcp.client.tool.MapService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalToolConfig {

    /**
     * 本地定义的工具
     */
    @Bean
    public ToolCallbackProvider toolCallbackProvider(MapService mapService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(mapService)
                .build();
    }

}
