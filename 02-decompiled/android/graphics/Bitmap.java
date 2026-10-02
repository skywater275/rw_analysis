/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.util.DisplayMetrics
 */
package android.graphics;

import android.graphics.Bitmap$1;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import java.io.OutputStream;

public final class Bitmap
implements Parcelable {
    public final int a;
    private final boolean d;
    private boolean e;
    private int f;
    private int g;
    private boolean h;
    int b;
    private static volatile int i = -1;
    public static final Parcelable.Creator c = new Bitmap$1();

    private void a(String string) {
        if (this.h) {
            throw new IllegalStateException(string);
        }
    }

    private static void b(int n, int n2) {
        if (n < 0) {
            throw new IllegalArgumentException("x must be >= 0");
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("y must be >= 0");
        }
    }

    public Bitmap a(Bitmap$Config bitmap$Config, boolean bl) {
        this.a("Can't copy a recycled bitmap");
        Bitmap bitmap = Bitmap.nativeCopy(this.a, bitmap$Config.e, bl);
        if (bitmap != null) {
            bitmap.e = this.e;
            bitmap.b = this.b;
        }
        return bitmap;
    }

    public static Bitmap a(int n, int n2, Bitmap$Config bitmap$Config) {
        return Bitmap.a(n, n2, bitmap$Config, true);
    }

    private static Bitmap a(int n, int n2, Bitmap$Config bitmap$Config, boolean bl) {
        return Bitmap.a(null, n, n2, bitmap$Config, bl);
    }

    private static Bitmap a(DisplayMetrics displayMetrics, int n, int n2, Bitmap$Config bitmap$Config, boolean bl) {
        if (n <= 0 || n2 <= 0) {
            throw new IllegalArgumentException("width and height must be > 0");
        }
        Bitmap bitmap = Bitmap.nativeCreate(null, 0, n, n, n2, bitmap$Config.e, true);
        if (displayMetrics != null) {
            bitmap.b = displayMetrics.densityDpi;
        }
        if (bitmap$Config == Bitmap$Config.d && !bl) {
            Bitmap.nativeErase(bitmap.a, -16777216);
            Bitmap.nativeSetHasAlpha(bitmap.a, bl);
        }
        return bitmap;
    }

    public boolean a(Bitmap$CompressFormat bitmap$CompressFormat, int n, OutputStream outputStream) {
        this.a("Can't compress a recycled bitmap");
        if (outputStream == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n > 100) {
            throw new IllegalArgumentException("quality must be 0..100");
        }
        return Bitmap.nativeCompress(this.a, bitmap$CompressFormat.d, n, outputStream, new byte[4096]);
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
        return Bitmap$Config.a(Bitmap.nativeConfig(this.a));
    }

    public void a(boolean bl) {
        Bitmap.nativeSetHasAlpha(this.a, bl);
    }

    public void a(int n) {
        this.a("Can't erase a recycled bitmap");
        if (!this.a()) {
            throw new IllegalStateException("cannot erase immutable bitmaps");
        }
        Bitmap.nativeErase(this.a, n);
    }

    public int a(int n, int n2) {
        this.a("Can't call getPixel() on a recycled bitmap");
        this.c(n, n2);
        return Bitmap.nativeGetPixel(this.a, n, n2, this.e);
    }

    public void a(int[] nArray, int n, int n2, int n3, int n4, int n5, int n6) {
        this.a("Can't call getPixels() on a recycled bitmap");
        if (n5 == 0 || n6 == 0) {
            return;
        }
        this.a(n3, n4, n5, n6, n, n2, nArray);
        Bitmap.nativeGetPixels(this.a, nArray, n, n2, n3, n4, n5, n6, this.e);
    }

    private void c(int n, int n2) {
        Bitmap.b(n, n2);
        if (n >= this.b()) {
            throw new IllegalArgumentException("x must be < bitmap.width()");
        }
        if (n2 >= this.c()) {
            throw new IllegalArgumentException("y must be < bitmap.height()");
        }
    }

    private void a(int n, int n2, int n3, int n4, int n5, int n6, int[] nArray) {
        Bitmap.b(n, n2);
        if (n3 < 0) {
            throw new IllegalArgumentException("width must be >= 0");
        }
        if (n4 < 0) {
            throw new IllegalArgumentException("height must be >= 0");
        }
        if (n + n3 > this.b()) {
            throw new IllegalArgumentException("x + width must be <= bitmap.width()");
        }
        if (n2 + n4 > this.c()) {
            throw new IllegalArgumentException("y + height must be <= bitmap.height()");
        }
        if (Math.abs(n6) < n3) {
            throw new IllegalArgumentException("abs(stride) must be >= width");
        }
        int n7 = n5 + (n4 - 1) * n6;
        int n8 = nArray.length;
        if (n5 < 0 || n5 + n3 > n8 || n7 < 0 || n7 + n3 > n8) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void a(int n, int n2, int n3) {
        this.a("Can't call setPixel() on a recycled bitmap");
        if (!this.a()) {
            throw new IllegalStateException();
        }
        this.c(n, n2);
        Bitmap.nativeSetPixel(this.a, n, n2, n3, this.e);
    }

    public void b(int[] nArray, int n, int n2, int n3, int n4, int n5, int n6) {
        this.a("Can't call setPixels() on a recycled bitmap");
        if (!this.a()) {
            throw new IllegalStateException();
        }
        if (n5 == 0 || n6 == 0) {
            return;
        }
        this.a(n3, n4, n5, n6, n, n2, nArray);
        Bitmap.nativeSetPixels(this.a, nArray, n, n2, n3, n4, n5, n6, this.e);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        this.a("Can't parcel a recycled bitmap");
        if (!Bitmap.nativeWriteToParcel(this.a, this.d, this.b, parcel)) {
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

    static /* synthetic */ Bitmap a(Parcel parcel) {
        return Bitmap.nativeCreateFromParcel(parcel);
    }
}
