/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;
import com.corrodinggames.rts.gameFramework.MusicController;

import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.TextFormatter;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.ab;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public final class FontRenderer {    public static final int a(int n2) {  // 02b m.aa.a(int): alpha
        return n2 >>> 24;
    }


    static int a = -1;
    static float b;
    static float c;
    static float d;
    public static final Rect e;
    static ab[] f;
    static Paint g;
    static final Rect h;
    static final Rect i;
    static final RectF j;

    public static final int b(int n2) {
        return n2 >> 16 & 0xFF;
    }

    public static final int c(int n2) {
        return n2 >> 8 & 0xFF;
    }

    public static final int d(int n2) {
        return n2 & 0xFF;
    }

    static {
        e = new Rect();
        h = new Rect();
        i = new Rect();
        j = new RectF();
    }



    public static final Bitmap a(Texture e2) {
        return e2.setKeepInGpuMemory();
    }



    public static void a(String string, float f2, float f3, Paint paint) {
        GlobalState l2 = GlobalState.B();
        String[] stringArray = com.corrodinggames.rts.gameFramework.GameUtils.c(string, '\n');
        float f4 = com.corrodinggames.rts.gameFramework.ui.TextFormatter.b(paint);
        float f5 = (float)(stringArray.length - 1) * f4;
        int n2 = 0;
        for (String string2 : stringArray) {
            Paint paint2 = paint;
            float f6 = f2;
            float f7 = f3 - f5 / 2.0f + (float)n2 * f4 + f4 / 2.0f;
            l2.bO.a(string2, f6, f7, paint2);
            ++n2;
        }
    }



    public static void a(TextureManagerInterface y2, float f2, float f3, float f4, Paint paint) {
        if (GlobalState.at()) {
            GlobalState l2 = GlobalState.B();
            FontRenderer.a(y2, f2, f3, f4, 40, paint, l2.cQ);
        } else {
            y2.a(f2, f3, f4, paint);
        }
    }



    public static ab a(float f2, float f3, boolean bl, TextureManagerInterface y2) {
        int n2 = (int)f2;
        int n3 = (int)f3;
        int n4 = 2;
        int n5 = 2;
        int n6 = 0;
        if (n2 > 20) {
            n6 = 1;
            n2 = 60;
        } else {
            n2 = 30;
        }
        int n7 = 0;
        if (n3 >= 2) {
            n7 = 1;
            n3 = 2;
        } else {
            n3 = 1;
        }
        int n8 = n6 + n7 * n4 + (bl ? 0 : n4 + n4 * n5);
        if (f == null) {
            f = new ab[(n4 + n4 * n5) * 2 + 1];
        }
        if (FontRenderer.f[n8] != null) {
            if (FontRenderer.f[n8].b != n2) {
                GlobalState.e("Mismatch on index: " + n8 + " size:" + n2);
            }
            return FontRenderer.f[n8];
        }
        ab ab2 = new ab();
        ab2.d = FontRenderer.a(n2, n3, bl, y2);
        ab2.b = n2;
        ab2.a = n3;
        ab2.c = bl;
        FontRenderer.f[n8] = ab2;
        return ab2;
    }



    public static Texture a(int n2, int n3, boolean bl, TextureManagerInterface y2) {
        Paint paint = new Paint();
        paint.b(-1);
        paint.a(bl ? Paint$Style.a : Paint$Style.b);
        paint.a((float)n3);
        int n4 = n2;
        Texture e2 = y2.b(n4 * 2 + 4, n4 * 2 + 4, true);
        TextureManagerInterface y3 = y2.b(e2);
        y3.b(n4 + 2, (float)(n4 + 2), (float)n4, paint);
        y3.p();
        e2.p();
        y3.q();
        return e2;
    }



    public static void a(TextureManagerInterface y2, float f2, float f3, float f4, Paint paint, float f5) {
        if (g == null) {
            g = new Paint();
            g.a(true);
            g.b(true);
        }
        int n2 = paint.e();
        if (GlobalState.at()) {
            g.a(new LightingColorFilter(n2, 0));
        }
        g.b(n2);
        ab ab2 = FontRenderer.a(f4 * f5, paint.g(), paint.d() == Paint$Style.a, y2);
        float f6 = f4 / (float)ab2.b;
        float f7 = -f4 - f6 * 2.0f;
        y2.loadImageFromResource(ab2.d, f2 + f7, f3 + f7, g, 0.0f, f6);
    }



    public static void a(TextureManagerInterface y2, float f2, float f3, float f4, int n2, Paint paint, Rect rect) {
        if (a != n2) {
            a = n2;
            b = 6.283185f / (float)n2;
            c = com.corrodinggames.rts.gameFramework.GameUtils.cosFast(b);
            d = com.corrodinggames.rts.gameFramework.GameUtils.sinFast(b);
        }
        float f5 = c;
        float f6 = d;
        int n3 = (int)(b * f4 * 0.5f);
        int n4 = n3 + 50;
        FontRenderer.e.a = rect.a - n4;
        FontRenderer.e.b = rect.b - n4;
        FontRenderer.e.c = rect.c + n4;
        FontRenderer.e.d = rect.d + n4;
        float f7 = f4;
        float f8 = 0.0f;
        ++n2;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        boolean bl = true;
        for (int j = 0; j < n2; ++j) {
            float f13 = f7 + f2;
            float f14 = f8 + f3;
            if (bl) {
                bl = false;
                f9 = f13;
                f10 = f14;
            } else if (FontRenderer.e.b((int)f13, (int)f14) || FontRenderer.e.b((int)f11, (int)f12)) {
                y2.a(f13, f14, f11, f12, paint);
            }
            f11 = f13;
            f12 = f14;
            float f15 = f7;
            f7 = f5 * f7 - f6 * f8;
            f8 = f6 * f15 + f5 * f8;
        }
        if (FontRenderer.e.b((int)f9, (int)f10) || FontRenderer.e.b((int)f11, (int)f12)) {
            y2.a(f9, f10, f11, f12, paint);
        }
    }



    public static void a(TextureManagerInterface y2, Texture e2, Rect rect, Paint paint, int n2, int n3, int n4, int n5) {
        int n6 = e2.m();
        int n7 = e2.l();
        if (n2 != 0 && (n2 %= e2.m()) < 0) {
            n2 += e2.m();
        }
        if (n3 != 0 && (n3 %= e2.l()) < 0) {
            n3 += e2.l();
        }
        int n8 = rect.b - n3;
        int n9 = n6 - n4;
        int n10 = n7 - n5;
        if (n9 <= 0 || n10 <= 0) {
            return;
        }
        int n11 = 0;
        for (int j = rect.a - n2; j < rect.c; j += n9) {
            while (n8 < rect.d) {
                int n12;
                int n13;
                if (++n11 > 2000) {
                    GlobalState.e("tileImage hit limit");
                    return;
                }
                int n14 = rect.c - j;
                if (n14 > n6) {
                    n14 = n6;
                }
                if ((n13 = rect.d - n8) > n7) {
                    n13 = n7;
                }
                if (n13 <= 0 || n14 <= 0) break;
                h.a(0, 0, n14, n13);
                i.a(j, n8, j + n14, n8 + n13);
                int n15 = FontRenderer.i.a - rect.a;
                if (n15 < 0) {
                    FontRenderer.h.a -= n15;
                    FontRenderer.i.a -= n15;
                }
                if ((n12 = FontRenderer.i.b - rect.b) < 0) {
                    FontRenderer.h.b -= n12;
                    FontRenderer.i.b -= n12;
                }
                y2.loadImageFromResource(e2, h, i, paint);
                n8 += n10;
            }
            n8 = rect.b - n3;
        }
    }



    public static void a(TextureManagerInterface y2, Texture e2, RectF rectF, Paint paint, float f2, float f3, int n2, int n3) {
        int n4 = e2.m();
        int n5 = e2.l();
        if (f2 != 0.0f && (f2 %= (float)n4) < 0.0f) {
            f2 += (float)n4;
        }
        if (f3 != 0.0f && (f3 %= (float)n5) < 0.0f) {
            f3 += (float)n5;
        }
        float f4 = rectF.b - f3;
        int n6 = 0;
        int n7 = n4 - n2;
        int n8 = n5 - n3;
        if (n7 <= 0 || n8 <= 0) {
            return;
        }
        for (float f5 = rectF.a - f2; f5 < rectF.c; f5 += (float)n7) {
            while (f4 < rectF.d) {
                float f6;
                float f7;
                if (++n6 > 2000) {
                    GlobalState.e("tileImage hit limit");
                    return;
                }
                float f8 = rectF.c - f5;
                if (f8 > (float)n4) {
                    f8 = n4;
                }
                if ((f7 = rectF.d - f4) > (float)n5) {
                    f7 = n5;
                }
                if (f7 <= 0.0f || f8 <= 0.0f) break;
                h.a(0, 0, (int)f8, (int)f7);
                j.a(f5, f4, f5 + f8, f4 + f7);
                float f9 = FontRenderer.j.a - rectF.a;
                if (f9 < 0.0f) {
                    FontRenderer.h.a = (int)((float)FontRenderer.h.a - f9);
                    FontRenderer.j.a -= f9;
                }
                if ((f6 = FontRenderer.j.b - rectF.b) < 0.0f) {
                    FontRenderer.h.b = (int)((float)FontRenderer.h.b - f6);
                    FontRenderer.j.b -= f6;
                }
                y2.loadImageFromResource(e2, h, j, paint);
                f4 += (float)n8;
            }
            f4 = rectF.b - f3;
        }
    }



    public static void a(TextureManagerInterface y2, Texture e2, Rect rect, Rect rect2, Paint paint, int n2, int n3, int n4, int n5, float f2) {
        int n6 = rect.b();
        int n7 = rect.c();
        if (n2 != 0 && (n2 %= n6) < 0) {
            n2 += n6;
        }
        if (n3 != 0 && (n3 %= n7) < 0) {
            n3 += n7;
        }
        int n8 = rect2.a - n2;
        int n9 = rect2.b - n3;
        int n10 = rect2.c - n8;
        int n11 = rect2.d - n9;
        float f3 = (float)n10 / (float)n6;
        float f4 = (float)n11 / (float)n7;
        int n12 = (int)(f3 + 0.5f);
        int n13 = (int)(f4 + 0.5f);
        if (n12 < 1) {
            n12 = 1;
        }
        if (n13 < 1) {
            n13 = 1;
        }
        float f5 = (float)n10 / (float)(n12 * n6);
        float f6 = (float)n11 / (float)(n13 * n7);
        f5 = com.corrodinggames.rts.gameFramework.GameUtils.f(1.0f, f5, f2);
        f6 = com.corrodinggames.rts.gameFramework.GameUtils.f(1.0f, f6, f2);
        int n14 = (int)((float)n6 * f5);
        int n15 = (int)((float)n7 * f6);
        float f7 = 1.0f / f5;
        float f8 = 1.0f / f6;
        int n16 = n14 - n4;
        int n17 = n15 - n5;
        if (n16 <= 0 || n17 <= 0) {
            return;
        }
        int n18 = 0;
        while (n8 < rect2.c) {
            while (n9 < rect2.d) {
                int n19;
                int n20;
                if (++n18 > 2000) {
                    GlobalState.e("tileImage hit limit");
                    return;
                }
                int n21 = rect2.c - n8;
                if (n21 > n14) {
                    n21 = n14;
                }
                if ((n20 = rect2.d - n9) > n15) {
                    n20 = n15;
                }
                if (n20 <= 0 || n21 <= 0) break;
                int n22 = (int)((float)n21 * f7);
                int n23 = (int)((float)n20 * f8);
                h.a(0, 0, n22, n23);
                h.a(rect.a, rect.b);
                i.a(n8, n9, n8 + n21, n9 + n20);
                int n24 = FontRenderer.i.a - rect2.a;
                if (n24 < 0) {
                    FontRenderer.h.a -= n24;
                    FontRenderer.i.a -= n24;
                }
                if ((n19 = FontRenderer.i.b - rect2.b) < 0) {
                    FontRenderer.h.b -= n19;
                    FontRenderer.i.b -= n19;
                }
                y2.loadImageFromResource(e2, h, i, paint);
                n9 += n17;
            }
            n8 += n16;
            n9 = rect2.b - n3;
        }
    }



    public static void a(Paint paint) {
        if (GlobalState.at()) {
            int n2 = paint.e();
            int n3 = n2 >> 16 & 0xFF;
            int n4 = n2 >> 8 & 0xFF;
            int n5 = n2 & 0xFF;
            int n6 = com.corrodinggames.rts.gameFramework.GameUtils.b(255, n3, n4, n5);
            paint.a(new LightingColorFilter(n6, 0));
        }
    }
}