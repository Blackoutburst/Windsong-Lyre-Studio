package com.blackoutburst.windlyrestudio.core.audio;

import com.blackoutburst.windlyrestudio.utils.IOUtils;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.system.libc.LibCStdlib.free;

public class Note {

    private int bufferId;
    private int sourceId;

    public Note(String filePath) {
        loadSound(filePath);
    }

    public void play() {
        alSourcei(sourceId, AL_POSITION, 0);
        alSourcef(sourceId, AL_GAIN, AudioPlayer.volume);
        alSourcePlay(sourceId);
    }

    public void stop() {
        alSourceStop(sourceId);
    }

    public void destroy() {
        alDeleteBuffers(bufferId);
        alDeleteSources(sourceId);
    }

    private void loadSound(String filePath) {
        try {
            MemoryStack.stackPush();
            IntBuffer channelBuffer = MemoryStack.stackMallocInt(1);
            IntBuffer sampleRateBuffer = MemoryStack.stackMallocInt(1);

            ShortBuffer rawAudioBuffer = STBVorbis.stb_vorbis_decode_memory(IOUtils.ioResourceToByteBuffer(filePath, 1024), channelBuffer, sampleRateBuffer);

            if (rawAudioBuffer == null)
                throw new Exception();

            MemoryStack.stackPop();

            int channels = channelBuffer.get();
            int sampleRate = sampleRateBuffer.get();

            int format = -1;

            if (channels == 1) {
                format = AL_FORMAT_MONO16;
            } else if (channels == 2) {
                format = AL_FORMAT_STEREO16;
            }

            bufferId = alGenBuffers();
            alBufferData(bufferId, format, rawAudioBuffer, sampleRate);

            sourceId = alGenSources();
            alSourcei(sourceId, AL_BUFFER, bufferId);

            free(rawAudioBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
