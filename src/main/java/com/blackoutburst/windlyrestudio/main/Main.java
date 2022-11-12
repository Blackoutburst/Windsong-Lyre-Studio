package com.blackoutburst.windlyrestudio.main;

import com.blackoutburst.windlyrestudio.core.Core;
import com.blackoutburst.windlyrestudio.core.LyrePlayer;
import com.blackoutburst.windlyrestudio.core.audio.AudioPlayer;
import com.blackoutburst.windlyrestudio.core.gui.NoteButton;
import com.blackoutburst.windlyrestudio.core.gui.render.Display;
import com.blackoutburst.windlyrestudio.core.gui.render.ShaderProgram;
import com.blackoutburst.windlyrestudio.core.gui.render.Texture;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;
import org.lwjgl.glfw.GLFW;

public class Main {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        Display display = new Display().create();
        LyrePlayer lyrePlayer = LyrePlayer.getInstance();


        while (display.isOpen()) {
            display.clear();

            lyrePlayer.update();

            lyrePlayer.draw();

            Core.update();
            display.update();
        }
        display.destroy();
        audioPlayer.destroy();
    }
}