package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.Sound;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.lwjgl.openal.AL10;

public class OpenALSound implements Sound {

   private int bufferID = -1;
   private final OpenALAudio audio;
   private float duration;
   private int bytesUsed;


   public OpenALSound(OpenALAudio var1) {
      this.audio = var1;
   }

   void setup(byte[] var1, int var2, int var3) {
      int var4 = var1.length - var1.length % (var2 > 1?4:2);
      int var5 = var4 / (2 * var2);
      this.duration = (float)var5 / (float)var3;
      ByteBuffer var6 = ByteBuffer.allocateDirect(var4);
      var6.order(ByteOrder.nativeOrder());
      var6.put(var1, 0, var4);
      var6.flip();
      this.bytesUsed = var4;
      if(this.bufferID == -1) {
         this.bufferID = AL10.alGenBuffers();
         AL10.alBufferData(this.bufferID, var2 > 1?4355:4353, var6.asShortBuffer(), var3);
      }

   }

   public int getBytesUsed() {
      return this.bytesUsed;
   }

   public long play() {
      return this.play(1.0F);
   }

   public long play(float var1) {
      if(this.audio.noDevice) {
         return 0L;
      } else {
         int var2 = this.audio.obtainSource(false);
         if(var2 == -1) {
            return -1L;
         } else {
            this.audio.retain(this, false);
            if(var2 == -1) {
               return -1L;
            } else {
               long var3 = this.audio.getSoundId(var2);
               AL10.alSourcei(var2, 4105, this.bufferID);
               AL10.alSourcei(var2, 4103, 0);
               AL10.alSourcef(var2, 4106, var1);
               AL10.alSourcePlay(var2);
               return var3;
            }
         }
      }
   }

   public long loop() {
      return this.loop(1.0F);
   }

   public long loop(float var1) {
      if(this.audio.noDevice) {
         return 0L;
      } else {
         int var2 = this.audio.obtainSource(false);
         if(var2 == -1) {
            return -1L;
         } else {
            long var3 = this.audio.getSoundId(var2);
            AL10.alSourcei(var2, 4105, this.bufferID);
            AL10.alSourcei(var2, 4103, 1);
            AL10.alSourcef(var2, 4106, var1);
            AL10.alSourcePlay(var2);
            return var3;
         }
      }
   }

   public void stop() {
      if(!this.audio.noDevice) {
         this.audio.stopSourcesWithBuffer(this.bufferID);
      }
   }

   public void dispose() {
      if(!this.audio.noDevice) {
         if(this.bufferID != -1) {
            this.audio.freeBuffer(this.bufferID);
            AL10.alDeleteBuffers(this.bufferID);
            this.bufferID = -1;
            this.audio.forget(this);
         }
      }
   }

   public void stop(long var1) {
      if(!this.audio.noDevice) {
         this.audio.stopSound(var1);
      }
   }

   public void pause() {
      if(!this.audio.noDevice) {
         this.audio.pauseSourcesWithBuffer(this.bufferID);
      }
   }

   public void pause(long var1) {
      if(!this.audio.noDevice) {
         this.audio.pauseSound(var1);
      }
   }

   public void resume() {
      if(!this.audio.noDevice) {
         this.audio.resumeSourcesWithBuffer(this.bufferID);
      }
   }

   public void resume(long var1) {
      if(!this.audio.noDevice) {
         this.audio.resumeSound(var1);
      }
   }

   public void setPitch(long var1, float var3) {
      if(!this.audio.noDevice) {
         this.audio.setSoundPitch(var1, var3);
      }
   }

   public void setVolume(long var1, float var3) {
      if(!this.audio.noDevice) {
         this.audio.setSoundGain(var1, var3);
      }
   }

   public void setLooping(long var1, boolean var3) {
      if(!this.audio.noDevice) {
         this.audio.setSoundLooping(var1, var3);
      }
   }

   public void setPan(long var1, float var3, float var4) {
      if(!this.audio.noDevice) {
         this.audio.setSoundPan(var1, var3, var4);
      }
   }

   public long play(float var1, float var2, float var3) {
      long var4 = this.play();
      this.setPitch(var4, var2);
      this.setPan(var4, var3, var1);
      return var4;
   }

   public long loop(float var1, float var2, float var3) {
      long var4 = this.loop();
      this.setPitch(var4, var2);
      this.setPan(var4, var3, var1);
      return var4;
   }

   public float duration() {
      return this.duration;
   }
}
