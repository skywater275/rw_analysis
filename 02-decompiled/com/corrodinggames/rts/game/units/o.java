/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h$1;
import com.corrodinggames.rts.game.units.o$1;
import com.corrodinggames.rts.game.units.o$2;
import com.corrodinggames.rts.game.units.o$3;
import com.corrodinggames.rts.game.units.o$4;
import com.corrodinggames.rts.game.units.o$5;
import com.corrodinggames.rts.gameFramework.l;

public strictfp abstract class o
extends Enum {
    public static final /* enum */ o a = new o$1();
    public static final /* enum */ o b = new o$2();
    public static final /* enum */ o c = new o$3();
    public static final /* enum */ o d = new o$4();
    public static final /* enum */ o e = new o$5();
    private static final /* synthetic */ o[] f;

    public static o[] values() {
        return (o[])f.clone();
    }

    public static o valueOf(String string) {
        return Enum.valueOf(o.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private o() {
        void var2_-1;
        void var1_-1;
    }

    public abstract boolean a(as var1);

    public String a() {
        return this.name();
    }

    public o a(boolean bl) {
        if (!bl) {
            return this.a(1, 0);
        }
        return this.a(-1, 0);
    }

    public o a(int n2, int n3) {
        o o2;
        int n4 = this.ordinal() + n2;
        if ((n4 %= o.values().length) < 0) {
            n4 += o.values().length;
        }
        if (!(o2 = o.values()[n4]).b()) {
            if (n3 > 30) {
                l.e("jumpBy recursion limit hit");
                return o2;
            }
            o2 = o2.a(n2, n3 + 1);
        }
        return o2;
    }

    public boolean b() {
        return true;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ o(h.1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        f = new o[]{a, b, c, d, e};
    }
}
