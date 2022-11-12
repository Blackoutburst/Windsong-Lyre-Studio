package com.blackoutburst.windlyrestudio.core.gui.render;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <h1>Shader</h1>
 *
 * <p>
 * Create and manager shaders
 * </p>
 *
 * @since 0.1
 * @author Blackoutburst
 */
public class Shader {

    /** The shader id*/
    protected int id;

    /** Vertex shader type */
    public static final int VERTEX = GL_VERTEX_SHADER;

    /** Fragment shader type */
    public static final int FRAGMENT = GL_FRAGMENT_SHADER;

    public static final Shader DEFAULT_VERTEX = loadShader(VERTEX, "shaders/quadNoTexture.vert");

    public static final Shader DEFAULT_FRAGMENT = loadShader(FRAGMENT, "shaders/quadNoTexture.frag");

    public static final Shader VERTEX_TEXTURE = loadShader(VERTEX, "shaders/quad.vert");

    public static final Shader FRAGMENT_TEXTURE = loadShader(FRAGMENT, "shaders/quad.frag");

    private Shader(int id) {
        this.id = id;
    }

    public static Shader loadShader(int shaderType, String filePath) {
        int shader = glCreateShader(shaderType);

        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream("/"+filePath)));
            String line;
            while((line = reader.readLine()) != null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        } catch(Exception e) {
            System.err.println("["+filePath+"] Doesn't exist");
        }
        glShaderSource(shader, shaderSource);
        glCompileShader(shader);
        if (glGetShaderInfoLog(shader).length() != 0) System.err.println("Error in: ["+filePath+"]\n"+glGetShaderInfoLog(shader));
        return (new Shader(shader));
    }
}