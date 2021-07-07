package com.dam.wonder.model.config.running;

import com.alibaba.fastjson.JSON;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.pojo.item.Talk;
import com.dam.wonder.model.pojo.itemList.TalkList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Configuration
@Slf4j
@Data
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
     * 对话列表
     */
    private TalkList talkList;
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
            this.talkList = loadTalkList();
        } catch (FileNotFoundException e) {
            log.error("加载配置文件异常 ",e);
        }
        log.info("初始化游戏参数 结束");
    }


    private TalkList loadTalkList () throws FileNotFoundException {
        log.info("正在加载游戏参数  对话列表");
        File file = ResourceUtils.getFile("classpath:static/talk/BirthdayStory.json");
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
        List<Talk> talks = JSON.parseArray(jsonString, Talk.class);
        TalkList talkList = new TalkList();
        talkList.setType(Constant.ItemCode.ITEM_TALK);
        talkList.setItemList(talks);
        return talkList;
    }

}
