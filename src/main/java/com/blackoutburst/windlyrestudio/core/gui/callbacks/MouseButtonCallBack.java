package com.blackoutburst.windlyrestudio.core.gui.callbacks;

import com.blackoutburst.windlyrestudio.core.devices.Mouse;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class MouseButtonCallBack implements GLFWMouseButtonCallbackI {

    @Override
    public void invoke(long window, int button, int action, int mods) {
        switch(button) {
            case 0:
                Mouse.getLeftButton().setPressed(action == 1);
                Mouse.getLeftButton().setDown(action == 1);
                Mouse.getLeftButton().setReleased(action == 0);
                break;
            case 1:
                Mouse.getRightButton().setPressed(action == 1);
                Mouse.getRightButton().setDown(action == 1);
                Mouse.getRightButton().setReleased(action == 0);
                break;
            case 2:
                Mouse.getMiddleButton().setPressed(action == 1);
                Mouse.getMiddleButton().setDown(action == 1);
                Mouse.getMiddleButton().setReleased(action == 0);
                break;
        }
    }
}