package com.dam.wonder.view.window;

/**
 * 主游戏窗口
 */
public interface GameWindow {
    /**
     * 初始化
     */
    void init();

    /**
     * 更新
     */
    void update();

    /**
     * 渲染
     */
    void render();

    /**
     * 启动
     */
    void start();

    /**
     * 循环
     */
    void loop();
}
