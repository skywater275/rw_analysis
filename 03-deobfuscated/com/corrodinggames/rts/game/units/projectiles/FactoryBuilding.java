/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ReclaimAction;
import com.corrodinggames.rts.game.units.actions.BuildQueueAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.MovementPath;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.ai;
import java.util.ArrayList;

public strictfp class FactoryBuilding
extends AbstractBuildingBase
implements MovementPath {
    public static com.corrodinggames.rts.gameFramework.rendering.Texture e;  // 02b units/e/b.java m.e
    public static com.corrodinggames.rts.gameFramework.rendering.Texture f;
    public static Texture[] h = new Texture[10];  // 02b e/b.java L27: public static m.e[] h (FactoryAction2 v() 引用)

    static Texture buildQueueList = null;  // 02b e/b.a
    public static Texture defaultBuildUnit = null;
    static Texture productionProgress = null;  // 02b e/b.c
    public static Texture[] costResources = new Texture[10];
    public static Texture productionResource = null;
    public static Texture consumptionResource = null;
    static Texture outputResource = null;  // 02b e/b.g
    public static Texture[] storageResources = new Texture[10];
    PointF[] i = new PointF[6];
    PointF[] j = new PointF[this.i.length];
    static Paint k;
    static Paint l;
    static Paint m;
    static GameAction n;

    public UnitRegistry f() {
        return com.corrodinggames.rts.game.units.UnitRegistry.h;
    }


    public PointF[] b() {
        return this.i;
    }


    public PointF[] e_() {
        return this.j;
    }

    @Override
    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return storageResources[this.player.getTeamIndex()];
    }

    public static void K() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        buildQueueList = l2.bO.a(R$drawable.builder);
        defaultBuildUnit = l2.bO.a(R$drawable.builder_dead);
        productionProgress = com.corrodinggames.rts.game.units.UnitInstance.a(buildQueueList, buildQueueList.m(), buildQueueList.l());
        costResources = com.corrodinggames.rts.game.PlayerState.a(buildQueueList);
        productionResource = l2.bO.a(R$drawable.builder_charge);
        consumptionResource = l2.bO.a(R$drawable.builder_decharge);
        outputResource = l2.bO.a(R$drawable.unit_icon_builder);
        storageResources = com.corrodinggames.rts.game.PlayerState.a(outputResource);
    }


    public boolean a(UnitInstance am2) {
        if (am2.q()) {
            return false;
        }
        return am2.isFactoryBuilding();
    }


    public Texture d() {
        if (this.isDead) {
            return defaultBuildUnit;
        }
        return costResources[this.player.getTeamIndex()];
    }


    public Texture k() {
        return productionProgress;
    }


    public Texture d(int n2) {
        return null;
    }


    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.M = defaultBuildUnit;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.b);
        return true;
    }

    public FactoryBuilding(boolean bl) {
        super(bl);
        k = new Paint();
        k.a(40, 0, 255, 0);
        k.a(true);
        k.a(2.0f);
        k.a(Paint$Cap.b);
        l = new Paint();
        l.a(k);
        l.a(55, 255, 60, 60);
        m = new Paint();
        m.a(60, 255, 255, 255);
        this.T(20);
        this.U(20);
        this.cj = 10.0f;
        this.ck = this.cj + 2.0f;
        this.hp = this.maxHp = 170.0f;
        this.M = buildQueueList;
        for (int i2 = 0; i2 < this.i.length; ++i2) {
            this.i[i2] = new PointF();
            this.j[i2] = new PointF();
        }
    }

    public static void b(float f2, MovementPath d2) {  // 02b e/b.java L156: b(float, units.d) — units.d=MovementPath 接口铁证 (WallBuilding 为幻觉)
        UnitType y2 = (UnitType)((Object)d2);
        UnitInstance am2 = y2.X();
        if (am2 != null) {
            boolean bl = y2.Y();
            if (!bl && y2.aO) {
                return;
            }
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            PointF[] pointFArray = d2.b();
            Paint paint = k;
            if (bl) {
                paint = l;
            }
            ai ai2 = y2.bn();
            for (int i2 = 0; i2 < pointFArray.length; ++i2) {
                PointF pointF = pointFArray[i2];
                float f3 = am2.eo + pointF.a - l2.cw;
                float f4 = am2.ep - am2.eq + pointF.b - l2.cx;
                l2.bO.a(ai2.a + pointF.a * 0.15f - l2.cw, ai2.b - ai2.c + pointF.b * 0.15f - l2.cx - y2.eq, f3, f4, paint);
                l2.bO.k();
                l2.bO.b(f3, f4);
                l2.bO.a(0.5f, 0.5f);
                if (bl) {
                    l2.bO.a(f, 0.0f, 0.0f, m);
                } else {
                    l2.bO.a(e, 0.0f, 0.0f, m);
                }
                l2.bO.l();
            }
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.isDead) {
            a(f2, this);
        }
    }


    public void a(float f2, boolean bl) {
super.a(f2, bl);  // 02b am.a(f,bl);
        if (!this.isDead) {
            b(f2, this);
        }
    }


    public float e(int n2) {
        return 30.0f;
    }


    public float f(int n2) {
        return 1.3f;
    }

    @Override
    public boolean c(float f2) {
        float f3;
        if (!super.c(f2)) {
            return false;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.isDead && (f3 = this.cL[0].maxRotationAngle / this.e(0)) != 0.0f) {
            ai ai2 = this.bn();
            l2.bO.i();
            l2.bO.b(ai2.a - l2.cw, ai2.b - ai2.c - l2.cx);
            l2.bO.a(f3, f3);
            if (this.Y()) {
                l2.bO.a(f, 0.0f, 0.0f, null);
            } else {
                l2.bO.a(e, 0.0f, 0.0f, null);
            }
            l2.bO.j();
        }
        return true;
    }


    public void a(UnitInstance am2, int n2) {
    }


    public float m() {
        return 30.0f;
    }


    public float b(int n2) {
        return 100.0f;
    }


    public float z() {
        if (this.cK()) {
            return 0.6f;
        }
        return 0.8f;
    }


    public float A() {
        if (this.cK()) {
            return 1.7f;
        }
        return 3.8f;
    }


    public float B() {
        return 0.35f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean l() {
        return false;
    }


    public float C() {
        return 0.04f;
    }


    public float D() {
        return 0.1f;
    }


    public void a(GameAction s2, boolean bl) {
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(n);
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.a, 1, 1));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.f, 1, 2));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.g, 1, 3));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.b, 1, 4));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.c, 1, 5));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.d, 1, 6));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.y, 1, 7));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.B, 1, 8));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.J, 1, 9));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.G, 1, 10));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.C, 1, 14));
        arrayList.add(new BuildQueueAction(com.corrodinggames.rts.game.units.UnitRegistry.D, 1, 15));
    }


    public ArrayList N() {
        return this.f().a(this.V());
    }


    public boolean E() {
        return true;
    }


    public float g(int n2) {
        return 10.0f;
    }


    public boolean F() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && !this.isDead;
    }


    public float G() {
        return 1.0f;
    }


    public float H() {
        return 1.0f;
    }


    public boolean g(UnitInstance am2, boolean bl) {
        return true;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.f();
    }

    static {
        n = new com.corrodinggames.rts.game.units.actions.ReclaimAction(false);
    }

    public static void a(float f2, MovementPath d2) {  // 02b e/b.java L117-154: 履带/腿动画插值 — units.d=MovementPath 接口铁证
        com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType) d2;
        android.graphics.PointF[] pointFArray = d2.b();
        android.graphics.PointF[] pointFArray2 = d2.e_();
        com.corrodinggames.rts.game.units.UnitInstance am2 = y2.X();
        y2.aN = am2 != null;
        if (am2 != null) {
            for (int i2 = 0; i2 < pointFArray.length; ++i2) {
                android.graphics.PointF pointF = pointFArray[i2];
                android.graphics.PointF pointF2 = pointFArray2[i2];
                pointF.a = com.corrodinggames.rts.gameFramework.GameUtils.a(pointF.a, pointF2.a, 0.1f * f2);
                pointF.b = com.corrodinggames.rts.gameFramework.GameUtils.a(pointF.b, pointF2.b, 0.1f * f2);
                pointF.a += (pointF2.a - pointF.a) * 0.04f * f2;
                pointF.b += (pointF2.b - pointF.b) * 0.04f * f2;
                float f3 = am2.cj * 0.75f;
                if (com.corrodinggames.rts.gameFramework.GameUtils.c(pointF.a - pointF2.a) < 1.0f) {
                    pointF2.a = com.corrodinggames.rts.gameFramework.GameUtils.d(-f3, f3);
                }
                if (com.corrodinggames.rts.gameFramework.GameUtils.c(pointF.b - pointF2.b) < 1.0f) {
                    pointF2.b = com.corrodinggames.rts.gameFramework.GameUtils.d(-f3, f3);
                }
            }
        } else if (pointFArray[0].a != 0.0f || pointFArray[0].b != 0.0f) {
            for (int i3 = 0; i3 < pointFArray.length; ++i3) {
                pointFArray[i3].a = 0.0f;
                pointFArray[i3].b = 0.0f;
                pointFArray2[i3].a = 0.0f;
                pointFArray2[i3].b = 0.0f;
            }
        }
    }

}
