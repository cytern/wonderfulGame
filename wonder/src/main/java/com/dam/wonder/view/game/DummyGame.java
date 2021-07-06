package com.dam.wonder.view.game;

import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.view.engine.IGameLogic;
import com.dam.wonder.view.render.Renderer;
import com.dam.wonder.view.window.GameWindow;
import com.dam.wonder.view.window.Window;
import org.springframework.stereotype.Service;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.opengl.GL11.glViewport;

@Service
public class DummyGame implements IGameLogic {
    private final Renderer renderer;
    private int direction = 0;
    private float color = 0.0f;
    private final GameWindow window;
    private final GameRunningData gameRunningData;

    public DummyGame(Renderer renderer, GameWindow window, GameRunningData gameRunningData) {
        this.renderer = renderer;
        this.window = window;
        this.gameRunningData = gameRunningData;
    }

    @Override
    public void init() throws Exception {
          renderer.init();
    }

    @Override
    public void input() {
             if (window.isKeyPressed(GLFW_KEY_UP)) {
                 direction = 1;
             }else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
                 direction = -1;
             }else {
                 direction = 0;
             }
    }

    @Override
    public void update(float interval) {
        color += direction * 0.01f;
        if (color > 1) {
            color = 1.0f;
        } else if ( color < 0 ) {
            color = 0.0f;
        }
    }

    @Override
    public void render() {
         window.resizedWindow();
         window.setClearColor(color,color,color,0.0f);
         renderer.clear();
    }
}
