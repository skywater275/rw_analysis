/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.a;
import com.corrodinggames.rts.a.a.c;
import com.corrodinggames.rts.a.a.d;
import com.corrodinggames.rts.a.a.e;
import com.corrodinggames.rts.a.a.f;
import com.corrodinggames.rts.a.a.k;
import com.corrodinggames.rts.a.a.m;
import com.corrodinggames.rts.gameFramework.l;

public class n {
    public void a() {
        l.e("Running unit tests");
        new c().a();
        new d().a();
        new k().a();
        new e().a();
        new a().a();
        new f().a();
        new m().a();
    }

    public static void a(boolean bl) {
        if (!bl) {
            throw new RuntimeException("Asset failed");
        }
    }

    public static void b(boolean bl) {
        if (bl) {
            throw new RuntimeException("Asset failed");
        }
    }

    public static void a(int n2, int n3) {
        if (n2 != n3) {
            throw new RuntimeException("Asset failed (int):" + n2 + "!=" + n3);
        }
    }

    public static void a(float f2, float f3) {
        if (com.corrodinggames.rts.gameFramework.f.c(f2 - f3) > 0.001f) {
            throw new RuntimeException("Asset failed (float):" + f2 + "!=" + f3);
        }
    }

    public static void a(String string, String string2) {
        if (!string.equals(string2)) {
            throw new RuntimeException("Asset failed:" + string + "!=" + string2);
        }
    }

    public static void b(String string, String string2) {
        l.e("assertEqualDebug:'" + string + "' vs '" + string2 + "'");
        n.a(string, string2);
    }

    public static void c(String string, String string2) {
        Float f2 = Float.valueOf(Float.parseFloat(string));
        Float f3 = Float.valueOf(Float.parseFloat(string2));
        n.a(f2.floatValue(), f3.floatValue());
    }

    public static void a(Object object, Object object2) {
        if (object != object2) {
            throw new RuntimeException("Asset failed:" + object + "!=" + object2);
        }
    }
}
