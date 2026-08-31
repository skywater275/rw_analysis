package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.m;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public class OpenALAudioDevice implements AudioDevice {

   private static final int bytesPerSample = 2;
   private final OpenALAudio audio;
   private final int channels;
   private IntBuffer buffers;
   private int sourceID = -1;
   private int format;
   private int sampleRate;
   private boolean isPlaying;
   private float volume = 1.0F;
   private float renderedSeconds;
   private float secondsPerBuffer;
   private byte[] bytes;
   private final int bufferSize;
   private final int bufferCount;
   private final ByteBuffer tempBuffer;


   public OpenALAudioDevice(OpenALAudio var1, int var2, boolean var3, int var4, int var5) {
      this.audio = var1;
      this.channels = var3?1:2;
      this.bufferSize = var4;
      this.bufferCount = var5;
      this.format = this.channels > 1?4355:4353;
      this.sampleRate = var2;
      this.secondsPerBuffer = (float)var4 / 2.0F / (float)this.channels / (float)var2;
      this.tempBuffer = BufferUtils.createByteBuffer(var4);
   }

   public void writeSamples(short[] var1, int var2, int var3) {
      if(this.bytes == null || this.bytes.length < var3 * 2) {
         this.bytes = new byte[var3 * 2];
      }

      int var4 = Math.min(var2 + var3, var1.length);
      int var5 = var2;

      for(int var6 = 0; var5 < var4; ++var5) {
         short var7 = var1[var5];
         this.bytes[var6++] = (byte)(var7 & 255);
         this.bytes[var6++] = (byte)(var7 >> 8 & 255);
      }

      this.writeSamples(this.bytes, 0, var3 * 2);
   }

   public void writeSamples(float[] var1, int var2, int var3) {
      if(this.bytes == null || this.bytes.length < var3 * 2) {
         this.bytes = new byte[var3 * 2];
      }

      int var4 = Math.min(var2 + var3, var1.length);
      int var5 = var2;

      for(int var6 = 0; var5 < var4; ++var5) {
         float var7 = var1[var5];
         var7 = m.a(var7, -1.0F, 1.0F);
         int var8 = (int)(var7 * 32767.0F);
         this.bytes[var6++] = (byte)(var8 & 255);
         this.bytes[var6++] = (byte)(var8 >> 8 & 255);
      }

      this.writeSamples(this.bytes, 0, var3 * 2);
   }

   public void writeSamples(byte[] var1, int var2, int var3) {
      if(var3 < 0) {
         throw new IllegalArgumentException("length cannot be < 0.");
      } else {
         int var4;
         if(this.sourceID == -1) {
            this.sourceID = this.audio.obtainSource(true);
            if(this.sourceID == -1) {
               return;
            }

            if(this.buffers == null) {
               this.buffers = BufferUtils.createIntBuffer(this.bufferCount);
               AL10.alGenBuffers(this.buffers);
               if(AL10.alGetError() != 0) {
                  throw new c("Unabe to allocate audio buffers.");
               }
            }

            AL10.alSourcei(this.sourceID, 4103, 0);
            AL10.alSourcef(this.sourceID, 4106, this.volume);
            var4 = 0;

            int var5;
            int var6;
            for(var5 = 0; var5 < this.bufferCount; ++var5) {
               var6 = this.buffers.get(var5);
               int var7 = Math.min(this.bufferSize, var3);
               this.tempBuffer.clear();
               this.tempBuffer.put(var1, var2, var7).flip();
               AL10.alBufferData(var6, this.format, this.tempBuffer, this.sampleRate);
               AL10.alSourceQueueBuffers(this.sourceID, var6);
               var3 -= var7;
               var2 += var7;
               ++var4;
            }

            this.tempBuffer.clear().flip();

            for(var5 = var4; var5 < this.bufferCount; ++var5) {
               var6 = this.buffers.get(var5);
               AL10.alBufferData(var6, this.format, this.tempBuffer, this.sampleRate);
               AL10.alSourceQueueBuffers(this.sourceID, var6);
            }

            AL10.alSourcePlay(this.sourceID);
            this.isPlaying = true;
         }

         while(var3 > 0) {
            var4 = this.fillBuffer(var1, var2, var3);
            var3 -= var4;
            var2 += var4;
         }

      }
   }

   private int fillBuffer(byte[] var1, int var2, int var3) {
      int var4 = Math.min(this.bufferSize, var3);

      while(true) {
         int var5 = AL10.alGetSourcei(this.sourceID, 4118);
         if(var5-- > 0) {
            int var6 = AL10.alSourceUnqueueBuffers(this.sourceID);
            if(var6 != '\ua003') {
               this.renderedSeconds += this.secondsPerBuffer;
               this.tempBuffer.clear();
               this.tempBuffer.put(var1, var2, var4).flip();
               AL10.alBufferData(var6, this.format, this.tempBuffer, this.sampleRate);
               AL10.alSourceQueueBuffers(this.sourceID, var6);
               if(!this.isPlaying || AL10.alGetSourcei(this.sourceID, 4112) != 4114) {
                  AL10.alSourcePlay(this.sourceID);
                  this.isPlaying = true;
               }

               return var4;
            }
         }

         try {
            Thread.sleep((long)(1000.0F * this.secondsPerBuffer));
         } catch (InterruptedException var7) {
            ;
         }
      }
   }

   public void stop() {
      if(this.sourceID != -1) {
         this.audio.freeSource(this.sourceID);
         this.sourceID = -1;
         this.renderedSeconds = 0.0F;
         this.isPlaying = false;
      }
   }

   public boolean isPlaying() {
      return this.sourceID == -1?false:this.isPlaying;
   }

   public void setVolume(float var1) {
      this.volume = var1;
      if(this.sourceID != -1) {
         AL10.alSourcef(this.sourceID, 4106, var1);
      }

   }

   public float getPosition() {
      return this.sourceID == -1?0.0F:this.renderedSeconds + AL10.alGetSourcef(this.sourceID, 4132);
   }

   public void setPosition(float var1) {
      this.renderedSeconds = var1;
   }

   public int getChannels() {
      return this.format == 4355?2:1;
   }

   public int getRate() {
      return this.sampleRate;
   }

   public void dispose() {
      if(this.buffers != null) {
         if(this.sourceID != -1) {
            this.audio.freeSource(this.sourceID);
            this.sourceID = -1;
         }

         AL10.alDeleteBuffers(this.buffers);
         this.buffers = null;
      }
   }

   public boolean isMono() {
      return this.channels == 1;
   }

   public int getLatency() {
      return (int)(this.secondsPerBuffer * (float)this.bufferCount * 1000.0F);
   }
}
