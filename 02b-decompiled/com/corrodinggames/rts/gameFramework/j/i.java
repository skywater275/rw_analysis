package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.h;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

public class i extends InputStream {

   LinkedBlockingDeque a;
   boolean b;
   byte[] c;
   // $FF: synthetic field
   final h d;


   public i(h var1) {
      this.d = var1;
      this.a = new LinkedBlockingDeque();
      this.b = true;
      this.c = new byte[1];
   }

   public void a(byte[] var1) {
      ByteBuffer var2 = ByteBuffer.wrap(var1);
      this.a.add(var2);
   }

   public int read() {
      int var1;
      do {
         var1 = this.read(this.c, 0, 1);
      } while(var1 <= 0);

      int var2 = this.c[0] & 255;
      return var2;
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) {
      if(this.d.c) {
         throw new IOException("closed");
      } else {
         int var4 = 0;
         int var5 = var3;
         int var6 = var2;

         while(!this.d.c) {
            ByteBuffer var7;
            try {
               var7 = (ByteBuffer)this.a.take();
            } catch (InterruptedException var9) {
               var9.printStackTrace();
               return var4;
            }

            if(var7 != null) {
               if(this.b) {
                  this.b = false;
                  com.corrodinggames.rts.gameFramework.l.e("First packet from forwarded:" + this.d.b);
               }

               int var8;
               if(var7.remaining() <= var5) {
                  var8 = var7.remaining();
                  var7.get(var1, var6, var8);
               } else {
                  var8 = var5;
                  var7.get(var1, var6, var5);
                  this.a.addFirst(var7);
               }

               var4 += var8;
               var5 -= var8;
               var6 += var8;
               if(var5 < 0) {
                  throw new IOException("bytesNeeded<0:" + var5);
               }

               if(var5 == 0) {
                  return var4;
               }
            }
         }

         throw new IOException("Closed");
      }
   }
}
