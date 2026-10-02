package com.corrodinggames.rts.java.audio.lwjgl;

import com.corrodinggames.rts.gameFramework.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadedWrappingAudioInputStream extends InputStream {

   InputStream source;
   PipedInputStream bufferInputStream;
   PipedOutputStream bufferOutputStream;
   byte[] transferBuffer = new byte[1024];
   boolean isClosed = false;
   boolean sourceEnded = false;
   final int bufferSize = '\uea60';
   int totalBytesRead;


   public ThreadedWrappingAudioInputStream(InputStream var1) {
      this.source = var1;

      try {
         this.bufferOutputStream = new PipedOutputStream();
         this.bufferInputStream = new PipedInputStream(this.bufferOutputStream, '\uea60');
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public void backgroundFillBuffer() {
      l.e("at backgroundFillBuffer with: " + this.bufferInputStream.available());
      if(!this.sourceEnded) {
         char var1 = '\uea5f';

         while(!this.isClosed) {
            int var2 = var1 - this.bufferInputStream.available();
            if(var2 < this.transferBuffer.length) {
               return;
            }

            int var3 = this.source.read(this.transferBuffer);
            if(var3 == -1) {
               this.sourceEnded = true;
               this.bufferOutputStream.close();
               return;
            }

            this.totalBytesRead += var3;
            this.bufferOutputStream.write(this.transferBuffer, 0, var3);
         }

      }
   }

   public int read() {
      try {
         if(this.bufferInputStream.available() == 0 && !this.sourceEnded) {
            l.e("Exhausted stream");
         }

         return this.bufferInputStream.read();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public int read(byte[] var1, int var2, int var3) {
      try {
         if(this.bufferInputStream.available() == 0 && !this.sourceEnded) {
            l.e("Exhausted stream");
         }

         return this.bufferInputStream.read(var1, var2, var3);
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      }
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public void close() {
      this.isClosed = true;
   }
}
