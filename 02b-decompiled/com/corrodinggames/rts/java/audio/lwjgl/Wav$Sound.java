package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.s;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALSound;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$WavInputStream;
import java.io.IOException;

public class Wav$Sound extends OpenALSound {

   public Wav$Sound(OpenALAudio var1, a var2) {
      super(var1);
      if(!var1.noDevice) {
         Wav$WavInputStream var3 = null;

         try {
            var3 = new Wav$WavInputStream(var2);
            this.setup(s.a(var3, var3.dataRemaining), var3.channels, var3.sampleRate);
         } catch (IOException var8) {
            throw new c("Error reading WAV file: " + var2, var8);
         } finally {
            s.a(var3);
         }

      }
   }
}
