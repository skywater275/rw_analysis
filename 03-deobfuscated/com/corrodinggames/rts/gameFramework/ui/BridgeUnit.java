/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.R;
import com.corrodinggames.rts.game.units.MovableUnit;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class BridgeUnit
extends com.corrodinggames.rts.game.units.MovableUnit {
    com.corrodinggames.rts.game.units.custom.effects.EffectManager a = new com.corrodinggames.rts.game.units.custom.effects.EffectManager();

    public com.corrodinggames.rts.game.units.UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.R;
    }

    public BridgeUnit() {
        super(true);
        this.player = com.corrodinggames.rts.game.PlayerState.i;
    }


    public void a(float f2) {
        super.a(f2);
        com.corrodinggames.rts.gameFramework.GlobalState.a("PlaceholderUnit was updated");
        this.ci();
    }


    public strictfp boolean t() {
        return true;
    }

    @Override
    public strictfp boolean u() {
        return true;
    }


    public com.corrodinggames.rts.game.units.custom.effects.EffectManager df() {
        return this.a;
    }

    public void a(com.corrodinggames.rts.game.units.custom.effects.EffectManager f2) {
        this.a = f2;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }
}
