package com.dam.wonder.model.util;

import com.alibaba.fastjson.JSON;
import com.dam.wonder.model.pojo.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AssetsLoader {
    /**
     * 加载资源工具类
     * @return
     */
    public static List loadItemAssets(String fileDir, Class<? extends Item> itemClass) throws FileNotFoundException {
        log.info("正在加载游戏参数  对话列表");
        List itemList = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:" + fileDir);
        if (!file.exists()) {
            log.error("加载游戏参数  失败 缺少必备的配置文件  对话列表");
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = stringBuilder.toString();
        List<? extends Item> items = JSON.parseArray(jsonString,itemClass);
        itemList.addAll(items);
        return itemList;
    }
}
