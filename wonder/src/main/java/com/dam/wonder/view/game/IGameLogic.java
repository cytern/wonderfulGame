package com.dam.wonder.view.game;


import com.dam.wonder.view.window.Window;

public interface IGameLogic {

    void init() throws Exception;
    
    void input(Window window);

    void update(float interval);
    
    void render(Window window);
    
    void cleanup();
}