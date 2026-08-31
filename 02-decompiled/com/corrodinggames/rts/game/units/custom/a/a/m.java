/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.a.n;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class m
extends a {
    public b a;
    public boolean b;
    public boolean c;
    public float d = -1.0f;
    public q e;
    public LogicBoolean f;
    public h g;
    public boolean h = true;
    public b i;
    public UnitReference j;
    public int k = 1;
    public u l;
    public u m;
    public u n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s = false;
    public static final com.corrodinggames.rts.gameFramework.utility.m t = new com.corrodinggames.rts.gameFramework.utility.m();
    public static final n u = new n();

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        boolean bl3 = ab2.a(string, string2 + "takeResources_includeUnitsInTransport", (Boolean)false);
        boolean bl4 = ab2.a(string, string2 + "takeResources_includeParent", (Boolean)false);
        LogicBoolean logicBoolean = ab2.b(l2, string, string2 + "takeResources_includeReference", null);
        float f2 = ab2.a(string, string2 + "takeResources_includeUnitsWithinRange", Float.valueOf(-1.0f)).floatValue();
        boolean bl5 = ab2.a(string, string2 + "takeResources_searchOnly", (Boolean)false);
        b b2 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "takeResources", true);
        if (bl5 && b2 != null && !b2.c()) {
            throw new bo("[" + string + "]takeResources not supported with takeResources_searchOnly");
        }
        boolean bl6 = ab2.a(string, string2 + "takeResources_saveFirstUnitToCustomTarget1", (Boolean)false);
        boolean bl7 = ab2.a(string, string2 + "takeResources_saveFirstUnitToCustomTarget2", (Boolean)false);
        if (!bl3 && !bl4 && f2 < 0.0f && logicBoolean == null) {
            if (b2 != null && !b2.c()) {
                throw new bo("[" + string + "]takeResources requires an include (eg: takeResources_includeUnitsWithinRange, takeResources_includeUnitsInTransport) otherwise no results would be found");
            }
            if (bl5) {
                throw new bo("[" + string + "]takeResources_searchOnly requires an include (eg: takeResources_includeUnitsWithinRange) otherwise no results would be found");
            }
            return;
        }
        m m2 = new m();
        d2.ac.add(m2);
        m2.b = bl3;
        m2.d = f2;
        m2.c = bl4;
        m2.f = logicBoolean;
        m2.a = b2;
        m2.s = ab2.a(string, string2 + "takeResources_directTransferStoppingAtZero", (Boolean)m2.s);
        m2.e = (q)ab2.a(string, "takeResources_includeUnitsWithinRange_team", com.corrodinggames.rts.game.q.a, q.class);
        m2.g = ab2.a(l2, string, string2 + "takeResources_excludeUnitsWithoutTags", (h)null);
        m2.j = UnitReference.parseUnitReferenceFromConf(l2, ab2, string, string2 + "takeResources_excludeUnitsWithoutCustomTarget1EqualTo", null);
        if (m2.s) {
            m2.h = false;
        }
        m2.h = ab2.a(string, string2 + "takeResources_excludeUnitsWithoutAllResources", (Boolean)m2.h);
        if (bl5) {
            m2.k = 200;
            m2.q = true;
            m2.r = true;
        }
        m2.k = ab2.b(string, string2 + "takeResources_maxUnits", m2.k);
        m2.l = ab2.a(l2, string, string2 + "takeResources_triggerActionIfAnyCollected", (u)null);
        m2.m = ab2.a(l2, string, string2 + "takeResources_triggerActionIfNoneCollected", (u)null);
        m2.n = ab2.a(l2, string, string2 + "takeResources_triggerActionForEach", (u)null);
        m2.o = bl6;
        m2.p = bl7;
        m2.q = ab2.a(string, string2 + "takeResources_discardCollected", (Boolean)m2.q);
        m2.r = ab2.a(string, string2 + "takeResources_keepResourcesOnTarget", (Boolean)m2.r);
        if (!(!bl5 || m2.q && m2.r)) {
            throw new bo("[" + string + "]takeResources_searchOnly shortcut expects takeResources_discardCollected and takeResources_keepResourcesOnTarget to be true");
        }
        m2.i = com.corrodinggames.rts.game.units.custom.d.b.a(l2, ab2, string, string2 + "takeResources_excludeUnitsWithTheseResources", true);
        if (m2.i != null && m2.i.c()) {
            m2.i = null;
        }
        if (m2.s) {
            if (m2.a.e()) {
                throw new bo("[" + string + "]takeResources_directTransferStoppingAtZero:true only supports custom resources right now");
            }
            if (m2.h) {
                throw new bo("[" + string + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_excludeUnitsWithoutAllResources:true");
            }
            if (m2.r) {
                throw new bo("[" + string + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_keepResourcesOnTarget:true");
            }
            if (m2.q) {
                throw new bo("[" + string + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_discardCollected:true");
            }
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        am am3;
        am am4;
        Object object;
        com.corrodinggames.rts.gameFramework.utility.m m2 = t;
        m2.clear();
        if (this.b) {
            object = j2.B.iterator();
            while (object.hasNext()) {
                am4 = (am)object.next();
                if (am4 == null || am4.bV) continue;
                m2.add(am4);
            }
        }
        if (this.c) {
            if (j2.cO != null) {
                m2.add(j2.cO);
            } else if (j2.cN != null) {
                m2.add(j2.cO);
            }
        }
        if (this.f != null && (object = this.f.readUnit(j2)) != null && !((am)object).bV) {
            m2.add(object);
        }
        if (this.d > 0.0f) {
            com.corrodinggames.rts.game.units.custom.a.a.m.u.b = this.d * this.d;
            com.corrodinggames.rts.game.units.custom.a.a.m.u.a = this.g;
            com.corrodinggames.rts.game.units.custom.a.a.m.u.c = true;
            com.corrodinggames.rts.game.units.custom.a.a.m.u.d = this.e;
            com.corrodinggames.rts.game.units.custom.a.a.m.u.e = m2;
            object = com.corrodinggames.rts.gameFramework.l.B();
            ((com.corrodinggames.rts.gameFramework.l)object).cc.a(j2.eo, j2.ep, this.d, j2, 0.0f, u);
        }
        if (this.g != null) {
            for (int i2 = m2.size() - 1; i2 >= 0; --i2) {
                am4 = (am)m2.get(i2);
                if (com.corrodinggames.rts.game.units.custom.g.a(this.g, am4.de())) continue;
                m2.remove(i2);
            }
        }
        if (this.h) {
            for (int i3 = m2.size() - 1; i3 >= 0; --i3) {
                am4 = (am)m2.get(i3);
                if (this.a.b(am4)) continue;
                m2.remove(i3);
            }
        }
        if (this.i != null) {
            for (int i4 = m2.size() - 1; i4 >= 0; --i4) {
                am4 = (am)m2.get(i4);
                if (!this.i.b(am4)) continue;
                m2.remove(i4);
            }
        }
        if (this.j != null) {
            am am5 = this.j.get(j2);
            for (int i5 = m2.size() - 1; i5 >= 0; --i5) {
                am3 = (am)m2.get(i5);
                if (am3.bu == am5) continue;
                m2.remove(i5);
            }
        }
        if (this.n != null) {
            m2 = new com.corrodinggames.rts.gameFramework.utility.m(m2);
        }
        boolean bl2 = false;
        boolean bl3 = false;
        am3 = null;
        int n3 = 0;
        int n4 = 0;
        for (int i6 = 0; i6 < m2.size(); ++i6) {
            am am6 = (am)m2.get(i6);
            if (am3 == null) {
                am3 = am6;
            }
            if (this.s) {
                boolean bl4 = this.a.a(am6, j2);
                if (!bl4) {
                    continue;
                }
            } else {
                if (!this.r) {
                    this.a.a(am6);
                }
                if (!this.q) {
                    this.a.h(j2);
                }
            }
            if (this.n != null && am6 != null) {
                PointF pointF2 = new PointF(am6.eo, am6.ep);
                this.n.a(j2, pointF2, am6, n2 + 1, n4);
                ++n4;
            }
            bl2 = true;
            if (++n3 < this.k) continue;
            bl3 = true;
            break;
        }
        if (am3 != null) {
            if (this.o) {
                j2.bu = am3;
            }
            if (this.p) {
                j2.bv = am3;
            }
        }
        if (bl2) {
            if (this.l != null) {
                this.l.a(j2, pointF, am2, n2 + 1, 0);
            }
        } else if (this.m != null) {
            this.m.a(j2, pointF, am2, n2 + 1, 0);
        }
        m2.clear();
        return true;
    }
}
