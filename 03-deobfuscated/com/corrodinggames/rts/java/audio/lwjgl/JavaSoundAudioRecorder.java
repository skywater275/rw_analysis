/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.AudioRecorder;
import com.corrodinggames.rts.java.audio.backend.c;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;

public class JavaSoundAudioRecorder
implements AudioRecorder {
    private TargetDataLine line;
    private byte[] buffer = new byte[4096];

    public JavaSoundAudioRecorder(int n, boolean bl) {
        try {
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, n, 16, bl ? 1 : 2, bl ? 2 : 4, n, false);
            this.line = AudioSystem.getTargetDataLine(audioFormat);
            this.line.open(audioFormat, this.buffer.length);
            this.line.start();
        }
        catch (Exception exception) {
            throw new c("Error creating JavaSoundAudioRecorder.", exception);
        }
    }

    @Override
    public void read(short[] sArray, int n, int n2) {
        if (this.buffer.length < n2 * 2) {
            this.buffer = new byte[n2 * 2];
        }
        int n3 = n2 * 2;
        for (int i = 0; i != n3; i += this.line.read(this.buffer, i, n3 - i)) {
        }
        int n4 = 0;
        int n5 = 0;
        while (n4 < n2 * 2) {
            sArray[n + n5] = (short)(this.buffer[n4 + 1] << 8 | this.buffer[n4] & 0xFF);
            n4 += 2;
            ++n5;
        }
    }

    @Override
    public void dispose() {
        this.line.close();
    }
}
