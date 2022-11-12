package com.blackoutburst.windlyrestudio.utils.maths;

public class Vector4f {

    public float x;

    public float y;

    public float z;

    public float w;

    public Vector4f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public Vector4f(float size) {
        this.x = size;
        this.y = size;
        this.z = size;
        this.w = size;
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public float getW() {
        return (w);
    }

    public void setW(float w) {
        this.w = w;
    }

    public Vector4f normalize() {
        float mag = (float) Math.sqrt(x * x + y * y + z * z + w * w);
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
            w /= mag;
        }

        return (this);
    }

    public Vector4f mul(float v) {
        x *= v;
        y *= v;
        z *= v;
        w *= v;

        return (this);
    }

    public float length() {
        return ((float) Math.sqrt(x * x + y * y + z * z + w * w));
    }

    public Vector4f copy() {
        Vector4f newVector = new Vector4f();
        newVector.x = this.x;
        newVector.y = this.y;
        newVector.z = this.z;
        newVector.w = this.w;

        return (newVector);
    }

    @Override
    public String toString() {
        return "["+x+", "+y+", "+z+", "+w+"]";
    }
}