package android.content.res;

import android.content.res.AssetManager;
import java.io.InputStream;

public final class AssetManager$AssetInputStream extends InputStream {

   private int b;
   private long c;
   private long d;
   // $FF: synthetic field
   final AssetManager a;


   public final int read() {
      return AssetManager.a(this.a, this.b);
   }

   public final boolean markSupported() {
      return true;
   }

   public final int available() {
      long var1 = AssetManager.b(this.a, this.b);
      return var1 > 2147483647L?Integer.MAX_VALUE:(int)var1;
   }

   public final void close() {
      AssetManager var1 = this.a;
      synchronized(this.a) {
         if(this.b != 0) {
            AssetManager.c(this.a, this.b);
            this.b = 0;
            AssetManager.d(this.a, this.hashCode());
         }

      }
   }

   public final void mark(int var1) {
      this.d = AssetManager.a(this.a, this.b, 0L, 0);
   }

   public final void reset() {
      AssetManager.a(this.a, this.b, this.d, -1);
   }

   public final int read(byte[] var1) {
      return AssetManager.a(this.a, this.b, var1, 0, var1.length);
   }

   public final int read(byte[] var1, int var2, int var3) {
      return AssetManager.a(this.a, this.b, var1, var2, var3);
   }

   public final long skip(long var1) {
      long var3 = AssetManager.a(this.a, this.b, 0L, 0);
      if(var3 + var1 > this.c) {
         var1 = this.c - var3;
      }

      if(var1 > 0L) {
         AssetManager.a(this.a, this.b, var1, 0);
      }

      return var1;
   }

   protected void finalize() {
      this.close();
   }
}
