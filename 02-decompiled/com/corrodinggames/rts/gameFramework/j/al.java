/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ak;

public strictfp class al {
    public String a;
    public long b;
    boolean c;
    final /* synthetic */ ak d;

    public al(ak ak2, String string) {
        this(ak2, string, true);
    }

    public al(ak ak2, String string, boolean bl) {
        this.d = ak2;
        this.a = string;
        this.c = bl;
        ak2.b.add(this);
    }
}
