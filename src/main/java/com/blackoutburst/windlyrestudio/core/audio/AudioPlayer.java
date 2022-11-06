package com.blackoutburst.windlyrestudio.core.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import static org.lwjgl.openal.ALC10.*;

public class AudioPlayer {

    private long audioContext;
    private long audioDevice;



    public AudioPlayer() {
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        audioDevice = alcOpenDevice(defaultDeviceName);

        int[] attributes = {0};

        audioContext = alcCreateContext(audioDevice, attributes);
        alcMakeContextCurrent(audioContext);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(audioDevice);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        if (!alCapabilities.OpenAL10)
            throw new IllegalStateException("Unable to initialize OpenAL");
    }

    public void destroy() {
        alcDestroyContext(audioContext);
        alcCloseDevice(audioDevice);
    }
}
