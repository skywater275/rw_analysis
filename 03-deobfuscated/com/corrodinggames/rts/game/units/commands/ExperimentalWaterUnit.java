/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitRegistry;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.RallyPointAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ActionType$1;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class ExperimentalWaterUnit
extends MobileBuilderBase {
    static Texture a = null;
    static Texture b = null;
    static Texture[] c = new Texture[10];
    static Texture[] d = new Texture[10];
    static Texture e = null;
    boolean f;
    static GameAction g = new ExperimentalWaterUnit$1(110);

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.f);
        as2.c(0);
        super.a(as2);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        boolean bl = k2.e();
        if (bl) {
            this.M();
        }
        k2.d();
        super.a(k2);
    }

    public static void b() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.experimental_unit_factory_front);
        b = l2.bO.a(R$drawable.experimental_unit_factory_base);
        e = l2.bO.a(R$drawable.experimental_unit_factory_dead);
        c = com.corrodinggames.rts.game.PlayerState.a(a);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.G;
    }


    public boolean L() {
        GlobalState l2 = GlobalState.B();
        this.previewTexture = null;
        this.M = e;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.h);
        return true;
    }


    public void a(int n2) {
    }


    public Texture d() {
        if (this.isDead) {
            return e;
        }
        if (this.player == null) {
            return c[c.length - 1];
        }
        if (!this.f) {
            return c[this.player.getTeamIndex()];
        }
        return d[this.player.getTeamIndex()];
    }


    public void S() {
        super.S();
        this.previewTexture = this.isDead ? null : b;
    }


    public Texture k() {
        return null;
    }

    public ExperimentalWaterUnit(boolean bl) {
        super(bl);
        this.M = a;
        this.previewTexture = b;
        this.b(this.M);
        this.ck = this.cj = 55.0f;
        this.hp = this.maxHp = 3200.0f;
        this.S(4);
        this.n.a(-2, -2, 2, 2);  // 02b n (sourceRect 幻觉)
        this.o.a(-2, -2, 2, 4);  // 02b o (destRect 幻觉)
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(g.N())) {
            this.M();
        } else {
            super.a(j2);
        }
    }

    public void M() {
        if (!this.f) {
            this.f = true;
            this.S();
        }
    }


    public com.corrodinggames.rts.game.units.actions.ActionId cm() {
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new com.corrodinggames.rts.game.units.actions.RallyPointAction());
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.F, 2.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.O, 3.0f));
    }


    public ArrayList N() {
        return this.K().a(this.V());
    }


    public boolean bJ() {
        return true;
    }


    public int V() {
        return 2;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }


}
