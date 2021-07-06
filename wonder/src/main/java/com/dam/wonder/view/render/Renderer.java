package com.dam.wonder.view.render;

import com.dam.wonder.view.shader.ShaderProgram;
import com.dam.wonder.view.util.Utils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.MemoryUtil;
import org.springframework.stereotype.Service;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

@Service
@Slf4j
@Data
public class Renderer {
    private final ShaderProgram shaderProgram;
    private int vboId;

    private int vaoId;
    public Renderer(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public void init() throws Exception {
        shaderProgram.setProgramId(glCreateProgram());
        shaderProgram.createVertexShader(Utils.loadResource("/vertex/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/vertex/fragment.fs"));

        float[] vertices = new float[]{
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        };

        FloatBuffer verticesBuffer = null;
        try {
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            // Create the VAO and bind to it
            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Create the VBO and bint to it
            vboId = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            // Enable location 0
            glEnableVertexAttribArray(0);
            // Define structure of the data
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Unbind the VBO
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            // Unbind the VAO
            glBindVertexArray(0);
        } finally {
            if (verticesBuffer != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
        }
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
