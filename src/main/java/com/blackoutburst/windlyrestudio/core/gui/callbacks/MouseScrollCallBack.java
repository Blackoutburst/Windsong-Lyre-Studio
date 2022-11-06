package com.blackoutburst.windlyrestudio.core.gui.callbacks;

import com.blackoutburst.windlyrestudio.core.devices.Mouse;
import org.lwjgl.glfw.GLFWScrollCallbackI;

public class MouseScrollCallBack implements GLFWScrollCallbackI {

    @Override
    public void invoke(long window, double xOffset, double yOffset) {
        Mouse.setScroll((float) (Mouse.getScroll() + yOffset));
    }
}