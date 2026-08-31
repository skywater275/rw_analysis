package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.s;
import com.corrodinggames.rts.java.audio.lwjgl.OggInputStream;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALMusic;
import com.corrodinggames.rts.java.audio.lwjgl.ThreadedWrappingAudioInputStream;
import java.io.IOException;

public class Ogg$MusicWithThreadedLoader extends OpenALMusic {

   private OggInputStream input;
   private OggInputStream previousInput;
   ThreadedWrappingAudioInputStream backgroundInput;


   public Ogg$MusicWithThreadedLoader(OpenALAudio var1, a var2) {
      super(var1, var2);
      if(!var1.noDevice) {
         this.input = new OggInputStream(var2.a());
         this.setup(this.input.getChannels(), this.input.getSampleRate());
         this.backgroundInput = new ThreadedWrappingAudioInputStream(this.input);
      }
   }

   public int read(byte[] var1) {
      if(this.input == null) {
         this.input = new OggInputStream(this.file.a(), this.previousInput);
         this.setup(this.input.getChannels(), this.input.getSampleRate());
         this.previousInput = null;
         this.backgroundInput = new ThreadedWrappingAudioInputStream(this.input);
      }

      long var2 = br.a();
      int var4 = this.backgroundInput.read(var1);
      double var5 = (double)br.a(var2);
      if(var5 > 0.5D) {
         l.e("ogg read took:" + br.a(var5));
      }

      return var4;
   }

   public void backgroundUpdate() {
      try {
         if(this.input != null && this.backgroundInput != null) {
            this.backgroundInput.backgroundFillBuffer();
         }

      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public void reset() {
      s.a(this.input);
      this.previousInput = null;
      this.input = null;
      this.backgroundInput.close();
   }

   protected void loop() {
      s.a(this.input);
      this.previousInput = this.input;
      this.input = null;
      this.backgroundInput.close();
   }
}
