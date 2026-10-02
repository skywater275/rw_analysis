/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.p$1;
import com.corrodinggames.rts.game.units.r$1;
import com.corrodinggames.rts.game.units.r$2;
import com.corrodinggames.rts.game.units.r$3;
import com.corrodinggames.rts.game.units.r$4;

public abstract class r
extends Enum {
    public static final /* enum */ r a = new r$1();
    public static final /* enum */ r b = new r$2();
    public static final /* enum */ r c = new r$3();
    public static final /* enum */ r d = new r$4();
    private static final /* synthetic */ r[] e;

    public static r[] values() {
        return (r[])e.clone();
    }

    public static r valueOf(String string) {
        return Enum.valueOf(r.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private r() {
        void var2_-1;
        void var1_-1;
    }

    public abstract String a();

    public abstract String b();

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ r(p.1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        e = new r[]{a, b, c, d};
    }
}
