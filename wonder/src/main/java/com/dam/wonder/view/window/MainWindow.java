package com.dam.wonder.view.window;

import com.dam.wonder.model.config.running.GameRunningData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainWindow implements GameWindow {

    private final GameRunningData gameRunningData;

    public MainWindow(GameRunningData gameRunningData) {
        this.gameRunningData = gameRunningData;
    }

    @Override
    public void init() {
       log.info("游戏初始化 开始");
       log.info("游戏初始化 结束");
    }

    /**
     * 更新
     */
    @Override
    public void update() {

    }

    /**
     * 渲染
     */
    @Override
    public void render() {

    }

    /**
     * 启动
     */
    @Async("taskPool")
    @Override
    public void start() {
       log.info("游戏线程启动 开始");
       init();
       while (gameRunningData.isGameRunning()) {
           update();
           render();
       }
    }

}
