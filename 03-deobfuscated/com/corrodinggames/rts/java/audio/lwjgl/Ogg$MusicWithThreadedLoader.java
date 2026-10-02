/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.java.audio.backend.AudioSourceBase;
import com.corrodinggames.rts.java.audio.backend.s;
import com.corrodinggames.rts.java.audio.lwjgl.OggInputStream;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALMusic;
import com.corrodinggames.rts.java.audio.lwjgl.ThreadedWrappingAudioInputStream;
import java.io.IOException;

public class Ogg$MusicWithThreadedLoader
extends OpenALMusic {
    private OggInputStream input;
    private OggInputStream previousInput;
    ThreadedWrappingAudioInputStream backgroundInput;

    public Ogg$MusicWithThreadedLoader(OpenALAudio openALAudio, AudioSourceBase a2) {
        super(openALAudio, a2);
        if (openALAudio.noDevice) {
            return;
        }
        this.input = new OggInputStream(a2.a());
        this.setup(this.input.getChannels(), this.input.getSampleRate());
        this.backgroundInput = new ThreadedWrappingAudioInputStream(this.input);
    }

    @Override
    public int read(byte[] byArray) {
        if (this.input == null) {
            this.input = new OggInputStream(this.file.a(), this.previousInput);
            this.setup(this.input.getChannels(), this.input.getSampleRate());
            this.previousInput = null;
            this.backgroundInput = new ThreadedWrappingAudioInputStream(this.input);
        }
        long l2 = ExtraManager.a();
        int n2 = this.backgroundInput.read(byArray);
        double d2 = ExtraManager.a(l2);
        if (d2 > 0.5) {
            GlobalState.e("ogg read took:" + ExtraManager.a(d2));
        }
        return n2;
    }

    @Override
    public void backgroundUpdate() {
        try {
            if (this.input != null && this.backgroundInput != null) {
                this.backgroundInput.backgroundFillBuffer();
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    @Override
    public void reset() {
        s.a(this.input);
        this.previousInput = null;
        this.input = null;
        this.backgroundInput.close();
    }

    @Override
    protected void loop() {
        s.a(this.input);
        this.previousInput = this.input;
        this.input = null;
        this.backgroundInput.close();
    }
}
