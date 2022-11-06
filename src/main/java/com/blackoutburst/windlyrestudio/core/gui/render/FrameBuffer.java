package com.blackoutburst.windlyrestudio.core.gui.render;

import com.blackoutburst.windlyrestudio.utils.maths.Matrix;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;


public class FrameBuffer {

    public static Matrix projection = new Matrix();

    public static void init() {
        setOrtho(Display.getWidth(), Display.getHeight());
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
    }

    public static void setOrtho(int width, int height) {
        glViewport(0, 0, width, height);
        Matrix.ortho2D(projection, 0, width, 0, height, -1, 1);
    }
}
