package com.dam.wonder.model.handler;

import com.dam.wonder.view.game.BasicGameSample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringApplicationStarterHandler implements ApplicationListener<ApplicationReadyEvent> {



    public SpringApplicationStarterHandler() {

    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("应用程序启动唤醒程序 开始唤起游戏");
        BasicGameSample basicGameSample = new BasicGameSample();
        basicGameSample.run(new String[0]);
    }
}
