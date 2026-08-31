/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class ExperimentalWallUnit
extends MobileBuilderBase {
    static Texture a = null;
    static Texture[] b = new Texture[10];
    static Texture c = null;

    public static void b() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.wall_v);
        c = l2.bO.a(R$drawable.wall_v);
        b = com.corrodinggames.rts.game.PlayerState.a(a);
    }


    public Texture d() {
        if (this.isDead) {
            return c;
        }
        if (this.player == null) {
            return b[b.length - 1];
        }
        return b[this.player.R()];
    }


    public Texture k() {
        return null;
    }


    public void a(int n2) {
    }

    public ExperimentalWallUnit(boolean bl) {
        super(bl);
        this.b(a);
        this.ck = this.cj = 15.0f;
        this.hp = this.maxHp = 700.0f;
        this.M = a;
        this.n.a(0, 0, 1, 0);
        this.o.a(0, 0, 1, 0);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.I;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }
}
