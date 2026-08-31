/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.GameTimer;

public abstract class PacketBuilder {
    public abstract void a(GameTimer var1);

    public void a(float f, float f2) {
        this.a(f);
    }

    public abstract void a(float var1);

    public abstract void a(boolean var1);

    public abstract void d();

    public abstract void a();

    public abstract void b();

    public abstract boolean c();

    public void e() {
    }
}
