package com.nbb.ai.mcp.client.controller;

import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class ChatController {

    @Resource
    private ChatClient chatClient;

    /**
     * 调用 MCP 工具的示例接口
     */
    @GetMapping("/string")
    public String chat(@RequestParam(value = "message", defaultValue = "上海当前天气怎么样") String message) {
        try {
            // 以默认请求对象模板创建一个新的请求对象
            ChatClient.ChatClientRequestSpec request = chatClient.prompt(message);

            // 调用 call() 方法实际上不会触发 AI 模型执行。相反，它仅指示 Spring AI 使用同步调用还是流式调用
            ChatClient.CallResponseSpec response = request.call();

            // 实际的 AI 模型调用发生在调用 content() 、 chatResponse() 和 responseEntity() 等方法时，内部的逻辑就是执行advisor链
            // 有两个关键的advisor，一个是ChatModelCallAdvisor，进行大模型调用，一个是ToolCallingAdvisor进行工具调用
            String content = response.content();

            return content;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/chatResponse")
    public ChatResponse chatResponse(@RequestParam(value = "message", defaultValue = "上海当前天气怎么样") String message) {
        return chatClient.prompt(message).call().chatResponse();
    }

    @GetMapping("/entity")
    public Result entity(@RequestParam(value = "message", defaultValue = "上海当前天气怎么样") String message) {
        return chatClient.prompt(message).call().entity(Result.class);
    }

    @Data
    public static class Result {
        private String result;
    }

    // prompt 流式返回
    @GetMapping(value ="/stream")
    // sse 流式返回
//    @GetMapping(value ="/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam(value = "message", defaultValue = "上海当前天气怎么样") String message) {
        return chatClient.prompt(message).stream().content();
    }
}
