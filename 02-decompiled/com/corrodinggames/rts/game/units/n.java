/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h$1;
import com.corrodinggames.rts.game.units.n$1;
import com.corrodinggames.rts.game.units.n$2;
import com.corrodinggames.rts.game.units.n$3;
import com.corrodinggames.rts.game.units.n$4;
import com.corrodinggames.rts.game.units.n$5;
import com.corrodinggames.rts.game.units.n$6;
import com.corrodinggames.rts.gameFramework.l;

public strictfp abstract class n
extends Enum {
    public static final /* enum */ n a = new n$1();
    public static final /* enum */ n b = new n$2();
    public static final /* enum */ n c = new n$3();
    public static final /* enum */ n d = new n$4();
    public static final /* enum */ n e = new n$5();
    public static final /* enum */ n f = new n$6();
    private static final /* synthetic */ n[] g;

    public static n[] values() {
        return (n[])g.clone();
    }

    public static n valueOf(String string) {
        return Enum.valueOf(n.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private n() {
        void var2_-1;
        void var1_-1;
    }

    public abstract boolean a(as var1);

    public String a() {
        return this.name();
    }

    public boolean b() {
        return true;
    }

    public n a(boolean bl) {
        if (!bl) {
            return this.a(1, 0);
        }
        return this.a(-1, 0);
    }

    public n a(int n2, int n3) {
        n n4;
        int n5 = this.ordinal() + n2;
        if ((n5 %= n.values().length) < 0) {
            n5 += n.values().length;
        }
        if (!(n4 = n.values()[n5]).b()) {
            if (n3 > 30) {
                l.e("jumpBy recursion limit hit");
                return n4;
            }
            n4 = n4.a(n2, n3 + 1);
        }
        return n4;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ n(h.1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        g = new n[]{a, b, c, d, e, f};
    }
}
