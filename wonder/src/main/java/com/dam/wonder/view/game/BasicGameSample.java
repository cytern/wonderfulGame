package com.dam.wonder.view.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.view.game.component.MoveComponent;
import com.dam.wonder.view.game.entity.DemoFactory;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BasicGameSample extends GameApplication {
    private final GameRunningData gameRunningData;

    public BasicGameSample(GameRunningData gameRunningData) {
        this.gameRunningData = gameRunningData;
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(Constant.GameWindow.WIDTH);
        gameSettings.setHeight(Constant.GameWindow.HEIGHT);
        gameSettings.setTitle(Constant.GameWindow.GAME_NAME);
        gameSettings.setVersion("demo01");
        gameSettings.setMainMenuEnabled(false);
    }

    @Override
    protected void onPreInit() {
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.W,"move up", () -> {
            Human mainHumans = gameRunningData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).up();
        });
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new DemoFactory());
    }

    public void run (String[] args) {
         launch(args);
    }

}
