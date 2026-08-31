package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.AudioRecorder;
import com.corrodinggames.rts.java.audio.a.c;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat.Encoding;

public class JavaSoundAudioRecorder implements AudioRecorder {

   private TargetDataLine line;
   private byte[] buffer = new byte[4096];


   public JavaSoundAudioRecorder(int var1, boolean var2) {
      try {
         AudioFormat var3 = new AudioFormat(Encoding.PCM_SIGNED, (float)var1, 16, var2?1:2, var2?2:4, (float)var1, false);
         this.line = AudioSystem.getTargetDataLine(var3);
         this.line.open(var3, this.buffer.length);
         this.line.start();
      } catch (Exception var4) {
         throw new c("Error creating JavaSoundAudioRecorder.", var4);
      }
   }

   public void read(short[] var1, int var2, int var3) {
      if(this.buffer.length < var3 * 2) {
         this.buffer = new byte[var3 * 2];
      }

      int var4 = var3 * 2;

      for(int var5 = 0; var5 != var4; var5 += this.line.read(this.buffer, var5, var4 - var5)) {
         ;
      }

      int var6 = 0;

      for(int var7 = 0; var6 < var3 * 2; ++var7) {
         var1[var2 + var7] = (short)(this.buffer[var6 + 1] << 8 | this.buffer[var6] & 255);
         var6 += 2;
      }

   }

   public void dispose() {
      this.line.close();
   }
}
