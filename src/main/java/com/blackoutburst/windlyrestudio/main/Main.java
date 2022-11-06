package com.blackoutburst.windlyrestudio.main;

import com.blackoutburst.windlyrestudio.core.Core;
import com.blackoutburst.windlyrestudio.core.Time;
import com.blackoutburst.windlyrestudio.core.audio.AudioPlayer;
import com.blackoutburst.windlyrestudio.core.audio.Note;
import com.blackoutburst.windlyrestudio.core.gui.render.Display;

public class Main {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        Display display = new Display().create();
        Note note = new Note("sounds/song.ogg");
        note.play();

        while (display.isOpen()) {
            if (Time.doUpdate()) {
                Core.update();
                display.clear();
                display.update();
            }
        }
        display.destroy();
        audioPlayer.destroy();
    }
}