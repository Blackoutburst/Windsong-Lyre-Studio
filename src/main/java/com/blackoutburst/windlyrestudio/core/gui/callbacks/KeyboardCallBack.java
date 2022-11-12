package com.blackoutburst.windlyrestudio.core.gui.callbacks;

import com.blackoutburst.windlyrestudio.core.LyrePlayer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class KeyboardCallBack implements GLFWKeyCallbackI {

    private final LyrePlayer lyrePLayer;

    public KeyboardCallBack() {
        lyrePLayer = LyrePlayer.getInstance();
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW.GLFW_PRESS)
            lyrePLayer.playNote(key);
    }
}
