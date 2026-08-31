/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bj;

public enum ac {
    a("A", null),
    b("B", bj.a),
    c("C", bj.b),
    d("D", bj.c),
    e("E", bj.d);

    private final String f;
    private final bj g;

    /*
     * WARNING - void declaration
     */
    private ac() {
        void var4_2;
        void var3_1;
        this.f = var3_1;
        this.g = var4_2;
    }

    public bj a() {
        return this.g;
    }
}
