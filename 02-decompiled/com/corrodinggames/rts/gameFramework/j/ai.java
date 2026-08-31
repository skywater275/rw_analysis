/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad$1;
import com.corrodinggames.rts.gameFramework.j.ai$1;
import com.corrodinggames.rts.gameFramework.j.ai$2;
import com.corrodinggames.rts.gameFramework.j.ai$3;

public strictfp abstract class ai
extends Enum {
    public static final /* enum */ ai a = new ai$1();
    public static final /* enum */ ai b = new ai$2();
    public static final /* enum */ ai c = new ai$3();
    private static final /* synthetic */ ai[] d;

    public static ai[] values() {
        return (ai[])d.clone();
    }

    public static ai valueOf(String string) {
        return Enum.valueOf(ai.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ai() {
        void var2_-1;
        void var1_-1;
    }

    public abstract String a();

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ ai(ad.1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        d = new ai[]{a, b, c};
    }
}
