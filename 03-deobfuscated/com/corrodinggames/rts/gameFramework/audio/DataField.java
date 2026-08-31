/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.audio;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.audio.DataFieldFloat;
import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;
import java.util.ArrayList;

public class DataField
extends DataFieldFloat {
    private final String fieldName;
    private final int fieldType;
    private final ArrayList fieldListeners;

    public DataField(int n2, ArrayList arrayList) {
        this.fieldType = n2;
        this.fieldListeners = arrayList;
        this.fieldName = "Team " + PlayerState.a(n2);
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public String b() {
        return this.fieldName;
    }

    @Override
    public int c() {
        return PlayerState.i(this.fieldType);
    }

    @Override
    public int d() {
        return PlayerState.i(this.fieldType);
    }

    @Override
    public int a(DataFieldProvider f2) {
        int n2 = 0;
        for (PlayerState n3 : (java.util.Collection<PlayerState>) (java.util.Collection) this.fieldListeners) {
            n2 += f2.a(n3);
        }
        return n2;
    }
}
