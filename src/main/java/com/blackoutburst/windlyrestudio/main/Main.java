package com.blackoutburst.windlyrestudio.main;

import com.blackoutburst.windlyrestudio.core.gui.render.Display;

public class Main {

    public static void main(String[] args) {
        Display display = new Display().create();
        while (display.isOpen()) {
            display.clear();
            display.update();
        }
        display.destroy();
    }
}