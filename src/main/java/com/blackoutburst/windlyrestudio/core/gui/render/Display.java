package com.blackoutburst.windlyrestudio.core.gui.render;

import com.blackoutburst.windlyrestudio.core.gui.callbacks.*;
import com.blackoutburst.windlyrestudio.core.listener.GlobalKeyListener;
import com.blackoutburst.windlyrestudio.utils.IOUtils;
import com.blackoutburst.windlyrestudio.utils.maths.Vector2i;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display {

    protected static long window;

    public Display() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
    }

    public static long getWindow() {
        return window;
    }

    public Display create() {
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1600, 900, "Windsong Lyre Studio", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        setIcons("images/icon256.png");

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        glfwSetWindowSizeCallback(window, new WindowCallBack());
        glfwSetCursorPosCallback(window, new MousePositionCallBack());
        glfwSetMouseButtonCallback(window, new MouseButtonCallBack());
        glfwSetScrollCallback(window, new MouseScrollCallBack());
        glfwSetKeyCallback(window, new KeyboardCallBack());

        FrameBuffer.init();

        GlobalKeyListener.register();

        return (this);
    }

    public void update() {
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public void clear() {
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
    }

    public boolean isOpen() {
        return (!glfwWindowShouldClose(window));
    }

    public void setIcons(String filePath) {
        GLFWImage image = GLFWImage.malloc();
        GLFWImage.Buffer imageBuffer = GLFWImage.malloc(1);
        try {
            Vector2i imageSize = new Vector2i();
            ByteBuffer byteBuffer = loadIcons(filePath, imageSize);

            image.set(imageSize.x, imageSize.y, byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageBuffer.put(0, image);
        glfwSetWindowIcon(window, imageBuffer);
    }

    private ByteBuffer loadIcons(String path, Vector2i imageSize) throws Exception {
        ByteBuffer image;
        MemoryStack.stackPush();
        IntBuffer comp = MemoryStack.stackMallocInt(1);
        IntBuffer w = MemoryStack.stackMallocInt(1);
        IntBuffer h = MemoryStack.stackMallocInt(1);

        image = STBImage.stbi_load_from_memory(IOUtils.ioResourceToByteBuffer(path, 1024), w, h, comp, 4);
        imageSize.set(w.get(), h.get());

        ((Buffer)comp).clear();
        ((Buffer)w).clear();
        ((Buffer)h).clear();
        if (image == null)
            throw new Exception("Failed to load icons");

        MemoryStack.stackPop();
        return image;
    }

    public static int getWidth() {
        int w = 0;

        MemoryStack.stackPush();
        IntBuffer width = MemoryStack.stackMallocInt(1);
        IntBuffer height = MemoryStack.stackMallocInt(1);
        glfwGetWindowSize(window, width, height);
        w = width.get();

        ((Buffer)width).clear();
        ((Buffer)height).clear();

        MemoryStack.stackPop();

        return (w);
    }

    public static int getHeight() {
        int h = 0;

        MemoryStack.stackPush();
        IntBuffer width = MemoryStack.stackMallocInt(1);
        IntBuffer height = MemoryStack.stackMallocInt(1);
        glfwGetWindowSize(window, width, height);

        h = height.get();

        ((Buffer)width).clear();
        ((Buffer)height).clear();

        MemoryStack.stackPop();

        return (h);
    }

    public static void callBackSetSize(int w, int h) {
        if (window != NULL)
            glfwSetWindowSize(window, w, h);
    }

    public void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        GlobalKeyListener.unregister();
    }
}
