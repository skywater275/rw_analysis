package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.Audio;
import com.corrodinggames.rts.java.audio.AudioDevice;
import com.corrodinggames.rts.java.audio.AudioRecorder;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.d;
import com.corrodinggames.rts.java.audio.a.e;
import com.corrodinggames.rts.java.audio.a.i;
import com.corrodinggames.rts.java.audio.a.m;
import com.corrodinggames.rts.java.audio.a.o;
import com.corrodinggames.rts.java.audio.lwjgl.JavaSoundAudioRecorder;
import com.corrodinggames.rts.java.audio.lwjgl.Ogg$Music;
import com.corrodinggames.rts.java.audio.lwjgl.Ogg$Sound;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio$1;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio$2;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudioDevice;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALMusic;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALSound;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$Music;
import com.corrodinggames.rts.java.audio.lwjgl.Wav$Sound;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Locale;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.OpenALException;

public class OpenALAudio implements Audio {

   private final int deviceBufferSize;
   private final int deviceBufferCount;
   private d idleSources;
   private d allSources;
   private i soundIdToSource;
   private e sourceToSoundId;
   private long nextSoundId;
   private o extensionToSoundClass;
   private o extensionToMusicClass;
   private OpenALSound[] recentSounds;
   private int mostRecetSound;
   ArrayList music;
   boolean noDevice;


   public OpenALAudio() {
      this(16, 9, 512);
   }

