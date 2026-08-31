/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.openal.AL10
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.Sound;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import org.lwjgl.openal.AL10;

public class OpenALSound
implements Sound {
    private int bufferID = -1;
    private final OpenALAudio audio;
    private float duration;
    private int bytesUsed;

    public OpenALSound(OpenALAudio openALAudio) {
        this.audio = openALAudio;
    }

    void setup(byte[] byArray, int n, int n2) {
        int n3 = byArray.length - byArray.length % (n > 1 ? 4 : 2);
        int n4 = n3 / (2 * n);
        this.duration = (float)n4 / (float)n2;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(n3);
        byteBuffer.order(ByteOrder.nativeOrder());
        byteBuffer.put(byArray, 0, n3);
        byteBuffer.flip();
        this.bytesUsed = n3;
        if (this.bufferID == -1) {
            this.bufferID = AL10.alGenBuffers();
            AL10.alBufferData((int)this.bufferID, (int)(n > 1 ? 4355 : 4353), (ShortBuffer)byteBuffer.asShortBuffer(), (int)n2);
        }
    }

    @Override
    public int getBytesUsed() {
        return this.bytesUsed;
    }

    @Override
    public long play() {
        return this.play(1.0f);
    }

    @Override
    public long play(float f) {
        if (this.audio.noDevice) {
            return 0L;
        }
        int n = this.audio.obtainSource(false);
        if (n == -1) {
            return -1L;
        }
        this.audio.retain(this, false);
        if (n == -1) {
            return -1L;
        }
        long l = this.audio.getSoundId(n);
        AL10.alSourcei((int)n, (int)4105, (int)this.bufferID);
        AL10.alSourcei((int)n, (int)4103, (int)0);
        AL10.alSourcef((int)n, (int)4106, (float)f);
        AL10.alSourcePlay((int)n);
        return l;
    }

    @Override
    public long loop() {
        return this.loop(1.0f);
    }

    @Override
    public long loop(float f) {
        if (this.audio.noDevice) {
            return 0L;
        }
        int n = this.audio.obtainSource(false);
        if (n == -1) {
            return -1L;
        }
        long l = this.audio.getSoundId(n);
        AL10.alSourcei((int)n, (int)4105, (int)this.bufferID);
        AL10.alSourcei((int)n, (int)4103, (int)1);
        AL10.alSourcef((int)n, (int)4106, (float)f);
        AL10.alSourcePlay((int)n);
        return l;
    }

    @Override
    public void stop() {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.stopSourcesWithBuffer(this.bufferID);
    }

    @Override
    public void dispose() {
        if (this.audio.noDevice) {
            return;
        }
        if (this.bufferID == -1) {
            return;
        }
        this.audio.freeBuffer(this.bufferID);
        AL10.alDeleteBuffers((int)this.bufferID);
        this.bufferID = -1;
        this.audio.forget(this);
    }

    @Override
    public void stop(long l) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.stopSound(l);
    }

    @Override
    public void pause() {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.pauseSourcesWithBuffer(this.bufferID);
    }

    @Override
    public void pause(long l) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.pauseSound(l);
    }

    @Override
    public void resume() {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.resumeSourcesWithBuffer(this.bufferID);
    }

    @Override
    public void resume(long l) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.resumeSound(l);
    }

    @Override
    public void setPitch(long l, float f) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundPitch(l, f);
    }

    @Override
    public void setVolume(long l, float f) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundGain(l, f);
    }

    @Override
    public void setLooping(long l, boolean bl) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundLooping(l, bl);
    }

    @Override
    public void setPan(long l, float f, float f2) {
        if (this.audio.noDevice) {
            return;
        }
        this.audio.setSoundPan(l, f, f2);
    }

    @Override
    public long play(float f, float f2, float f3) {
        long l = this.play();
        this.setPitch(l, f2);
        this.setPan(l, f3, f);
        return l;
    }

    @Override
    public long loop(float f, float f2, float f3) {
        long l = this.loop();
        this.setPitch(l, f2);
        this.setPan(l, f3, f);
        return l;
    }

    public float duration() {
        return this.duration;
    }
}
