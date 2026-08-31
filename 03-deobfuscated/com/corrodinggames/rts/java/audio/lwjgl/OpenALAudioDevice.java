/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.openal.AL10
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.backend.c;
import com.corrodinggames.rts.java.audio.backend.m;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public class OpenALAudioDevice
implements AudioDevice {
    private static final int bytesPerSample = 2;
    private final OpenALAudio audio;
    private final int channels;
    private IntBuffer buffers;
    private int sourceID = -1;
    private int format;
    private int sampleRate;
    private boolean isPlaying;
    private float volume = 1.0f;
    private float renderedSeconds;
    private float secondsPerBuffer;
    private byte[] bytes;
    private final int bufferSize;
    private final int bufferCount;
    private final ByteBuffer tempBuffer;

    public OpenALAudioDevice(OpenALAudio openALAudio, int n, boolean bl, int n2, int n3) {
        this.audio = openALAudio;
        this.channels = bl ? 1 : 2;
        this.bufferSize = n2;
        this.bufferCount = n3;
        this.format = this.channels > 1 ? 4355 : 4353;
        this.sampleRate = n;
        this.secondsPerBuffer = (float)n2 / 2.0f / (float)this.channels / (float)n;
        this.tempBuffer = BufferUtils.createByteBuffer((int)n2);
    }

    @Override
    public void writeSamples(short[] sArray, int n, int n2) {
        if (this.bytes == null || this.bytes.length < n2 * 2) {
            this.bytes = new byte[n2 * 2];
        }
        int n3 = Math.min(n + n2, sArray.length);
        int n4 = 0;
        for (int i = n; i < n3; ++i) {
            short s = sArray[i];
            this.bytes[n4++] = (byte)(s & 0xFF);
            this.bytes[n4++] = (byte)(s >> 8 & 0xFF);
        }
        this.writeSamples(this.bytes, 0, n2 * 2);
    }

    @Override
    public void writeSamples(float[] fArray, int n, int n2) {
        if (this.bytes == null || this.bytes.length < n2 * 2) {
            this.bytes = new byte[n2 * 2];
        }
        int n3 = Math.min(n + n2, fArray.length);
        int n4 = 0;
        for (int i = n; i < n3; ++i) {
            float f = fArray[i];
            f = com.corrodinggames.rts.java.audio.backend.m.a(f, -1.0f, 1.0f);  // 02b m.a clamp (v19.133f4 修正)
            int n5 = (int)(f * 32767.0f);
            this.bytes[n4++] = (byte)(n5 & 0xFF);
            this.bytes[n4++] = (byte)(n5 >> 8 & 0xFF);
        }
        this.writeSamples(this.bytes, 0, n2 * 2);
    }

    public void writeSamples(byte[] byArray, int n, int n2) {
        int n3;
        if (n2 < 0) {
            throw new IllegalArgumentException("length cannot be < 0.");
        }
        if (this.sourceID == -1) {
            int n4;
            int n5;
            this.sourceID = this.audio.obtainSource(true);
            if (this.sourceID == -1) {
                return;
            }
            if (this.buffers == null) {
                this.buffers = BufferUtils.createIntBuffer((int)this.bufferCount);
                AL10.alGenBuffers((IntBuffer)this.buffers);
                if (AL10.alGetError() != 0) {
                    throw new c("Unabe to allocate audio buffers.");
                }
            }
            AL10.alSourcei((int)this.sourceID, (int)4103, (int)0);
            AL10.alSourcef((int)this.sourceID, (int)4106, (float)this.volume);
            n3 = 0;
            for (n5 = 0; n5 < this.bufferCount; ++n5) {
                n4 = this.buffers.get(n5);
                int n6 = Math.min(this.bufferSize, n2);
                this.tempBuffer.clear();
                this.tempBuffer.put(byArray, n, n6).flip();
                AL10.alBufferData((int)n4, (int)this.format, (ByteBuffer)this.tempBuffer, (int)this.sampleRate);
                AL10.alSourceQueueBuffers((int)this.sourceID, (int)n4);
                n2 -= n6;
                n += n6;
                ++n3;
            }
            this.tempBuffer.clear().flip();
            for (n5 = n3; n5 < this.bufferCount; ++n5) {
                n4 = this.buffers.get(n5);
                AL10.alBufferData((int)n4, (int)this.format, (ByteBuffer)this.tempBuffer, (int)this.sampleRate);
                AL10.alSourceQueueBuffers((int)this.sourceID, (int)n4);
            }
            AL10.alSourcePlay((int)this.sourceID);
            this.isPlaying = true;
        }
        while (n2 > 0) {
            n3 = this.fillBuffer(byArray, n, n2);
            n2 -= n3;
            n += n3;
        }
    }

    private int fillBuffer(byte[] byArray, int n, int n2) {
        int n3;
        int n4 = Math.min(this.bufferSize, n2);
        while (true) {
            int n5 = AL10.alGetSourcei((int)this.sourceID, (int)4118);
            if (n5-- > 0 && (n3 = AL10.alSourceUnqueueBuffers((int)this.sourceID)) != 40963) {
                this.renderedSeconds += this.secondsPerBuffer;
                break;
            }
            try {
                Thread.sleep((long)(1000.0f * this.secondsPerBuffer));
            }
            catch (InterruptedException interruptedException) {}
        }
        this.tempBuffer.clear();
        this.tempBuffer.put(byArray, n, n4).flip();
        AL10.alBufferData((int)n3, (int)this.format, (ByteBuffer)this.tempBuffer, (int)this.sampleRate);
        AL10.alSourceQueueBuffers((int)this.sourceID, (int)n3);
        if (!this.isPlaying || AL10.alGetSourcei((int)this.sourceID, (int)4112) != 4114) {
            AL10.alSourcePlay((int)this.sourceID);
            this.isPlaying = true;
        }
        return n4;
    }

    public void stop() {
        if (this.sourceID == -1) {
            return;
        }
        this.audio.freeSource(this.sourceID);
        this.sourceID = -1;
        this.renderedSeconds = 0.0f;
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        if (this.sourceID == -1) {
            return false;
        }
        return this.isPlaying;
    }

    @Override
    public void setVolume(float f) {
        this.volume = f;
        if (this.sourceID != -1) {
            AL10.alSourcef((int)this.sourceID, (int)4106, (float)f);
        }
    }

    public float getPosition() {
        if (this.sourceID == -1) {
            return 0.0f;
        }
        return this.renderedSeconds + AL10.alGetSourcef((int)this.sourceID, (int)4132);
    }

    public void setPosition(float f) {
        this.renderedSeconds = f;
    }

    public int getChannels() {
        return this.format == 4355 ? 2 : 1;
    }

    public int getRate() {
        return this.sampleRate;
    }

    @Override
    public void dispose() {
        if (this.buffers == null) {
            return;
        }
        if (this.sourceID != -1) {
            this.audio.freeSource(this.sourceID);
            this.sourceID = -1;
        }
        AL10.alDeleteBuffers((IntBuffer)this.buffers);
        this.buffers = null;
    }

    @Override
    public boolean isMono() {
        return this.channels == 1;
    }

    @Override
    public int getLatency() {
        return (int)(this.secondsPerBuffer * (float)this.bufferCount * 1000.0f);
    }
}
