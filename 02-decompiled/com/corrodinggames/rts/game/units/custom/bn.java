/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.b.k;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.o;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class bn {
    public String a;
    public String b;
    public boolean c;
    public boolean d;
    public int e;
    public float f;
    public float g;
    public float h;
    public float i = 1.0f;
    public float j;
    public float k;
    public boolean l;
    public float m = -1.0f;
    public float n = 0.0f;
    public float o = 4.0f;
    public float p = 0.0f;
    public float q = 4.0f;
    public float r = 7.0f;
    public boolean s = false;
    public float t;
    public float u = 0.0f;
    public b v;
    public int w = -1;
    public int x = -1;
    public bn y;
    public bn z;
    public boolean A;
    public boolean B = true;
    public LogicBoolean C;
    public bl D;
    public z E;
    public z F;
    public Integer G;
    public boolean H;
    public boolean I;
    public LogicBoolean J;
    public LogicBoolean K;
    public LogicBoolean L;
    public LogicBoolean M;
    public LogicBoolean N;
    public h O;
    public h P;
    public float Q = 5.0f;
    public int R = 0;
    public int S = -1;
    public LogicBoolean T;
    public Float U;
    public float V = -1.0f;
    public float W = -1.0f;
    public float X;
    public float Y;
    public float Z = 0.0f;
    public float aa;
    public float ab = 99999.0f;
    public Boolean ac;
    public float ad;
    public float ae;
    public float af;
    public float ag = -1.0f;
    public float ah = -1.0f;
    public float ai = -1.0f;
    public Float aj;
    public h ak;
    public float al = -1.0f;
    public float am = 2000.0f;
    public float an = -999.0f;
    public bn ao;
    public int ap = -1;
    public boolean aq = true;
    public float ar = 0.0f;
    public float as = 0.0f;
    public float at = 10.0f;
    public float au;
    public LogicBoolean av;
    public float aw = -1.0f;
    public float ax;
    public boolean ay;
    public int az;
    public o aA;
    public float aB;
    public u aC;
    public e aD;
    public e[] aE;
    public e aF;
    public float aG;
    public float aH;
    public int aI;
    public h aJ;

    public int a(j j2) {
        if (this.S >= 0 && this.T.read(j2)) {
            return this.S;
        }
        return this.R;
    }

    public void a(bn bn2) {
        this.f = bn2.f;
        this.g = bn2.g;
        this.h = bn2.h;
        this.j = bn2.j;
        this.k = bn2.k;
        this.l = bn2.l;
        this.m = bn2.m;
        this.n = bn2.n;
        this.o = bn2.o;
        this.u = bn2.u;
        this.v = bn2.v;
        this.aI = bn2.aI;
        this.aJ = bn2.aJ;
        this.s = bn2.s;
        this.t = bn2.t;
        this.p = bn2.p;
        this.q = bn2.q;
        this.r = bn2.r;
        this.y = bn2.y;
        this.A = bn2.A;
        this.B = bn2.B;
        this.C = bn2.C;
        this.D = bn2.D;
        this.E = bn2.E;
        this.G = bn2.G;
        this.F = bn2.F;
        this.J = bn2.J;
        this.K = bn2.K;
        this.L = bn2.L;
        this.M = bn2.M;
        this.N = bn2.N;
        this.O = bn2.O;
        this.P = bn2.P;
        this.Q = bn2.Q;
        this.aD = bn2.aD;
        this.aE = bn2.aE;
        this.aF = bn2.aF;
        this.R = bn2.R;
        this.S = bn2.S;
        this.T = bn2.T;
        this.U = bn2.U;
        this.V = bn2.V;
        this.W = bn2.W;
        this.X = bn2.X;
        this.Y = bn2.Y;
        this.Z = bn2.Z;
        this.aa = bn2.aa;
        this.ao = bn2.ao;
        this.aq = bn2.aq;
        this.as = bn2.as;
        this.at = bn2.at;
        this.au = bn2.au;
        this.av = bn2.av;
        this.aw = bn2.aw;
        this.ax = bn2.ax;
        this.ab = bn2.ab;
        this.ag = bn2.ag;
        this.ai = bn2.ai;
        this.af = bn2.af;
        this.ay = bn2.ay;
        this.az = bn2.az;
        this.ak = bn2.ak;
        this.al = bn2.al;
        this.am = bn2.am;
        this.an = bn2.an;
        this.aA = bn2.aA;
        this.aG = bn2.aG;
        this.aH = bn2.aH;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(bn bn2, l l2, ab ab2, String string) {
        e e2;
        Object object;
        Object object2;
        Float f2;
        Float f3;
        String string2;
        Float f4;
        Boolean bl2;
        Float f5;
        Float f6;
        Float f7;
        Object object3;
        bn2.c = true;
        if (bn2.d) {
            return;
        }
        String string3 = string;
        Float f8 = Float.valueOf(ab2.i(string3, "x"));
        Float f9 = Float.valueOf(ab2.i(string3, "y"));
        String string4 = ab2.b(string3, "copyFrom", (String)null);
        if (string4 != null) {
            object3 = l2.e(string4);
            if (object3 == null) {
                throw new RuntimeException("[" + string + "] Could not find copy turret target with name:" + string4);
            }
            if (((bn)object3).c && !((bn)object3).d) {
                throw new RuntimeException("[" + string + "] Infinite loop detected with turret copies:" + string4);
            }
            if (!((bn)object3).d) {
                bn.a((bn)object3, l2, ab2, ((bn)object3).b);
            }
            bn2.a((bn)object3);
        } else {
            bn2.af = l2.ez;
            bn2.X = l2.ea;
        }
        bn2.f = f8.floatValue();
        bn2.g = f9.floatValue();
        bn2.h = ab2.a(string3, "height", Float.valueOf(0.0f)).floatValue();
        bn2.i = ab2.a(string3, "yAxisScaling", Float.valueOf(1.0f)).floatValue();
        object3 = ab2.b(string3, "linkDelayWithTurret", (String)null);
        if (object3 != null) {
            bn2.ao = l2.e((String)object3);
            if (bn2.ao == null) {
                throw new RuntimeException("[" + string + "] Could not find 'linkDelayWithTurret' turret target with name:" + (String)object3);
            }
            bn2.m = 9000.0f;
        }
        if ((f7 = ab2.b(string3, "delay", (Float)null)) != null) {
            bn2.m = f7.floatValue();
        }
        if (bn2.m == -1.0f) {
            bn2.m = l2.dM;
        }
        if ((f6 = ab2.b(string3, "warmup", (Float)null)) != null) {
            bn2.n = f6.floatValue();
        }
        if ((f5 = ab2.a(string3, "warmupCallDownRate", (Float)null)) != null) {
            bn2.o = f5.floatValue();
        }
        if ((bl2 = ab2.a(string3, "warmupNoReset", (Boolean)null)) != null) {
            bn2.s = bl2;
        }
        if ((f4 = ab2.a(string3, "warmupShootDelayTransfer", (Float)null)) != null) {
            bn2.t = f4.floatValue();
        }
        bn2.p = ab2.a(string3, "recoilOffset", Float.valueOf(bn2.p)).floatValue();
        bn2.q = ab2.a(string3, "recoilOutTime", Float.valueOf(bn2.q)).floatValue();
        bn2.r = ab2.b(string3, "recoilReturnTime", Float.valueOf(bn2.r)).floatValue();
        Float f10 = ab2.a(string3, "energyUsage", (Float)null);
        if (f10 != null) {
            bn2.u = f10.floatValue();
        }
        bn2.aI = ab2.b(string3, "unloadUpToXUnitsAndGiveAttackOrder", bn2.aI);
        b b2 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string3, "resourceUsage", true);
        if (b2 != null && b2.d()) {
            bn2.v = b2;
            l2.a(b2);
        }
        if ((string2 = ab2.b(string3, "attachedTo", (String)null)) != null) {
            bn2.y = l2.e(string2);
            if (bn2.y == null) {
                throw new RuntimeException("[" + string + "] Could not find attachedTo turret target:" + string2);
            }
            if (bn2.y == bn2) {
                throw new RuntimeException("Turret cannot be attachedTo self");
            }
            l2.fU = true;
        }
        if ((f3 = ab2.a(string3, "idleDir", (Float)null)) != null) {
            bn2.j = f3.floatValue();
        }
        if ((f2 = ab2.a(string3, "idleDirReversing", (Float)null)) != null) {
            bn2.k = f2.floatValue();
            bn2.l = true;
        } else if (!bn2.l) {
            bn2.k = bn2.y != null ? 0.0f : bn2.j + 180.0f;
        }
        Boolean bl3 = ab2.a(string3, "canShoot", (Boolean)null);
        Boolean bl4 = ab2.a(string3, "canAttack", (Boolean)null);
        if (bl3 != null && bl4 != null) {
            throw new RuntimeException("[" + string + "] Cannot use canShoot and canAttack at the same time, they have the same meaning");
        }
        if (bl3 != null) {
            bn2.B = bl3;
        } else if (bl4 != null) {
            bn2.B = bl4;
        }
        bn2.D = bl.a(l2, ab2.b(string3, "shoot_sound", (String)null), bn2.D);
        Float f11 = ab2.a(string3, "shoot_sound_vol", (Float)null);
        if (f11 != null) {
            bn2.D.a(f11.floatValue());
        }
        bn2.E = l2.a(ab2.b(string3, "shoot_flame", (String)null), bn2.E);
        bn2.G = ab2.a(string3, "shoot_light", bn2.G);
        bn2.F = l2.a(ab2.b(string3, "warmupStartEffect", (String)null), bn2.F);
        bn2.A = ab2.a(string3, "slave", (Boolean)bn2.A);
        if (bn2.A) {
            if (bn2.y == null) {
                throw new RuntimeException("Turret cannot be a slave without being 'attachedTo' to another turret");
            }
            bn2.z = bn2.y;
        }
        bn2.C = ab2.a(l2, string3, "invisible", bn2.C);
        bn2.J = ab2.a(l2, string3, "canAttackFlyingUnits", bn2.J);
        bn2.K = ab2.a(l2, string3, "canAttackLandUnits", bn2.K);
        bn2.L = ab2.a(l2, string3, "canAttackUnderwaterUnits", bn2.L);
        bn2.M = ab2.a(l2, string3, "canAttackNotTouchingWaterUnits", bn2.M);
        bn2.N = ab2.a(l2, string3, "canAttackCondition", bn2.N);
        bn2.O = ab2.a(l2, string3, "canOnlyAttackUnitsWithTags", bn2.O);
        bn2.P = ab2.a(l2, string3, "canOnlyAttackUnitsWithoutTags", bn2.P);
        String string5 = ab2.b(string3, "projectile", (String)null);
        if (string5 != null) {
            object2 = l2.f(string5);
            if (object2 == null) {
                if (!"0".equals(string5) || l2.fT.size() != 1 || l2.f("1") == null) throw new RuntimeException("[" + string + "] Could not find projectile with name:" + string5);
                bn2.R = 0;
            } else {
                bn2.R = ((bh)object2).bi;
            }
        }
        if ((object2 = ab2.b(string3, "altProjectile", (String)null)) != null) {
            object = l2.f((String)object2);
            if (object == null) {
                throw new RuntimeException("[" + string + "]altProjectile: Could not find projectile with name:" + (String)object2);
            }
            bn2.S = ((bh)object).bi;
        }
        bn2.T = ab2.a(l2, string3, "altProjectileCondition", bn2.T);
        if (bn2.S >= 0 && bn2.T == null) {
            throw new RuntimeException("[" + string + "]altProjectileCondition is required with altProjectile");
        }
        bn2.Q = ab2.a(string3, "canAttackMaxAngle", Float.valueOf(bn2.Q)).floatValue();
        bn2.U = ab2.a(string3, "turnSpeed", bn2.U);
        bn2.V = ab2.a(string3, "turnSpeedAcceleration", Float.valueOf(bn2.V)).floatValue();
        bn2.W = ab2.a(string3, "turnSpeedDeceleration", Float.valueOf(bn2.W)).floatValue();
        object = ab2.a(string3, "barrelY", (Float)null);
        Float f12 = ab2.a(string3, "size", (Float)null);
        if (object != null && f12 != null) {
            throw new RuntimeException("Turret [" + string3 + "]: barrelY and size can not both be used at the same time as they have the same meaning");
        }
        if (object != null) {
            bn2.X = ((Float)object).floatValue();
        }
        if (f12 != null) {
            bn2.X = f12.floatValue();
        }
        bn2.Y = ab2.a(string3, "barrelX", Float.valueOf(bn2.Y)).floatValue();
        bn2.Z = ab2.a(string3, "barrelOffsetX_onOddShots", Float.valueOf(bn2.Z)).floatValue();
        bn2.aa = ab2.a(string3, "barrelHeight", Float.valueOf(bn2.aa)).floatValue();
        bn2.ab = ab2.a(string3, "limitingRange", Float.valueOf(bn2.ab)).floatValue();
        bn2.ai = ab2.a(string3, "limitingAngle", Float.valueOf(bn2.ai)).floatValue();
        bn2.ag = ab2.a(string3, "limitingMinRange", Float.valueOf(bn2.ag)).floatValue();
        bn2.af = ab2.a(string3, "aimOffsetSpread", Float.valueOf(bn2.af)).floatValue();
        if (bn2.ai >= 0.0f) {
            l2.bG = true;
        }
        bn2.ad = bn2.ab < 99999.0f ? bn2.ab : l2.cL.i;
        bn2.ae = bn2.ad * bn2.ad;
        bn2.ah = bn2.ag > 0.0f ? bn2.ag * bn2.ag : -1.0f;
        bn2.ac = ab2.a(string3, "showRangeUIGuide", (Boolean)null);
        bn2.aj = ab2.a(string3, "laserDefenceEnergyUse", bn2.aj);
        if (bn2.aj != null) {
            l2.bE = true;
            l2.a(com.corrodinggames.rts.game.units.custom.b.k.a);
        }
        bn2.ak = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string3, "interceptProjectiles_withTags", (String)null), bn2.ak);
        if (bn2.ak != null) {
            l2.bF = true;
            l2.a(com.corrodinggames.rts.game.units.custom.b.k.a);
            bn2.al = ab2.a(string3, "interceptProjectiles_andTargetingGroundUnderDistance", Float.valueOf(bn2.al)).floatValue();
            bn2.am = ab2.a(string3, "interceptProjectiles_andUnderDistance", Float.valueOf(bn2.am)).floatValue();
            bn2.an = ab2.a(string3, "interceptProjectiles_andOverHeight", Float.valueOf(bn2.an)).floatValue();
        }
        bn2.aq = ab2.a(string3, "shouldResetTurret", (Boolean)bn2.aq);
        bn2.ar = ab2.a(string3, "idleSpin", Float.valueOf(bn2.ar)).floatValue();
        bn2.as = ab2.a(string3, "idleSweepAngle", Float.valueOf(bn2.as)).floatValue();
        bn2.at = ab2.a(string3, "idleSweepDelay", Float.valueOf(bn2.at)).floatValue();
        bn2.au = ab2.a(string3, "idleSweepSpeed", Float.valueOf(bn2.au)).floatValue();
        bn2.av = ab2.a(l2, string3, "idleSweepCondition", bn2.av);
        bn2.av = LogicBoolean.convertAlwaysTrueToNull(bn2.av);
        bn2.aw = ab2.a(string3, "idleSweepAddRandomDelay", Float.valueOf(bn2.aw)).floatValue();
        if (bn2.aw < 0.0f) {
            float f13 = 1.0f;
            if (bn2.at > 200.0f) {
                f13 = 20.0f;
            } else if (bn2.at > 50.0f) {
                f13 = 5.0f;
            }
            bn2.aw = f13;
        }
        bn2.ax = ab2.a(string3, "idleSweepAddRandomAngle", Float.valueOf(bn2.ax)).floatValue();
        if (bn2.ax < 0.0f) {
            throw new RuntimeException("Turret [" + string3 + "]: idleSweepAddRandomAngle must be >= 0");
        }
        if (bn2.as < 0.0f) {
            throw new RuntimeException("Turret [" + string3 + "]: idleSweepAngle must be >= 0");
        }
        bn2.ay = ab2.a(string3, "clearTurretTargetAfterFiring", (Boolean)bn2.ay);
        bn2.aA = l2.a(ab2.b(string3, "onShoot_playAnimation", (String)null), bn2.aA);
        bn2.aB = ab2.b(string3, "onShoot_freezeBodyMovementFor", Float.valueOf(bn2.aB)).floatValue();
        bn2.aC = ab2.a(l2, string3, "onShoot_triggerActions", bn2.aC);
        if (ab2.a(string3, "isMainNanoTurret", (Boolean)false).booleanValue()) {
            l2.fV = bn2;
        }
        if ((e2 = l2.a(ab2, string3, "image")) != null) {
            bn2.aD = e2;
            boolean bl5 = l2.s;
            Boolean bl6 = ab2.a(string3, "image_applyTeamColors", (Boolean)null);
            if (bl6 != null) {
                bl5 = bl6;
            }
            bn2.aE = bl5 ? l2.a(bn2.aD, l2.ac) : null;
        }
        bn2.aG = ab2.a(string3, "image_drawOffsetX", Float.valueOf(bn2.aG)).floatValue();
        bn2.aH = ab2.a(string3, "image_drawOffsetY", Float.valueOf(bn2.aH)).floatValue();
        e e3 = l2.a(ab2, string3, "chargeEffectImage");
        if (e3 != null) {
            bn2.aF = e3;
            l2.fP = true;
        }
        if (l2.fR[bn2.R] == null) {
            throw new RuntimeException("Turret [" + string3 + "]: cannot find linked projectile:" + bn2.R);
        }
        if (bn2.S >= 0 && l2.fR[bn2.S] == null) {
            throw new RuntimeException("Turret [" + string3 + "]altProjectile: cannot find linked projectile");
        }
        bn2.J = LogicBoolean.convertAlwaysTrueToNull(bn2.J);
        bn2.K = LogicBoolean.convertAlwaysTrueToNull(bn2.K);
        bn2.L = LogicBoolean.convertAlwaysTrueToNull(bn2.L);
        bn2.M = LogicBoolean.convertAlwaysTrueToNull(bn2.M);
        bn2.N = LogicBoolean.convertAlwaysTrueToNull(bn2.N);
        if (bn2.ai != -1.0f || bn2.J != null || bn2.K != null || bn2.L != null || bn2.M != null || bn2.N != null) {
            bn2.H = true;
        }
        if (bn2.ab < 99999.0f || bn2.ag > 0.0f) {
            bn2.H = true;
            bn2.I = true;
        }
        if (bn2.O != null || bn2.P != null) {
            bn2.H = true;
        }
        bn2.d = true;
    }
}
