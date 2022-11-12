package com.blackoutburst.windlyrestudio.core.gui.render;

import com.blackoutburst.windlyrestudio.utils.maths.Matrix;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;


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

    public static boolean outOfFrame(Vector2f position, Vector2f size) {
        if (position.x + size.x / 2 < 0)
            return (true);
        if (position.x - size.x / 2 > Display.getWidth() )
            return (true);
        if (position.y - size.y / 2 > Display.getHeight())
            return (true);
        return position.y + size.y / 2 < 0;
    }

}
