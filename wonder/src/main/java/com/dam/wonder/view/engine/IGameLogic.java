package com.dam.wonder.view.engine;

import com.dam.wonder.view.window.Window;

/**
 * 游戏引擎接口
 */
public interface IGameLogic {
    void init() throws Exception;

    void input();

    void update(float interval);

    void render();
}
