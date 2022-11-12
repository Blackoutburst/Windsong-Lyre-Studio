package com.blackoutburst.windlyrestudio.core.gui.render;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.blackoutburst.windlyrestudio.utils.IOUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;


public class Texture {

    protected int id;

    protected IntBuffer width;
    protected IntBuffer height;

    public Texture(String filePath) {
        ByteBuffer data = null;
        try {
            MemoryStack.stackPush();
            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);

            IntBuffer comp = MemoryStack.stackMallocInt(1);
            this.width = MemoryStack.stackMallocInt(1);
            this.height = MemoryStack.stackMallocInt(1);

            try {
                data = STBImage.stbi_load_from_memory(IOUtils.ioResourceToByteBuffer(filePath, 1024), this.width, this.height, comp, 0);
            } catch (Exception ignored) {}
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width.get(0), this.height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
            ((Buffer)comp).clear();

            MemoryStack.stackPop();

            if (data == null)
                System.err.println("Couldn't load ["+filePath+"]");

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data != null) {
            STBImage.stbi_image_free(data);
        }
    }

    public int getWidth() {
        return (this.width.get(0));
    }

    public int getHeight() {
        return (this.height.get(0));
    }

    public int getTextureId() {
        return (this.id);
    }
}