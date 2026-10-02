/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.MaskFilter
 *  android.graphics.PathEffect
 *  android.graphics.Rasterizer
 *  android.graphics.Shader
 */
package android.graphics;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint$Align;
import android.graphics.Paint$Cap;
import android.graphics.Paint$FontMetrics;
import android.graphics.Paint$Join;
import android.graphics.Paint$Style;
import android.graphics.PathEffect;
import android.graphics.Rasterizer;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.TemporaryBuffer;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import java.util.Locale;

public class Paint {
    public int a;
    private ColorFilter r;
    private MaskFilter s;
    private PathEffect t;
    private Rasterizer u;
    private Shader v;
    private Typeface w;
    private Xfermode x;
    private boolean y;
    private float z;
    private float A;
    private Locale B;
    public boolean b;
    public float c;
    public float d;
    public float e;
    public int f;
    public int g = 2;
    static final Paint$Style[] h = new Paint$Style[]{Paint$Style.a, Paint$Style.b, Paint$Style.c};
    static final Paint$Cap[] i = new Paint$Cap[]{Paint$Cap.a, Paint$Cap.b, Paint$Cap.c};
    static final Paint$Join[] j = new Paint$Join[]{Paint$Join.a, Paint$Join.b, Paint$Join.c};
    static final Paint$Align[] k = new Paint$Align[]{Paint$Align.a, Paint$Align.b, Paint$Align.c};
    int l;
    Paint$Style m = Paint$Style.a;
    int n;
    float o = 0.0f;
    Paint$Align p;
    float q = 16.0f;

    public Paint() {
        this(0);
    }

    public Paint(int n) {
        this.a = Paint.o();
        this.a();
        this.a(n | 0x500);
        this.A = 1.0f;
        this.z = 1.0f;
        this.a(Locale.getDefault());
    }

    public Paint(Paint paint) {
        this.a = Paint.d(paint.a);
        this.b(paint);
    }

    public void a() {
        Paint.e(this.a);
        this.a(1280);
        this.n = -1;
        this.m = Paint$Style.a;
        this.q = 16.0f;
        this.p = Paint$Align.a;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = false;
        this.z = 1.0f;
        this.A = 1.0f;
        this.b = false;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0;
        this.g = 2;
        this.a(Locale.getDefault());
    }

    public void a(Paint paint) {
        if (this != paint) {
            Paint.a(this.a, paint.a);
            this.b(paint);
        }
    }

    private void b(Paint paint) {
        this.m = paint.m;
        this.n = paint.n;
        this.q = paint.q;
        this.p = paint.p;
        this.r = paint.r;
        this.s = paint.s;
        this.t = paint.t;
        this.u = paint.u;
        this.w = paint.w;
        this.x = paint.x;
        this.y = paint.y;
        this.z = paint.z;
        this.A = paint.A;
        this.b = paint.b;
        this.c = paint.c;
        this.d = paint.d;
        this.e = paint.e;
        this.f = paint.f;
        this.g = paint.g;
        this.B = paint.B;
        this.o = paint.o;
    }

    public int b() {
        return this.l;
    }

    public void a(int n) {
        this.l = n;
    }

    public final boolean c() {
        return (this.b() & 1) != 0;
    }

    public void a(boolean bl) {
        if (bl) {
            this.a(this.l | 1);
        } else {
            this.a(this.l & 0xFFFFFFFE);
        }
    }

    public void b(boolean bl) {
    }

    public void c(boolean bl) {
    }

    public void d(boolean bl) {
    }

    public Paint$Style d() {
        return this.m;
    }

    public void a(Paint$Style paint$Style) {
        this.m = paint$Style;
    }

    public int e() {
        return this.n;
    }

    public void b(int n) {
        this.n = n;
    }

    public int f() {
        return Color.a(this.n);
    }

    public void c(int n) {
        this.n = Color.a(n, Color.b(this.n), Color.c(this.n), Color.d(this.n));
    }

    public void a(int n, int n2, int n3, int n4) {
        this.b(n << 24 | n2 << 16 | n3 << 8 | n4);
    }

    public float g() {
        return this.o;
    }

    public void a(float f) {
        this.o = f;
    }

    public void a(Paint$Cap paint$Cap) {
        Paint.b(this.a, paint$Cap.d);
    }

    public ColorFilter h() {
        return this.r;
    }

    public ColorFilter a(ColorFilter colorFilter) {
        int n = 0;
        Paint.c(this.a, n);
        this.r = colorFilter;
        return colorFilter;
    }

    public Xfermode a(Xfermode xfermode) {
        int n = 0;
        Paint.d(this.a, n);
        this.x = xfermode;
        return xfermode;
    }

    public Typeface i() {
        return this.w;
    }

    public Typeface a(Typeface typeface) {
        this.w = typeface;
        return typeface;
    }

    public Paint$Align j() {
        return this.p;
    }

    public void a(Paint$Align paint$Align) {
        this.p = paint$Align;
    }

