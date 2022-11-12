package com.blackoutburst.windlyrestudio.main;

import com.blackoutburst.windlyrestudio.core.Core;
import com.blackoutburst.windlyrestudio.core.LyrePlayer;
import com.blackoutburst.windlyrestudio.core.audio.AudioPlayer;
import com.blackoutburst.windlyrestudio.core.gui.render.Display;

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