   public OpenALAudio(int var1, int var2, int var3) {
      this.nextSoundId = 0L;
      this.extensionToSoundClass = new o();
      this.extensionToMusicClass = new o();
      this.mostRecetSound = -1;
      this.music = new ArrayList();
      this.noDevice = false;
      this.deviceBufferSize = var3;
      this.deviceBufferCount = var2;
      this.registerSound("ogg", Ogg$Sound.class);
      this.registerMusic("ogg", Ogg$Music.class);
      this.registerSound("wav", Wav$Sound.class);
      this.registerMusic("wav", Wav$Music.class);

      try {
         AL.create();
      } catch (LWJGLException var7) {
         this.noDevice = true;
         var7.printStackTrace();
         return;
      } catch (OpenALException var8) {
         this.noDevice = true;
         var8.printStackTrace();
         return;
      } catch (NullPointerException var9) {
         this.noDevice = true;
         var9.printStackTrace();
         return;
      }

      this.allSources = new d(false, var1);

      for(int var4 = 0; var4 < var1; ++var4) {
         int var5 = AL10.alGenSources();
         if(AL10.alGetError() != 0) {
            break;
         }

         this.allSources.a(var5);
      }

      this.idleSources = new d(this.allSources);
      this.soundIdToSource = new i();
      this.sourceToSoundId = new e();
      FloatBuffer var10 = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[]{0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F}).flip();
      AL10.alListener(4111, var10);
      FloatBuffer var11 = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F}).flip();
      AL10.alListener(4102, var11);
      FloatBuffer var6 = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[]{0.0F, 0.0F, 0.0F}).flip();
      AL10.alListener(4100, var6);
      this.recentSounds = new OpenALSound[var1];
   }

   public void registerSound(String var1, Class var2) {
      if(var1 == null) {
         throw new IllegalArgumentException("extension cannot be null.");
      } else if(var2 == null) {
         throw new IllegalArgumentException("soundClass cannot be null.");
      } else {
         this.extensionToSoundClass.a(var1, var2);
      }
   }

   public void registerMusic(String var1, Class var2) {
      if(var1 == null) {
         throw new IllegalArgumentException("extension cannot be null.");
      } else if(var2 == null) {
         throw new IllegalArgumentException("musicClass cannot be null.");
      } else {
         this.extensionToMusicClass.a(var1, var2);
      }
   }

   public OpenALSound newSound(a var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("file cannot be null.");
      } else {
         Class var2 = (Class)this.extensionToSoundClass.a(var1.b().toLowerCase(Locale.ROOT));
         if(var2 == null) {
            throw new c("Unknown file extension for sound: " + var1);
         } else {
            try {
               return (OpenALSound)var2.getConstructor(new Class[]{OpenALAudio.class, a.class}).newInstance(new Object[]{this, var1});
            } catch (Exception var4) {
               throw new c("Error creating sound " + var2.getName() + " for file: " + var1, var4);
            }
         }
      }
   }

   public OpenALMusic newMusic(a var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("file cannot be null.");
      } else {
         Class var2 = (Class)this.extensionToMusicClass.a(var1.b().toLowerCase(Locale.ROOT));
         if(var2 == null) {
            throw new c("Unknown file extension for music: " + var1);
         } else {
            try {
               return (OpenALMusic)var2.getConstructor(new Class[]{OpenALAudio.class, a.class}).newInstance(new Object[]{this, var1});
            } catch (Exception var4) {
               throw new c("Error creating music " + var2.getName() + " for file: " + var1, var4);
            }
         }
      }
   }

   int obtainSource(boolean var1) {
      if(this.noDevice) {
         return 0;
      } else {
         int var2 = 0;

         for(int var3 = this.idleSources.b; var2 < var3; ++var2) {
            int var4 = this.idleSources.b(var2);
            int var5 = AL10.alGetSourcei(var4, 4112);
            if(var5 != 4114 && var5 != 4115) {
               if(var1) {
                  this.idleSources.c(var2);
               } else {
                  long var6;
                  if(this.sourceToSoundId.e(var4)) {
                     var6 = ((Long)this.sourceToSoundId.a(var4)).longValue();
                     this.sourceToSoundId.b(var4);
                     this.soundIdToSource.b(var6);
                  }

                  var6 = (long)(this.nextSoundId++);
                  this.sourceToSoundId.a(var4, Long.valueOf(var6));
                  this.soundIdToSource.a(var6, Integer.valueOf(var4));
               }

               AL10.alSourceStop(var4);
               AL10.alSourcei(var4, 4105, 0);
               AL10.alSourcef(var4, 4106, 1.0F);
               AL10.alSourcef(var4, 4099, 1.0F);
               AL10.alSource3f(var4, 4100, 0.0F, 0.0F, 1.0F);
               return var4;
            }
         }

         return -1;
      }
   }

   void freeSource(int var1) {
      if(!this.noDevice) {
         AL10.alSourceStop(var1);
         AL10.alSourcei(var1, 4105, 0);
         if(this.sourceToSoundId.e(var1)) {
            long var2 = ((Long)this.sourceToSoundId.b(var1)).longValue();
            this.soundIdToSource.b(var2);
         }

         this.idleSources.a(var1);
      }
   }

   void freeBuffer(int var1) {
      if(!this.noDevice) {
         int var2 = 0;

         for(int var3 = this.idleSources.b; var2 < var3; ++var2) {
            int var4 = this.idleSources.b(var2);
            if(AL10.alGetSourcei(var4, 4105) == var1) {
               if(this.sourceToSoundId.e(var4)) {
                  long var5 = ((Long)this.sourceToSoundId.b(var4)).longValue();
                  this.soundIdToSource.b(var5);
               }

               AL10.alSourceStop(var4);
               AL10.alSourcei(var4, 4105, 0);
            }
         }

      }
   }

   void stopSourcesWithBuffer(int var1) {
      if(!this.noDevice) {
         int var2 = 0;

         for(int var3 = this.idleSources.b; var2 < var3; ++var2) {
            int var4 = this.idleSources.b(var2);
            if(AL10.alGetSourcei(var4, 4105) == var1) {
               if(this.sourceToSoundId.e(var4)) {
                  long var5 = ((Long)this.sourceToSoundId.b(var4)).longValue();
                  this.soundIdToSource.b(var5);
               }

               AL10.alSourceStop(var4);
            }
         }

      }
   }

   void pauseSourcesWithBuffer(int var1) {
      if(!this.noDevice) {
         int var2 = 0;

         for(int var3 = this.idleSources.b; var2 < var3; ++var2) {
            int var4 = this.idleSources.b(var2);
            if(AL10.alGetSourcei(var4, 4105) == var1) {
               AL10.alSourcePause(var4);
            }
         }

      }
   }

   void resumeSourcesWithBuffer(int var1) {
      if(!this.noDevice) {
         int var2 = 0;

         for(int var3 = this.idleSources.b; var2 < var3; ++var2) {
            int var4 = this.idleSources.b(var2);
            if(AL10.alGetSourcei(var4, 4105) == var1 && AL10.alGetSourcei(var4, 4112) == 4115) {
               AL10.alSourcePlay(var4);
            }
         }

      }
   }

   public void backgroundUpdate() {
      if(!this.noDevice) {
         for(int var1 = 0; var1 < this.music.size(); ++var1) {
            ((OpenALMusic)this.music.get(var1)).backgroundUpdate();
         }

      }
   }

   public boolean hasDevice() {
      return this.noDevice;
   }

   public void update() {
      if(!this.noDevice) {
         for(int var1 = 0; var1 < this.music.size(); ++var1) {
            ((OpenALMusic)this.music.get(var1)).update();
         }

      }
   }

   public long getSoundId(int var1) {
      return !this.sourceToSoundId.e(var1)?-1L:((Long)this.sourceToSoundId.a(var1)).longValue();
   }

   public void stopSound(long var1) {
      if(this.soundIdToSource.d(var1)) {
         int var3 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         AL10.alSourceStop(var3);
      }
   }

   public void pauseSound(long var1) {
      if(this.soundIdToSource.d(var1)) {
         int var3 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         AL10.alSourcePause(var3);
      }
   }

   public void resumeSound(long var1) {
      if(this.soundIdToSource.d(var1)) {
         int var3 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         if(AL10.alGetSourcei(var3, 4112) == 4115) {
            AL10.alSourcePlay(var3);
         }

      }
   }

   public void setSoundGain(long var1, float var3) {
      if(this.soundIdToSource.d(var1)) {
         int var4 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         AL10.alSourcef(var4, 4106, var3);
      }
   }

   public void setSoundLooping(long var1, boolean var3) {
      if(this.soundIdToSource.d(var1)) {
         int var4 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         AL10.alSourcei(var4, 4103, var3?1:0);
      }
   }

   public void setSoundPitch(long var1, float var3) {
      if(this.soundIdToSource.d(var1)) {
         int var4 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         AL10.alSourcef(var4, 4099, var3);
      }
   }

   public void setSoundPan(long var1, float var3, float var4) {
      if(this.soundIdToSource.d(var1)) {
         int var5 = ((Integer)this.soundIdToSource.a(var1)).intValue();
         AL10.alSource3f(var5, 4100, m.b((var3 - 1.0F) * 3.1415927F / 2.0F), 0.0F, m.a((var3 + 1.0F) * 3.1415927F / 2.0F));
         AL10.alSourcef(var5, 4106, var4);
      }
   }

   public void dispose() {
      if(!this.noDevice) {
         int var1 = 0;

         for(int var2 = this.allSources.b; var1 < var2; ++var1) {
            int var3 = this.allSources.b(var1);
            int var4 = AL10.alGetSourcei(var3, 4112);
            if(var4 != 4116) {
               AL10.alSourceStop(var3);
            }

            AL10.alDeleteSources(var3);
         }

         this.sourceToSoundId.a();
         this.soundIdToSource.a();
         AL.destroy();

         while(AL.isCreated()) {
            try {
               Thread.sleep(10L);
            } catch (InterruptedException var5) {
               ;
            }
         }

      }
   }

   public AudioDevice newAudioDevice(int var1, boolean var2) {
      return (AudioDevice)(this.noDevice?new OpenALAudio$1(this, var2):new OpenALAudioDevice(this, var1, var2, this.deviceBufferSize, this.deviceBufferCount));
   }

   public AudioRecorder newAudioRecorder(int var1, boolean var2) {
      return (AudioRecorder)(this.noDevice?new OpenALAudio$2(this):new JavaSoundAudioRecorder(var1, var2));
   }

   protected void retain(OpenALSound var1, boolean var2) {
      ++this.mostRecetSound;
      this.mostRecetSound %= this.recentSounds.length;
      if(var2 && this.recentSounds[this.mostRecetSound] != null) {
         this.recentSounds[this.mostRecetSound].stop();
      }

      this.recentSounds[this.mostRecetSound] = var1;
   }

   public void forget(OpenALSound var1) {
      for(int var2 = 0; var2 < this.recentSounds.length; ++var2) {
         if(this.recentSounds[var2] == var1) {
            this.recentSounds[var2] = null;
         }
      }

   }
}
