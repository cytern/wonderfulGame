package com.dam.wonder.view.window;

import com.dam.wonder.model.config.running.GameRunningData;
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
    /**
     * 窗口编号
     */
    private long window;

    public Window(GameRunningData gameRunningData) {
        this.gameRunningData = gameRunningData;
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
        window = glfwCreateWindow(gameRunningData.getWindowWidth(), gameRunningData.getWindowHeight(), gameRunningData.getGameWindowName(), NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("游戏初始化 失败 窗口创建失败 ");
        }
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - gameRunningData.getWindowWidth()) / 2,
                (vidmode.height() - gameRunningData.getWindowHeight()) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync  垂直同步启动
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);


        log.info("游戏初始化 结束");
    }

    /**
     * 更新
     */
    @Override
    public void update() {

    }

    /**
     * 渲染
     */
    @Override
    public void render() {

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
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
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
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

}
