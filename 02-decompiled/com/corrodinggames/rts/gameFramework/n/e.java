/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n;

import com.corrodinggames.rts.gameFramework.n.e$1;
import com.corrodinggames.rts.gameFramework.n.e$10;
import com.corrodinggames.rts.gameFramework.n.e$11;
import com.corrodinggames.rts.gameFramework.n.e$2;
import com.corrodinggames.rts.gameFramework.n.e$3;
import com.corrodinggames.rts.gameFramework.n.e$4;
import com.corrodinggames.rts.gameFramework.n.e$5;
import com.corrodinggames.rts.gameFramework.n.e$6;
import com.corrodinggames.rts.gameFramework.n.e$7;
import com.corrodinggames.rts.gameFramework.n.e$8;
import com.corrodinggames.rts.gameFramework.n.e$9;

public abstract class e
extends Enum {
    public static final /* enum */ e a = new e$1();
    public static final /* enum */ e b = new e$4();
    public static final /* enum */ e c = new e$5();
    public static final /* enum */ e d = new e$6();
    public static final /* enum */ e e = new e$7();
    public static final /* enum */ e f = new e$8();
    public static final /* enum */ e g = new e$9();
    public static final /* enum */ e h = new e$10();
    public static final /* enum */ e i = new e$11();
    public static final /* enum */ e j = new e$2();
    public static final /* enum */ e k = new e$3();
    private static final /* synthetic */ e[] l;

    public static e[] values() {
        return (e[])l.clone();
    }

    public static e valueOf(String string) {
        return Enum.valueOf(e.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private e() {
        void var2_-1;
        void var1_-1;
    }

    public abstract String a();

    public static e a(String string) {
        for (e e2 : com.corrodinggames.rts.gameFramework.n.e.values()) {
            if (!e2.a().equalsIgnoreCase(string)) continue;
            return e2;
        }
        return null;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ e(e$1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        l = new e[]{a, b, c, d, e, f, g, h, i, j, k};
    }
}
