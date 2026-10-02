/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.LibRocket$TextureHolder;
import com.corrodinggames.librocket.b;
import com.corrodinggames.rts.game.units.as;

public abstract class c
extends LibRocket$TextureHolder {
    public String a;
    public boolean b;
    public boolean c;
    public boolean d;
    public float e;
    public as f;
    final /* synthetic */ b g;

    public c(b b2) {
        this.g = b2;
        super(b2);
        this.e = 1.0f;
    }

    public abstract boolean a();
}
