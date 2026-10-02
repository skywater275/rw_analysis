package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;

class OpenALAudio$1 implements AudioDevice {

   // $FF: synthetic field
   final boolean val$isMono;
   // $FF: synthetic field
   final OpenALAudio this$0;


   OpenALAudio$1(OpenALAudio var1, boolean var2) {
      this.this$0 = var1;
      this.val$isMono = var2;
   }

   public void writeSamples(float[] var1, int var2, int var3) {}

   public void writeSamples(short[] var1, int var2, int var3) {}

   public void setVolume(float var1) {}

   public boolean isMono() {
      return this.val$isMono;
   }

   public int getLatency() {
      return 0;
   }

   public void dispose() {}
}
