package com.nbb.ai.mcp.server.mcptool;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherService {

    private final Map<String, String> weatherData = new ConcurrentHashMap<>();

    public WeatherService() {
        weatherData.put("北京", "晴天，温度 25°C");
        weatherData.put("上海", "高温，300°C");
        weatherData.put("广州", "小雨，温度 30°C");
        weatherData.put("深圳", "晴天，温度 31°C");
    }

    @McpTool(description = "根据城市名称获取当前的天气信息，城市名称支持中英文")
    public String getWeather(@McpToolParam(description = "城市名称，例如：beijing 或 上海") String city) {
        return weatherData.getOrDefault(city.toLowerCase(), "暂无该城市的天气信息：" + city);
    }

    @McpTool(description = "获取所有支持查询天气的城市列表")
    public String listSupportedCities() {
        return String.join(", ", weatherData.keySet());
    }
}