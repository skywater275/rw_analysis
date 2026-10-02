package android.content.res;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class AssetManager {

   private static final Object b = new Object();
   static AssetManager a = null;
   private final long[] c = new long[2];
   private int d = 1;
   private boolean e = true;


   public final InputStream a(String var1) {
      return this.a(var1, 2);
   }

   public final InputStream a(String var1, int var2) {
      synchronized(this) {
         if(!this.e) {
            throw new RuntimeException("Assetmanager has been closed");
         } else {
            return new FileInputStream("assets/" + var1);
         }
      }
   }

   public final AssetFileDescriptor b(String var1) {
      synchronized(this) {
         if(!this.e) {
            throw new RuntimeException("Assetmanager has been closed");
         }

         ParcelFileDescriptor var3 = this.openAssetFd(var1, this.c);
         if(var3 != null) {
            return new AssetFileDescriptor(var3, this.c[0], this.c[1]);
         }
      }

      throw new FileNotFoundException("Asset file: " + var1);
   }

   public final String[] c(String var1) {
      return new String[0];
   }

   protected void finalize() {
      try {
         this.a();
      } finally {
         super.finalize();
      }

   }

   private final native ParcelFileDescriptor openAssetFd(String var1, long[] var2);

   private final native void destroyAsset(int var1);

   private final native int readAssetChar(int var1);

   private final native int readAsset(int var1, byte[] var2, int var3, int var4);

   private final native long seekAsset(int var1, long var2, int var4);

   private final native long getAssetRemainingLength(int var1);

   private final void a() {}

   private final void a(int var1) {
      --this.d;
      if(this.d == 0) {
         this.a();
      }

   }

   // $FF: synthetic method
   static int a(AssetManager var0, int var1) {
      return var0.readAssetChar(var1);
   }

   // $FF: synthetic method
   static long b(AssetManager var0, int var1) {
      return var0.getAssetRemainingLength(var1);
   }

   // $FF: synthetic method
   static void c(AssetManager var0, int var1) {
      var0.destroyAsset(var1);
   }

   // $FF: synthetic method
   static void d(AssetManager var0, int var1) {
      var0.a(var1);
   }

   // $FF: synthetic method
   static long a(AssetManager var0, int var1, long var2, int var4) {
      return var0.seekAsset(var1, var2, var4);
   }

   // $FF: synthetic method
   static int a(AssetManager var0, int var1, byte[] var2, int var3, int var4) {
      return var0.readAsset(var1, var2, var3, var4);
   }

}
