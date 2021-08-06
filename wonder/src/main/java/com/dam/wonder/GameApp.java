package com.dam.wonder;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.dam.wonder.component.MoveComponent;
import com.dam.wonder.constant.EntityType;
import com.dam.wonder.factory.CustomerEntityFactory;
import com.dam.wonder.ui.TalkScene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class GameApp extends GameApplication {

    /**
     * Initialize UI objects.
     */
    @Override
    protected void initUI() {
//        Rectangle rectangle = new Rectangle(200, 200, Color.color(0, 0, 0, 0.85));
//        rectangle.setStroke(Color.BLUE);
//        rectangle.setStrokeWidth(1.75);
//        rectangle.setEffect(new DropShadow(28,Color.color(0,0,0,0.9)));
//        VBox.setVgrow(new HBox(), Priority.ALWAYS);
//        rectangle.setTranslateX(0);
//        rectangle.setTranslateY(0);
//        FXGL.addUINode(rectangle,20,20);
    }

    /**
     * Initialize game objects.
     */
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntity(CustomerEntityFactory.createEntity(EntityType.PLANE));
        FXGL.getInput().addAction(new UserAction("up") {
            @Override
            protected void onAction() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).up();
            }
            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stopY();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("down") {
            @Override
            protected void onAction() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).down();
            }
            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stopY();
            }
        }, KeyCode.S);

        FXGL.getInput().addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).left();
            }
            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stopX();
            }
        }, KeyCode.A);


        FXGL.getInput().addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                List<Entity> entitiesByType = FXGL.getGameWorld().getEntitiesByType(EntityType.PLANE);
                entitiesByType.get(0).getComponent(MoveComponent.class).stopX();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("show talk") {
            @Override
            protected void onActionBegin() {
                List<String> talkList = new ArrayList<>();
                talkList.add("生日快乐铁汁");
                talkList.add("我终于做完了");
                talkList.add("嘿嘿嘿");
                TalkScene instance = TalkScene.getInstance();
                instance.show(talkList);
            }
        },KeyCode.T);
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
