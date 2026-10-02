package a.a;

import a.a.h;
import java.io.OutputStream;

class q extends OutputStream {

   protected h a;
   protected byte[] b;
   protected int c;


   public q(h var1) {
      if(var1 == null) {
         throw new NullPointerException("sock");
      } else {
         this.a = var1;
         this.b = new byte[this.a.getSendBufferSize()];
         this.c = 0;
      }
   }

   public synchronized void write(int var1) {
      if(this.c >= this.b.length) {
         this.flush();
      }

      this.b[this.c++] = (byte)(var1 & 255);
   }

   public synchronized void write(byte[] var1) {
      this.write(var1, 0, var1.length);
   }

   public synchronized void write(byte[] var1, int var2, int var3) {
      if(var1 == null) {
         throw new NullPointerException();
      } else if(var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         int var4;
         for(int var5 = 0; var5 < var3; var5 += var4) {
            var4 = Math.min(this.b.length, var3 - var5);
            if(var4 > this.b.length - this.c) {
               this.flush();
            }

            System.arraycopy(var1, var2 + var5, this.b, this.c, var4);
            this.c += var4;
         }

      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized void flush() {
      if(this.c > 0) {
         this.a.a(this.b, 0, this.c);
         this.c = 0;
      }

   }

   public synchronized void close() {
      this.flush();
      this.a.shutdownOutput();
   }
}
