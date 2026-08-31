package android.graphics;

import android.graphics.Bitmap$1;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import java.io.OutputStream;

public final class Bitmap implements Parcelable {

   public final int a;
   private final boolean d;
   private boolean e;
   private int f;
   private int g;
   private boolean h;
   int b;
   private static volatile int i = -1;
   public static final Creator c = new Bitmap$1();


   private void a(String var1) {
      if(this.h) {
         throw new IllegalStateException(var1);
      }
   }

   private static void b(int var0, int var1) {
      if(var0 < 0) {
         throw new IllegalArgumentException("x must be >= 0");
      } else if(var1 < 0) {
         throw new IllegalArgumentException("y must be >= 0");
      }
   }

   public Bitmap a(Bitmap$Config var1, boolean var2) {
      this.a("Can\'t copy a recycled bitmap");
      Bitmap var3 = nativeCopy(this.a, var1.e, var2);
      if(var3 != null) {
         var3.e = this.e;
         var3.b = this.b;
      }

      return var3;
   }

   public static Bitmap a(int var0, int var1, Bitmap$Config var2) {
      return a(var0, var1, var2, true);
   }

   private static Bitmap a(int var0, int var1, Bitmap$Config var2, boolean var3) {
      return a((DisplayMetrics)null, var0, var1, var2, var3);
   }

   private static Bitmap a(DisplayMetrics var0, int var1, int var2, Bitmap$Config var3, boolean var4) {
      if(var1 > 0 && var2 > 0) {
         Bitmap var5 = nativeCreate((int[])null, 0, var1, var1, var2, var3.e, true);
         if(var0 != null) {
            var5.b = var0.densityDpi;
         }

         if(var3 == Bitmap$Config.d && !var4) {
            nativeErase(var5.a, -16777216);
            nativeSetHasAlpha(var5.a, var4);
         }

         return var5;
      } else {
         throw new IllegalArgumentException("width and height must be > 0");
      }
   }

   public boolean a(Bitmap$CompressFormat var1, int var2, OutputStream var3) {
      this.a("Can\'t compress a recycled bitmap");
      if(var3 == null) {
         throw new NullPointerException();
      } else if(var2 >= 0 && var2 <= 100) {
         return nativeCompress(this.a, var1.d, var2, var3, new byte[4096]);
      } else {
         throw new IllegalArgumentException("quality must be 0..100");
      }
   }

   public final boolean a() {
      return this.d;
   }

   public final int b() {
      return this.f;
   }

   public final int c() {
      return this.g;
   }

   public final Bitmap$Config d() {
      return Bitmap$Config.a(nativeConfig(this.a));
   }

   public void a(boolean var1) {
      nativeSetHasAlpha(this.a, var1);
   }

   public void a(int var1) {
      this.a("Can\'t erase a recycled bitmap");
      if(!this.a()) {
         throw new IllegalStateException("cannot erase immutable bitmaps");
      } else {
         nativeErase(this.a, var1);
      }
   }

   public int a(int var1, int var2) {
      this.a("Can\'t call getPixel() on a recycled bitmap");
      this.c(var1, var2);
      return nativeGetPixel(this.a, var1, var2, this.e);
   }

   public void a(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.a("Can\'t call getPixels() on a recycled bitmap");
      if(var6 != 0 && var7 != 0) {
         this.a(var4, var5, var6, var7, var2, var3, var1);
         nativeGetPixels(this.a, var1, var2, var3, var4, var5, var6, var7, this.e);
      }
   }

   private void c(int var1, int var2) {
      b(var1, var2);
      if(var1 >= this.b()) {
         throw new IllegalArgumentException("x must be < bitmap.width()");
      } else if(var2 >= this.c()) {
         throw new IllegalArgumentException("y must be < bitmap.height()");
      }
   }

   private void a(int var1, int var2, int var3, int var4, int var5, int var6, int[] var7) {
      b(var1, var2);
      if(var3 < 0) {
         throw new IllegalArgumentException("width must be >= 0");
      } else if(var4 < 0) {
         throw new IllegalArgumentException("height must be >= 0");
      } else if(var1 + var3 > this.b()) {
         throw new IllegalArgumentException("x + width must be <= bitmap.width()");
      } else if(var2 + var4 > this.c()) {
         throw new IllegalArgumentException("y + height must be <= bitmap.height()");
      } else if(Math.abs(var6) < var3) {
         throw new IllegalArgumentException("abs(stride) must be >= width");
      } else {
         int var8 = var5 + (var4 - 1) * var6;
         int var9 = var7.length;
         if(var5 < 0 || var5 + var3 > var9 || var8 < 0 || var8 + var3 > var9) {
            throw new ArrayIndexOutOfBoundsException();
         }
      }
   }

   public void a(int var1, int var2, int var3) {
      this.a("Can\'t call setPixel() on a recycled bitmap");
      if(!this.a()) {
         throw new IllegalStateException();
      } else {
         this.c(var1, var2);
         nativeSetPixel(this.a, var1, var2, var3, this.e);
      }
   }

   public void b(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.a("Can\'t call setPixels() on a recycled bitmap");
      if(!this.a()) {
         throw new IllegalStateException();
      } else if(var6 != 0 && var7 != 0) {
         this.a(var4, var5, var6, var7, var2, var3, var1);
         nativeSetPixels(this.a, var1, var2, var3, var4, var5, var6, var7, this.e);
      }
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel var1, int var2) {
      this.a("Can\'t parcel a recycled bitmap");
      if(!nativeWriteToParcel(this.a, this.d, this.b, var1)) {
         throw new RuntimeException("native writeToParcel failed");
      }
   }

   private static native Bitmap nativeCreate(int[] var0, int var1, int var2, int var3, int var4, int var5, boolean var6);

   private static native Bitmap nativeCopy(int var0, int var1, boolean var2);

   private static native boolean nativeCompress(int var0, int var1, int var2, OutputStream var3, byte[] var4);

   private static native void nativeErase(int var0, int var1);

   private static native int nativeConfig(int var0);

   private static native int nativeGetPixel(int var0, int var1, int var2, boolean var3);

   private static native void nativeGetPixels(int var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8);

   private static native void nativeSetPixel(int var0, int var1, int var2, int var3, boolean var4);

   private static native void nativeSetPixels(int var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8);

   private static native Bitmap nativeCreateFromParcel(Parcel var0);

   private static native boolean nativeWriteToParcel(int var0, boolean var1, int var2, Parcel var3);

   private static native void nativeSetHasAlpha(int var0, boolean var1);

   // $FF: synthetic method
   static Bitmap a(Parcel var0) {
      return nativeCreateFromParcel(var0);
   }

}
