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
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.io.IOException;

public class FabricatorUnit
extends MobileBuilderBase {
    static Texture a = null;
    static Texture b = null;
    static Texture[] c = new Texture[10];
    static Texture[] d = new Texture[10];
    static Texture e = null;
    int f = 1;  // 02b v.java L18: f=1 (ModDownloader$1 为幻觉)
    float g = 0.0f;
    int h = 0;
    public static int i = 0;
    static GameAction j = new FabricatorUnit$1(102);
    static ArrayList k = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.f);
        super.a(as2);
    }



    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.N;
    }

    public static void K() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.supply_depot);
        b = l2.bO.a(R$drawable.supply_depot_t2);
        c = com.corrodinggames.rts.game.PlayerState.a(a);
        d = com.corrodinggames.rts.game.PlayerState.a(b);
        e = l2.bO.a(R$drawable.supply_depot_dead);
    }


    public boolean L() {
        GlobalState l2 = GlobalState.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = e;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.p, 0.8f, this.eo, this.ep);
        return false;
    }


    public Texture d() {
        if (this.isDead) {
            return e;
        }
        if (this.player == null) {
            return c[c.length - 1];  // 02b v.java L61: c[c.length-1]
        }
        if (this.f == 1) {  // 02b v.java L61: f==1
            return c[this.player.R()];
        }
        return d[this.player.R()];
    }


    public Texture k() {
        return null;
    }

    public FabricatorUnit(boolean bl) {
        super(bl);
        this.M = a;
        this.a(this.M, 1);  // 02b v.java L71: this.a(this.M, 1)
        this.ck = this.cj = 20.0f;
        this.hp = this.maxHp = 800.0f;
        this.n.a(-1, -1, 0, 0);
        this.o.a(this.n);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(j.N())) {
            this.M();
            this.W();
        }
    }


    public void a(int n2) {
        this.f = n2;
    }

    public void M() {
        if (this.f == 1) {  // 02b v.java L100
            this.f = 2;
            this.S();
        }
    }


    public ActionId cm() {
        if (this.f == 1) {  // 02b v.java L100
            return j.N();
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public ArrayList N() {
        return k;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }

    static {
        k.add(j);
    }


    // v19.112d 琛ユ彃 (02b units/d/v.java)
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        int var2 = var1.f();
        this.a(var2);
        super.a(var1);
   }
}

