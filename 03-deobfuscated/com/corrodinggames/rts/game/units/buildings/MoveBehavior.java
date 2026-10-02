/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.BuildingBase;
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
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.buildings.AbstractUnitBehavior;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

public strictfp class MoveBehavior
extends AbstractUnitBehavior
implements UnitShield {
    static com.corrodinggames.rts.gameFramework.rendering.Texture targetPosition = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture waypointIndex = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture c = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] d = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    float e = 0.0f;
    float f;
    boolean g;
    CustomArrayList o = new CustomArrayList();
    Rect p = new Rect();
    public static final GameAction q = new MoveBehavior$1(109);
    public static final GameAction r = new MoveBehavior$2(110);
    static ArrayList s = new ArrayList();

    @Override
    /* 覆写链 super.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.o.size());
        Iterator var2 = this.o.iterator();
        while (var2.hasNext()) {
            UnitInstance am2 = (UnitInstance)var2.next();
            as2.a(am2);
        }
        super.a(as2);
    }

    @Override
    public void a(InputNetStream k2) {
        this.e = k2.readFloat();
        this.f = k2.readFloat();
        this.g = k2.e();
        this.o.clear();
        int n2 = k2.f();
        for (int i = 0; i < n2; ++i) {
            UnitInstance am2 = k2.o();
            if (am2 == null) continue;
            this.o.add(am2);
        }
        super.a(k2);
    }


    public int bY() {
        return com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.m(this.o);
    }


    public int bZ() {
        return 4;
    }

    public UnitRegistry b() {
        return com.corrodinggames.rts.game.units.UnitRegistry.z;
    }

    public static void L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        waypointIndex = l2.bO.loadImageFromResource(R$drawable.dropship);
        c = l2.bO.loadImageFromResource(R$drawable.dropship_shadow);
        targetPosition = l2.bO.loadImageFromResource(R$drawable.dropship_dead);
        d = com.corrodinggames.rts.game.PlayerState.a(waypointIndex);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return targetPosition;
        }
        return d[this.player.getTeamIndex()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return c;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d(int n2) {
        return null;
    }

    @Override
    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = targetPosition;
        this.S(0);
        this.bT = false;
        this.f(true);
        return true;
    }

    public MoveBehavior(boolean bl) {
        super(bl);
        this.T(45);
        this.U(47);
        this.cj = 20.0f;
        this.ck = this.cj + 0.0f;
        this.hp = this.maxHp = 500.0f;
        this.M = waypointIndex;
        this.N = c;
        this.eq = 0.0f;
    }

    @Override
    public boolean I() {
        return true;
    }

    @Override
    public boolean i() {
        return this.eq >= 4.0f;
    }


    public boolean ct() {
        return true;
    }

    @Override
    public void a(float f2) {
        boolean bl;
        super.a(f2);
        if (this.isDead) {
            return;
        }
        boolean bl2 = this.cK();
        if (this.g && !bl2 && !this.cK && this.eq < 4.0f) {
            this.f = GameUtils.a(this.f, f2);
            if (this.f == 0.0f) {
                this.f = 30.0f;
                if (this.o.size() == 0) {
                    this.g = false;
                } else {
                    bl = this.o.size() % 2 == 0;
                    float f3 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg) * -9.0f;
                    float f4 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg) * -9.0f;
                    f3 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg + 90.0f) * (float)(bl ? -7 : 7);
                    UnitInstance am2 = (UnitInstance) this.o.remove(this.o.size() - 1);
                    if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, f3 += com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg + 90.0f) * (float)(bl ? -7 : 7), f4)) {
                        f3 += 10.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, f3, f4)) {
                        f3 -= 20.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, f3, f4)) {
                        f3 -= 10.0f;
                        f4 += 10.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, f3, f4)) {
                        f4 -= 20.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, f3, f4)) {
                        this.o.add(am2);
                    } else {
                        am2.cN = null;
                        am2.eo = f3;
                        am2.ep = f4;
                        am2.bZ += 0.1f;
                        am2.cg = this.cg + 180.0f;
                        am2.bR = this;
                        am2.bS = 45.0f;
                        if (am2 instanceof UnitType) {
                            UnitType y2 = (UnitType)am2;
                            y2.az();
                            y2.d(this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg) * -66.0f, this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg) * -66.0f);
                        }
                        if (this.o.size() == 0) {
                            this.g = false;
                        }
                    }
                }
            }
        }
        this.e += 2.0f * f2;
        if (this.e > 360.0f) {
            this.e -= 360.0f;
        }
        bl = this.i();
        if (this.bT()) {
            this.eq = this.aq() && !bl2 ? GameUtils.a(this.eq, 2.0f, 0.4f * f2) : GameUtils.a(this.eq, 35.0f + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.e) * 1.5f, 0.35f * f2);
        }
        if (bl != this.i()) {
            this.ay = true;
            if (this.i()) {
                this.S(5);
            } else {
                this.S(2);
            }
        }
    }


    public PointF E(int n2) {
        float f2 = this.g(n2);
        float f3 = this.cg;
        float f4 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(f3) * f2;
        float f5 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(f3) * f2;
        bg.a(f4, f5);
        return bg;
    }


    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
        f2.ar = Color.a(255, 150, 230, 40);
        f2.U = 35.0f;
        f2.l = am2;
        f2.h = 80.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].turretAngle);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.u, 0.3f, this.eo, this.ep);
    }


    public float m() {
        return 140.0f;
    }


    public float b(int n2) {
        return 40.0f;
    }


    public float z() {
        return 2.3f;
    }


    public float A() {
        return 1.4f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean E() {
        return false;
    }


    public float C() {
        return 0.03f;
    }


    public float D() {
        return 0.05f;
    }


    public boolean l() {
        return false;
    }


    public float g(int n2) {
        return 15.0f;
    }

    @Override
    public void a() {
        this.f(true);
        super.a();
    }

    public void f(boolean bl) {
        Iterator var2 = this.o.iterator();
        while (var2.hasNext()) {
            UnitInstance am2 = (UnitInstance)var2.next();
            am2.cN = null;
            am2.eo = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg) * -9.0f;
            am2.ep = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg) * -9.0f;
            if (bl) {
                am2.getAttackRange();
            }
        }
        this.o.clear();
    }

    @Override
    public boolean bA() {
        return this.g;
    }

    public void M() {
        this.g = true;
        this.f = 30.0f;
    }

    public void ds() {
        this.g = false;
    }


    public float bN() {
        return 16000.0f;
    }


    public boolean d(UnitInstance am2, boolean bl) {
        if (this.g) {
            return false;
        }
        if (!com.corrodinggames.rts.game.units.projectiles.SpecialBuilding.m(this.o, 4, am2)) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        if (this.player != am2.player && !bl) {
            return false;
        }
        return com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(am2, true, true);
    }


    public boolean e(UnitInstance am2, boolean bl) {
        if (!this.d(am2, bl)) {
            return false;
        }
        this.C(am2);
        return true;
    }

    public void C(UnitInstance am2) {
        am2.cN = this;
        this.o.add(am2);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bS.l(am2);
    }


    public void e(UnitInstance am2) {
        if (am2.cN == this) {
            this.o.remove(am2);
            am2.cN = null;
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.isKeyJustPressed("Unit is not being transported");
        }
    }


    public void D(GameAction s2, boolean bl) {
        if (s2 == q) {
            this.M();
        }
        if (s2 == r) {
            this.ds();
        }
    }

    @Override
    public int bB() {
        return this.o.size();
    }


    public boolean cr() {
        return true;
    }


    public com.corrodinggames.rts.game.units.actions.ActionId cp() {
        return q.N();
    }


    public ArrayList N() {
        return s;
    }


    public boolean f() {
        return !this.cK();
    }


    public boolean j() {
        return true;
    }


    public CustomArrayList bz() {
        return this.o;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.b();
    }

    static {
        s.add(q);
        s.add(r);
    }
}
