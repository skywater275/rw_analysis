/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.y;
import java.util.ArrayList;

public class d {
    static Rect a = new Rect();
    static ArrayList b = new ArrayList();
    static final RectF c = new RectF();
    static final RectF d = new RectF();

    public static int a(Paint paint) {
        String string = "abcABC123!|";
        return l.B().bO.a(string, paint) + 4;
    }

    public static int b(Paint paint) {
        String string = "abcABC123!|";
        int n2 = l.B().bO.a(string, paint);
        if (l.aY) {
            return n2 + 2;
        }
        return n2;
    }

    public static ArrayList a(String string, Rect rect, Paint paint, Paint paint2, boolean bl) {
        int n2;
        b.clear();
        String string2 = "";
        int n3 = 0;
        for (int j = 0; j < string.length() && (n2 = paint2.a(string, j, string.length(), true, (float)(rect.b() - 5), null)) != 0; j += n2) {
            String string3;
            int n4;
            int n5 = string.indexOf("\n", j + 1);
            if (n5 != -1 && n5 < j + n2) {
                n2 = n5 - j;
            } else if (j + n2 < string.length() && (n4 = (string3 = string.substring(j, j + n2)).lastIndexOf(" ")) != -1 && n4 != 0) {
                n2 = n4;
            }
            string3 = string.substring(j, j + n2);
            string3 = string3.replaceAll("(\\n)", "");
            if (string3.length() > string2.length()) {
                string2 = string3;
                n3 = b.size();
            }
            b.add(string3);
        }
        rect.d = rect.b + b.size() * com.corrodinggames.rts.gameFramework.f.d.a(paint2);
        if (bl) {
            float f2;
            float f3 = rect.d();
            Paint paint3 = paint2;
            if (n3 == 0) {
                paint3 = paint;
            }
            if ((f2 = (float)l.B().bO.b(string2, paint3)) < (float)rect.b()) {
                rect.a = (int)(f3 - f2 / 2.0f);
                rect.c = (int)(f3 + f2 / 2.0f);
            }
        }
        return b;
    }

    public static void a(String string, float f2, float f3, Paint paint, Paint paint2, float f4, float f5, float f6, float f7) {
        y y2 = l.B().bO;
        float f8 = y2.b(string, paint);
        d.a(f2, f3, f2 + f8, f3 + (float)y2.a(string, paint));
        c.a(d);
        if (paint.j() == Paint$Align.b) {
            c.a(-(f8 / 2.0f), 0.0f);
        }
        com.corrodinggames.rts.gameFramework.f.d.c.a -= f4;
        com.corrodinggames.rts.gameFramework.f.d.c.b -= f5;
        com.corrodinggames.rts.gameFramework.f.d.c.c += f6;
        com.corrodinggames.rts.gameFramework.f.d.c.d += f7;
        y2.a(c, paint2);
        y2.a(string, com.corrodinggames.rts.gameFramework.f.d.d.a, com.corrodinggames.rts.gameFramework.f.d.d.d, paint);
    }

    public static float a(e e2, float f2, float f3) {
        return com.corrodinggames.rts.gameFramework.f.d.a(e2, f2, f3, f2, f3);
    }

    public static float a(e e2, float f2, float f3, float f4, float f5) {
        float f6;
        float f7 = e2.p;
        float f8 = e2.q;
        float f9 = 1.0f;
        if (f7 * f9 < f2 && (f6 = f2 / f7) > f9) {
            f9 = f6;
        }
        if (f8 * f9 < f3 && (f6 = f3 / f8) > f9) {
            f9 = f6;
        }
        if (f7 * f9 > f4 && (f6 = f4 / f7) < f9) {
            f9 = f6;
        }
        if (f8 * f9 > f5 && (f6 = f5 / f8) < f9) {
            f9 = f6;
        }
        return f9;
    }
}
