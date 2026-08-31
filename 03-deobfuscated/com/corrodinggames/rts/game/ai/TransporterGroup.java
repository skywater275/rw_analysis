/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.game.TagFilter;

import android.graphics.PointF;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.AIUnitGroupBase;
import com.corrodinggames.rts.game.ai.Projectile;
import com.corrodinggames.rts.game.ai.BaseZoneType;
import com.corrodinggames.rts.game.ai.RallyGroup;
import com.corrodinggames.rts.game.ai.AIStrategyNode;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class TransporterGroup
extends AIUnitGroupBase {
    boolean a;
    int b;
    int c;
    CombatMain d;
    float e = 100.0f;
    float f = 4000.0f;
    float g = 100.0f;
    float h;
    float i;
    float j;
    float k;
    int l;
    com.corrodinggames.rts.game.ai.AIUnitGroupBase m;
    com.corrodinggames.rts.game.units.UnitType n;
    float o = 0.0f;
    boolean unloading = false;
    boolean q;
    float r;
    float s;


    @Override
    public void serializeToStream(OutputNetStream as2) {
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.c);
        int n2 = this.F.size();
        as2.a(n2);
        for (Object obj2 : this.F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            as2.a(y2);
        }
        as2.c(5);
        as2.a(this.R.a(this.m));
        as2.a(this.q);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.unloading);
        as2.a(this.r);
        as2.a(this.s);
        super.serializeToStream(as2);
    }

    @Override
    public void a(InputNetStream k2) {
        int n2;
        this.a = k2.e();
        this.b = k2.f();
        this.c = k2.f();
        this.q();
        int n3 = k2.f();
        for (n2 = 0; n2 < n3; ++n2) {
            com.corrodinggames.rts.game.units.UnitType y2 = k2.p();
            if (y2 == null) continue;
            if (!this.R.g(y2)) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("TransporterGroup:readIn: Unit is not transporterUnit");
                continue;
            }
            this.a(y2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            this.m = (AIUnitGroupBase) this.R.m(k2.f());
        }
        if (n2 >= 2) {
            this.q = k2.e();
        }
        if (n2 >= 3) {
            this.n = k2.p();
        }
        if (n2 >= 4) {
            this.o = k2.readFloat();
            this.unloading = k2.e();
        }
        if (n2 >= 5) {
            this.r = k2.readFloat();
            this.s = k2.readFloat();
        }
        super.a(k2);
    }

    public TransporterGroup(AIStrategy a2) {
        super(a2);
    }

    public void c() {
        for (UnitInstance am2 : UnitInstance.bE) {
            if (am2.isDead || am2.player != this.R || this.l <= this.F.size() || !(am2 instanceof com.corrodinggames.rts.game.units.UnitType)) continue;
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)am2;
            if (y2.bN || y2.aB != null || !this.R.g(y2) || !this.R.i(y2)) continue;
            this.a(y2);
        }
    }

    public boolean d() {
        return this.m != null;
    }

    @Override
    public void c(float f2) {
        Object object;
        Object object22;
        Object object32;
        Object object42;
        Object object5;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.h += f2;
        this.n();
        if (this.l <= this.F.size()) {
            // empty if block
        }
        this.i = com.corrodinggames.rts.gameFramework.GameUtils.a(this.i, f2);
        this.j = com.corrodinggames.rts.gameFramework.GameUtils.a(this.j, f2);
        this.k = com.corrodinggames.rts.gameFramework.GameUtils.a(this.k, f2);
        if (!this.d() && !this.q && this.l > this.F.size() && this.i == 0.0f) {
            this.i = 300.0f;
            this.c();
        }
        if (!this.d() && this.F.size() != 0) {
            if (!this.d()) {
                this.f = com.corrodinggames.rts.gameFramework.GameUtils.a(this.f, f2);
                if (this.f == 0.0f) {
                    this.f = 4000.0f;
                    if (this.d != null) {
                        object5 = this.d.w();
                        this.S = ((PointF)object5).a;
                        this.T = ((PointF)object5).b;
                    }
                }
            }
            if (this.j == 0.0f) {
                this.j = 400.0f;
                object5 = l2.cf.a(this.R);
                for (Object object42_160 : this.F) {
                    if (this.c((UnitInstance) object42_160) > 28900.0f && !((com.corrodinggames.rts.game.units.UnitType)object42_160).aw()) {
                        ((Command) object5).a((com.corrodinggames.rts.game.units.UnitType)object42_160);
                        continue;
                    }
                    object32 = (UnitShield) object42_160;
                    if (((UnitShield) object32).bB() == 0) continue;
                    object22 = ((UnitInstance) object42_160).applyDamage();
                    object = l2.cf.a(this.R);
                    ((Command) object).a((com.corrodinggames.rts.game.units.UnitType)object42_160);
                    ((Command) object).a((ActionId) object22);
                }
                ((Command) object5).a(this.S, this.T);
            }
            if (this.m == null) {
                this.g = com.corrodinggames.rts.gameFramework.GameUtils.a(this.g, f2);
                if (this.g == 0.0f) {
                    this.g = 100.0f;
                    if (com.corrodinggames.rts.gameFramework.GameUtils.a(0, 100) < 80) {
                        this.a(f2, true);
                    }
                    if (this.m == null) {
                        this.a(f2, false);
                    }
                }
            }
        }
        if (this.m != null && this.m.V) {
            this.m = null;
        }
        if (!this.q) {
            if (this.m != null) {
                Iterator iterator;
                object5 = this.m.G;
                if (this.n != null && (this.n.isDead || this.n.cN != null || this.n.cO != null)) {
                    ((ArrayList)object5).remove(this.n);
                    this.n = null;
                }
                if (this.n == null) {
                    iterator = ((ArrayList)object5).iterator();
                    block1: while (iterator.hasNext()) {
                        object42 = (com.corrodinggames.rts.game.units.UnitType)iterator.next();
                        if (((com.corrodinggames.rts.game.units.UnitType)object42).cN != null) continue;
                        for (Object object22_203 : this.F) {
                            if (!((UnitInstance) object22_203).d((UnitInstance) object42, false)) continue;
                            this.n = (com.corrodinggames.rts.game.units.UnitType)object42;
                            continue block1;
                        }
                    }
                    if (this.n == null) {
                        this.q = true;
                        this.j = 0.0f;
                        this.k = 0.0f;
                        this.r = this.m.S;
                        this.s = this.m.T;
                    }
                }
                if (this.n != null) {
                    if (this.j == 0.0f) {
                        this.j = 400.0f;
                        Command e4 = l2.cf.a(this.R);
                        for (Object object32_221 : this.F) {
                            e4.a((com.corrodinggames.rts.game.units.UnitType)object32_221);
                        }
                        e4.a(this.n.eo, this.n.ep);
                    }
                    if (this.k == 0.0f) {
                        this.k = 80.0f;
                        iterator = ((ArrayList)object5).iterator();
                        block4: while (iterator.hasNext()) {
                            object42 = (com.corrodinggames.rts.game.units.UnitType)iterator.next();
                            for (Object object22_231 : this.F) {
                                float f3;
                                if (!((UnitInstance) object22_231).d((UnitInstance) object42, false) || !((f3 = com.corrodinggames.rts.gameFramework.GameUtils.a(((com.corrodinggames.rts.game.units.UnitType)object22_231).eo, ((com.corrodinggames.rts.game.units.UnitType)object22_231).ep, ((com.corrodinggames.rts.game.units.UnitType)object42).eo, ((com.corrodinggames.rts.game.units.UnitType)object42).ep)) < 14400.0f)) continue;
                                Command e2 = l2.cf.a(this.R);
                                e2.a((com.corrodinggames.rts.game.units.UnitType)object42);
                                e2.e((UnitInstance) object22_231);
                                continue block4;
                            }
                        }
                        boolean bl = false;
                        for (Object object32_241 : this.F) {
                            if (!((UnitInstance) object32_241).d(this.n, false)) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.n = null;
                        }
                    }
                }
            }
        } else if (this.m == null) {
            this.e();
        } else {
            if (this.j == 0.0f) {
                this.j = 400.0f;
                float f4 = this.m.S + com.corrodinggames.rts.gameFramework.GameUtils.c(-40.0f, 40.0f);
                float f5 = this.m.T + com.corrodinggames.rts.gameFramework.GameUtils.c(-40.0f, 40.0f);
                if (this.o > 600.0f) {
                    f4 += com.corrodinggames.rts.gameFramework.GameUtils.c(-300.0f, 300.0f);
                    f5 += com.corrodinggames.rts.gameFramework.GameUtils.c(-300.0f, 300.0f);
                }
                if (this.o > 1200.0f) {
                    f4 += com.corrodinggames.rts.gameFramework.GameUtils.c(-300.0f, 300.0f);
                    f5 += com.corrodinggames.rts.gameFramework.GameUtils.c(-300.0f, 300.0f);
                }
                if (PathfindingUtils.a(f4, f5, MovementTypeEnum.b)) {
                    f4 += com.corrodinggames.rts.gameFramework.GameUtils.c(-100.0f, 100.0f);
                    f5 += com.corrodinggames.rts.gameFramework.GameUtils.c(-100.0f, 100.0f);
                }
                if (PathfindingUtils.a(f4, f5, MovementTypeEnum.b)) {
                    f4 += com.corrodinggames.rts.gameFramework.GameUtils.c(-200.0f, 200.0f);
                    f5 += com.corrodinggames.rts.gameFramework.GameUtils.c(-200.0f, 200.0f);
                }
                if (PathfindingUtils.a(f4, f5, MovementTypeEnum.b)) {
                    f4 += com.corrodinggames.rts.gameFramework.GameUtils.c(-200.0f, 200.0f);
                    f5 += com.corrodinggames.rts.gameFramework.GameUtils.c(-200.0f, 200.0f);
                }
                if (PathfindingUtils.a(f4, f5, MovementTypeEnum.b)) {
                    this.j = 30.0f;
                } else {
                    this.r = f4;
                    this.s = f5;
                    object42 = l2.cf.a(this.R);
                    Iterator iter3 = this.F.iterator();
                    while (iter3.hasNext()) {
                        object22 = (com.corrodinggames.rts.game.units.UnitType)iter3.next();
                        object = (UnitShield) object22;
                        if (((UnitShield) object).bB() != 0) {
                            float f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(((com.corrodinggames.rts.game.units.UnitType)object22).eo, ((com.corrodinggames.rts.game.units.UnitType)object22).ep, this.r, this.s);
                            if (!(f6 > 1600.0f)) continue;
                            ((Command) object42).a((com.corrodinggames.rts.game.units.UnitType)object22);
                            continue;
                        }
                        Command e3 = l2.cf.a(this.R);
                        e3.a((com.corrodinggames.rts.game.units.UnitType)object22);
                        e3.a(this.S, this.T);
                    }
                    ((Command) object42).a(this.r, this.s);
                }
            }
            if (this.k == 0.0f) {
                this.k = 100.0f;
                for (Object obj2 : this.F) {
                    com.corrodinggames.rts.game.units.UnitType y3 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                    float f7 = com.corrodinggames.rts.gameFramework.GameUtils.a(y3.eo, y3.ep, this.r, this.s);
                    if (!(f7 < 6400.0f)) continue;
                    this.unloading = true;
                    object32 = y3.applyDamage();
                    object22 = l2.cf.a(this.R);
                    ((Command) object22).a(y3);
                    ((Command) object22).a((ActionId) object32);
                }
            }
            if (this.unloading) {
                this.m.o();
                this.o += f2;
            }
            boolean bl = false;
            for (Object obj2 : this.F) {
                com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                if (y2.isDead || ((UnitShield) ((Object)y2)).bB() == 0) continue;
                bl = true;
            }
            if (!bl || this.o > 1700.0f) {
                this.e();
            }
        }
        if (this.h > 1500.0f && this.F.size() == 0) {
            this.p();
        }
    }

    public void e() {
        this.q = false;
        this.m = null;
        this.o = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.unloading = false;
        this.f();
    }

    public void a(float f2, boolean bl) {
        for (Object obj2 : this.R.zoneSnapshot) {
            AIStrategyNode o2 = (AIStrategyNode)obj2;
            if (!(o2 instanceof AIUnitGroupBase) || o2 instanceof TransporterGroup || bl && !(o2 instanceof RallyGroup)) continue;
            AIUnitGroupBase h2 = (AIUnitGroupBase) o2;
            if (h2.G.size() == 0 || h2.m()) continue;
            this.m = h2;
            this.n = null;
            return;
        }
    }

    public CombatMain a(boolean bl) {
        CombatMain i2 = null;
        for (Object obj2 : this.R.zoneSnapshot) {
            AIStrategyNode o2 = (AIStrategyNode)obj2;
            if (!(o2 instanceof CombatMain)) continue;
            CombatMain i3 = (CombatMain) o2;
            if (i3.s && bl || i3.b != com.corrodinggames.rts.game.ai.BaseZoneType.c) continue;
            i2 = i3;
            if (com.corrodinggames.rts.gameFramework.GameUtils.c(3) != 0) continue;
            return i2;
        }
        return i2;
    }

    public void f() {
        boolean bl = true;
        PointF pointF = null;
        if (bl) {
            this.d = this.a(true);
            if (this.d == null) {
                this.d = this.a(false);
            }
            if (this.d != null) {
                pointF = this.d.w();
            }
        }
        if (pointF == null) {
            pointF = this.R.am();
            this.d = null;
        }
        this.S = pointF.a;
        this.T = pointF.b;
    }
}