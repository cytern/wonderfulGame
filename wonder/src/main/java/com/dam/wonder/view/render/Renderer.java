package com.dam.wonder.view.render;

import com.dam.wonder.view.shader.Mesh;
import com.dam.wonder.view.shader.ShaderProgram;
import com.dam.wonder.view.util.Utils;
import com.dam.wonder.view.window.Window;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private Matrix4f projectionMatrix;

    private ShaderProgram shaderProgram;

    public Renderer() {
    }

    public void init(Window window) throws Exception {
        // Create shader
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/vertex/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/vertex/fragment.fs"));
        shaderProgram.link();

        // Create projection matrix
        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().setPerspective(Renderer.FOV, aspectRatio, Renderer.Z_NEAR, Renderer.Z_FAR);
        shaderProgram.createUniform("projectionMatrix");
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Mesh mesh) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());

        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
