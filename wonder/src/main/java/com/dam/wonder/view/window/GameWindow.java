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

    /**
     * 按键
     */
    boolean isKeyPressed (int keyCode);

    /**
     * 关闭事件
     */
    boolean windowShouldClose();

    /**
     * 设置颜色
     */
    void setClearColor(float r,float g,float b,float alpha);

    /**
     * 窗口变化事件
     */
    void resizedWindow ();

    /**
     * 输入反馈事件
     */
    void input ();

    /**
     * 解除绑定事件
     */
    void cleanUp();
}
