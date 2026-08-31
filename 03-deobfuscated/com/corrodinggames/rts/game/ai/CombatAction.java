/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.units.projectiles.SpecialBuilding;
import com.corrodinggames.rts.gameFramework.GameInput;

import android.graphics.PointF;
import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.ai.AIUnitGroupBase;
import com.corrodinggames.rts.game.ai.Projectile;
import com.corrodinggames.rts.game.ai.BaseZoneType;
import com.corrodinggames.rts.game.ai.AIStrategyNode;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.PathfindingHelper;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.PathfindingHelper;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class CombatAction
extends AIUnitGroupBase {
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase A;
    boolean a;
    String lastActionName;
    boolean recruitingEnabled;
    boolean attackMoveEnabled;
    boolean isVipGroup;
    boolean isFleeing;
    com.corrodinggames.rts.game.units.UnitType recruitFromUnit;
    boolean isAttacker = true;
    int minGroupUnits;
    int maxGroupUnits;
    CombatMain baseZone;
    float idleTimer = 1000.0f;
    float reEvaluateTimer = 100.0f;
    float randomMoveTimer = 4000.0f;
    float missionTimer = 0.0f;
    float defenseTimer = 1000.0f;
    boolean isStaging = false;
    boolean hasAttackTarget = false;
    boolean stagingComplete = false;
    float stagingTimer = 0.0f;
    float stagingWaitTimer = 0.0f;
    boolean isRetreating;
    UnitInstance currentTarget;
    float elapsedTimer;
    float recruitTimer;
    float engagementCooldown;
    int field_A;
    boolean field_B;
    public int field_C = -9999;
    public UnitInstance field_D = null;
    MovementTypeEnum field_E = MovementTypeEnum.a;
    ArrayList field_F = new ArrayList();


    public boolean a() {
        return this.a;
    }

    @Override
    public boolean b() {
        return !this.isAttacker;
    }

    public static CombatAction a(AIStrategy a2, com.corrodinggames.rts.game.units.UnitType y2) {
        CombatAction g2 = new CombatAction(a2, false);
        g2.a = true;
        g2.recruitingEnabled = true;
        g2.attackMoveEnabled = true;
        g2.isVipGroup = true;
        g2.recruitFromUnit = y2;
        g2.a(y2);
        g2.field_A = 0;
        g2.k();
        return g2;
    }

    @Override
    public void serializeToStream(OutputNetStream as2) {
        as2.a(this.isAttacker);
        as2.a(this.minGroupUnits);
        as2.a(this.maxGroupUnits);
        int n2 = this.field_F.size();
        as2.a(n2);
        for (Object obj2 : this.field_F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            as2.a(y2);
        }
        as2.c(7);
        as2.a(false);
        as2.a(this.stagingComplete);
        as2.a(this.missionTimer);
        as2.a(this.G.size());
        for (Object obj2 : this.G) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            as2.a(y2);
        }
        as2.a(this.field_B);
        as2.a(this.a);
        as2.a(this.recruitingEnabled);
        as2.a(this.attackMoveEnabled);
        as2.a(this.isVipGroup);
        as2.a(this.isFleeing);
        as2.a(this.recruitFromUnit);
        as2.a(this.field_A);
        super.serializeToStream(as2);
    }


    public void a(InputNetStream k2) {
        int n2;
        this.isAttacker = k2.e();
        this.minGroupUnits = k2.f();
        this.maxGroupUnits = k2.f();
        this.q();
        int n3 = k2.f();
        for (n2 = 0; n2 < n3; ++n2) {
            com.corrodinggames.rts.game.units.UnitType y2 = k2.p();
            if (y2 == null) continue;
            this.a(y2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            k2.e();
        }
        if (n2 >= 2) {
            this.stagingComplete = k2.e();
        }
        if (n2 >= 3) {
            this.missionTimer = k2.readFloat();
        }
        if (n2 >= 4) {
            this.G.clear();
            int n4 = k2.f();
            for (int i2 = 0; i2 < n4; ++i2) {
                com.corrodinggames.rts.game.units.UnitType y3 = k2.p();
                if (y3 == null) continue;
                this.G.add(y3);
            }
        }
        if (n2 >= 5) {
            this.field_B = k2.e();
        }
        if (n2 >= 6) {
            this.a = k2.e();
            this.recruitingEnabled = k2.e();
            this.attackMoveEnabled = k2.e();
            this.isVipGroup = k2.e();
            this.isFleeing = k2.e();
            this.recruitFromUnit = k2.p();
        }
        if (n2 >= 7) {
            this.field_A = k2.f();
        }
        if (!this.field_B) {
            Iterator iterator = this.field_F.iterator();
            while (iterator.hasNext()) {
                com.corrodinggames.rts.game.units.UnitType y4 = (com.corrodinggames.rts.game.units.UnitType)iterator.next();
                if (!(y4 instanceof com.corrodinggames.rts.game.units.debug.FactoryAction6)) continue;
                if (y4 != null && y4.aB == this) {
                    y4.aB = null;
                }
                if (y4 != null) {
                    this.G.remove(y4);
                }
                iterator.remove();
            }
        }
        super.a(k2);
    }

    public CombatAction(AIStrategy a2) {
        super(a2);
    }

    public CombatAction(AIStrategy a2, boolean bl) {
        this(a2);
        this.isAttacker = bl;
    }


    protected void a(com.corrodinggames.rts.game.units.UnitType y2) {
        super.a(y2);
        this.field_E = this.j();
    }

    public void c() {
        for (UnitInstance am2 : UnitInstance.bE) {
            if (am2.isDead || am2.player != this.R || this.field_A <= this.field_F.size() || !(am2 instanceof com.corrodinggames.rts.game.units.UnitType)) continue;
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)am2;
            if (y2.bM || y2.bN || y2.aB != null || !this.R.h(y2) || !this.R.i(y2) || (!this.field_B ? am2.h() == MovementTypeEnum.e : am2.h() == MovementTypeEnum.b)) continue;
            if (!this.R.a(y2, this.S, this.T) && (!this.b() || com.corrodinggames.rts.gameFramework.GameUtils.a(0, 100) > 2)) continue;
            this.a(y2);
        }
    }

    public boolean d() {
        return this.field_A <= this.field_F.size();
    }

    public UnitInstance a(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if ((float)l2.by - f2 * 1000.0f < (float)this.field_C) {
            return this.field_D;
        }
        return null;
    }

    public UnitInstance e() {
        UnitInstance am2 = this.a(6.0f);
        if (am2 != null) {
            return am2;
        }
        return null;
    }

    public UnitInstance f() {
        for (Object obj2 : this.field_F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            UnitInstance am2 = y2.ab();
            if (am2 == null) continue;
            return am2;
        }
        return null;
    }

    public void a(Command e2, boolean bl, UnitInstance am2) {
        for (Object obj2 : this.field_F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            if (bl && !y2.aq() || am2 != null && !this.R.a((UnitInstance) y2, am2)) continue;
            e2.a(y2);
        }
    }

    public void a(String string) {
        this.lastActionName = string;
    }

    public PointF a(UnitInstance am2) {
        PointF pointF = new PointF();
        pointF.a = this.S;
        pointF.b = this.T;
        float f2 = 50.0f;
        float f3 = 100.0f;
        float f4 = (float)(Math.random() * 360.0);
        float f5 = com.corrodinggames.rts.gameFramework.GameUtils.c(f2, f3);
        pointF.a += com.corrodinggames.rts.gameFramework.GameUtils.k(f4) * f5;
        pointF.b += com.corrodinggames.rts.gameFramework.GameUtils.j(f4) * f5;
        if (am2 != null) {
            f2 = 100.0f;
            f3 = 200.0f;
            f4 = com.corrodinggames.rts.gameFramework.GameUtils.d(pointF.a, pointF.b, am2.eo, am2.ep);
            f5 = com.corrodinggames.rts.gameFramework.GameUtils.c(f2, f3);
            pointF.a += com.corrodinggames.rts.gameFramework.GameUtils.k(f4) * -f5;
            pointF.b += com.corrodinggames.rts.gameFramework.GameUtils.j(f4) * -f5;
        }
        return pointF;
    }

    @Override
    public void b(float f2) {
        UnitInstance am2;
        UnitInstance am3;
        super.b(f2);
        this.n();
        this.field_E = this.j();
        if (!this.isFleeing && (am3 = this.e()) != null && (am2 = this.f()) == null) {
            if (this.a(am3, false)) {
                this.a("fighting attacker");
                GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                Command e2 = l2.cf.a(this.R);
                this.a(e2, true, am3);
                boolean bl = false;
                e2.a(am3.eo, am3.ep, bl);
            } else {
                this.a("flight from attacker");
                PointF pointF = this.a(am3);
                this.S = pointF.a;
                this.T = pointF.b;
                if (this.engagementCooldown > 200.0f) {
                    this.engagementCooldown = 200.0f;
                }
            }
        }
    }

    @Override
    public void c(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.elapsedTimer += f2;
        for (Object object : this.field_F) {
            if (object == null || this.field_C >= ((com.corrodinggames.rts.game.units.UnitType)object).bs) continue;
            this.field_C = ((com.corrodinggames.rts.game.units.UnitType)object).bs;
            this.field_D = ((com.corrodinggames.rts.game.units.UnitType)object).bt;
        }
        this.n();
        if (this.d()) {
            this.idleTimer = com.corrodinggames.rts.gameFramework.GameUtils.a(this.idleTimer, f2);
        } else if (this.isRetreating) {
            // empty if block
        }
        this.recruitTimer = com.corrodinggames.rts.gameFramework.GameUtils.a(this.recruitTimer, f2);
        this.engagementCooldown = com.corrodinggames.rts.gameFramework.GameUtils.a(this.engagementCooldown, f2);
        this.defenseTimer = com.corrodinggames.rts.gameFramework.GameUtils.a(this.defenseTimer, f2);
        if (!(this.isRetreating || this.hasAttackTarget || this.d() || this.recruitTimer != 0.0f)) {
            this.recruitTimer = 200 + com.corrodinggames.rts.gameFramework.GameUtils.c(200);
            this.c();
        }
        if (!this.isRetreating || this.isStaging) {
            Object object;
            if (!this.isStaging) {
                this.randomMoveTimer = com.corrodinggames.rts.gameFramework.GameUtils.a(this.randomMoveTimer, f2);
                if (this.randomMoveTimer == 0.0f) {
                    if (this.baseZone == null) {
                        this.baseZone = this.g();
                    }
                    if (this.baseZone != null) {
                        object = this.baseZone.w();
                        if (!this.a(((PointF)object).a, ((PointF)object).b)) {
                            this.randomMoveTimer = 100.0f;
                            this.a("random move: bad target");
                        } else {
                            this.randomMoveTimer = 4000.0f;
                            this.S = ((PointF)object).a;
                            this.T = ((PointF)object).b;
                            this.a("random move");
                        }
                    } else {
                        this.a("random move: no linked base");
                    }
                }
            }
            if (this.engagementCooldown == 0.0f) {
                this.engagementCooldown = 800.0f;
                object = l2.cf.a(this.R);
                for (Object obj2 : this.field_F) {
                    com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                    boolean bl = true;
                    if (this.c(y2) < 28900.0f) {
                        bl = false;
                    }
                    if (!this.isFleeing && y2.aj() && !y2.aq()) {
                        bl = false;
                    }
                    if (!bl) continue;
                    ((Command) object).a(y2);
                }
                if (this.isFleeing) {
                    ((Command) object).a(this.S, this.T);
                } else {
                    ((Command) object).b(this.S, this.T);
                }
            }
        }
        if (this.isAttacker) {
            this.e(f2);
        } else {
            this.d(f2);
        }
        if (this.field_A == 0 && this.field_F.size() == 0) {
            this.p();
        }
        if (this.recruitingEnabled && (this.recruitFromUnit == null || this.recruitFromUnit.isDead)) {
            this.p();
        }
    }

    CombatMain g() {
        float f2 = -1.0f;
        CombatMain i2 = null;
        for (Object obj2 : this.R.zoneSnapshot) {
            AIStrategyNode o2 = (AIStrategyNode)obj2;
            if (!(o2 instanceof CombatMain)) continue;
            CombatMain i3 = (CombatMain) o2;
            if (!this.b(i3.S, i3.T)) continue;
            float f3 = i3.d(this.S, this.T);
            if (i2 != null && !(f3 < f2)) continue;
            f2 = f3;
            i2 = i3;
        }
        return i2;
    }

    public void d(float f2) {
        int n2;
        if (this.baseZone == null || this.baseZone.V) {
            this.k();
        }
        if (this.recruitingEnabled && this.recruitFromUnit != null) {
            if (this.isVipGroup && !this.isFleeing) {
                if ((double)(this.recruitFromUnit.hp / this.recruitFromUnit.maxHp) < 0.5) {
                    this.isFleeing = true;
                    if (this.engagementCooldown > 100.0f) {
                        this.engagementCooldown = 100.0f;
                    }
                }
                if (this.currentTarget == null) {
                    this.k();
                }
            } else {
                if ((double)(this.recruitFromUnit.hp / this.recruitFromUnit.maxHp) > 0.6) {
                    this.isFleeing = false;
                }
                n2 = 0;
                if (this.baseZone != null && !this.baseZone.t) {
                    n2 = 1;
                }
                if (n2 == 0) {
                    boolean bl = true;
                    CombatMain i2 = this.R.a(this.recruitFromUnit.h(), this.recruitFromUnit.eo, this.recruitFromUnit.ep, bl);
                    if (i2 != null) {
                        this.baseZone = i2;
                    }
                    if (this.baseZone != null) {
                        PointF pointF = this.baseZone.w();
                        this.S = pointF.a;
                        this.T = pointF.b;
                        if (this.engagementCooldown > 100.0f) {
                            this.engagementCooldown = 100.0f;
                        }
                        this.a("moving to new base");
                    }
                }
            }
        }
        if (this.baseZone != null) {
            for (n2 = 0; n2 < 2; ++n2) {
                if (this.defenseTimer != 0.0f) continue;
                UnitInstance am2 = this.baseZone.g();
                if (am2 == null) break;
                if (!this.a(am2, false)) continue;
                this.currentTarget = am2;
                this.defenseTimer = 500.0f;
                this.randomMoveTimer = 2000.0f;
                if (!this.isFleeing) {
                    this.S = am2.eo;
                    this.T = am2.ep;
                }
                if (this.engagementCooldown > 100.0f) {
                    this.engagementCooldown = 100.0f;
                }
                this.a("defending base");
            }
            if (this.defenseTimer == 0.0f) {
                this.isFleeing = false;
                this.currentTarget = null;
            }
        }
    }

    public void e(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (!this.isRetreating) {
            if (this.idleTimer == 0.0f) {
                this.isRetreating = true;
                this.isStaging = true;
            }
        } else {
            if (this.currentTarget == null || !this.currentTarget.isAlive() || this.currentTarget.isDead || !this.hasAttackTarget) {
                this.currentTarget = this.R.as();
                if (this.currentTarget != null && !this.a(this.currentTarget, true)) {
                    this.currentTarget = null;
                }
            }
            if (this.currentTarget != null) {
                if (this.isStaging) {
                    this.stagingWaitTimer += f2;
                    if (!this.hasAttackTarget) {
                        this.stagingTimer = com.corrodinggames.rts.gameFramework.GameUtils.a(this.stagingTimer, f2);
                        if (this.stagingTimer == 0.0f) {
                            this.stagingTimer = 20.0f;
                            this.h();
                        }
                    } else {
                        boolean bl = false;
                        for (Object obj2 : this.field_F) {
                            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                            if (!(this.c(y2) > 28900.0f)) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.isStaging = false;
                        }
                        for (Object obj2 : this.field_F) {
                            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                            if (y2.bs <= l2.by - 1000) continue;
                            this.isStaging = false;
                            this.a("Not staging due to damage");
                        }
                    }
                    if (this.stagingWaitTimer > 17000.0f) {
                        this.isStaging = false;
                        this.a("attacking target");
                    }
                } else {
                    this.missionTimer += f2;
                    if (this.engagementCooldown == 0.0f) {
                        this.engagementCooldown = 800.0f;
                        boolean bl = false;
                        CustomArrayList m2 = new CustomArrayList();
                        for (Object obj2 : this.field_F) {
                            com.corrodinggames.rts.game.units.UnitType y3 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                            boolean bl2 = true;
                            if (this.currentTarget != null) {
                                if (!this.R.a((UnitInstance) y3, this.currentTarget)) {
                                    bl2 = false;
                                }
                                if (bl2 && !PathfindingHelper.a(y3, this.currentTarget)) {
                                    bl2 = false;
                                }
                            }
                            if (!bl2) continue;
                            bl = true;
                            m2.add(y3);
                        }
                        if (!bl) {
                            this.isStaging = false;
                            this.a("cannot reach main target");
                        } else {
                            Command e2 = l2.cf.a(this.R);
                            e2.a(m2);
                            boolean bl3 = true;
                            if (this.currentTarget != null && com.corrodinggames.rts.gameFramework.GameUtils.a(0, 100) < 80) {
                                e2.a(this.currentTarget.eo, this.currentTarget.ep, bl3);
                            } else {
                                e2.a(this.currentTarget, bl3);
                            }
                            this.a("attacking main target");
                        }
                    }
                }
            }
        }
        if (this.isRetreating) {
            if (this.field_F.size() == 0) {
                this.p();
            }
            if (this.missionTimer > 1000.0f && this.field_F.size() < 3) {
                this.p();
            }
            if (this.missionTimer > 11000.0f) {
                this.p();
            }
        }
    }

    public void h() {
        int n2;
        float f2 = this.currentTarget.eo;
        float f3 = this.currentTarget.ep;
        float f4 = com.corrodinggames.rts.gameFramework.GameUtils.d(f2, f3, this.S, this.T);
        float f5 = com.corrodinggames.rts.gameFramework.GameUtils.b(f2, f3, this.S, this.T);
        if (com.corrodinggames.rts.gameFramework.GameUtils.a(0, 100) < 80) {
            f4 += (float)com.corrodinggames.rts.gameFramework.GameUtils.a(-110, 110);
        }
        if ((n2 = (int)((double)f5 * 0.6)) < 720) {
            n2 = 720;
        }
        float f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(50, n2);
        if (com.corrodinggames.rts.gameFramework.GameUtils.a(0, 100) < 80 && f6 < 450.0f) {
            f6 = com.corrodinggames.rts.gameFramework.GameUtils.a(450, n2);
        }
        boolean bl = true;
        if (!this.a(f2 += com.corrodinggames.rts.gameFramework.GameUtils.k(f4) * f6, f3 += com.corrodinggames.rts.gameFramework.GameUtils.j(f4) * f6)) {
            bl = false;
        }
        boolean bl2 = false;
        boolean bl3 = false;
        for (Object obj2 : this.field_F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            if (y2.h() == MovementTypeEnum.b) {
                bl2 = true;
            }
            if (y2.h() != MovementTypeEnum.e) continue;
            bl3 = true;
        }
        if (bl2) {
            if (this.R.scoutCount == 0 && !this.b(f2, f3)) {
                bl = false;
            }
            if (!this.R.a(f2, f3, this.currentTarget.eo, this.currentTarget.ep, MovementTypeEnum.b) && com.corrodinggames.rts.gameFramework.GameUtils.a(0, 100) < 98) {
                bl = false;
            }
        }
        if (bl3) {
            if (!this.b(f2, f3)) {
                bl = false;
            }
            if (!this.R.a(f2, f3, this.currentTarget.eo, this.currentTarget.ep, MovementTypeEnum.e)) {
                bl = false;
            }
        }
        if (bl) {
            this.S = f2;
            this.T = f3;
            this.engagementCooldown = 0.0f;
            this.hasAttackTarget = true;
            this.G.clear();
            for (Object obj2 : this.field_F) {
                com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
                if (y2.h() == MovementTypeEnum.e || this.R.a(y2, this.S, this.T)) continue;
                this.G.add(y2);
            }
        }
    }

    public MovementTypeEnum i() {
        return this.field_E;
    }

    public MovementTypeEnum j() {
        if (this.field_F.size() == 0) {
            if (this.field_B) {
                return MovementTypeEnum.e;
            }
            return MovementTypeEnum.b;
        }
        boolean bl = true;
        for (Object object : this.field_F) {
            Object object2 = ((UnitInstance) object).h();
            if (object2 == MovementTypeEnum.d) continue;
            bl = false;
            break;
        }
        if (bl) {
            return MovementTypeEnum.d;
        }
        if (this.field_B) {
            boolean bl2 = true;
            for (Object object2 : this.field_F) {
                MovementTypeEnum ao2 = ((UnitInstance) object2).h();
                if (ao2 != MovementTypeEnum.e) continue;
                bl2 = false;
            }
            if (bl2) {
                return MovementTypeEnum.f;
            }
            return MovementTypeEnum.e;
        }
        boolean bl3 = true;
        for (Object object2 : this.field_F) {
            MovementTypeEnum ao3 = ((UnitInstance) object2).h();
            if (ao3 != MovementTypeEnum.b && ao3 != MovementTypeEnum.g) continue;
            bl3 = false;
        }
        if (bl3) {
            return MovementTypeEnum.f;
        }
        return MovementTypeEnum.b;
    }

    public boolean a(float f2, float f3) {
        return !com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(f2, f3, this.i());
    }

    public boolean b(float f2, float f3) {
        for (Object obj2 : this.field_F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            if (this.R.a((UnitInstance) y2, f2, f3)) continue;
            return false;
        }
        return true;
    }

    public boolean a(UnitInstance am2, boolean bl) {
        for (Object obj2 : this.field_F) {
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)obj2;
            if (!bl && !this.R.a((UnitInstance) y2, am2.eo, am2.ep) || !PathfindingHelper.a(y2, am2)) continue;
            return true;
        }
        return false;
    }

    public void k() {
        boolean bl = true;
        PointF pointF = null;
        if (this.recruitingEnabled && this.recruitFromUnit != null) {
            this.S = this.recruitFromUnit.eo;
            this.T = this.recruitFromUnit.ep;
            this.baseZone = this.R.c(this.recruitFromUnit.eo, this.recruitFromUnit.ep);
            return;
        }
        if (bl) {
            for (int i2 = 0; i2 < 7; ++i2) {
                boolean bl2;
                boolean bl3 = bl2 = i2 > 3;
                if (pointF != null) continue;
                for (Object obj2 : this.R.zoneSnapshot) {
                    AIStrategyNode o2 = (AIStrategyNode)obj2;
                    if (!(o2 instanceof CombatMain)) continue;
                    CombatMain i3 = (CombatMain) o2;
                    if (i3.b != com.corrodinggames.rts.game.ai.BaseZoneType.c || i3.u() <= 2 && !bl2 || pointF != null && com.corrodinggames.rts.gameFramework.GameUtils.c(this.R.forwardZoneCount + 2) != 0) continue;
                    for (int i4 = 0; i4 < 10; ++i4) {
                        if (pointF != null) continue;
                        PointF pointF2 = i3.w();
                        if (!this.a(pointF2.a, pointF2.b)) continue;
                        pointF = pointF2;
                    }
                    this.baseZone = i3;
                }
            }
        }
        if (pointF == null) {
            pointF = this.R.am();
            this.baseZone = null;
        }
        this.S = pointF.a;
        this.T = pointF.b;
    }

}