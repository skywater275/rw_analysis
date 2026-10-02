package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.s;
import com.corrodinggames.rts.java.audio.lwjgl.OggInputStream;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALMusic;

public class Ogg$Music extends OpenALMusic {

   private OggInputStream input;
   private OggInputStream previousInput;


   public Ogg$Music(OpenALAudio var1, a var2) {
      super(var1, var2);
      if(!var1.noDevice) {
         this.input = new OggInputStream(var2.a());
         this.setup(this.input.getChannels(), this.input.getSampleRate());
      }
   }

   public int read(byte[] var1) {
      if(this.input == null) {
         this.input = new OggInputStream(this.file.a(), this.previousInput);
         this.setup(this.input.getChannels(), this.input.getSampleRate());
         this.previousInput = null;
      }

      int var2 = this.input.read(var1);
      return var2;
   }

   public void reset() {
      s.a(this.input);
      this.previousInput = null;
      this.input = null;
   }

   protected void loop() {
      s.a(this.input);
      this.previousInput = this.input;
      this.input = null;
   }
}
