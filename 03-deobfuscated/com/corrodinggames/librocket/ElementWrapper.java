/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket;

import com.LibRocket$TextureHolder;
import com.corrodinggames.librocket.LibRocketBridge;
import com.corrodinggames.rts.game.units.UnitTypeHandle;

public abstract class ElementWrapper
extends LibRocket$TextureHolder {
    public String a;
    public boolean b;
    public boolean c;
    public boolean d;
    public float e;
    public UnitTypeHandle f;
    final /* synthetic */ LibRocketBridge g;

    public ElementWrapper(LibRocketBridge b2) {
        super(b2);
        this.g = b2;
        this.e = 1.0f;
    }

    public abstract boolean isEnabled();
}
