package com.dam.wonder.view.engine;

import com.dam.wonder.view.window.GameWindow;
import com.dam.wonder.view.window.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameEngine{
    private final GameWindow window;
    private final Timer timer;


    public GameEngine(GameWindow window, Timer timer) {
        this.window = window;
        this.timer = timer;
    }



}
