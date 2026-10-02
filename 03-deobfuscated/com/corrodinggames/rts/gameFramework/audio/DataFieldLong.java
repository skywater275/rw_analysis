/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.audio;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.audio.DataFieldFloat;
import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;

public class DataFieldLong
extends DataFieldFloat {
    private final PlayerState a;

    public DataFieldLong(PlayerState n2) {
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
        return this.a.getMaxUnitCapacity();
    }

    @Override
    public int d() {
        return -1;
    }

    @Override
    public int a(DataFieldProvider f2) {
        return f2.a(this.a);
    }
}
