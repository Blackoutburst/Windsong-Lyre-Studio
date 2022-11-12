package com.blackoutburst.windlyrestudio.core.gui.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.blackoutburst.windlyrestudio.utils.Color;
import com.blackoutburst.windlyrestudio.utils.maths.Matrix;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;
import org.lwjgl.BufferUtils;

public class Quad {
    public static Matrix model;

    private ShaderProgram program;

    public Vector2f position;
    public Vector2f size;

    public Color color;

    public Texture texture;

    public float rotation;

    private int vaoID;

    private static final int[] indices = {0, 1, 3, 1, 2, 3};
    private final float[] vertices =
    {
        0.5f, -0.5f, 1.0f, 1.0f, // Bottom right
        0.5f, 0.5f, 1.0f, 0.0f, // Top right
        -0.5f, 0.5f, 0.0f, 0.0f,  // Top left
        -0.5f, -0.5f, 0.0f, 1.0f // Bottom left
    };

    public Quad(ShaderProgram program, Texture texture) {
        this.texture = texture;
        this.program = program;
        this.position = new Vector2f();
        this.size = new Vector2f();
        this.rotation = 0;
        this.color = Color.WHITE;

        vaoID = glGenVertexArrays();

        int vboID = glGenBuffers();
        int eboID = glGenBuffers();

        glBindVertexArray(vaoID);

        final FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        ((Buffer) verticesBuffer.put(vertices)).flip();

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        final IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        ((Buffer) indicesBuffer.put(indices)).flip();


        glVertexAttribPointer(0, 2, GL_FLOAT, false, 16, 0);
        glEnableVertexAttribArray(0);

        // UV
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 16, 8);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        model = new Matrix();
    }


    private void setMatricesUniform() {
        program.setUniformMat4("projection", FrameBuffer.projection);
        program.setUniformMat4("model", model);
        program.setUniform4f("color", color);
    }

    private void setTransformation() {
        Matrix.setIdentity(model);
        Matrix.translate(position, model);
        Matrix.rotate((float) Math.toRadians(rotation), model);
        Matrix.scale(size, model);
    }

    public void draw() {
        if (FrameBuffer.outOfFrame(position, size)) return;

        if (texture != null) {
            glBindTexture(GL_TEXTURE_2D, texture.getTextureId());
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        }

        setTransformation();
        setMatricesUniform();

        glUseProgram(program.getID());
        glBindVertexArray(vaoID);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
        glUseProgram(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
