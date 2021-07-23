package com.dam.wonder.view.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.dam.wonder.model.config.running.EntityData;
import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.model.util.ApplicationContextUtil;
import com.dam.wonder.view.game.component.MoveComponent;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BasicGameSample extends GameApplication {
   private static final GameRunningData gameRunningData;
   private static final EntityData entityData;

   static  {
        gameRunningData = ApplicationContextUtil.getBean(GameRunningData.class);
        entityData = ApplicationContextUtil.getBean(EntityData.class);
    }

    @Override
    public void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(gameRunningData.getWindowWidth().intValue());
        gameSettings.setHeight(gameRunningData.getWindowHeight().intValue());
        gameSettings.setTitle(gameRunningData.getGameWindowName());
        gameSettings.setVersion("demo01");

    }

    @Override
    public void onPreInit() {
        entityData.init();
    }

    @Override
    public void initInput() {
        FXGL.onKey(KeyCode.W,"move up", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).up(mainHumans.getSpeed());
        });
        FXGL.onKey(KeyCode.S,"move down", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).down(mainHumans.getSpeed());
        });
        FXGL.onKey(KeyCode.A,"move left", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).left(mainHumans.getSpeed());
        });
        FXGL.onKey(KeyCode.D,"move right", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).right(mainHumans.getSpeed());
        });
    }

    @Override
    public void initGame() {
        FXGL.getGameWorld().addEntity(entityData.getMainHumans().getEntity());
    }

    public void run (String[] args) {
         launch(args);
    }

}
