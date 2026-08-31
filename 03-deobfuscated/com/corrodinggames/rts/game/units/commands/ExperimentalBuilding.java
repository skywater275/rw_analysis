/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.game.units.AirUnit;
import com.corrodinggames.rts.game.units.UnitRegistry;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.UnitFactoryHelper;
import com.corrodinggames.rts.game.units.commands.ExperimentalAirUnit;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class ExperimentalBuilding
extends MobileBuilderBase {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    static Texture[] e = new Texture[10];
    static Texture f = null;
    boolean g;
    static final ActionId h = com.corrodinggames.rts.game.units.actions.ActionId.a(String.valueOf(110));

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.g);
        as2.c(0);
        super.a(as2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        boolean bl = k2.readBoolean();
        if (bl) {
            this.a(2);
        }
        k2.d();
        super.a(k2);
    }

    public static void b() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.land_factory_front);
        b = l2.bO.a(R$drawable.land_factory_front_t2);
        c = l2.bO.a(R$drawable.land_factory_back);
        f = l2.bO.a(R$drawable.land_factory_dead);
        d = com.corrodinggames.rts.game.PlayerState.a(a);
        e = com.corrodinggames.rts.game.PlayerState.a(b);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.b;
    }


    public boolean L() {
        GlobalState l2 = GlobalState.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.m = null;
        this.M = f;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.p, 0.8f, this.eo, this.ep);
        return true;
    }


    public void S() {
        super.S();
        this.m = this.isDead ? null : c;
    }


    public Texture d() {
        if (this.isDead) {
            return f;
        }
        if (this.player == null) {
            return d[d.length - 1];
        }
        if (!this.g) {
            return d[this.player.R()];
        }
        return e[this.player.R()];  // 02b m.java L79: e[bX.R()]
    }


    public Texture k() {
        return null;
    }

    public ExperimentalBuilding(boolean bl) {
        super(bl);
        this.M = a;
        this.m = c;
        this.b(this.M);
        this.ck = this.cj = 30.0f;
        this.hp = this.maxHp = 1200.0f;
        this.S(3);
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 3);
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(h)) {  // 02b m.java: var1.j.equals(h)
            com.corrodinggames.rts.game.PlayerState.b((UnitInstance) this);
            this.a(2);
            com.corrodinggames.rts.game.PlayerState.c(this);
            this.W();
        } else {
            super.a(j2);
        }
    }


    public void a(int n2) {
        if (n2 == 1) {
            this.g = false;
        } else if (n2 == 2 && !this.g) {
            this.g = true;
        }
        this.S();
    }


    public ActionId cm() {
        if (!this.g) {
            return h;
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new com.corrodinggames.rts.game.units.actions.RallyPointAction());
        if (n2 == 1) {
            arrayList.add(new com.corrodinggames.rts.game.units.commands.CustomBuildingUnit());
        }
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.h, 1.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.i, 2.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.j, 3.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.k, 4.0f));
        if (n2 >= 2) {
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.s, 5.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.w, 6.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.x, 7.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.r, 8.0f));
        }
    }


    public ArrayList N() {
        return this.K().a(this.V());
    }


    public int V() {
        if (this.g) {
            return 2;
        }
        return 1;
    }

    @Override
    public UnitFactoryHelper du() {
        return new ExperimentalAirUnit(this);
    }


    public boolean bJ() {
        return true;
    }


    public float db() {
        return super.db() - 8.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }
}
