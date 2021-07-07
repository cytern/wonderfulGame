package com.dam.wonder.view.rts;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.MouseButton;

import static com.almasb.fxgl.dsl.FXGL.*;


/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class RTSApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("FXGL RTS Game");
    }

    @Override
    protected void initInput() {
        onBtnDown(MouseButton.PRIMARY, () -> {
            var worker = byType(EntityType.WORKER).get(0);
            worker.getComponent(GathererComponent.class).sendTo(getInput().getMousePositionWorld());
        });
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new RTSFactory());

        spawn("tree", 100, 300);
        spawn("tree", 250, 300);
        spawn("tree", 400, 300);

        spawn("stone", 100, 500);
        spawn("stone", 250, 500);
        spawn("stone", 400, 500);

        spawn("stockpile", 600, 50);

        spawn("worker", 700, 400);

        byType(EntityType.TREE, EntityType.STONE).forEach(e -> {
            e.getViewComponent().addOnClickHandler(event -> {
                var worker = byType(EntityType.WORKER).get(0);
                worker.getComponent(GathererComponent.class).sendTo(e);
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
