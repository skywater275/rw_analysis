/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a.a;

import com.corrodinggames.rts.game.a.a.a;
import com.corrodinggames.rts.game.a.a.a$1;
import com.corrodinggames.rts.game.a.a.b$1;
import com.corrodinggames.rts.game.a.a.b$2;

public abstract class b
extends Enum {
    public static final /* enum */ b a = new b$1();
    public static final /* enum */ b b = new b$2();
    private static final /* synthetic */ b[] c;

    public static b[] values() {
        return (b[])c.clone();
    }

    public static b valueOf(String string) {
        return Enum.valueOf(b.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private b() {
        void var2_-1;
        void var1_-1;
    }

    public abstract a a();

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ b(a.1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        c = new b[]{a, b};
    }
}
