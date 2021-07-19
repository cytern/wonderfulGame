package com.dam.wonder.model.config.running;

import com.alibaba.fastjson.JSON;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.model.pojo.item.Item;
import com.dam.wonder.model.pojo.item.Talk;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@Data
@Scope("property")
public class GameRunningData {
    /**
     * 游戏是否启动
     */
    private boolean gameRunning;
    /**
     * 窗口高度
     */
    private Integer windowHeight;
    /**
     * 窗口宽度
     */
    private Integer windowWidth;
    /**
     * 游戏名
     */
    private String gameWindowName;
    /**
     * 是否开启垂直同步
     */
    private boolean vSync;
    /**
     * 是否变动过大小
     */
    private boolean resized;
    /**
     * 值
     */
    private int direction;
    /**
     * 颜色
     */
    private float color;

    /**
     * 实体列表
     */
    private List<Item> itemList;
    /**
     * 构造时初始化
     */
    public GameRunningData() {
        log.info("初始化游戏参数 开始");
        this.gameRunning = true;
        this.windowHeight = Constant.GameWindow.HEIGHT;
        this.windowWidth = Constant.GameWindow.WIDTH;
        this.gameWindowName = Constant.GameWindow.GAME_NAME;
        this.vSync = true;
        this.resized = false;
        try {
          loadAssetsList(Constant.assetsStatusDir.DIR_TALK,Talk.class);
          loadAssetsList(Constant.assetsStatusDir.DIR_TALK, Human.class);

        } catch (FileNotFoundException e) {
            log.error("加载配置文件异常 ",e);
        }
        log.info("初始化游戏参数 结束");
    }

    /**
     * 加载对话列表
     */
    private void loadAssetsList (String fileDir,Class<? extends Item> itemClass) throws FileNotFoundException {
        log.info("正在加载游戏参数  对话列表");
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
        if(this.itemList == null) {
            this.itemList = new ArrayList<>();
            this.itemList.addAll(items);
        }else {
            this.itemList.addAll(items);
        }

    }

    /**
     * 获取全部的任务
     */
    public List<Human> getAllHumans() {
        List<Item> itemList = this.itemList;
        ArrayList<Human> objects = new ArrayList<>();
        itemList.forEach(t -> {
            if (t.getType().equals(Constant.ItemCode.ITEM_HUMAN)) {
                Human tempHum =  (Human) t;
                objects.add(tempHum);
            }
        });
        return objects;
    }

    /**
     * 获取主角色
     */
    public Human getMainHumans() {
        List<Item> items = this.itemList;
         Human human = new Human();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(Constant.idCode.CODE_MAIN_HUMAN)){
                human = (Human) items.get(i);
            }
        }
        return human;
    }
}
