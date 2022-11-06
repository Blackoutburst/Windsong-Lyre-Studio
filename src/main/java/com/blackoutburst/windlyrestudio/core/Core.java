package com.blackoutburst.windlyrestudio.core;

import com.blackoutburst.windlyrestudio.core.devices.Mouse;

public class Core {

    private static int renderPasses = 0;
    private static int fps = 0;

    private static double previousTime = Time.getRuntime();

    public static int getFPS() {
        double currentTime = Time.getRuntime();
        renderPasses++;

        if (currentTime - previousTime >= 1.0) {
            fps = renderPasses;
            renderPasses = 0;
            previousTime = currentTime;
        }
        return (fps);
    }

    public static void update() {
        Time.updateDelta();
        Mouse.getLeftButton().reset();
        Mouse.getRightButton().reset();
        Mouse.getMiddleButton().reset();
        Mouse.setScroll(0);
    }
}
