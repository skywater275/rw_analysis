/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n;

import com.corrodinggames.rts.gameFramework.n.a;
import com.corrodinggames.rts.gameFramework.utility.m;

public class b {
    m a = new m();
    boolean b;

    public void a(a a2) {
        this.a.add(a2);
    }

    public boolean a() {
        return this.a.a > 0;
    }

    public boolean b() {
        boolean bl = false;
        boolean bl2 = true;
        for (a a2 : this.a) {
            if (a2.j) {
                bl = true;
                continue;
            }
            bl2 = false;
        }
        if (this.b && !bl2) {
            bl = false;
        }
        return bl;
    }
}
