/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.bi;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.w;

public class bh
extends com.corrodinggames.rts.game.g {
    public String bh;
    public int bi;
    public l bj;

    public static void a(bh bh2, l l2, ab ab2, String string) {
        Object object;
        Object object2;
        Object object3;
        bp bp2;
        String string2;
        String string3;
        e e2;
        e e3;
        Object object4;
        String string4 = string;
        Integer n2 = ab2.b(string4, "directDamage", (Integer)null);
        Integer n3 = ab2.b(string4, "areaDamage", (Integer)null);
        if (n2 == null && n3 == null) {
            throw new RuntimeException("[" + string4 + "]: directDamage or areaDamage must be set");
        }
        bh2.s = ab2.a(string4, "targetGround", (Boolean)bh2.s);
        bh2.t = ab2.a(string4, "targetGround_includeTargetHeight", (Boolean)bh2.t);
        Integer n4 = ab2.b(string4, "areaRadius", (Integer)null);
        if (n4 != null) {
            bh2.i = n4;
        }
        bh2.b = ab2.b(string4, "directDamage", bh2.b);
        bh2.c = ab2.b(string4, "areaDamage", bh2.c);
        bh2.d = ab2.a(string4, "interceptProjectile_removeTargetLifeOnly", (Boolean)bh2.d);
        bh2.g = ab2.a(string4, "areaDamageNoFalloff", (Boolean)bh2.g);
        bh2.j = ab2.a(string4, "areaIgnoreUnitsCloserThan", Float.valueOf(bh2.j)).floatValue();
        bh2.h = ab2.a(string4, "areaRadiusFromEdge", (Boolean)bh2.h);
        if ("only-ignoreEnemy".equalsIgnoreCase(ab2.b(string4, "friendlyFire", (String)null))) {
            bh2.l = true;
        } else {
            object4 = ab2.a(string4, "friendlyFire", (Boolean)null);
            if (object4 != null) {
                bh2.l = false;
                bh2.k = (Boolean)object4;
            }
        }
        bh2.m = ab2.a(string4, "areaHitAirAndLandAtSameTime", (Boolean)bh2.m);
        bh2.n = ab2.a(string4, "areaHitUnderwaterAlways", (Boolean)bh2.n);
        bh2.o = ab2.a(string4, "deflectionPower", Float.valueOf(bh2.o)).floatValue();
        bh2.p = ab2.a(string4, "nukeWeapon", (Boolean)bh2.p);
        bh2.q = ab2.a(string4, "shouldRevealFog", (Boolean)bh2.q);
        bh2.r = ab2.a(string4, "alwaysVisibleInFog", (Boolean)bh2.r);
        bh2.v = ab2.h(string4, "life").floatValue();
        bh2.u = ab2.b(string4, "delayedStartTimer", Float.valueOf(0.0f)).floatValue();
        bh2.w = ab2.a(string4, "speed", Float.valueOf(bh2.w)).floatValue();
        bh2.x = ab2.a(string4, "frame", (Short)bh2.x);
        bh2.y = ab2.a(string4, "drawType", (Short)bh2.y);
        bh2.z = ab2.a(string4, "shadowFrame", (Short)bh2.z);
        object4 = l2.a(ab2, string4, "image");
        if (object4 != null) {
            bh2.B = object4;
        }
        if ((e3 = l2.a(ab2, string4, "shadowImage")) != null) {
            bh2.C = e3;
        }
        bh2.ad = ab2.a(string4, "beamImageOffsetRate", Float.valueOf(bh2.ad)).floatValue();
        e e4 = l2.a(ab2, string4, "beamImage");
        if (e4 != null) {
            bh2.Y = e4;
            bh2.X = true;
            if (e4.q < 20 && !com.corrodinggames.rts.gameFramework.l.ax()) {
                throw new RuntimeException("beamImage height must currently be 20 pixels or greater (performance when tiling)");
            }
        }
        if ((e2 = l2.a(ab2, string4, "beamImageStart")) != null) {
            bh2.Z = e2;
            if (e4 == null) {
                throw new RuntimeException("beamImageStart requires beamImage to be set");
            }
        }
        bh2.aa = ab2.a(string4, "beamImageStartRotated", (Boolean)false);
        e e5 = l2.a(ab2, string4, "beamImageEnd");
        if (e5 != null) {
            bh2.ab = e5;
            if (e4 == null) {
                throw new RuntimeException("beamImageEnd requires beamImage to be set");
            }
        }
        bh2.ac = ab2.a(string4, "beamImageEndRotated", (Boolean)false);
        bh2.A = ab2.a(string4, "invisible", (Boolean)bh2.A);
        bh2.D = ab2.a(string4, "initialUnguidedSpeedHeight", Float.valueOf(bh2.D)).floatValue();
        bh2.E = ab2.a(string4, "initialUnguidedSpeedX", Float.valueOf(bh2.E)).floatValue();
        bh2.F = ab2.a(string4, "initialUnguidedSpeedY", Float.valueOf(bh2.F)).floatValue();
        bh2.G = ab2.a(string4, "gravity", Float.valueOf(bh2.G)).floatValue();
        bh2.H = ab2.a(string4, "trueGravity", Float.valueOf(bh2.H)).floatValue();
        bh2.I = ab2.a(string4, "instant", (Boolean)bh2.I);
        bh2.L = ab2.a(string4, "instantReuseLast", (Boolean)bh2.L);
        bh2.M = ab2.a(string4, "instantReuseLast_alsoChangeTurretAim", (Boolean)bh2.M);
        if (bh2.M) {
            if (!bh2.L) {
                throw new RuntimeException("[" + string4 + "]instantReuseLast_alsoChangeTurretAim also requires instantReuseLast");
            }
            l2.eA = true;
        }
        bh2.N = ab2.a(string4, "instantReuseLast_keepAreaDamageList", (Boolean)bh2.N);
        bh2.T = ab2.a(string4, "moveWithParent", (Boolean)bh2.T);
        bh2.J = ab2.a(string4, "disableLeadTargeting", (Boolean)bh2.J);
        bh2.K = ab2.a(string4, "leadTargetingSpeedCalculation", Float.valueOf(bh2.K)).floatValue();
        bh2.ae = ab2.a(string4, "ballistic", (Boolean)bh2.ae);
        String string5 = ab2.b(string4, "trailEffect", (String)null);
        if (string5 != null) {
            if (string5.equalsIgnoreCase("true")) {
                bh2.af = true;
            } else if (string5.equalsIgnoreCase("false")) {
                bh2.af = false;
            } else {
                bh2.af = false;
                bh2.ah = l2.a(string5, (z)null);
            }
        }
        if ((string3 = ab2.b(string4, "effectOnCreate", (String)null)) != null) {
            bh2.ai = l2.a(string3, (z)null);
        }
        bh2.ag = ab2.a(string4, "trailEffectRate", Float.valueOf(bh2.ag)).floatValue();
        if (bh2.af) {
            bh2.ao = -1118720;
        }
        bh2.am = ab2.a(string4, "wobbleAmplitude", Float.valueOf(bh2.am)).floatValue();
        bh2.an = ab2.b(string4, "wobbleFrequency", Float.valueOf(bh2.an)).floatValue();
        if (bh2.an <= 0.0f) {
            throw new RuntimeException("wobbleFrequency must be greater than 0");
        }
        bh2.ak = com.corrodinggames.rts.game.units.custom.bi.a(l2, ab2, string4, "spawnProjectilesOnEndOfLife", null);
        bh2.aj = com.corrodinggames.rts.game.units.custom.bi.a(l2, ab2, string4, "spawnProjectilesOnExplode", null);
        bh2.al = com.corrodinggames.rts.game.units.custom.bi.a(l2, ab2, string4, "spawnProjectilesOnCreate", null);
        bh2.ao = ab2.a(string4, "lightColor", (Integer)bh2.ao);
        bh2.ap = ab2.a(string4, "lightSize", Float.valueOf(bh2.ap)).floatValue();
        bh2.aq = ab2.a(string4, "lightCastOnGround", (Boolean)bh2.aq);
        bh2.ar = ab2.a(string4, "largeHitEffect", (Boolean)bh2.ar);
        bh2.O = ab2.a(string4, "turnSpeed", Float.valueOf(bh2.O)).floatValue();
        bh2.P = ab2.a(string4, "turnSpeedWhenNear", Float.valueOf(bh2.P)).floatValue();
        bh2.Q = ab2.a(string4, "sweepSpeed", Float.valueOf(bh2.Q)).floatValue();
        bh2.R = ab2.a(string4, "sweepOffset", Float.valueOf(bh2.R)).floatValue();
        bh2.S = ab2.a(string4, "sweepOffsetFromTargetRadius", Float.valueOf(bh2.S)).floatValue();
        bh2.U = ab2.a(string4, "drawUnderUnits", (Boolean)bh2.U);
        bh2.V = ab2.a(string4, "lightingEffect", (Boolean)bh2.V);
        bh2.W = ab2.a(string4, "laserEffect", (Boolean)bh2.W);
        if (bh2.W && bh2.Y == null) {
            bh2.aE = Color.a(80, 255, 0, 0);
        }
        if (bh2.V && bh2.s) {
            throw new RuntimeException("lightingEffect must be targeted, cannot be targetGround");
        }
        if (bh2.W && bh2.s) {
            throw new RuntimeException("laserEffect must be targeted, cannot be targetGround");
        }
        bh2.as = ab2.a(string4, "ballistic_delaymove_height", Float.valueOf(bh2.as)).floatValue();
        bh2.at = ab2.a(string4, "ballistic_height", Float.valueOf(bh2.at)).floatValue();
        bh2.au = ab2.a(string4, "targetSpeed", Float.valueOf(bh2.au)).floatValue();
        bh2.av = ab2.a(string4, "targetSpeedAcceleration", Float.valueOf(bh2.av)).floatValue();
        bh2.aw = ab2.a(string4, "autoTargetingOnDeadTarget", (Boolean)bh2.aw);
        bh2.ax = ab2.a(string4, "autoTargetingOnDeadTargetRange", Float.valueOf(bh2.ax)).floatValue();
        bh2.ay = ab2.a(string4, "autoTargetingOnDeadTargetLead", Float.valueOf(bh2.ay)).floatValue();
        bh2.az = ab2.a(string4, "retargetingInFlight", (Boolean)bh2.az);
        bh2.aA = ab2.a(string4, "retargetingInFlightSearchDelay", Float.valueOf(bh2.aA)).floatValue();
        bh2.aB = ab2.a(string4, "retargetingInFlightSearchRange", Float.valueOf(bh2.aB)).floatValue();
        bh2.aC = ab2.a(string4, "retargetingInFlightSearchLead", Float.valueOf(bh2.aC)).floatValue();
        bh2.aD = ab2.a(l2, string4, "retargetingInFlightSearchOnlyTags", (h)null);
        if (bh2.ax > 1500.0f) {
            throw new RuntimeException("for performance autoTargetingOnDeadTargetRange cannot be >1500");
        }
        if (bh2.aB > 1500.0f) {
            throw new RuntimeException("for performance retargetingInFlightSearchRange cannot be >1500");
        }
        bh2.aE = ab2.a(string4, "color", (Integer)bh2.aE);
        bh2.aG = ab2.a(string4, "teamColorRatio", Float.valueOf(bh2.aG)).floatValue();
        if (bh2.aG < 0.0f || bh2.aG > 1.0f) {
            throw new RuntimeException("teamColorRatio should be between 0-1 got:" + bh2.aG);
        }
        bh2.aH = ab2.a(string4, "teamColorRatio_sourceRatio", Float.valueOf(1.0f - bh2.aG)).floatValue();
        if (bh2.aH < 0.0f || bh2.aH > 1.0f) {
            throw new RuntimeException("teamColorRatio_sourceRatio should be between 0-1 got:" + bh2.aH);
        }
        if (bh2.aG == 0.0f && bh2.aH != 1.0f) {
            throw new RuntimeException("teamColorRatio_sourceRatio requires teamColorRatio");
        }
        bh2.aF = ab2.a(string4, "drawSize", Float.valueOf(bh2.aF)).floatValue();
        bh2.aI = ab2.a(string4, "flameWeapon", (Boolean)bh2.aI);
        bh2.aJ = ab2.a(string4, "hitSound", (Boolean)bh2.aJ);
        bh2.aL = ab2.a(string4, "targetGroundHeightOffset", Float.valueOf(bh2.aL)).floatValue();
        bh2.aK = ab2.a(string4, "targetGroundSpread", Float.valueOf(bh2.aK)).floatValue();
        bh2.aM = ab2.a(string4, "speedSpread", Float.valueOf(bh2.aM)).floatValue();
        bh2.aO = ab2.a(string4, "explodeOnEndOfLife", (Boolean)bh2.aO);
        bh2.aN = ab2.a(string4, "ignoreParentShootDamageMultiplier", (Boolean)bh2.aN);
        bh2.aP = ab2.a(string4, "pushForce", Float.valueOf(bh2.aP)).floatValue();
        bh2.aQ = ab2.a(string4, "pushVelocity", Float.valueOf(bh2.aQ)).floatValue();
        bh2.aR = ab2.a(string4, "buildingDamageMultiplier", Float.valueOf(bh2.aR)).floatValue();
        bh2.aS = ab2.a(string4, "shieldDamageMultiplier", Float.valueOf(bh2.aS)).floatValue();
        bh2.aT = ab2.a(string4, "shieldDefectionMultiplier", Float.valueOf(bh2.aT)).floatValue();
        bh2.aU = ab2.a(string4, "hullDamageMultiplier", Float.valueOf(bh2.aU)).floatValue();
        bh2.aV = ab2.a(string4, "armourIgnoreAmount", Float.valueOf(bh2.aV)).floatValue();
        bh2.aW = ab2.a(string4, "areaExpandTime", Float.valueOf(bh2.aW)).floatValue();
        String string6 = ab2.b(string4, "explodeEffect", (String)null);
        if (string6 != null) {
            bh2.aX = l2.a(string6, (z)null);
        }
        if ((string2 = ab2.b(string4, "explodeEffectOnShield", (String)null)) != null) {
            bh2.aY = l2.a(string2, (z)null);
        }
        if ((bp2 = bp.a(l2, ab2, string4, "spawnUnit")) != null && !bp2.b()) {
            bh2.aZ = bp2;
        }
        bh2.ba = ab2.b(string4, "unloadUpToXUnitsFromSource", bh2.ba);
        bh2.bb = ab2.a(string4, "teleportSource", (Boolean)bh2.bb);
        bh2.bc = ab2.a(string4, "convertHitToSourceTeam", (Boolean)bh2.bc);
        bh2.bd = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string4, "tags", (String)null));
        m m2 = ab2.k(string, "mutator");
        m m3 = new m();
        for (String string7 : m2) {
            object3 = string7.split("_");
            if (((String[])object3).length <= 1 || m3.contains(object2 = (String)(object = object3[0]) + "_") || ((String)object).length() <= "mutator".length()) continue;
            m3.add(object2);
        }
        for (String string7 : m3) {
            String string8;
            object3 = new com.corrodinggames.rts.game.h();
            object3.a = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string4, string7 + "ifUnitWithTags", (String)null));
            object3.b = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string4, string7 + "ifUnitWithoutTags", (String)null));
            if (object3.a == null && object3.b == null) {
                throw new RuntimeException("[" + string4 + "]" + string7 + " requires: unitWithTags and/or unitWithoutTags");
            }
            object3.c = ab2.a(string4, string7 + "directDamageMultiplier", Float.valueOf(1.0f)).floatValue();
            object3.d = ab2.a(string4, string7 + "areaDamageMultiplier", Float.valueOf(1.0f)).floatValue();
            object = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string7 + "addResourcesDirectHit", true);
            if (object != null && ((b)object).d()) {
                object3.e = object;
                if (bh2.s) {
                    throw new RuntimeException("[" + string4 + "]" + string7 + "addResourcesDirectHit doesn't work with targetGround, as it will never get direct hits (use addResourcesAreaHit)");
                }
            }
            if ((object2 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string7 + "addResourcesAreaHit", true)) != null && ((b)object2).d()) {
                object3.f = object2;
                if (n4 == null) {
                    throw new RuntimeException("[" + string4 + "]" + string7 + "addResourcesAreaHit requires areaRadius to be set");
                }
            }
            if ((string8 = ab2.b(string4, string7 + "changedExplodeEffect", (String)null)) != null) {
                object3.g = l2.a(string8, (z)null);
                if (object3.g != null && !object3.g.a()) {
                    object3.g = null;
                }
            }
            boolean bl2 = false;
            boolean bl3 = false;
            if (!com.corrodinggames.rts.gameFramework.f.k(object3.c, 1.0f)) {
                bl2 = true;
            }
            if (!com.corrodinggames.rts.gameFramework.f.k(object3.d, 1.0f) && bh2.c != 0 && bh2.i > 0) {
                bl3 = true;
            }
            if (object3.e != null) {
                bl2 = true;
            }
            if (object3.f != null) {
                bl3 = true;
            }
            if (bl2) {
                if (bh2.be == null) {
                    bh2.be = new m();
                }
                bh2.be.add(object3);
            }
            if (bl3) {
                if (bh2.bf == null) {
                    bh2.bf = new m();
                }
                bh2.e = true;
                bh2.bf.add(object3);
            }
            if (object3.g == null) continue;
            if (bh2.bg == null) {
                bh2.bg = new m();
            }
            bh2.bg.add(object3);
        }
        if (bh2.c != 0 && bh2.i > 0) {
            bh2.e = true;
        }
        if ((bh2.aP != 0.0f || bh2.aQ != 0.0f) && bh2.i > 0) {
            bh2.e = true;
        }
        bh2.f = !bh2.e;
        l2.fT.add(bh2);
    }

    public static void a(bh bh2, com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(bh2.bj);
        as2.c(bh2.bh);
    }

    public static com.corrodinggames.rts.game.g b(k k2) {
        as as2 = k2.q();
        String string = k2.l();
        if (as2 == null) {
            return null;
        }
        if (!(as2 instanceof l)) {
            com.corrodinggames.rts.gameFramework.l.b("ProjectileTemplate:readInLinkCustom: Got non CustomUnitMetadata object of:" + as2.i() + " loading real_meta");
            return null;
        }
        l l2 = (l)as2;
        bh bh2 = l2.f(string);
        if (bh2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("ProjectileTemplate:readInLinkCustom: Could not find projectile with name:" + string);
            return null;
        }
        return bh2;
    }

    public void a(am am2, f f2, am am3, float f3, float f4, float f5) {
        bh bh2 = this;
        if (am3 == null) {
            f2.aC = true;
            f2.n = f3;
            f2.o = f4;
            if (bh2.aK != 0.0f) {
                f2.n += (float)com.corrodinggames.rts.gameFramework.f.a((w)am2, (int)(-bh2.aK * 100.0f), (int)(bh2.aK * 100.0f), 2) / 100.0f;
                am2.bC = (int)((float)am2.bC + f2.n);
                f2.o += (float)com.corrodinggames.rts.gameFramework.f.a((w)am2, (int)(-bh2.aK * 100.0f), (int)(bh2.aK * 100.0f), 3) / 100.0f;
                am2.bC = (int)((float)am2.bC + f2.o);
            }
            f2.p = 0.0f;
            f2.p += bh2.aL;
        } else if (f2.m) {
            f2.aC = true;
            if (!bh2.J) {
                float f6 = f2.t;
                if (bh2.au != -1.0f) {
                    f6 = bh2.au;
                }
                if (bh2.K >= 0.0f) {
                    f6 = bh2.K;
                }
                PointF pointF = am3.a(f2.eo, f2.ep, f6, f2.h, f5);
                f2.n = pointF.a;
                f2.o = pointF.b;
            } else {
                f2.n = am3.eo;
                f2.o = am3.ep;
            }
            f2.p = bh2.t ? am3.eq : 0.0f;
            f2.p += bh2.aL;
            if (bh2.aK != 0.0f) {
                f2.n += (float)com.corrodinggames.rts.gameFramework.f.a((w)am2, (int)(-bh2.aK * 100.0f), (int)(bh2.aK * 100.0f), 2) / 100.0f;
                f2.o += (float)com.corrodinggames.rts.gameFramework.f.a((w)am2, (int)(-bh2.aK * 100.0f), (int)(bh2.aK * 100.0f), 7) / 100.0f;
            }
        } else {
            f2.l = am3;
        }
    }
}
