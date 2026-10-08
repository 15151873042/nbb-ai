package com.nbb.ai.mcp.client.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController {

    @Resource
    private ChatClient chatClient;

    /**
     * 调用 MCP 工具的示例接口
     */
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "上海当前天气怎么样") String message) {
        try {
            return chatClient.prompt(message).call().content();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "Error: " + e.getMessage();
        }
    }
}
