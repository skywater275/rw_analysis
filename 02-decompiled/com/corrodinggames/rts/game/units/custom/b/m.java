/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.a.g;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.b.n;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.ArrayList;

public strictfp final class m
extends a {
    public static final m a = new m();

    public static void a(l l2, ab ab2) {
        String string = "attachment_";
        com.corrodinggames.rts.gameFramework.utility.m m2 = ab2.e(string);
        if (m2.size() > 0) {
            l2.a(a);
            short s2 = 0;
            for (String string2 : m2) {
                String string3 = string2.substring(string.length());
                n n2 = new n();
                m.a(n2, l2, ab2, string2, string3);
                n2.b = string3;
                n2.a = s2;
                s2 = (short)(s2 + 1);
                l2.aA.add(n2);
            }
        }
    }

    public static void a(n n2, l l2, ab ab2, String string, String string2) {
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
    public void a(j j2, float f2) {
        this.b(j2, f2);
    }

    @Override
    public void b(j j2, float f2) {
        Object[] objectArray;
        Object object;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l l3 = j2.x;
        com.corrodinggames.rts.gameFramework.utility.m m2 = l3.aA;
        if (m2.a == 0) {
            return;
        }
        if (l3.aB) {
            object = m2.a();
            block0: for (int i2 = 0; i2 < m2.a; ++i2) {
                y y2;
                objectArray = (Object[])object[i2];
                if (!objectArray.D || j2.B.a <= 0 || (y2 = m.a(j2, (n)objectArray)) != null) continue;
                for (Object object2 : j2.B) {
                    if (!(object2 instanceof y) || ((am)object2).cO != null || !j2.a((y)object2, (n)objectArray)) continue;
                    ((am)object2).cN = null;
                    continue block0;
                }
            }
        }
        if ((object = j2.C) == null) {
            return;
        }
        float f3 = j2.cg - j2.D;
        j2.D = j2.cg;
        objectArray = ((com.corrodinggames.rts.gameFramework.utility.m)object).a();
        for (int i3 = ((com.corrodinggames.rts.gameFramework.utility.m)object).a - 1; i3 >= 0; --i3) {
            float f4;
            Object object2;
            y y3 = (y)objectArray[i3];
            if (y3 == null) continue;
            if (y3.bV) {
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
            object2 = (n)m2.get(i3);
            float f5 = f.k(j2.cg);
            float f6 = f.j(j2.cg);
            float f7 = f5 * ((n)object2).d - f6 * ((n)object2).c;
            float f8 = f6 * ((n)object2).d + f5 * ((n)object2).c;
            f7 += j2.eo;
            f8 += j2.ep;
            float f9 = j2.eq + ((n)object2).e;
            if (com.corrodinggames.rts.gameFramework.utility.y.b(y3.cQ, (int)((n)object2).G)) {
                f4 = 0.05f;
                y3.eo += (f7 - y3.eo) * f4;
                y3.ep += (f8 - y3.ep) * f4;
                y3.eq += (f9 - y3.eq) * f4;
            } else {
                y3.eo = f7;
                y3.ep = f8;
                y3.eq = f9;
            }
            if (y3.cm < 1.0f && ((n)object2).x) {
                y3.r(j2.cm);
                y3.cn = j2.cm;
            }
            if (((n)object2).A) {
                if (y3.em <= j2.em) {
                    int n2 = 0;
                    if (y3 instanceof j) {
                        n2 = ((j)y3).x.cI;
                    }
                    y3.em = j2.em;
                    y3.en = j2.en + 1 + n2;
                }
            } else if (((n)object2).B && y3.em >= j2.em) {
                y3.em = j2.em;
                y3.en = j2.en - 1;
            }
            f4 = j2.ci ? j2.cg + ((n)object2).g : j2.cg + ((n)object2).f;
            if (!y3.bI()) {
                if (((n)object2).u) {
                    y3.h(f4);
                } else {
                    if (f3 != 0.0f && ((n)object2).r) {
                        y3.i(f3);
                    }
                    if (((n)object2).h && y3.R == null) {
                        y3.c(f2, f4);
                    }
                }
            }
            if (((n)object2).K) {
                y3.R = j2.R;
                y3.S = 5.0f;
            }
            if (((n)object2).L && y3.R == null) {
                y3.R = j2.R;
            }
            if (((n)object2).J && j2.R != null && y3.R != j2.R) {
                boolean bl2 = false;
                if (((n)object2).L) {
                    bl2 = true;
                }
                if (y3.a(j2.R, bl2)) {
                    y3.R = j2.R;
                    y3.S = 5.0f;
                }
            }
            if (!(y3 instanceof j)) continue;
            j j3 = (j)y3;
            if (!((n)object2).s) continue;
            j3.dP = j3.eo;
            j3.dP = j3.ep;
            j3.dR = j3.eq;
        }
    }

    public void a(j j2, boolean bl2) {
        com.corrodinggames.rts.gameFramework.utility.m m2 = j2.C;
        if (m2 == null) {
            return;
        }
        com.corrodinggames.rts.gameFramework.utility.m m3 = j2.x.aA;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            y y2 = (y)objectArray[i2];
            if (y2 == null) continue;
            n n2 = (n)m3.get(i2);
            y2.bx();
            objectArray[i2] = null;
            if (!bl2 || n2.v) continue;
            y2.ci();
        }
    }

    @Override
    public void b(j j2) {
        this.a(j2, true);
    }

    @Override
    public void c(j j2) {
        this.a(j2, true);
    }

    @Override
    public void a(j j2) {
        boolean bl2 = false;
        com.corrodinggames.rts.gameFramework.utility.m m2 = j2.x.aA;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            n n2 = (n)objectArray[i2];
            if (n2.w == null) continue;
            y y2 = m.a(j2, n2);
            if (y2 != null) {
                if (n2.y) continue;
                y2.ci();
            }
            com.corrodinggames.rts.gameFramework.utility.m m3 = new com.corrodinggames.rts.gameFramework.utility.m();
            n2.w.a(m3, j2.bX, j2, true);
            if (m3.size() > 1) {
                com.corrodinggames.rts.gameFramework.l.b("onCreateSpawnUnitOf: created an extra " + (m3.size() - 1) + " units");
                for (int i3 = 1; i3 < m3.size(); ++i3) {
                    ((am)m3.get(i3)).ci();
                }
            }
            if (m3.size() == 0) {
                com.corrodinggames.rts.gameFramework.l.b("onCreateSpawnUnitOf: Warning no units created");
                continue;
            }
            am am2 = (am)m3.get(0);
            if (!(am2 instanceof y)) {
                com.corrodinggames.rts.gameFramework.l.b("onCreateSpawnUnitOf: Warning " + am2.r().i() + " not an orderable unit type, cannot attach");
                am2.ci();
                continue;
            }
            y y3 = (y)am2;
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
    public void a(j j2, l l2) {
        y y2;
        int n2;
        com.corrodinggames.rts.gameFramework.utility.m m2 = j2.C;
        com.corrodinggames.rts.gameFramework.utility.m m3 = j2.x.aA;
        if (m3.size() == 0) {
            j2.C = null;
            return;
        }
        if (m2 == null) {
            return;
        }
        for (n2 = m2.size() - 1; n2 >= 0; --n2) {
            y2 = (y)m2.get(n2);
            if (y2 == null || n2 < m3.size()) continue;
            y2.ci();
            m2.remove(n2);
        }
        for (n2 = m2.size() - 1; n2 >= 0; --n2) {
            y2 = (y)m2.get(n2);
            if (y2 == null) continue;
            y2.cP = (n)m3.get(n2);
        }
    }

    public static n a(j j2, short s2) {
        com.corrodinggames.rts.gameFramework.utility.m m2 = j2.x.aA;
        if (m2.a <= s2) {
            return null;
        }
        return (n)m2.get(s2);
    }

    public static y a(j j2, n n2) {
        com.corrodinggames.rts.gameFramework.utility.m m2 = j2.C;
        if (m2 == null) {
            return null;
        }
        short s2 = n2.a;
        if (m2.a <= s2) {
            return null;
        }
        return (y)m2.get(s2);
    }

    public static boolean a(j j2, n n2, y y2) {
        com.corrodinggames.rts.gameFramework.utility.m m2;
        l l2 = j2.x;
        short s2 = n2.a;
        if (l2.aA.a <= s2 && y2 != null) {
            com.corrodinggames.rts.gameFramework.l.b("setAttachedUnitLookup: slot:" + s2 + " larger than max slot size:" + l2.aA.a);
            return false;
        }
        if (j2.C == null) {
            j2.C = new com.corrodinggames.rts.gameFramework.utility.m();
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

    public static void a(j j2, com.corrodinggames.rts.gameFramework.utility.m m2, boolean bl2) {
        com.corrodinggames.rts.gameFramework.utility.m m3 = j2.C;
        if (m3 != null) {
            for (am am2 : m3) {
                n n2;
                if (am2 == null || !(am2 instanceof y) || (n2 = am2.dn()) == null || n2.N == null) continue;
                ArrayList arrayList = am2.N();
                for (s s2 : arrayList) {
                    boolean bl3 = bl2 ? an.a(n2.N, j2) : n2.N.read(j2);
                    if (!bl3) continue;
                    g g2 = new g(s2, (y)am2, s2.N());
                    m2.add(g2);
                }
            }
        }
    }
}
