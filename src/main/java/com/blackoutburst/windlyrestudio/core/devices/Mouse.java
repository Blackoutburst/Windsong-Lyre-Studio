package com.blackoutburst.windlyrestudio.core.devices;

import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;

public class Mouse {

    private static float x;
    private static float y;
    private static float scroll;

    private static Vector2f position = new Vector2f();

    private static final MouseButton leftButton = new MouseButton(0);

    private static final MouseButton rightButton = new MouseButton(1);

    private static final MouseButton middleButton = new MouseButton(2);

    public static float getX() {
        return x;
    }

    public static void setX(float x) {
        Mouse.x = x;
    }

    public static float getY() {
        return y;
    }

    public static void setY(float y) {
        Mouse.y = y;
    }

    public static Vector2f getPosition() {
        return position;
    }

    public static void setPosition(Vector2f position) {
        Mouse.position = position;
    }

    public static MouseButton getLeftButton() {
        return leftButton;
    }

    public static MouseButton getRightButton() {
        return rightButton;
    }

    public static MouseButton getMiddleButton() {
        return middleButton;
    }

    public static float getScroll() {
        return scroll;
    }

    public static void setScroll(float scroll) {
        Mouse.scroll = scroll;
    }
}