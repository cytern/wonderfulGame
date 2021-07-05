package com.dam.wonder.view.render;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    public Renderer() {
    }

    public void init() throws Exception {
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
