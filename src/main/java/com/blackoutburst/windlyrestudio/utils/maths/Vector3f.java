package com.blackoutburst.windlyrestudio.utils.maths;

public class Vector3f {

    public float x;

    public float y;

    public float z;

    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3f(float size) {
        this.x = size;
        this.y = size;
        this.z = size;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public float getZ() {
        return (z);
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3f normalize() {
        float mag = (float) Math.sqrt(x * x + y * y + z * z);
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
        }

        return (this);
    }

    public Vector3f mul(float v) {
        x *= v;
        y *= v;
        z *= v;

        return (this);
    }

    public float length() {
        return ((float) Math.sqrt(x * x + y * y + z * z));
    }

    public Vector3f copy() {
        Vector3f newVector = new Vector3f();
        newVector.x = this.x;
        newVector.y = this.y;
        newVector.z = this.z;

        return (newVector);
    }

    @Override
    public String toString() {
        return "["+x+", "+y+", "+z+"]";
    }
}