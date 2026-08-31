/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.backend.AudioSourceBase;
import com.corrodinggames.rts.java.audio.backend.s;
import com.corrodinggames.rts.java.audio.lwjgl.OggInputStream;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALSound;
import java.io.ByteArrayOutputStream;

public class Ogg$Sound
extends OpenALSound {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Ogg$Sound(OpenALAudio openALAudio, AudioSourceBase a2) {
        super(openALAudio);
        if (openALAudio.noDevice) {
            return;
        }
        OggInputStream oggInputStream = null;
        try {
            int n;
            oggInputStream = new OggInputStream(a2.a());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            byte[] byArray = new byte[2048];
            while (!oggInputStream.atEnd() && (n = oggInputStream.read(byArray)) != -1) {
                byteArrayOutputStream.write(byArray, 0, n);
            }
            this.setup(byteArrayOutputStream.toByteArray(), oggInputStream.getChannels(), oggInputStream.getSampleRate());
        }
        catch (Throwable throwable) {
            s.a(oggInputStream);
            throw throwable;
        }
        s.a(oggInputStream);
    }
}
