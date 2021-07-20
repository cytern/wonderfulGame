package com.dam.wonder.view.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.config.running.EntityData;
import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.view.game.component.MoveComponent;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BasicGameSample extends GameApplication {
    private final GameRunningData gameRunningData;
    private final EntityData entityData;

    public BasicGameSample(GameRunningData gameRunningData, EntityData entityData) {
        this.gameRunningData = gameRunningData;
        this.entityData = entityData;
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
        entityData.init();
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.W,"move up", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).up();
        });
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntity(entityData.getMainHumans().getEntity());
    }

    public void run (String[] args) {
         launch(args);
    }

}
