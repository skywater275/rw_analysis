package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.audio.Music$OnCompletionListener;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.b;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.m;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public abstract class OpenALMusic implements Music {

   private static final int bufferSize = 40960;
   private static final int bufferCount = 3;
   private static final int bytesPerSample = 2;
   private static final byte[] tempBytes = new byte['\ua000'];
   private static final ByteBuffer tempBuffer = BufferUtils.createByteBuffer('\ua000');
   private b renderedSecondsQueue = new b(3);
   private final OpenALAudio audio;
   private IntBuffer buffers;
   private int sourceID = -1;
   private int format;
   private int sampleRate;
   private boolean isLooping;
   private boolean isPlaying;
   private float volume = 1.0F;
   private float pan = 0.0F;
   private float renderedSeconds;
   private float maxSecondsPerBuffer;
   protected final a file;
   protected int bufferOverhead = 0;
   private Music$OnCompletionListener onCompletionListener;


   public OpenALMusic(OpenALAudio var1, a var2) {
      this.audio = var1;
      this.file = var2;
      this.onCompletionListener = null;
   }

   protected void setup(int var1, int var2) {
      this.format = var1 > 1?4355:4353;
      this.sampleRate = var2;
      this.maxSecondsPerBuffer = (float)('\ua000' - this.bufferOverhead) / (float)(2 * var1 * var2);
   }

   public void playWhenReady() {
      if(!this.audio.noDevice) {
         ;
      }
   }

   public void play() {
      if(!this.audio.noDevice) {
         if(this.sourceID == -1) {
            this.sourceID = this.audio.obtainSource(true);
            if(this.sourceID == -1) {
               return;
            }

            this.audio.music.add(this);
            if(this.buffers == null) {
               this.buffers = BufferUtils.createIntBuffer(3);
               AL10.alGenBuffers(this.buffers);
               int var1 = AL10.alGetError();
               if(var1 != 0) {
                  throw new c("Unable to allocate audio buffers. AL Error: " + var1);
               }
            }

            AL10.alSourcei(this.sourceID, 4103, 0);
            this.setPan(this.pan, this.volume);
            boolean var4 = false;

            for(int var2 = 0; var2 < 3; ++var2) {
               int var3 = this.buffers.get(var2);
               if(!this.fill(var3)) {
                  break;
               }

               var4 = true;
               AL10.alSourceQueueBuffers(this.sourceID, var3);
            }

            if(!var4 && this.onCompletionListener != null) {
               this.onCompletionListener.onCompletion(this);
            }

            if(AL10.alGetError() != 0) {
               this.stop();
               return;
            }
         }

         if(!this.isPlaying) {
            AL10.alSourcePlay(this.sourceID);
            this.isPlaying = true;
         }

      }
   }

   public void stop() {
      if(!this.audio.noDevice) {
         if(this.sourceID != -1) {
            this.audio.music.remove(this);
            this.reset();
            this.audio.freeSource(this.sourceID);
            this.sourceID = -1;
            this.renderedSeconds = 0.0F;
            this.renderedSecondsQueue.c();
            this.isPlaying = false;
         }
      }
   }

   public void pause() {
      if(!this.audio.noDevice) {
         if(this.sourceID != -1) {
            AL10.alSourcePause(this.sourceID);
         }

         this.isPlaying = false;
      }
   }

   public boolean isPlaying() {
      return this.audio.noDevice?false:(this.sourceID == -1?false:this.isPlaying);
   }

   public void setLooping(boolean var1) {
      this.isLooping = var1;
   }

   public boolean isLooping() {
      return this.isLooping;
   }

   public void setVolume(float var1) {
      this.volume = var1;
      if(!this.audio.noDevice) {
         if(this.sourceID != -1) {
            AL10.alSourcef(this.sourceID, 4106, var1);
         }

      }
   }

   public float getVolume() {
      return this.volume;
   }

   public void setPan(float var1, float var2) {
      this.volume = var2;
      this.pan = var1;
      if(!this.audio.noDevice) {
         if(this.sourceID != -1) {
            AL10.alSource3f(this.sourceID, 4100, m.b((var1 - 1.0F) * 3.1415927F / 2.0F), 0.0F, m.a((var1 + 1.0F) * 3.1415927F / 2.0F));
            AL10.alSourcef(this.sourceID, 4106, var2);
         }
      }
   }

   public void setPosition(float var1) {
      if(!this.audio.noDevice) {
         if(this.sourceID != -1) {
            boolean var2 = this.isPlaying;
            this.isPlaying = false;
            AL10.alSourceStop(this.sourceID);
            AL10.alSourceUnqueueBuffers(this.sourceID, this.buffers);

            while(this.renderedSecondsQueue.b > 0) {
               this.renderedSeconds = this.renderedSecondsQueue.a();
            }

            if(var1 <= this.renderedSeconds) {
               this.reset();
               this.renderedSeconds = 0.0F;
            }

            while(this.renderedSeconds < var1 - this.maxSecondsPerBuffer && this.read(tempBytes) > 0) {
               this.renderedSeconds += this.maxSecondsPerBuffer;
            }

            this.renderedSecondsQueue.a(this.renderedSeconds);
            boolean var3 = false;

            for(int var4 = 0; var4 < 3; ++var4) {
               int var5 = this.buffers.get(var4);
               if(!this.fill(var5)) {
                  break;
               }

               var3 = true;
               AL10.alSourceQueueBuffers(this.sourceID, var5);
            }

            this.renderedSecondsQueue.a();
            if(!var3) {
               this.stop();
               if(this.onCompletionListener != null) {
                  this.onCompletionListener.onCompletion(this);
               }
            }

            AL10.alSourcef(this.sourceID, 4132, var1 - this.renderedSeconds);
            if(var2) {
               AL10.alSourcePlay(this.sourceID);
               this.isPlaying = true;
            }

         }
      }
   }

   public float getPosition() {
      return this.audio.noDevice?0.0F:(this.sourceID == -1?0.0F:this.renderedSeconds + AL10.alGetSourcef(this.sourceID, 4132));
   }

   public abstract int read(byte[] var1);

   public void reset() {}

   protected void loop() {
      this.reset();
   }

   public int getChannels() {
      return this.format == 4355?2:1;
   }

   public int getRate() {
      return this.sampleRate;
   }

   public void backgroundUpdate() {}

   public void update() {
      if(!this.audio.noDevice) {
         if(this.sourceID != -1) {
            boolean var1 = false;
            int var2 = AL10.alGetSourcei(this.sourceID, 4118);

            while(var2-- > 0) {
               int var3 = AL10.alSourceUnqueueBuffers(this.sourceID);
               if(var3 == '\ua003') {
                  break;
               }

               this.renderedSeconds = this.renderedSecondsQueue.a();
               if(!var1) {
                  if(this.fill(var3)) {
                     AL10.alSourceQueueBuffers(this.sourceID, var3);
                  } else {
                     var1 = true;
                  }
               }
            }

            if(var1 && AL10.alGetSourcei(this.sourceID, 4117) == 0) {
               this.stop();
               if(this.onCompletionListener != null) {
                  this.onCompletionListener.onCompletion(this);
               }
            }

            if(this.isPlaying && AL10.alGetSourcei(this.sourceID, 4112) != 4114) {
               AL10.alSourcePlay(this.sourceID);
            }

         }
      }
   }

   private boolean fill(int var1) {
      tempBuffer.clear();
      int var2 = this.read(tempBytes);
      if(var2 <= 0) {
         if(!this.isLooping) {
            return false;
         }

         this.loop();
         var2 = this.read(tempBytes);
         if(var2 <= 0) {
            return false;
         }

         if(this.renderedSecondsQueue.b > 0) {
            this.renderedSecondsQueue.a(0, 0.0F);
         }
      }

      float var3 = this.renderedSecondsQueue.b > 0?this.renderedSecondsQueue.b():0.0F;
      float var4 = this.maxSecondsPerBuffer * (float)var2 / 40960.0F;
      this.renderedSecondsQueue.b(0, var3 + var4);
      tempBuffer.put(tempBytes, 0, var2).flip();
      AL10.alBufferData(var1, this.format, tempBuffer, this.sampleRate);
      return true;
   }

   public void dispose() {
      this.stop();
      if(!this.audio.noDevice) {
         if(this.buffers != null) {
            AL10.alDeleteBuffers(this.buffers);
            this.buffers = null;
            this.onCompletionListener = null;
         }
      }
   }

   public void setOnCompletionListener(Music$OnCompletionListener var1) {
      this.onCompletionListener = var1;
   }

   public int getSourceId() {
      return this.sourceID;
   }

}
