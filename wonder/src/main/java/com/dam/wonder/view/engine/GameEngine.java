package com.dam.wonder.view.engine;

import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.view.window.GameWindow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameEngine{
    private final GameWindow window;
    private final Timer timer;
    private final IGameLogic gameLogic;
    private final GameRunningData gameRunningData;

    public GameEngine(GameWindow window, Timer timer, IGameLogic gameLogic, GameRunningData gameRunningData) {
        this.window = window;
        this.timer = timer;
        this.gameLogic = gameLogic;
        this.gameRunningData = gameRunningData;
    }

    public void init() throws Exception{
        window.init();
        timer.init();
        gameLogic.init();
    }
    @Async("taskPool")
    public void run () {
        try {
            init();
            gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void gameLoop () {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / Constant.GameWindow.TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!gameRunningData.isVSync()) {
                sync();
            }
        }
    }

    private void sync() {
        float loopSlot = 1f / Constant.GameWindow.TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void input() {

        gameLogic.input();
    }

    protected void update(float interval) {
        gameLogic.update(interval);
    }

    protected void render() {
        gameLogic.render();
        window.update();
    }

}
