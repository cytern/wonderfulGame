package com.dam.wonder.model.config.running;

import com.dam.wonder.model.config.constant.Constant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

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
     * 构造时初始化
     */
    public GameRunningData() {
        log.info("初始化游戏参数 开始");
        this.gameRunning = true;
        this.windowHeight = Constant.GameWindow.HEIGHT;
        this.windowWidth = Constant.GameWindow.WIDTH;

        log.info("初始化游戏参数 结束");
    }
}
