/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.backend.AudioSourceBase;
import com.corrodinggames.rts.java.audio.backend.c;
import com.corrodinggames.rts.java.audio.backend.s;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALSound;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$WavInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Wav$Sound
extends OpenALSound {
    public Wav$Sound(OpenALAudio openALAudio, AudioSourceBase a2) {
        super(openALAudio);
        if (openALAudio.noDevice) {
            return;
        }
        Wav$WavInputStream wav$WavInputStream = null;
        try {
            wav$WavInputStream = new Wav$WavInputStream(a2);
            this.setup(s.a((InputStream)wav$WavInputStream, wav$WavInputStream.dataRemaining), wav$WavInputStream.channels, wav$WavInputStream.sampleRate);
        }
        catch (IOException iOException) {
            try {
                throw new c("Error reading WAV file: " + a2, iOException);
            }
            catch (Throwable throwable) {
                s.a(wav$WavInputStream);
                throw throwable;
            }
        }
        s.a(wav$WavInputStream);
    }
}
