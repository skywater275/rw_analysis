/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;
import com.corrodinggames.rts.R;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.actions.BuildAction;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase;
import com.corrodinggames.rts.game.units.custom.animation.UnitTrait;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.UnitStateTracker;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.ArrayList;

public strictfp final class AnimationTurretCurve
extends AnimationCurveBase {
    public static AnimationTurretCurve a = new AnimationTurretCurve();

    /* 02b custom/b/m.java L21: 调 a(UnitTrait,...) 抛 checked bo */
    public static void a(ModUnitRegistry l2, ab ab2) throws bo {
        String string = "attachment_";
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = ab2.e(string);
        if (m2.size() > 0) {
            l2.a(a);
            short s2 = 0;
            for (String string2 : (java.util.Collection<String>) (java.util.Collection) m2) {
                String string3 = string2.substring(string.length());
                UnitTrait n2 = new UnitTrait();
                AnimationTurretCurve.a(n2, l2, ab2, string2, string3);
                n2.b = string3;
                n2.a = s2;
                s2 = (short)(s2 + 1);
                l2.aA.add(n2);
            }
        }
    }

    /* 02b custom/b/m.java L42: 大量抛 checked bo */
    public static void a(UnitTrait n2, ModUnitRegistry l2, ab ab2, String string, String string2) throws bo {
        n2.c = ab2.i(string, "x");
        n2.d = ab2.i(string, "y");
        n2.e = ab2.a(string, "height", Float.valueOf(n2.e)).floatValue();
        n2.i = ab2.a(string, "lockDir", (Boolean)n2.i);
        n2.j = ab2.a(string, "redirectDamageToParent", (Boolean)n2.j);
        n2.k = ab2.a(string, "redirectDamageToParent_shieldOnly", (Boolean)n2.k);
        if (!n2.j && n2.k) {
            throw new bo("[" + string + "] redirectDamageToParent_shieldOnly requires redirectDamageToParent");
        }
        n2.l = ab2.a(string, "canBeAttackedAndDamaged", (Boolean)n2.l);
        n2.m = ab2.a(string, "isUnselectable", (Boolean)n2.m);
        n2.n = ab2.a(string, "isUnselectableAsTarget", (Boolean)n2.m);
        n2.o = ab2.a(string, "isVisible", (Boolean)n2.o);
        n2.p = ab2.a(string, "showMiniHp", (Boolean)n2.p);
        n2.q = ab2.a(string, "hideHp", (Boolean)n2.q);
        n2.N = ab2.a(l2, string, "showAllActionsFrom", (LogicBoolean)null);
        if (LogicBoolean.isStaticFalse(n2.N)) {
            n2.N = null;
        }
        Float f2 = ab2.a(string, "idleDir", (Float)null);
        Float f3 = ab2.a(string, "idleDirReversing", (Float)null);
        if (f2 != null) {
            n2.f = f2.floatValue();
            n2.g = f2.floatValue();
        }
        n2.g = f3 != null ? f3.floatValue() : n2.f;
        n2.h = ab2.a(string, "resetRotationWhenNotAttacking", (Boolean)false);
        n2.r = ab2.a(string, "rotateWithParent", (Boolean)n2.r);
        n2.s = ab2.a(string, "lockLegMovement", (Boolean)n2.s);
        n2.t = ab2.a(string, "freezeLegMovement", (Boolean)n2.t);
        n2.u = ab2.a(string, "lockRotation", (Boolean)n2.u);
        if (n2.u && n2.h) {
            throw new bo("[" + string + "] Cannot use lockRotation and resetRotationWhenIdle at same time");
        }
        n2.v = ab2.a(string, "keepAliveWhenParentDies", (Boolean)n2.v);
        n2.w = bp.b(l2, ab2, string, "onCreateSpawnUnitOf");
        if (n2.w.b()) {
            n2.w = null;
        }
        n2.x = ab2.a(string, "createIncompleteIfParentIs", (Boolean)n2.x);
        n2.y = ab2.a(string, "onConvertKeepExistingUnitInSameSlot", (Boolean)n2.y);
        n2.z = ab2.a(string, "onParentTeamChangeKeepCurrentTeam", (Boolean)n2.z);
        n2.B = ab2.a(string, "setDrawLayerOnBottom", (Boolean)n2.B);
        if (n2.B) {
            n2.A = false;
        }
        n2.A = ab2.a(string, "setDrawLayerOnTop", (Boolean)n2.A);
        if (n2.A && n2.B) {
            throw new bo("[" + string + "] Cannot use setDrawLayerOnTop and setDrawLayerOnBottom at same time");
        }
        n2.D = ab2.a(string, "addTransportedUnits", (Boolean)n2.D);
        n2.E = ab2.a(string, "unloadInCurrentPosition", (Boolean)n2.E);
        n2.F = ab2.a(string, "smoothlyBlendPositionWhenExistingUnitAdded", (Boolean)n2.F);
        n2.G = n2.F ? 500.0f : 0.0f;
        n2.H = ab2.a(string, "deattachIfWantingToMove", (Boolean)n2.H);
        n2.I = ab2.a(string, "hidden", (Boolean)n2.I);
        n2.J = ab2.a(string, "prioritizeParentsMainTarget", (Boolean)n2.J);
        n2.K = ab2.a(string, "onlyAttackParentsMainTarget", (Boolean)n2.K);
        n2.L = ab2.a(string, "alwaysAllowedToAttackParentsMainTarget", (Boolean)n2.L);
        n2.M = ab2.a(string, "canAttack", (Boolean)n2.M);
        n2.O = ab2.a(string, "keepWaypointsNeedingMovement", (Boolean)n2.O);
        if (n2.D) {
            l2.aB = true;
        }
    }

    @Override
    public void a(CustomUnitType j2, float f2) {
        this.b(j2, f2);
    }

    @Override
    public void b(CustomUnitType j2, float f2) {
        Object[] objectArray;
        Object object;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.game.units.custom.ModUnitRegistry l3 = j2.x;
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = l3.aA;
        if (m2.a == 0) {
            return;
        }
        if (l3.aB) {
            objectArray = m2.a();  // 02b custom/b/m.java: m.a() 返回 Object[]
            block0: for (int i2 = 0; i2 < m2.a; ++i2) {
                UnitType y2;
                UnitTrait unitTrait = (UnitTrait) objectArray[i2];  // 02b custom/b/m.java: (n)var8.next()
                if (!unitTrait.D || j2.B.a <= 0 || (y2 = AnimationTurretCurve.a(j2, unitTrait)) != null) continue;
                for (Object object2 : j2.B) {
                    if (!(object2 instanceof UnitType) || ((UnitInstance) object2).cO != null || !j2.a((UnitType)object2, unitTrait)) continue;
                    ((UnitInstance) object2).cN = null;
                    continue block0;
                }
            }
        }
        if ((object = j2.C) == null) {
            return;
        }
        float f3 = j2.cg - j2.D;
        j2.D = j2.cg;
        objectArray = ((com.corrodinggames.rts.gameFramework.utility.CustomArrayList)object).a();
        for (int i3 = ((com.corrodinggames.rts.gameFramework.utility.CustomArrayList)object).a - 1; i3 >= 0; --i3) {
            float f4;
            Object object2;
            UnitType y3 = (UnitType)objectArray[i3];
            if (y3 == null) continue;
            if (y3.isDead) {
                y3.bx();
                objectArray[i3] = null;
                continue;
            }
            if (j2.cN != null) {
                if (y3.cN == null) {
                    y3.cN = j2.cN;
                    l2.bS.l(y3);
                }
            } else if (y3.cN != null && y3.cN != j2) {
                y3.cN = null;
            }
            object2 = (UnitTrait) m2.get(i3);
            float f5 = com.corrodinggames.rts.gameFramework.GameUtils.cosFast(j2.cg);
            float f6 = com.corrodinggames.rts.gameFramework.GameUtils.sinFast(j2.cg);
            float f7 = f5 * ((UnitTrait) object2).d - f6 * ((UnitTrait) object2).c;
            float f8 = f6 * ((UnitTrait) object2).d + f5 * ((UnitTrait) object2).c;
            f7 += j2.eo;
            f8 += j2.ep;
            float f9 = j2.eq + ((UnitTrait) object2).e;
            if (com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.b(y3.cQ, (int)((UnitTrait) object2).G)) {
                f4 = 0.05f;
                y3.eo += (f7 - y3.eo) * f4;
                y3.ep += (f8 - y3.ep) * f4;
                y3.eq += (f9 - y3.eq) * f4;
            } else {
                y3.eo = f7;
                y3.ep = f8;
                y3.eq = f9;
            }
            if (y3.cm < 1.0f && ((UnitTrait) object2).x) {
                y3.r(j2.cm);
                y3.cn = j2.cm;
            }
            if (((UnitTrait) object2).A) {
                if (y3.em <= j2.em) {
                    int n2 = 0;
                    if (y3 instanceof CustomUnitType) {
                        n2 = ((CustomUnitType) y3).x.cI;
                    }
                    y3.em = j2.em;
                    y3.en = j2.en + 1 + n2;
                }
            } else if (((UnitTrait) object2).B && y3.em >= j2.em) {
                y3.em = j2.em;
                y3.en = j2.en - 1;
            }
            f4 = j2.ci ? j2.cg + ((UnitTrait) object2).g : j2.cg + ((UnitTrait) object2).f;
            if (!y3.bI()) {
                if (((UnitTrait) object2).u) {
                    y3.h(f4);
                } else {
                    if (f3 != 0.0f && ((UnitTrait) object2).r) {
                        y3.i(f3);
                    }
                    if (((UnitTrait) object2).h && y3.R == null) {
                        y3.c(f2, f4);
                    }
                }
            }
            if (((UnitTrait) object2).K) {
                y3.R = j2.R;
                y3.S = 5.0f;
            }
            if (((UnitTrait) object2).L && y3.R == null) {
                y3.R = j2.R;
            }
            if (((UnitTrait) object2).J && j2.R != null && y3.R != j2.R) {
                boolean bl2 = false;
                if (((UnitTrait) object2).L) {
                    bl2 = true;
                }
                if (y3.a(j2.R, bl2)) {
                    y3.R = j2.R;
                    y3.S = 5.0f;
                }
            }
            if (!(y3 instanceof CustomUnitType)) continue;
            com.corrodinggames.rts.game.units.custom.CustomUnitType j3 = (CustomUnitType) y3;
            if (!((UnitTrait) object2).s) continue;
            j3.dP = j3.eo;
            j3.dP = j3.ep;
            j3.dR = j3.eq;
        }
    }

    public void a(CustomUnitType j2, boolean bl2) {
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = j2.C;
        if (m2 == null) {
            return;
        }
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m3 = j2.x.aA;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            UnitType y2 = (UnitType)objectArray[i2];
            if (y2 == null) continue;
            UnitTrait n2 = (UnitTrait) m3.get(i2);
            y2.bx();
            objectArray[i2] = null;
            if (!bl2 || n2.v) continue;
            y2.ci();
        }
    }

    @Override
    public void b(CustomUnitType j2) {
        this.a(j2, true);
    }

    @Override
    public void c(CustomUnitType j2) {
        this.a(j2, true);
    }

    @Override
    public void a(CustomUnitType j2) {
        boolean bl2 = false;
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = j2.x.aA;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            UnitTrait n2 = (UnitTrait) objectArray[i2];
            if (n2.w == null) continue;
            UnitType y2 = AnimationTurretCurve.a(j2, n2);
            if (y2 != null) {
                if (n2.y) continue;
                y2.ci();
            }
            com.corrodinggames.rts.gameFramework.utility.CustomArrayList m3 = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
            n2.w.a(m3, j2.player, j2, true);
            if (m3.size() > 1) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("onCreateSpawnUnitOf: created UnitStateTracker extra " + (m3.size() - 1) + " units");
                for (int i3 = 1; i3 < m3.size(); ++i3) {
                    ((UnitInstance) m3.get(i3)).canBuild();
                }
            }
            if (m3.size() == 0) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("onCreateSpawnUnitOf: Warning no units created");
                continue;
            }
            UnitInstance am2 = (UnitInstance) m3.get(0);
            if (!(am2 instanceof UnitType)) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("onCreateSpawnUnitOf: Warning " + am2.r().i() + " not UnitStateTracker orderable unit type, cannot attach");
                am2.canBuild();
                continue;
            }
            UnitType y3 = (UnitType)am2;
            if (!j2.a(y3, n2)) continue;
            y3.cQ = -9999;
            if (j2.cm < 1.0f && n2.x) {
                y3.r(j2.cm);
                y3.cn = j2.cm;
            }
            bl2 = true;
        }
        if (bl2) {
            this.b(j2, 0.0f);
        }
    }

    @Override
    public void a(CustomUnitType j2, ModUnitRegistry l2) {
        UnitType y2;
        int n2;
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = j2.C;
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m3 = j2.x.aA;
        if (m3.size() == 0) {
            j2.C = null;
            return;
        }
        if (m2 == null) {
            return;
        }
        for (n2 = m2.size() - 1; n2 >= 0; --n2) {
            y2 = (UnitType)m2.get(n2);
            if (y2 == null || n2 < m3.size()) continue;
            y2.ci();
            m2.remove(n2);
        }
        for (n2 = m2.size() - 1; n2 >= 0; --n2) {
            y2 = (UnitType)m2.get(n2);
            if (y2 == null) continue;
            y2.cP = (UnitTrait) m3.get(n2);
        }
    }

    public static UnitTrait a(CustomUnitType j2, short s2) {
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = j2.x.aA;
        if (m2.a <= s2) {
            return null;
        }
        return (UnitTrait) m2.get(s2);
    }

    public static UnitType a(CustomUnitType j2, UnitTrait n2) {
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2 = j2.C;
        if (m2 == null) {
            return null;
        }
        short s2 = n2.a;
        if (m2.a <= s2) {
            return null;
        }
        return (UnitType)m2.get(s2);
    }

    public static boolean a(CustomUnitType j2, UnitTrait n2, UnitType y2) {
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2;
        com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = j2.x;
        short s2 = n2.a;
        if (l2.aA.a <= s2 && y2 != null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("setAttachedUnitLookup: slot:" + s2 + " larger than max slot size:" + l2.aA.a);
            return false;
        }
        if (j2.C == null) {
            j2.C = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
        }
        if ((m2 = j2.C).size() == 0) {
            j2.D = j2.cg;
        }
        if (y2 == null && s2 >= m2.size()) {
            return true;
        }
        while (m2.size() <= s2) {
            m2.add((Object)null);
        }
        m2.set((int)s2, y2);
        return true;
    }

    public static void a(CustomUnitType j2, com.corrodinggames.rts.gameFramework.utility.CustomArrayList m2, boolean bl2) {
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList m3 = j2.C;
        if (m3 != null) {
            for (UnitInstance am2 : (java.util.Collection<UnitInstance>) (java.util.Collection) m3) {
                UnitTrait n2;
                if (am2 == null || !(am2 instanceof UnitType) || (n2 = am2.dn()) == null || n2.N == null) continue;
                ArrayList arrayList = am2.N();
                for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) arrayList) {
                    boolean bl3 = bl2 ? com.corrodinggames.rts.gameFramework.ui.UnitStateTracker.a(n2.N, j2) : n2.N.read(j2);
                    if (!bl3) continue;
                    com.corrodinggames.rts.game.units.actions.BuildAction g2 = new com.corrodinggames.rts.game.units.actions.BuildAction(s2, (UnitType)am2, s2.N());
                    m2.add(g2);
                }
            }
        }
    }
}