    public void a(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("locale cannot be null");
        }
        if (locale.equals(this.B)) {
            return;
        }
        this.B = locale;
        Paint.a(this.a, locale.toString());
    }

    public float k() {
        return this.q;
    }

    public void b(float f) {
        this.q = f;
    }

    public float l() {
        return -this.q;
    }

    public float m() {
        return 0.0f;
    }

    public float a(Paint$FontMetrics fontMetrics) {
        return 0.0f;
    }

    public Paint$FontMetrics n() {
        Paint$FontMetrics paint$FontMetrics = new Paint$FontMetrics();
        this.a(paint$FontMetrics);
        return paint$FontMetrics;
    }

    public float a(String string) {
        if (string == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        if (string.length() == 0) {
            return 0.0f;
        }
        if (!this.y) {
            return (float)Math.ceil(this.a(string, this.g));
        }
        float f = this.k();
        this.b(f * this.z);
        float f2 = this.a(string, this.g);
        this.b(f);
        return (float)Math.ceil(f2 * this.A);
    }

    private float a(String string, int n) {
        return (float)string.length() * this.k();
    }

    public int a(char[] cArray, int n, int n2, float f, float[] fArray) {
        if (cArray == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        if (n < 0 || cArray.length - n < Math.abs(n2)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (cArray.length == 0 || n2 == 0) {
            return 0;
        }
        if (!this.y) {
            return this.a(cArray, n, n2, f, this.g, fArray);
        }
        float f2 = this.k();
        this.b(f2 * this.z);
        int n3 = this.a(cArray, n, n2, f * this.z, this.g, fArray);
        this.b(f2);
        if (fArray != null) {
            fArray[0] = fArray[0] * this.A;
        }
        return n3;
    }

    private int a(char[] cArray, int n, int n2, float f, int n3, float[] fArray) {
        float f2 = this.k();
        if (f > f2 * (float)n2) {
            return n2;
        }
        if (f == 0.0f) {
            return 1;
        }
        int n4 = (int)(f / f2);
        if (n4 < 1) {
            n4 = 1;
        }
        return n4;
    }

    private int a(String string, boolean bl, float f, int n, float[] fArray) {
        return string.length();
    }

    public int a(CharSequence charSequence, int n, int n2, boolean bl, float f, float[] fArray) {
        if (charSequence == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        if ((n | n2 | n2 - n | charSequence.length() - n2) < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (charSequence.length() == 0 || n == n2) {
            return 0;
        }
        if (n == 0 && charSequence instanceof String && n2 == charSequence.length()) {
            return this.a((String)charSequence, bl, f, fArray);
        }
        char[] cArray = TemporaryBuffer.a(n2 - n);
        Paint.a(charSequence, n, n2, cArray, 0);
        int n3 = bl ? this.a(cArray, 0, n2 - n, f, fArray) : this.a(cArray, 0, -(n2 - n), f, fArray);
        TemporaryBuffer.a(cArray);
        return n3;
    }

    public static void a(CharSequence charSequence, int n, int n2, char[] cArray, int n3) {
        Class<?> clazz = charSequence.getClass();
        if (clazz == String.class) {
            ((String)charSequence).getChars(n, n2, cArray, n3);
        } else if (clazz == StringBuffer.class) {
            ((StringBuffer)charSequence).getChars(n, n2, cArray, n3);
        } else if (clazz == StringBuilder.class) {
            ((StringBuilder)charSequence).getChars(n, n2, cArray, n3);
        } else {
            for (int i = n; i < n2; ++i) {
                cArray[n3++] = charSequence.charAt(i);
            }
        }
    }

    public int a(String string, boolean bl, float f, float[] fArray) {
        if (string == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        if (string.length() == 0) {
            return 0;
        }
        if (!this.y) {
            return this.a(string, bl, f, this.g, fArray);
        }
        float f2 = this.k();
        this.b(f2 * this.z);
        int n = this.a(string, bl, f * this.z, this.g, fArray);
        this.b(f2);
        if (fArray != null) {
            fArray[0] = fArray[0] * this.A;
        }
        return n;
    }

    public int a(char[] cArray, int n, int n2, float[] fArray) {
        if (cArray == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        if ((n | n2) < 0 || n + n2 > cArray.length || n2 > fArray.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (cArray.length == 0 || n2 == 0) {
            return 0;
        }
        if (!this.y) {
            return Paint.a(this.a, cArray, n, n2, this.g, fArray);
        }
        float f = this.k();
        this.b(f * this.z);
        int n3 = Paint.a(this.a, cArray, n, n2, this.g, fArray);
        this.b(f);
        int n4 = 0;
        while (n4 < n3) {
            int n5 = n4++;
            fArray[n5] = fArray[n5] * this.A;
        }
        return n3;
    }

    public void a(String string, int n, int n2, Rect rect) {
        if ((n | n2 | n2 - n | string.length() - n2) < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (rect == null) {
            throw new NullPointerException("need bounds Rect");
        }
        rect.a(0, 0, 0, (int)this.q);
    }

    protected void finalize() {
        try {
            Paint.f(this.a);
        }
        finally {
            super.finalize();
        }
    }

    private static int o() {
        return 0;
    }

    private static int d(int n) {
        return 0;
    }

    private static void e(int n) {
    }

    private static void a(int n, int n2) {
    }

    private static void b(int n, int n2) {
    }

    private static int c(int n, int n2) {
        return 0;
    }

    private static int d(int n, int n2) {
        return 0;
    }

    private static void a(int n, String string) {
    }

    private static int a(int n, char[] cArray, int n2, int n3, int n4, float[] fArray) {
        return 0;
    }

    private static void f(int n) {
    }
}
