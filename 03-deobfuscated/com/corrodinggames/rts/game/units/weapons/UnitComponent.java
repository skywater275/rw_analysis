/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.weapons;
import com.corrodinggames.rts.game.units.actions.StopAction;

import com.corrodinggames.rts.game.units.weapons.ComponentType;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;

public abstract class UnitComponent {
    int a;

    public UnitComponent() {
    }

    public UnitComponent(int n) {
        this.a = n;
    }

    public int a() {
        return this.a;
    }

    public abstract ComponentType b();

    public void a(UnitType y2, float f) {
    }

    public void a(UnitType y2, OutputNetStream as2) {
        as2.a(this.a);
    }

    public void a(UnitType y2, InputNetStream k2) {
        this.a = k2.readInt();
    }
}
