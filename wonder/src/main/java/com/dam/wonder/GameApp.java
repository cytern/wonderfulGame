package com.dam.wonder;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.dam.wonder.constant.EntityType;
import com.dam.wonder.factory.CustomerEntityFactory;

public class GameApp extends GameApplication {

    /**
     * Initialize game objects.
     */
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntity(CustomerEntityFactory.createEntity(EntityType.PLANE));
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("demo");
        settings.setHeight(720);
        settings.setWidth(1080);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
