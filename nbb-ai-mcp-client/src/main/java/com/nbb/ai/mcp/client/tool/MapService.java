package com.nbb.ai.mcp.client.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class MapService {


    @Tool(description = "获取所有支持查询地图的城市列表")
    public String getMapCityList() {
        return "南京，镇江，常州";
    }
}
