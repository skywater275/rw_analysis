/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import java.util.List;

public class aq {
    public static aq a = new aq();
    public static int b = 2;
    static int c = 3;
    static int d = 2;
    static int e = 3;
    public static int f = 4;
    static String g = "tx";
    static String h = "_";
    public static int i = 55;
    public static int j = 66;
    public static int k = 100;
    public static boolean l = true;

    public static float a(float f, float f2, float f3) {
        return f + (f2 - f) * f3;
    }

    public void a(String string, List list) {
        long l2 = com.corrodinggames.rts.gameFramework.l.V();
        n.a(list, h + "1", "" + l2);
        n.a(list, g + "2", com.corrodinggames.rts.gameFramework.f.d("_" + string + (b + c)));
        n.a(list, g + "3", com.corrodinggames.rts.gameFramework.f.d("_" + string + ((long)(b + c) + l2)));
    }

    public void b(String string, List list) {
        n.a(list, g + "3", com.corrodinggames.rts.gameFramework.f.d("-" + string + (d + e) + f));
    }

    public void c(String string, List list) {
        if (f > 1000) {
            n.a(list, g + "4", com.corrodinggames.rts.gameFramework.f.d("+" + string + (d + e) + f));
        }
    }

    public static void a(c c2) {
        if (c2.N) {
            long l2 = com.corrodinggames.rts.gameFramework.l.V();
            if (com.corrodinggames.rts.gameFramework.l.B().bx > -5) {
                c2.O = com.corrodinggames.rts.gameFramework.f.a(0.0f, 0.0f, (float)k, 0.0f) > 10.0f;
            }
        }
    }
}
