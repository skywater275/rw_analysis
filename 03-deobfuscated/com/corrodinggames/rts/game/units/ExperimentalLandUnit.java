/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.MovementPath;
import com.corrodinggames.rts.game.units.projectiles.FactoryBuilding;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.GameObject;

public strictfp class ExperimentalLandUnit
extends com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase
implements MovementPath {
    public boolean a;
    PointF[] b = new PointF[6];
    PointF[] c = new PointF[this.b.length];
    static Paint d;
    static Paint e;
    static Paint f;
    int g;
    float h;
    float i;
    int j;

    public UnitRegistry f() {
        return UnitRegistry.h;
    }

    @Override
    public PointF[] b() {
        return this.b;
    }

    @Override
    public PointF[] e_() {
        return this.c;
    }

    @Override
    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return dN[this.player.getTeamIndex()];
    }


    public boolean a(UnitInstance am2) {
        return true;
    }

    @Override
    public Texture d() {
        if (this.isDead) {
            return com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.defaultBuildUnit;  // 02b e/b.b
        }
        return com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.costResources[this.player.getTeamIndex()];  // 02b e/b.d
    }

    @Override
    public float z() {  // 02b g.java L254-256
        return 0.0f;
    }

    @Override
    public Texture k() {
        return null;
    }

    @Override
    public Texture d(int n2) {  // 02b g.java L54-56: d(int) return null
        return null;
    }

    @Override
    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.defaultBuildUnit;  // 02b e/b.b
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.o, 0.8f, this.eo, this.ep);  // 02b a/e.o
        this.bq();
        return true;
    }

    public ExperimentalLandUnit(boolean bl) {
        super(bl);
        d = new Paint();
        d.a(40, 0, 255, 0);
        d.a(true);
        d.a(2.0f);
        d.a(Paint$Cap.b);
        e = new Paint();
        e.a(d);  // 02b g L78: e.a(d)
        e.a(55, 255, 60, 60);  // 02b g L79
        f = new Paint();
        f.a(60, 255, 255, 255);  // 02b g L81
        this.T(20);
        this.U(20);
        this.cj = 10.0f;
        this.eo = -1000.0f;
        this.ep = -1000.0f;
        this.ck = this.cj;
        this.hp = this.maxHp = 170000.0f;
        this.M = com.corrodinggames.rts.game.units.projectiles.FactoryBuilding.defaultBuildUnit;  // 02b e/b.b
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2] = new PointF();
            this.c[i2] = new PointF();
        }
    }

    public static void a(float f2, MovementPath d2) {
        block4: {
            PointF[] pointFArray;
            PointF[] pointFArray2;
            block3: {
                UnitType y2 = (UnitType) ((Object)d2);
                pointFArray2 = d2.b();
                pointFArray = d2.e_();
                UnitInstance am2 = y2.X();
                boolean bl = y2.aN = am2 != null;
                if (am2 == null) break block3;
                for (int i2 = 0; i2 < pointFArray2.length; ++i2) {
                    PointF pointF = pointFArray2[i2];
                    PointF pointF2 = pointFArray[i2];
                    pointF.a = GameUtils.a(pointF.a, pointF2.a, 0.1f * f2);
                    pointF.b = GameUtils.a(pointF.b, pointF2.b, 0.1f * f2);
                    pointF.a += (pointF2.a - pointF.a) * 0.04f * f2;
                    pointF.b += (pointF2.b - pointF.b) * 0.04f * f2;
                    float f3 = am2.cj * 0.75f;
                    if (GameUtils.c(pointF.a - pointF2.a) < 1.0f) {
                        pointF2.a = com.corrodinggames.rts.gameFramework.GameUtils.d(-f3, f3);
                    }
                    if (!(GameUtils.c(pointF.b - pointF2.b) < 1.0f)) continue;
                    pointF2.b = com.corrodinggames.rts.gameFramework.GameUtils.d(-f3, f3);
                }
                break block4;
            }
            if (pointFArray2[0].a == 0.0f && pointFArray2[0].b == 0.0f) break block4;
            for (int i3 = 0; i3 < pointFArray2.length; ++i3) {
                PointF pointF = pointFArray2[i3];
                PointF pointF3 = pointFArray[i3];
                pointF.a = 0.0f;
                pointF.b = 0.0f;
                pointF3.a = 0.0f;
                pointF3.b = 0.0f;
            }
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.isDead) {
            a(f2, this);  // 02b g L141: a(var1, this)
        }
        this.hp = this.maxHp;
        ++this.g;
        this.h += f2;
        this.i += f2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.a) {
            com.corrodinggames.rts.gameFramework.GlobalState.e("Stress test active");
            for (int i2 = 0; i2 < 6000; ++i2) {
                this.w();
            }
            this.ci();
            return;
        }
        if (this.i > 3.0f) {
            this.i = 0.0f;
            this.w();
        }
    }

    public void w() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        ++this.j;
        int n2 = UnitRegistry.ae.size();
        int n3 = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 0, n2 - 1, 1 + this.j);  // 02b L170: f.a(this,...)
        UnitTypeHandle as2 = (UnitTypeHandle) UnitRegistry.ae.get(n3);
        boolean bl2 = true;
        if (ModUnitRegistry.b == as2) {  // 02b L173: custom.l.b
            bl2 = false;
        }
        if (as2 == UnitRegistry.S) {
            bl2 = false;
        }
        if (bl2) {
            UnitInstance am2 = as2.a();
            am2.eo = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 200, (int)l2.bL.i() - 200, 2 + this.g + this.j);
            am2.ep = GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 200, (int)l2.bL.j() - 200, 3 + this.g + this.j + this.j * 9);
            try {
                am2.Q(GameUtils.a((com.corrodinggames.rts.gameFramework.GameObject) this, 0, 3, 4 + this.g + this.j + this.j * 9));
            }
            catch (com.corrodinggames.rts.game.map.MapException f2) {
                throw new RuntimeException(f2);
            }
            com.corrodinggames.rts.game.PlayerState.c(am2);  // 02b L192: n.c(var6)
            if (am2.u()) {
                am2.a();
            }
            if (am2.hasSpawnedDeathEffect()) {
                am2.a();
            }
        }
    }

    @Override
    public void a(float f2, boolean bl2) {
        if (!this.isDead) {
            // empty if block
        }
    }

    @Override
    public float e(int n2) {
        return 0.0f;
    }
    // 02b g.java L215: f(int) 闈?override, 鍘绘帀 @Override
    public float f(int n2) {
        return 0.0f;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return true;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
    }


    public boolean b_() {
        return false;
    }


    public int y() {
        return 850000;
    }


    public float b(UnitInstance am2) {
        return 1.0E7f;
    }


    public float c(UnitInstance am2) {
        return 1.0E7f;
    }

    @Override
    public float m() {  // 02b g.java L246-248 (閺冄嗩嚖閸?getMaxMoveDistance)
        return 30.0f;
    }

    @Override
    public float b(int n2) {
        return 100.0f;
    }



    @Override
    public float A() {
        if (this.cK()) {
            return 4.7f;
        }
        return 4.8f;
    }

    @Override
    public float B() {
        return 0.35f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public float C() {
        return 0.04f;
    }

    @Override
    public float D() {
        return 0.1f;
    }

    @Override
    public boolean E() {
        return true;
    }

    @Override
    public float g(int n2) {
        return 10.0f;
    }

    @Override
    public boolean F() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && !this.isDead;
    }

    @Override
    public float G() {
        return 1.0f;
    }


    public float H() {
        return 1.0f;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean I() {
        return false;
    }

    @Override
    public boolean d(UnitInstance am2) {
        return false;
    }

    @Override
    public boolean J() {
        return true;
    }

    @Override  // 02b g.java L318-321: a(UnitInstance,float,MovementController)
    public float a(UnitInstance am2, float f2, com.corrodinggames.rts.game.MovementController f3) {  // 02b g.java L318-321
        f2 = 0.0f;
        return super.a(am2, f2, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.f();
    }
}
