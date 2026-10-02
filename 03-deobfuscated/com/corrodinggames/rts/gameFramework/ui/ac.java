/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.StatsCategory;

public enum ac {
    a("A", null),
    b("B", StatsCategory.a),
    c("C", StatsCategory.b),
    d("D", StatsCategory.c),
    e("E", StatsCategory.d);

    private final String f;
    private final StatsCategory g;




    private ac(String var3_1, StatsCategory var4_2) {


        this.f = var3_1;
        this.g = var4_2;
    }

    public StatsCategory a() {
        return this.g;
    }
}
