/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.actions.StopAction;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ReclaimAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.game.units.commands.AutoRepairCallback;
import com.corrodinggames.rts.game.units.projectiles.FactoryBuilding;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class RepairBayUnit
extends BuildSlot
implements com.corrodinggames.rts.game.units.MovementPath {
    static Texture a = null;
    static Texture[] b = new Texture[10];
    static Texture c = null;
    float d;
    public static AutoRepairCallback e = new AutoRepairCallback(true);  // 02b d/r: static s e = new s(true) (s=AutoRepairCallback, 闈?GameAction)
    Rect f = new Rect();
    Rect g = new Rect();
    static ArrayList h = new ArrayList();
    PointF[] i = new PointF[6];
    PointF[] j = new PointF[this.i.length];


    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        super.a(as2);
    }



    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.B;
    }

    public static void M() {
        GlobalState l2 = GlobalState.B();
        a = l2.bO.a(R$drawable.repair_bay);
        c = l2.bO.a(R$drawable.repair_bay_dead);
        b = com.corrodinggames.rts.game.PlayerState.a(a);
    }


    public boolean L() {
        this.M = c;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.c);
        return true;
    }


    public Texture d() {
        if (this.isDead) {
            return c;
        }
        if (this.player == null) {
            return b[b.length - 1];
        }
        return b[this.player.getTeamIndex()];
    }


    public Texture k() {
        return null;
    }


    public void a(int n2) {
    }

    public RepairBayUnit(boolean bl) {
        super(bl);
        this.M = a;
        this.b(a);
        this.ck = this.cj = 30.0f;
        this.hp = this.maxHp = 1000.0f;
        this.n.a(-1, -1, 1, 1);  // 02b n (sourceRect 楠炴槒顫?
        this.o.a(-1, -1, 1, 1);  // 02b o (destRect 楠炴槒顫?
        for (int j = 0; j < this.i.length; ++j) {
            this.i[j] = new PointF();
            this.j[j] = new PointF();
        }
    }


    public int y() {
        return 230;
    }


    public float c(UnitInstance am2) {
        return 0.2f;
    }


    public boolean a(UnitInstance am2) {
        return !am2.q();
    }

    public static WeaponAction a(UnitType y2, float f2, float f3, boolean bl) {
        GlobalState l2 = GlobalState.B();
        e.a((float)y2.y() + f3, bl);
        l2.cc.a(y2.eo, y2.ep, (float)y2.y() + f3, y2, f2, e);
        UnitInstance am2 = e.e;
        if (am2 != null) {
            WeaponAction au2 = y2.ao();  // 02b r.java L104: var0.ao() (insertWaypointFront 涓哄够瑙?
            au2.b(am2);
            if (au2 != null) {
                au2.k = f3;
                au2.m = true;
                return au2;
            }
        }
        return null;
    }


    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        this.d += f2;
        if (this.aq() && this.d > 40.0f) {
            this.d = 0.0f;
            a(this, f2, 0.0f, false);
        }
        if (!this.isDead) {
            com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.a(f2, this);
        }
    }


    public boolean c(float f2) {
        return super.c(f2);
    }


    public void isVisibleTo(float f2, boolean bl) {
super.a(f2, bl);  // 02b am.a(f,bl);
        if (!this.isDead) {
            com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.b(f2, this);
        }
    }


    public boolean l() {
        return false;
    }


    public void a(UnitInstance am2, int n2) {
        throw new RuntimeException("Unit cannot shoot");
    }


    public float b(int n2) {
        return 0.0f;
    }


    public float c(int n2) {
        return 0.0f;
    }


    public PointF E(int n2) {
        PointF pointF = this.G(n2);
        float f2 = pointF.a + 0.0f;
        float f3 = pointF.b - 33.0f;
        bg.a(f2, f3);
        return bg;
    }


    public ArrayList N() {
        return h;
    }

    @Override
    public PointF[] b() {
        return this.i;
    }

    @Override
    public PointF[] e_() {
        return this.j;
    }


    public float m() {
        return this.y();
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.y();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public boolean g(UnitInstance am2, boolean bl) {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }

    static {
        h.add(new ExperimentalBuilding(true));
        h.add(new com.corrodinggames.rts.game.units.actions.RepairAction());
    }


    // v19.112d 鐞涖儲褰?(02b units/d/r.java 鐠囩粯鏌熷▔?a(j.k))
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        super.a(var1);
   }
}

