/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio;

import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.AudioRecorder;
import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.audio.Sound;
import com.corrodinggames.rts.java.audio.a.a;

public interface Audio {
    public AudioDevice newAudioDevice(int var1, boolean var2);

    public AudioRecorder newAudioRecorder(int var1, boolean var2);

    public Sound newSound(a var1);

    public Music newMusic(a var1);
}
