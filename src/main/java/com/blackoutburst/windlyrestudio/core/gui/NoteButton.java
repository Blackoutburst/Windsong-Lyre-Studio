package com.blackoutburst.windlyrestudio.core.gui;

import com.blackoutburst.windlyrestudio.core.LyrePlayer;
import com.blackoutburst.windlyrestudio.core.devices.Mouse;
import com.blackoutburst.windlyrestudio.core.gui.render.Display;
import com.blackoutburst.windlyrestudio.core.gui.render.Quad;
import com.blackoutburst.windlyrestudio.core.gui.render.ShaderProgram;
import com.blackoutburst.windlyrestudio.core.gui.render.Texture;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;

public class NoteButton extends Quad {

    private Texture activeTexture;
    private Texture regularTexture;

    private int key;

    private int xOffset;
    private int yOffset;

    private int animationTimer = 0;

    private boolean active;

    public NoteButton(ShaderProgram program, Texture texture, Texture activeTexture, int xOffset, int yOffset, int key) {
        super(program, texture);
        this.size = new Vector2f(70);
        this.regularTexture = texture;
        this.activeTexture = activeTexture;
        this.key = key;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void update() {
        onClick();
        animate();

        this.position.setX(Display.getWidth() - size.getX() / 2 - xOffset);
        this.position.setY(size.getY() / 2 - yOffset);

    }

    public void setActive() {
        this.active = true;
        this.animationTimer = 10;
    }

    private void onClick() {
        float x = (position.x - Mouse.getX());
        float y = (position.y - Mouse.getY());
        float distance = (x * x) + (y * y);

        if (Math.abs(distance) <= 1500 && Mouse.getLeftButton().isPressed())
            LyrePlayer.getInstance().playNote(key);
    }

    private void animate() {
        if (animationTimer > 0) {
            animationTimer--;
        }

        if (animationTimer == 0)
            this.active = false;

        this.texture = active ? activeTexture : regularTexture;
    }
}
