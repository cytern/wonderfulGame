package com.dam.wonder.view.game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.view.game.entity.DemoFactory;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BasicGameSample extends GameApplication {
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
        Input input = FXGL.getInput();
        input.addAction(new UserAction("wuwuwuwuw") {
            @Override
            protected void onAction() {
                log.info("摁下了");
            }

            @Override
            protected void onActionBegin() {
                log.info("摁下");
            }

            @Override
            protected void onActionEnd() {
                log.info("摁完");
                FXGL.spawn("explosion");
            }
        }, KeyCode.C);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new DemoFactory());
    }

    public void run (String[] args) {
         launch(args);
    }

    private static class RotatingComponent extends Component {
        @Override
        public void onUpdate(double tpf) {
            entity.rotateBy(tpf* 45);
        }
    }
}
