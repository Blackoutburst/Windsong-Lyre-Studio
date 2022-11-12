package com.blackoutburst.windlyrestudio.core;

import com.blackoutburst.windlyrestudio.core.audio.Note;
import com.blackoutburst.windlyrestudio.core.gui.NoteButton;
import com.blackoutburst.windlyrestudio.core.gui.render.ShaderProgram;
import com.blackoutburst.windlyrestudio.core.gui.render.Texture;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LyrePlayer {

    private static Map<Integer, Note> notes = new LinkedHashMap<>();

    private Map<Integer, NoteButton> buttons = new HashMap<>();

    private static LyrePlayer instance = null;

    private String[] notation = new String[] {"do", "re", "mi", "fa", "sol", "la", "si"};

    public static LyrePlayer getInstance() {
        if (instance == null)
            instance = new LyrePlayer();

        return instance;
    }

    private LyrePlayer() {
        notes.put(GLFW.GLFW_KEY_Q, new Note("sounds/do1.ogg"));
        notes.put(GLFW.GLFW_KEY_W, new Note("sounds/re1.ogg"));
        notes.put(GLFW.GLFW_KEY_E, new Note("sounds/mi1.ogg"));
        notes.put(GLFW.GLFW_KEY_R, new Note("sounds/fa1.ogg"));
        notes.put(GLFW.GLFW_KEY_T, new Note("sounds/sol1.ogg"));
        notes.put(GLFW.GLFW_KEY_Y, new Note("sounds/la1.ogg"));
        notes.put(GLFW.GLFW_KEY_U, new Note("sounds/si1.ogg"));

        notes.put(GLFW.GLFW_KEY_A, new Note("sounds/do2.ogg"));
        notes.put(GLFW.GLFW_KEY_S, new Note("sounds/re2.ogg"));
        notes.put(GLFW.GLFW_KEY_D, new Note("sounds/mi2.ogg"));
        notes.put(GLFW.GLFW_KEY_F, new Note("sounds/fa2.ogg"));
        notes.put(GLFW.GLFW_KEY_G, new Note("sounds/sol2.ogg"));
        notes.put(GLFW.GLFW_KEY_H, new Note("sounds/la2.ogg"));
        notes.put(GLFW.GLFW_KEY_J, new Note("sounds/si2.ogg"));

        notes.put(GLFW.GLFW_KEY_Z, new Note("sounds/do3.ogg"));
        notes.put(GLFW.GLFW_KEY_X, new Note("sounds/re3.ogg"));
        notes.put(GLFW.GLFW_KEY_C, new Note("sounds/mi3.ogg"));
        notes.put(GLFW.GLFW_KEY_V, new Note("sounds/fa3.ogg"));
        notes.put(GLFW.GLFW_KEY_B, new Note("sounds/sol3.ogg"));
        notes.put(GLFW.GLFW_KEY_N, new Note("sounds/la3.ogg"));
        notes.put(GLFW.GLFW_KEY_M, new Note("sounds/si3.ogg"));

        Integer[] keys = notes.keySet().toArray(new Integer[21]);
        int index = 0;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < notation.length; x++ ) {
                Texture texture = new Texture("images/" + notation[x] + ".png");
                Texture textureActive = new Texture("images/" + notation[x] + "_active.png");

                buttons.put(keys[index], new NoteButton(ShaderProgram.TEXTURE, texture, textureActive, 10 + ((6 - x) * 80), -170 + (y * 80), keys[index]));
                index++;
            }
        }
    }

    public void update() {
        for (NoteButton button : buttons.values())
            button.update();
    }

    public void draw() {
        for (NoteButton button : buttons.values())
            button.draw();
    }

    public void playNote(int key) {
        Note note = notes.get(key);
        if (note != null) {
            buttons.get(key).setActive();
            note.play();
        }
    }

    public static void clean() {
        for (Note note : notes.values())
            note.destroy();
    }
}
