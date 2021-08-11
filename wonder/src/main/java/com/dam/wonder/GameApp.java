package com.dam.wonder;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.dam.wonder.component.MoveComponent;
import com.dam.wonder.constant.EntityType;
import com.dam.wonder.factory.CustomerEntityFactory;
import com.dam.wonder.factory.GameEntityFactory;
import com.dam.wonder.ui.TalkScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class GameApp extends GameApplication {
    /**
     * Can be overridden to provide global variables.
     *
     * @param vars map containing CVars (global variables)
     *
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score",0);
    }

    /**
     * Called every frame _only_ in Play state.
     *
     * @param tpf time per frame
     */
    @Override
    protected void onUpdate(double tpf) {
        FXGL.inc("score",1);
    }

    /**
     * Initialize input, i.e. bind key presses, bind mouse buttons.
     * <pre>
     * Example:
     *
     * Input input = getInput();
     * input.addAction(new UserAction("Move Left") {
     *      protected void onAction() {
     *          playerControl.moveLeft();
     *      }
     * }, KeyCode.A);
     * </pre>
     */
    @Override
    protected void initInput() {
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
        }, KeyCode.P);
    }

    /**
     * Initialize UI objects.
     */
    @Override
    protected void initUI() {
        Image image = new Image("assets/ui/buttons/ui1.png");
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setFill(new ImagePattern(image));
        rectangle.setOnMouseClicked(e -> FXGL.getInput().mockKeyPress(KeyCode.P));
        FXGL.addUINode(rectangle,900,20);
    }

    /**
     * Initialize game objects.
     */
    @Override
    protected void initGame() {
        setLevel();
        FXGL.getGameWorld().addEntityFactory(new GameEntityFactory());
        Entity entity = CustomerEntityFactory.createEntity(EntityType.PLANE);
        //绑定视角 固定视角
        FXGL.getGameWorld().addEntity(entity);
                Viewport viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(-10000,-10000,250 *70,10000);
        viewport.bindToEntity(entity, FXGL.getAppWidth() / 2, FXGL.getAppHeight() / 2);


    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("demo");
        settings.setHeight(720);
        settings.setWidth(1080);
        settings.setDeveloperMenuEnabled(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setLevel() {
        //首先移除全部的实体
      FXGL.getGameWorld().getEntitiesCopy().forEach(t -> t.removeFromWorld());
      //加载地图
      FXGL.setLevelFromMap("tmx/new_home.tmx");
    }
}
