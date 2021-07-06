package com.dam.wonder.model.handler;

import com.dam.wonder.view.engine.GameEngine;
import com.dam.wonder.view.window.GameWindow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringApplicationStarterHandler implements ApplicationListener<ApplicationReadyEvent> {
   private final GameWindow gameWindow;
   private final GameEngine gameEngine;

    public SpringApplicationStarterHandler(GameWindow gameWindow, GameEngine gameEngine) {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("应用程序启动唤醒程序 开始唤起游戏");
        try {
            gameWindow.start();
        } catch (Exception e) {
            log.error("游戏非正常关闭 关闭中");
            System.exit(-1);
        }
    }
}
