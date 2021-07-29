package com.dam.wonder;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.dam.wonder.component.MoveComponent;
import com.dam.wonder.constant.EntityType;
import com.dam.wonder.factory.CustomerEntityFactory;
import javafx.scene.input.KeyCode;

import java.util.List;

public class GameApp extends GameApplication {

    /**
     * Initialize game objects.
     */
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntity(CustomerEntityFactory.createEntity(EntityType.PLANE));
        FXGL.getInput().addAction(new UserAction("up") {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).up();

            }

            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stop();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("down") {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).down();

            }

            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stop();
            }
        }, KeyCode.S);

        FXGL.getInput().addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).left();

            }

            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stop();
            }
        }, KeyCode.A);


        FXGL.getInput().addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).right();

            }

            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stop();
            }
        }, KeyCode.D);
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
