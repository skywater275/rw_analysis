/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.PerformanceMonitor$1;
import com.corrodinggames.rts.game.units.commands.PerformanceMonitor$2;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public class UnitActionHelper
extends MobileBuilderBase {
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] a = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture b = null;
    int c;
    static com.corrodinggames.rts.gameFramework.rendering.Texture d = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] e = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    PointF f = new PointF();
    Rect g = new Rect();
    static GameAction h = new UnitActionHelper$1(142);
    static GameAction i = new UnitActionHelper$2(143);
    static ArrayList j = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.c);
        super.a(as2);
    }




    public com.corrodinggames.rts.gameFramework.rendering.Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return e[this.player.R()];  // 02b q.java L44: e[bX.R()] (SoundRegistry 涓哄够瑙?
    }

    public static void b() {
        GlobalState l2 = GlobalState.B();
        b = l2.bO.a(R$drawable.nuke_launcher_dead);
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.a(R$drawable.nuke_launcher);
        a = com.corrodinggames.rts.game.PlayerState.a(e2);
        e2.n();
        d = l2.bO.a(R$drawable.unit_icon_building);
        e = com.corrodinggames.rts.game.PlayerState.a(d);
    }


    public int bp() {
        return 20;
    }


    public boolean L() {
        GlobalState l2 = GlobalState.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.h);
        float f2 = this.eo;
        float f3 = this.ep;
        float f4 = 0.0f;
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(f2, f3, this.eq, Color.a(255, 255, 255, 255));
        if (e2 != null) {
            e2.G = 8.0f;
            e2.F = 5.0f;
            e2.E = 0.9f;
            e2.W = e2.V = 20.0f;
            e2.r = true;
        }
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        e2 = l2.bR.c(f2, f3, f4, -1127220);
        if (e2 != null) {
            e2.G = 0.2f;
            e2.F = 2.0f;
            e2.ar = (short)2;  // 02b q.java L86/L195: var.ar = 2 (F25 $N 数字污染)
            e2.W = e2.V = 45.0f;
            e2.U = 0.0f;
        }
        float f5 = 40.0f;
        float f6 = 120.0f;
        l2.bR.a(this.eo, this.ep, this.eq, f5, f6);
        return true;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return b;
        }
        return a[this.player.R()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return null;
    }


    public void a(int n2) {
    }

    public UnitActionHelper(boolean bl) {
        super(bl);
        this.M = a[a.length - 1];
        this.b(this.M);
        this.ck = this.cj = 40.0f;
        this.hp = this.maxHp = 1500.0f;
        this.n.a(-2, -1, 2, 1);  // 02b q.java L116: n.a(-2,-1,2,1)
        this.o.a(-2, -1, 2, 2);  // 02b q.java L117: o.a(-2,-1,2,2)
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
    }


    public PointF E(int n2) {
        bg.a(this.eo, this.ep - 3.0f);
        return bg;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
    }

    @Override
    public float c(int n2) {
        return 4.0f;
    }


    public boolean b(int n2, float f2) {
        return false;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.C;
    }

    @Override
    public boolean l() {
        return false;
    }


    public float g(int n2) {
        return 1.0f;
    }

    @Override
    public float bV() {
        return super.bV();  // 02b q.java L159: super.bV() (isDead 涓哄够瑙?
    }

    public void a(float f2, float f3) {
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2;
        GlobalState l2 = GlobalState.B();
        if (this.c <= 0) {
            return;
        }
        float f4 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f2, f3);
        if (f4 < 202500.0f) {
            if (this.player == l2.bs) {
                l2.bS.b("Nuke target too close");
            }
            return;
        }
        --this.c;
        int n2 = 0;
        PointF pointF = this.E(n2);
        float f5 = 5.0f;
        MovementController f6 = UnitActionHelper.a((UnitInstance) this, pointF.a, pointF.b, f2, f3);  // 02b q.java L176: a(this,...) (q 涓哄够瑙?
        f6.i = f5;
        com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        if (e3 != null) {
            e3.U = f5;
            e3.G = 2.1f;
            e3.F = 2.1f;
            e3.ar = (short)2;  // 02b q.java L183: var9.ar = 2
            e3.s = true;
            e3.t = 70.0f;
            e3.W = e3.V = 370.0f;
            e3.E = 1.0f;
        }
        if ((e2 = l2.bR.d(pointF.a, pointF.b, 0.0f, -1)) != null) {
            e2.G = 1.0f;
            e2.F = 3.1f;
            e2.ar = (short)2;  // 02b q.java L86/L195: var.ar = 2 (F25 $N 数字污染)
            e2.W = e2.V = 170.0f;
            e2.U = f5 + 20.0f;
        }
        float f7 = 0.8f;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.D, 0.27f, f7, pointF.a, pointF.b);
    }

    public static MovementController a(UnitInstance am2, float f2, float f3, float f4, float f5) {
        MovementController f6 = com.corrodinggames.rts.game.MovementController.a(am2, f2, f3);
        f6.S(10);
        f6.P = 0;
        f6.Q = 1;  // 02b q.java L211: var5.Q = 1
        f6.R = 1;  // 02b q.java L212: var5.R = 1
        f6.x = 1.0f;
        f6.aC = true;
        f6.m = true;
        f6.n = f4;
        f6.o = f5;
        f6.h = 99999.0f;
        f6.t = 0.1f;
        f6.r = 2.7f;
        f6.ar = Color.a(255, 225, 225, 225);
        f6.U = 300.0f;
        f6.aH = true;
        f6.aM = true;
        f6.aQ = true;
        f6.C = true;
        f6.D = true;
        f6.aI = 80.0f;
        f6.aJ = 100.0f;
        f6.aL = 1.1f;
        f6.Y = 5400.0f;
        f6.Z = 250.0f;
        f6.ad = true;
        f6.ae = false;
        f6.ao = true;
        f6.X = f6.W = 75.0f;
        f6.aY = true;
        GlobalState l2 = GlobalState.B();
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(f6, -1118720);
        if (e2 != null) {
            e2.W = e2.V = 1300.0f;
            e2.E = 0.2f;
            e2.G = 1.0f;
        }
        return f6;
    }

    public void M() {
        ++this.c;
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(i.N())) {  // 02b q.java L257: var1.j (buildState 涓哄够瑙?
            this.M();
        }
    }


    public ActionId cm() {
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public void a(com.corrodinggames.rts.game.units.actions.GameAction s2, boolean bl, PointF pointF, UnitInstance am2) {  // 02b q.java L267: a(a.s,boolean,PointF,am) (AutoRepairCallback 涓哄够瑙?
        if (bl) {
            return;
        }
        if (s2 == h) {
            if (pointF == null) {
                com.corrodinggames.rts.gameFramework.network.NetEngine.sendPacketToClients("action:" + h.N() + " needs point but it is missing");
                return;
            }
            this.a(pointF.a, pointF.b);
            return;
        }
        super.a(s2, bl, pointF, am2);
    }


    public ArrayList N() {
        return j;
    }


    public void e(float f2) {
        super.e(f2);
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.b((UnitInstance) this, 450.0f, false);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }

    static {
        j.add(h);
        j.add(i);
    }


    // v19.112d 琛ユ彃 (02b units/d/q.java)
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        this.c = var1.f();
        super.a(var1);
   }
}
