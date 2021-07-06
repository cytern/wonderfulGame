package com.dam.wonder.view.window;

import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.view.render.Renderer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

@Service
@Slf4j
@Data
public class Window implements GameWindow {

    /**
     * 游戏运行时数据类
     */
    private final GameRunningData gameRunningData;

    private final Renderer renderer;
    /**
     * 窗口编号
     */
    private long windowHandle;

    public Window(GameRunningData gameRunningData, Renderer renderer) {
        this.gameRunningData = gameRunningData;
        this.renderer = renderer;
    }

    @Override
    public void init() {
       log.info("游戏初始化 开始");
        if (!glfwInit()) {
            throw new IllegalStateException("游戏初始化 失败");
        }
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
        // Create the window
        windowHandle = glfwCreateWindow(gameRunningData.getWindowWidth(), gameRunningData.getWindowHeight(), gameRunningData.getGameWindowName(), NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("游戏初始化 失败 窗口创建失败 ");
        }
        // Setup resize callback
        glfwSetFramebufferSizeCallback(windowHandle, (window, width, height) -> {
            gameRunningData.setWindowWidth(width);
           gameRunningData.setWindowHeight(height);
            gameRunningData.setResized(true);
        });

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                windowHandle,
                (vidmode.width() - gameRunningData.getWindowWidth()) / 2,
                (vidmode.height() - gameRunningData.getWindowHeight()) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);
        // Enable v-sync  垂直同步启动
        if (gameRunningData.isVSync()) {
            glfwSwapInterval(1);
        }

        // Make the window visible
        glfwShowWindow(windowHandle);


        log.info("游戏初始化 结束");
    }

    /**
     * 更新
     */
    @Override
    public void update() {
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
        gameRunningData.setColor(gameRunningData.getColor() + gameRunningData.getDirection()* 0.01f);
        if (gameRunningData.getColor() >1) {
            gameRunningData.setColor(1.0f);
        }else if (gameRunningData.getColor() <0) {
            gameRunningData.setColor(0.0f);
        }
    }

    /**
     * 渲染
     */
    @Override
    public void render() {
         resizedWindow();
         setClearColor(gameRunningData.getColor(),gameRunningData.getColor(),gameRunningData.getColor(),0.0f);
         renderer.clear();
    }

    /**
     * 启动
     */
    @Async("taskPool")
    @Override
    public void start() {
       log.info("游戏线程启动 开始");
        try {
            init();
            loop();
            glfwFreeCallbacks(windowHandle);
            glfwDestroyWindow(windowHandle);
        } finally {
           glfwTerminate();
           glfwSetErrorCallback(null).free();
        }

    }

    /**
     * 循环
     */
    @Override
    public void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(windowHandle)) {

            glfwPollEvents();
            input();
            render();
            update();
        }
    }

    /**
     * 按键
     *
     * @param keyCode
     */
    @Override
    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

    /**
     * 关闭事件
     */
    @Override
    public boolean windowShouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    /**
     * 设置颜色
     */
    @Override
    public void setClearColor(float r, float g, float b, float alpha) {
        glClearColor(r,g,b,alpha);
    }

    /**
     *
     */
    @Override
    public void resizedWindow() {
        if (gameRunningData.isResized()) {
            glViewport(0, 0, gameRunningData.getWindowWidth(), gameRunningData.getWindowHeight());
            gameRunningData.setResized(false);
        }
    }

    @Override
    public void input() {
         if (isKeyPressed(GLFW_KEY_UP)) {
             gameRunningData.setDirection(1);
         }else if (isKeyPressed(GLFW_KEY_DOWN)) {
             gameRunningData.setDirection(-1);
         }else {
             gameRunningData.setDirection(0);
         }
    }

}
