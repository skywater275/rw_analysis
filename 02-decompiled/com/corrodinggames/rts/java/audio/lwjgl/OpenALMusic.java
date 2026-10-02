/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.openal.AL10
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.audio.Music$OnCompletionListener;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.b;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.m;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public abstract class OpenALMusic
implements Music {
    private static final int bufferSize = 40960;
    private static final int bufferCount = 3;
    private static final int bytesPerSample = 2;
    private static final byte[] tempBytes = new byte[40960];
    private static final ByteBuffer tempBuffer = BufferUtils.createByteBuffer((int)40960);
    private b renderedSecondsQueue = new b(3);
    private final OpenALAudio audio;
    private IntBuffer buffers;
    private int sourceID = -1;
    private int format;
    private int sampleRate;
    private boolean isLooping;
    private boolean isPlaying;
    private float volume = 1.0f;
    private float pan = 0.0f;
    private float renderedSeconds;
    private float maxSecondsPerBuffer;
    protected final a file;
    protected int bufferOverhead = 0;
    private Music$OnCompletionListener onCompletionListener;

    public OpenALMusic(OpenALAudio openALAudio, a a2) {
        this.audio = openALAudio;
        this.file = a2;
        this.onCompletionListener = null;
    }

    protected void setup(int n, int n2) {
        this.format = n > 1 ? 4355 : 4353;
        this.sampleRate = n2;
        this.maxSecondsPerBuffer = (float)(40960 - this.bufferOverhead) / (float)(2 * n * n2);
    }

    public void playWhenReady() {
        if (this.audio.noDevice) {
            return;
        }
    }

    @Override
    public void play() {
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID == -1) {
            int n;
            int n2;
            this.sourceID = this.audio.obtainSource(true);
            if (this.sourceID == -1) {
                return;
            }
            this.audio.music.add(this);
            if (this.buffers == null) {
                this.buffers = BufferUtils.createIntBuffer((int)3);
                AL10.alGenBuffers((IntBuffer)this.buffers);
                n2 = AL10.alGetError();
                if (n2 != 0) {
                    throw new c("Unable to allocate audio buffers. AL Error: " + n2);
                }
            }
            AL10.alSourcei((int)this.sourceID, (int)4103, (int)0);
            this.setPan(this.pan, this.volume);
            n2 = 0;
            for (int j = 0; j < 3 && this.fill(n = this.buffers.get(j)); ++j) {
                n2 = 1;
                AL10.alSourceQueueBuffers((int)this.sourceID, (int)n);
            }
            if (n2 == 0 && this.onCompletionListener != null) {
                this.onCompletionListener.onCompletion(this);
            }
            if (AL10.alGetError() != 0) {
                this.stop();
                return;
            }
        }
        if (!this.isPlaying) {
            AL10.alSourcePlay((int)this.sourceID);
            this.isPlaying = true;
        }
    }

    @Override
    public void stop() {
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID == -1) {
            return;
        }
        this.audio.music.remove(this);
        this.reset();
        this.audio.freeSource(this.sourceID);
        this.sourceID = -1;
        this.renderedSeconds = 0.0f;
        this.renderedSecondsQueue.c();
        this.isPlaying = false;
    }

    @Override
    public void pause() {
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID != -1) {
            AL10.alSourcePause((int)this.sourceID);
        }
        this.isPlaying = false;
    }

    @Override
    public boolean isPlaying() {
        if (this.audio.noDevice) {
            return false;
        }
        if (this.sourceID == -1) {
            return false;
        }
        return this.isPlaying;
    }

    @Override
    public void setLooping(boolean bl) {
        this.isLooping = bl;
    }

    @Override
    public boolean isLooping() {
        return this.isLooping;
    }

    @Override
    public void setVolume(float f) {
        this.volume = f;
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID != -1) {
            AL10.alSourcef((int)this.sourceID, (int)4106, (float)f);
        }
    }

    @Override
    public float getVolume() {
        return this.volume;
    }

    @Override
    public void setPan(float f, float f2) {
        this.volume = f2;
        this.pan = f;
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID == -1) {
            return;
        }
        AL10.alSource3f((int)this.sourceID, (int)4100, (float)m.b((f - 1.0f) * (float)Math.PI / 2.0f), (float)0.0f, (float)m.a((f + 1.0f) * (float)Math.PI / 2.0f));
        AL10.alSourcef((int)this.sourceID, (int)4106, (float)f2);
    }

    @Override
    public void setPosition(float f) {
        int n;
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID == -1) {
            return;
        }
        boolean bl = this.isPlaying;
        this.isPlaying = false;
        AL10.alSourceStop((int)this.sourceID);
        AL10.alSourceUnqueueBuffers((int)this.sourceID, (IntBuffer)this.buffers);
        while (this.renderedSecondsQueue.b > 0) {
            this.renderedSeconds = this.renderedSecondsQueue.a();
        }
        if (f <= this.renderedSeconds) {
            this.reset();
            this.renderedSeconds = 0.0f;
        }
        while (this.renderedSeconds < f - this.maxSecondsPerBuffer && this.read(tempBytes) > 0) {
            this.renderedSeconds += this.maxSecondsPerBuffer;
        }
        this.renderedSecondsQueue.a(this.renderedSeconds);
        boolean bl2 = false;
        for (int j = 0; j < 3 && this.fill(n = this.buffers.get(j)); ++j) {
            bl2 = true;
            AL10.alSourceQueueBuffers((int)this.sourceID, (int)n);
        }
        this.renderedSecondsQueue.a();
        if (!bl2) {
            this.stop();
            if (this.onCompletionListener != null) {
                this.onCompletionListener.onCompletion(this);
            }
        }
        AL10.alSourcef((int)this.sourceID, (int)4132, (float)(f - this.renderedSeconds));
        if (bl) {
            AL10.alSourcePlay((int)this.sourceID);
            this.isPlaying = true;
        }
    }

    @Override
    public float getPosition() {
        if (this.audio.noDevice) {
            return 0.0f;
        }
        if (this.sourceID == -1) {
            return 0.0f;
        }
        return this.renderedSeconds + AL10.alGetSourcef((int)this.sourceID, (int)4132);
    }

    public abstract int read(byte[] var1);

    public void reset() {
    }

    protected void loop() {
        this.reset();
    }

    public int getChannels() {
        return this.format == 4355 ? 2 : 1;
    }

    public int getRate() {
        return this.sampleRate;
    }

    public void backgroundUpdate() {
    }

    public void update() {
        int n;
        if (this.audio.noDevice) {
            return;
        }
        if (this.sourceID == -1) {
            return;
        }
        boolean bl = false;
        int n2 = AL10.alGetSourcei((int)this.sourceID, (int)4118);
        while (n2-- > 0 && (n = AL10.alSourceUnqueueBuffers((int)this.sourceID)) != 40963) {
            this.renderedSeconds = this.renderedSecondsQueue.a();
            if (bl) continue;
            if (this.fill(n)) {
                AL10.alSourceQueueBuffers((int)this.sourceID, (int)n);
                continue;
            }
            bl = true;
        }
        if (bl && AL10.alGetSourcei((int)this.sourceID, (int)4117) == 0) {
            this.stop();
            if (this.onCompletionListener != null) {
                this.onCompletionListener.onCompletion(this);
            }
        }
        if (this.isPlaying && AL10.alGetSourcei((int)this.sourceID, (int)4112) != 4114) {
            AL10.alSourcePlay((int)this.sourceID);
        }
    }

    private boolean fill(int n) {
        tempBuffer.clear();
        int n2 = this.read(tempBytes);
        if (n2 <= 0) {
            if (this.isLooping) {
                this.loop();
                n2 = this.read(tempBytes);
                if (n2 <= 0) {
                    return false;
                }
                if (this.renderedSecondsQueue.b > 0) {
                    this.renderedSecondsQueue.a(0, 0.0f);
                }
            } else {
                return false;
            }
        }
        float f = this.renderedSecondsQueue.b > 0 ? this.renderedSecondsQueue.b() : 0.0f;
        float f2 = this.maxSecondsPerBuffer * (float)n2 / 40960.0f;
        this.renderedSecondsQueue.b(0, f + f2);
        tempBuffer.put(tempBytes, 0, n2).flip();
        AL10.alBufferData((int)n, (int)this.format, (ByteBuffer)tempBuffer, (int)this.sampleRate);
        return true;
    }

    @Override
    public void dispose() {
        this.stop();
        if (this.audio.noDevice) {
            return;
        }
        if (this.buffers == null) {
            return;
        }
        AL10.alDeleteBuffers((IntBuffer)this.buffers);
        this.buffers = null;
        this.onCompletionListener = null;
    }

    @Override
    public void setOnCompletionListener(Music$OnCompletionListener music$OnCompletionListener) {
        this.onCompletionListener = music$OnCompletionListener;
    }

    public int getSourceId() {
        return this.sourceID;
    }
}
