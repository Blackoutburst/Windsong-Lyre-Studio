package com.blackoutburst.windlyrestudio.core.gui.callbacks;

import com.blackoutburst.windlyrestudio.core.gui.render.Display;
import com.blackoutburst.windlyrestudio.core.gui.render.FrameBuffer;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;

public class WindowCallBack implements GLFWWindowSizeCallbackI {

    @Override
    public void invoke(long window, int width, int height) {
        Display.callBackSetSize(width, height);
        FrameBuffer.setOrtho(width, height);
    }
}