package com.corrodinggames.rts.java.audio;

import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.AudioRecorder;
import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.audio.Sound;
import com.corrodinggames.rts.java.audio.a.a;

public interface Audio {

   AudioDevice newAudioDevice(int var1, boolean var2);

   AudioRecorder newAudioRecorder(int var1, boolean var2);

   Sound newSound(a var1);

   Music newMusic(a var1);
}
