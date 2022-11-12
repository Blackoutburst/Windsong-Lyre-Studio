package com.blackoutburst.windlyrestudio.core.gui.render;

import com.blackoutburst.windlyrestudio.utils.Color;
import com.blackoutburst.windlyrestudio.utils.maths.Matrix;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2f;
import com.blackoutburst.windlyrestudio.utils.maths.Vector3f;
import com.blackoutburst.windlyrestudio.utils.maths.Vector4f;

import static org.lwjgl.opengl.ARBProgramInterfaceQuery.GL_UNIFORM;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL41.glProgramUniform4f;
import static org.lwjgl.opengl.ARBProgramInterfaceQuery.glGetProgramResourceLocation;
import static org.lwjgl.opengl.GL41.glProgramUniform1f;
import static org.lwjgl.opengl.GL41.glProgramUniform2f;
import static org.lwjgl.opengl.GL41.glProgramUniform3f;
import static org.lwjgl.opengl.GL41.glProgramUniformMatrix4fv;


public class ShaderProgram {

    protected int id;

    protected Shader vertex;
    protected Shader fragment;

    public static final ShaderProgram COLOR = new ShaderProgram(Shader.DEFAULT_VERTEX, Shader.DEFAULT_FRAGMENT);

    public static final ShaderProgram TEXTURE = new ShaderProgram(Shader.VERTEX_TEXTURE, Shader.FRAGMENT_TEXTURE);

    public ShaderProgram(Shader vertex, Shader fragment) {
        this.vertex = vertex;
        this.fragment = fragment;
        this.createProgram();
    }

    private void createProgram() {
        id = glCreateProgram();
        glAttachShader(id, vertex.id);
        glAttachShader(id, fragment.id);
        glLinkProgram(id);
        glDetachShader(id, vertex.id);
        glDetachShader(id, fragment.id);
    }

    public void clean() {
        glDeleteProgram(id);
    }

    public int getID() {
        return (id);
    }

    public Shader getVertex() {
        return (vertex);
    }

    public Shader getFragment() {
        return (fragment);
    }

    public void setUniform1f(String varName, float x) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform1f(id, loc, x);
    }

    public void setUniform2f(String varName, float x, float y) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform2f(id, loc, x, y);
    }

    public void setUniform2f(String varName, Vector2f vec) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform2f(id, loc, vec.x, vec.y);
    }

    public void setUniform3f(String varName, float x, float y, float z) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform3f(id, loc, x, y, z);
    }

    public void setUniform3f(String varName, Vector3f vec) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform3f(id, loc, vec.x, vec.y, vec.z);
    }

    public void setUniform3f(String varName, Color color) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform3f(id, loc, color.r, color.g, color.b);
    }

    public void setUniform4f(String varName, float x, float y, float z, float w) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform4f(id, loc, x, y, z, w);
    }

    public void setUniform4f(String varName, Vector4f vec) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform4f(id, loc, vec.x, vec.y, vec.z, vec.w);
    }

    public void setUniform4f(String varName, Color color) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniform4f(id, loc, color.r, color.g, color.b, color.a);
    }

    public void setUniformMat4(String varName, Matrix mat) {
        int loc = glGetProgramResourceLocation(id, GL_UNIFORM, varName);
        glProgramUniformMatrix4fv(id, loc, false, Matrix.getValues(mat));
    }

}