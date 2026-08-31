/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.GameTimer;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

public abstract class TeamColor {
    protected MusicController e;

    public void a(int n) {
    }

    public void a(float f) {
    }

    public abstract GameTimer a(String var1);

    public abstract PacketBuilder a();

    public abstract void a(MusicController var1);

    public abstract void b();

    public boolean c() {
        return false;
    }

    public boolean d() {
        return true;
    }

    public int e() {
        return 0;
    }
}
