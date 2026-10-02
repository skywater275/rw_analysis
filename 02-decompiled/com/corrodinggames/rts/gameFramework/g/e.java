/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.g.d;
import com.corrodinggames.rts.gameFramework.g.f;

public class e
extends d {
    private final n a;

    public e(n n2) {
        this.a = n2;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public String b() {
        if (this.a.v == null) {
            return "";
        }
        return this.a.v;
    }

    @Override
    public int c() {
        return this.a.K();
    }

    @Override
    public int d() {
        return -1;
    }

    @Override
    public int a(f f2) {
        return f2.a(this.a);
    }
}
