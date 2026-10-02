/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.RallyPointAction;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.UpgradeToT2Action;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;

public strictfp class Structures
extends MobileBuilderBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    static Texture[] e = new Texture[10];
    static Texture f = null;
    static final ActionId g = com.corrodinggames.rts.game.units.actions.ActionId.a(String.valueOf(110));  // 02b: a.c g

    public static void b() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.sea_factory);
        b = l2.bO.a(R$drawable.sea_factory_t2);
        f = l2.bO.a(R$drawable.sea_factory_dead);
        d = com.corrodinggames.rts.game.PlayerState.a(a);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.d;
    }


    public boolean L() {
        this.m = null;
        this.M = f;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.d);
        return true;
    }


    public Texture d() {
        if (this.isDead) {
            return f;
        }
        if (this.player == null) {
            return d[d.length - 1];
        }
        if (this.r == 1) {
            return d[this.player.R()];
        }
        return e[this.player.R()];  // 02b: e[bX.R()]
    }


    public Texture k() {
        return null;
    }

    public Structures(boolean bl) {
        super(bl);
        this.M = a;
        this.b(a);
        this.ck = this.cj = 45.0f;
        this.hp = this.maxHp = 1000.0f;
        this.S(2);
        this.n.a(-1, -1, 1, 2);
        this.o.a(-2, -1, 2, 4);
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(g)) {  // 02b: var1.j
            com.corrodinggames.rts.game.PlayerState.b((UnitInstance) this);
            this.a(2);
            com.corrodinggames.rts.game.PlayerState.c(this);
            this.W();
        } else {
            super.a(j2);
        }
    }

    @Override
    public int dv() {
        return -20;
    }


    public int V() {
        return this.r;
    }


    public void a(int n2) {
        if (n2 == 1) {
            this.r = 1;
        } else if (n2 == 2 && this.r == 1) {
            this.r = 2;
        }
        this.S();
    }


    public ActionId cm() {
        if (this.r == 1) {
            return g;
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new RallyPointAction());  // 02b units.a.o = RallyPointAction
        arrayList.add(new UpgradeToT2Action());
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.L, 1.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.p, 2.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.o, 3.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.s, 4.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.u, 5.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.K, 6.0f));
        if (n2 > 1) {
            // empty if block
        }
    }


    public ArrayList N() {
        return this.K().a(this.V());
    }


    public boolean bJ() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }
}
