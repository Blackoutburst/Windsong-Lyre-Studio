package com.blackoutburst.windlyrestudio.core.gui.callbacks;

import com.blackoutburst.windlyrestudio.core.devices.Mouse;
import com.blackoutburst.windlyrestudio.core.gui.render.Display;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class MousePositionCallBack implements GLFWCursorPosCallbackI {

    @Override
    public void invoke(long window, double xPos, double yPos) {
        Mouse.setX((float) xPos);
        Mouse.setY((float) (Display.getHeight() - yPos));
        Mouse.setPosition(new Vector2f((float) xPos, (float) (Display.getHeight() - yPos)));
    }
}