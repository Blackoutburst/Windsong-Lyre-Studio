package com.blackoutburst.windlyrestudio.utils.maths;

public class Vector2f {

    public float x;

    public float y;

    public Vector2f() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2f(float size) {
        this.x = size;
        this.y = size;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return (x);
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return (y);
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f normalize() {
        double mag = Math.sqrt(x * x + y * y);
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }

        return (this);
    }

    public Vector2f mul(float v) {
        x *= v;
        y *= v;

        return (this);
    }

    public float length() {
        return ((float) Math.sqrt(x * x + y * y));
    }

    public Vector2f copy() {
        Vector2f newVector = new Vector2f();
        newVector.x = this.x;
        newVector.y = this.y;

        return (newVector);
    }

    @Override
    public String toString() {
        return "["+x+", "+y+"]";
    }
}