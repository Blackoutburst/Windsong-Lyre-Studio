package com.blackoutburst.windlyrestudio.core;

import org.lwjgl.glfw.GLFW;

public class Time {

    private static long init = System.nanoTime();
    private static long lastTime = System.nanoTime();

    private static double deltaTime = 0;

    private static final double UPDATE = 1000000000.0 / 60.0;

    public static boolean doUpdate() {
        if (System.nanoTime() - Time.init > Time.UPDATE) {
            Time.init += Time.UPDATE;
            return (true);
        }
        return (false);
    }

    protected static void updateDelta() {
        long time = System.nanoTime();
        deltaTime = ((time - lastTime) / 1000000.0f);
        lastTime = time;
    }


    public static double getDelta() {
        return (deltaTime);
    }

    public static double getRuntime() {
        return (GLFW.glfwGetTime());
    }
}