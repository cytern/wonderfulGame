package com.dam.wonder.view.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.dam.wonder.model.config.running.EntityData;
import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.model.pojo.item.Human;
import com.dam.wonder.model.util.ApplicationContextUtil;
import com.dam.wonder.view.game.component.MoveComponent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
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
            mainHumans.getEntity().getComponent(MoveComponent.class).up();
        });
        FXGL.onKey(KeyCode.S,"move down", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).down();
        });
        FXGL.onKey(KeyCode.A,"move left", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).left();
        });
        FXGL.onKey(KeyCode.D,"move right", () -> {
            Human mainHumans = entityData.getMainHumans();
            mainHumans.getEntity().getComponent(MoveComponent.class).right();
        });
        FXGL.getInput().addAction(new UserAction("new userAction") {
            @Override
            protected void onAction() {
                super.onAction();
            }

            @Override
            protected void onActionBegin() {
                VBox content = new VBox(
                        FXGL.getUIFactoryService().newText("1231"),
                        FXGL.getUIFactoryService().newText("21321"),
                        FXGL.getAssetLoader().loadTexture("tree.png"),
                        FXGL.getUIFactoryService().newText("Line 3"),
                        FXGL.getUIFactoryService().newText("Line 4")
                );
                Button button = FXGL.getUIFactoryService().newButton("点击我关闭");
                button.setPrefWidth(300);
                FXGL.getDialogService().showBox("这个是个确定框",content,button);
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
            }
        },KeyCode.C);
    }

    @Override
    public void initGame() {
        FXGL.getGameWorld().addEntity(entityData.getMainHumans().getEntity());
    }

    public void run (String[] args) {
         launch(args);
    }


}
