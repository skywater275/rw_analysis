/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.weapons;

import com.corrodinggames.rts.game.units.weapons.UnitComponent;
import com.corrodinggames.rts.game.units.weapons.ComponentType;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;

public class FloatComponent
extends UnitComponent {
    float b;

    @Override
    public ComponentType b() {
        return com.corrodinggames.rts.game.units.weapons.ComponentType.a;
    }

    @Override
    public void a(UnitType y2, OutputNetStream as2) {
        as2.a(this.b);
        super.a(y2, as2);
    }

    @Override
    public void a(UnitType y2, InputNetStream k2) {
        this.b = k2.readFloat();
        super.a(y2, k2);
    }
}
