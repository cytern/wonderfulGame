package com.dam.wonder;

import com.dam.wonder.model.config.thread.ThreadPoolConfig;
import com.dam.wonder.view.engine.GameEngine;
import com.dam.wonder.view.game.DummyGame;
import com.dam.wonder.view.game.IGameLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties({ThreadPoolConfig.class} )
public class WonderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WonderApplication.class, args);
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEngine = new GameEngine("奇妙物语",1080,720,vSync,gameLogic);
            gameEngine.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
