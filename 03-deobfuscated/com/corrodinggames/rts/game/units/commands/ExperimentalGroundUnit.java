/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.game.units.UnitRegistry;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.RallyPointAction;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class ExperimentalGroundUnit
extends MobileBuilderBase {
    static Texture a = null;
    static Texture b = null;
    static Texture[] c = new Texture[10];
    static Texture[] d = new Texture[10];
    static Texture e = null;
    int f = 1;
    float g = 0.0f;
    static final ActionId h = com.corrodinggames.rts.game.units.actions.ActionId.a(String.valueOf(110));

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.f);
        super.a(as2);
    }



    public static void b() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.air_factory);
        b = l2.bO.a(R$drawable.air_factory_t2);
        e = l2.bO.a(R$drawable.air_factory_dead);
        c = com.corrodinggames.rts.game.PlayerState.a(a);
        d = com.corrodinggames.rts.game.PlayerState.a(b);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.c;
    }


    public boolean L() {
        this.M = e;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.d);
        return true;
    }


    @Override
    public Texture d() {  // 02b units/d/a.java L56-59: d() 单位贴图 (旧误名 k())
        if (this.isDead) {
            return e;
        }
        if (this.player == null) {
            return c[c.length - 1];  // 02b: c[...]
        }
        if (this.f == 1) {
            return c[this.player.R()];  // 02b: c[bX.R()]
        }
        return d[this.player.R()];
    }


    @Override
    public Texture k() {  // 02b a.java L64-66: k() return null (旧误名 gete2)
        return null;
    }

    public ExperimentalGroundUnit(boolean bl) {
        super(bl);
        this.M = a;
        this.T(40);
        this.U(61);
        this.ck = this.cj = 30.0f;
        this.hp = this.maxHp = 1000.0f;
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 2);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        this.g = com.corrodinggames.rts.gameFramework.GameUtils.a(this.g, f2);
        if (this.g == 0.0f) {
            this.g = 27.0f;
            ++this.s;
            if (this.s > 4) {
                this.s = 0;
            }
        }
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(h)) {  // 02b: var1.j (BuilderUnit ActionId)
            com.corrodinggames.rts.game.PlayerState.b((UnitInstance) this);
            this.a(2);
            com.corrodinggames.rts.game.PlayerState.c(this);
            this.W();
        } else {
            super.a(j2);
        }
    }


    public int V() {
        return this.f;
    }


    public void a(int n2) {
        if (n2 == 1) {
            this.f = 1;
        } else if (n2 == 2 && this.f == 1) {
            this.f = 2;
        }
        this.S();
    }


    public ActionId cm() {
        if (this.f == 1) {
            return h;
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new RallyPointAction());  // 02b units.a.o = RallyPointAction
        if (n2 == 1) {
            arrayList.add(new CustomGroundUnit());
        }
        if (n2 > 1) {
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.z, 3.2f));
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.n, 4.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.M, 5.0f));
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


    // v19.112d 补插 (02b units/d/a.java 读方法 a(j.k))
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        if(var1.b() >= 17) {
           int var2 = var1.f();
           this.a(var2);
        }

        super.a(var1);
   }
}
