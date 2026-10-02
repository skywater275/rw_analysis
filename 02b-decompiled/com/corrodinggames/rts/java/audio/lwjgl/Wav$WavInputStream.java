package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.a.s;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;

public class Wav$WavInputStream extends FilterInputStream {

   public int channels;
   public int sampleRate;
   public int dataRemaining;


   public Wav$WavInputStream(a var1) {
      super(var1.a());

      try {
         if(this.read() == 82 && this.read() == 73 && this.read() == 70 && this.read() == 70) {
            this.skipFully(4);
            if(this.read() == 87 && this.read() == 65 && this.read() == 86 && this.read() == 69) {
               int var2 = this.seekToChunk('f', 'm', 't', ' ');
               int var3 = this.read() & 255 | (this.read() & 255) << 8;
               if(var3 != 1) {
                  String var6;
                  switch(var3) {
                  case 2:
                     var6 = "ADPCM";
                     break;
                  case 3:
                     var6 = "IEEE float";
                     break;
                  case 6:
                     var6 = "8-bit ITU-T G.711 A-law";
                     break;
                  case 7:
                     var6 = "8-bit ITU-T G.711 u-law";
                     break;
                  case '\ufffe':
                     var6 = "Extensible";
                     break;
                  default:
                     var6 = "Unknown";
                  }

                  throw new c("WAV files must be PCM, unsupported format: " + var6 + " (" + var3 + ")");
               } else {
                  this.channels = this.read() & 255 | (this.read() & 255) << 8;
                  if(this.channels != 1 && this.channels != 2) {
                     throw new c("WAV files must have 1 or 2 channels: " + this.channels);
                  } else {
                     this.sampleRate = this.read() & 255 | (this.read() & 255) << 8 | (this.read() & 255) << 16 | (this.read() & 255) << 24;
                     this.skipFully(6);
                     int var4 = this.read() & 255 | (this.read() & 255) << 8;
                     if(var4 != 16) {
                        throw new c("WAV files must have 16 bits per sample: " + var4);
                     } else {
                        this.skipFully(var2 - 16);
                        this.dataRemaining = this.seekToChunk('d', 'a', 't', 'a');
                     }
                  }
               }
            } else {
               throw new c("Invalid wave file header: " + var1);
            }
         } else {
            throw new c("RIFF header not found: " + var1);
         }
      } catch (Throwable var5) {
         s.a(this);
         throw new c("Error reading WAV file: " + var1, var5);
      }
   }

   private int seekToChunk(char var1, char var2, char var3, char var4) {
      while(true) {
         boolean var5 = this.read() == var1;
         var5 &= this.read() == var2;
         var5 &= this.read() == var3;
         var5 &= this.read() == var4;
         int var6 = this.read() & 255 | (this.read() & 255) << 8 | (this.read() & 255) << 16 | (this.read() & 255) << 24;
         if(var6 == -1) {
            throw new IOException("Chunk not found: " + var1 + var2 + var3 + var4);
         }

         if(var5) {
            return var6;
         }

         this.skipFully(var6);
      }
   }

   private void skipFully(int var1) {
      while(var1 > 0) {
         long var2 = this.in.skip((long)var1);
         if(var2 <= 0L) {
            throw new EOFException("Unable to skip.");
         }

         var1 = (int)((long)var1 - var2);
      }

   }

   public int read(byte[] var1) {
      if(this.dataRemaining == 0) {
         return -1;
      } else {
         int var2 = Math.min(super.read(var1), this.dataRemaining);
         if(var2 == -1) {
            return -1;
         } else {
            this.dataRemaining -= var2;
            return var2;
         }
      }
   }
}
