/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.a;

import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.f;

public abstract class i {
    public float d = 1.0f;
    public String e;
    public boolean f = false;
    public boolean g;

    public i(String string, h h2) {
        this.e = com.corrodinggames.rts.gameFramework.f.g(string);
        if (h2 != null) {
            h2.h.put(this.e, this);
        }
    }

    public abstract void a(float var1, float var2, int var3, int var4, float var5);

    public abstract int a();
}
