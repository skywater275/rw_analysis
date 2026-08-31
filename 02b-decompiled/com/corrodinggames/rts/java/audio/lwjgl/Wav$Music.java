package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.s;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALMusic;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$WavInputStream;
import java.io.IOException;

public class Wav$Music extends OpenALMusic {

   private Wav$WavInputStream input;


   public Wav$Music(OpenALAudio var1, a var2) {
      super(var1, var2);
      this.input = new Wav$WavInputStream(var2);
      if(!var1.noDevice) {
         this.setup(this.input.channels, this.input.sampleRate);
      }
   }

   public int read(byte[] var1) {
      if(this.input == null) {
         this.input = new Wav$WavInputStream(this.file);
         this.setup(this.input.channels, this.input.sampleRate);
      }

      try {
         return this.input.read(var1);
      } catch (IOException var3) {
         throw new c("Error reading WAV file: " + this.file, var3);
      }
   }

   public void reset() {
      s.a(this.input);
      this.input = null;
   }
}
