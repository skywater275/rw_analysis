package a.a;

import a.a.h;
import java.io.InputStream;

class o extends InputStream {

   protected h a;
   protected byte[] b;
   protected int c;
   protected int d;


   public o(h var1) {
      if(var1 == null) {
         throw new NullPointerException("sock");
      } else {
         this.a = var1;
         this.b = new byte[this.a.getReceiveBufferSize()];
         this.c = this.d = 0;
      }
   }

   public synchronized int read() {
      return this.a() < 0?-1:this.b[this.c++] & 255;
   }

   public synchronized int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public synchronized int read(byte[] var1, int var2, int var3) {
      if(var1 == null) {
         throw new NullPointerException();
      } else if(var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         if(this.a() < 0) {
            return -1;
         } else {
            int var4 = Math.min(this.available(), var3);
            System.arraycopy(this.b, this.c, var1, var2, var4);
            this.c += var4;
            return var4;
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized int available() {
      return this.d - this.c;
   }

   public boolean markSupported() {
      return false;
   }

   public void close() {
      this.a.shutdownInput();
   }

   private int a() {
      if(this.available() == 0) {
         this.d = this.a.b(this.b, 0, this.b.length);
         this.c = 0;
      }

      return this.d;
   }
}
