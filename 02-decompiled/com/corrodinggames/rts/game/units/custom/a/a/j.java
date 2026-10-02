/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.g.e;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.ai;

public class j
extends a {
    boolean a;
    boolean b;
    boolean c;
    LogicBoolean d;
    LogicBoolean e;
    LogicBoolean f;
    LogicBoolean g;
    boolean h;
    float i;
    float j;
    u k;
    boolean l;
    boolean m;
    float n = -1.0f;
    ai o;
    boolean p;
    VariableScope$CachedWriter q;

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        float f2;
        float f3;
        Object object;
        Object object2;
        Object object3;
        boolean bl3;
        boolean bl4 = ab2.a(string, string2 + "resetUnitStats", (Boolean)false);
        String string4 = ab2.b(string, string2 + "setUnitStats", (String)null);
        if (bl4 || string4 != null) {
            j j2 = new j();
            j2.p = bl4;
            if (string4 != null) {
                j2.q = as.a(string4, l2, string, string2 + "setUnitStats");
            }
            d2.ac.add(j2);
        }
        if (bl3 = ab2.a(string, string2 + "deleteSelf", (Boolean)false).booleanValue()) {
            j j3 = new j();
            j3.a = bl3;
            d2.ac.add(j3);
        }
        boolean bl5 = ab2.a(string, string2 + "switchToNeutralTeam", (Boolean)false);
        boolean bl6 = ab2.a(string, string2 + "switchToAggressiveTeam", (Boolean)false);
        LogicBoolean logicBoolean = ab2.a(l2, string, string2 + "switchToTeam", null, LogicBoolean$ReturnType.number);
        if (bl5 || bl6 || logicBoolean != null) {
            object3 = new j();
            ((j)object3).b = bl5;
            ((j)object3).c = bl6;
            ((j)object3).d = logicBoolean;
            d2.ac.add(object3);
        }
        if ((object3 = ab2.c(l2, string, string2 + "setBodyRotation", null)) != null) {
            object2 = new j();
            ((j)object2).e = object3;
            d2.ac.add(object2);
        }
        if ((object2 = ab2.c(l2, string, string2 + "setHeight", null)) != null) {
            object = new j();
            ((j)object).f = object2;
            d2.ac.add(object);
        }
        if ((object = ab2.b(l2, string, string2 + "teleportTo", null)) != null) {
            j j4 = new j();
            j4.g = object;
            d2.ac.add(j4);
        }
        if ((f3 = ab2.a(string, string2 + "setBuilt", Float.valueOf(-1.0f)).floatValue()) > 1.0f) {
            throw new bo("[" + string + "] setBuilt cannot be greater than 1");
        }
        boolean bl7 = ab2.a(string, string2 + "clearAllActionCooldowns", (Boolean)false);
        float f4 = ab2.c(string, string2 + "addAllActionCooldownsTime", Float.valueOf(0.0f)).floatValue();
        if (f4 == 0.0f) {
            f4 = ab2.c(string, string2 + "addAllActionCooldownsFor", Float.valueOf(0.0f)).floatValue();
        }
        if ((f2 = ab2.c(string, string2 + "addActionCooldownTime", Float.valueOf(0.0f)).floatValue()) == 0.0f) {
            f2 = ab2.c(string, string2 + "addActionCooldownFor", Float.valueOf(0.0f)).floatValue();
        }
        u u2 = ab2.a(l2, string, string2 + "addActionCooldownApplyToActions", (u)null);
        ai ai2 = ab2.a(string, string2 + "offsetSelfAbsolute", (ai)null);
        if (u2 != null && f2 <= 0.0f) {
            throw new bo("[" + string + "]addActionCooldownApplyToActions requires addActionCooldownTime to be set");
        }
        boolean bl8 = ab2.a(string, string2 + "removeAllQueuedItemsWithoutRefund", (Boolean)false);
        boolean bl9 = ab2.a(string, string2 + "refundAllQueuedItems", (Boolean)false);
        if (bl8 && bl9) {
            throw new bo("[" + string + "]Cannot set removeAllQueuedActionsWithoutRefund and refundAllQueuedActions at the same time, pick one.");
        }
        if (f2 > 0.0f || f4 > 0.0f || bl7 || f3 >= 0.0f || ai2 != null || bl8 || bl9) {
            j j5 = new j();
            j5.h = bl7;
            j5.i = f4;
            j5.j = f2;
            j5.k = u2;
            j5.n = f3;
            j5.o = ai2;
            j5.l = bl8;
            j5.m = bl9;
            d2.ac.add(j5);
        }
    }

    @Override
    public boolean a(com.corrodinggames.rts.game.units.custom.j j2, s s2, PointF pointF, am am2, int n2) {
        int n3;
        Comparable comparable2;
        Object object;
        if (this.p) {
            j2.y = j2.x.cL;
            j2.cv = j2.y.c;
            if (j2.cu > j2.cv) {
                j2.o(j2.cv);
            }
            j2.cA = j2.y.g;
            if (j2.cx > j2.cA) {
                j2.cx = j2.cA;
            }
        }
        if (this.q != null) {
            this.q.writeToUnit(j2);
            com.corrodinggames.rts.game.units.custom.d.b.d(j2);
        }
        if (this.a) {
            j2.ci();
            if (j2.bI()) {
                object = com.corrodinggames.rts.gameFramework.l.B();
                ((com.corrodinggames.rts.gameFramework.l)object).bU.a(j2);
            }
        }
        if (this.b) {
            j2.e(com.corrodinggames.rts.game.n.i);
        }
        if (this.c) {
            j2.e(com.corrodinggames.rts.game.n.h);
        }
        if (this.d != null && (comparable2 = com.corrodinggames.rts.game.n.k(n3 = (int)this.d.readNumber(j2))) != null) {
            j2.e((n)comparable2);
        }
        if (this.e != null) {
            float f2 = this.e.readNumber(j2);
            j2.h(f2);
        }
        if (this.f != null) {
            float f3;
            j2.eq = f3 = this.f.readNumber(j2);
        }
        if (this.g != null && (object = this.g.readUnit(j2)) != null) {
            j2.f(((am)object).eo, ((am)object).ep);
        }
        if (this.h) {
            com.corrodinggames.rts.game.units.g.e.c(j2, s.i);
        }
        if (this.l) {
            j2.i(false);
        }
        if (this.m) {
            j2.i(true);
        }
        if (this.i > 0.0f) {
            com.corrodinggames.rts.game.units.g.e.a(j2, s.i, (int)this.i);
        }
        if (this.j > 0.0f) {
            if (this.k == null) {
                com.corrodinggames.rts.game.units.g.e.a(j2, s2.N(), (int)this.j);
            } else {
                for (Comparable comparable2 : this.k.a()) {
                    com.corrodinggames.rts.game.units.g.e.a(j2, ((s)comparable2).N(), (int)this.j);
                }
            }
        }
        if (this.n >= 0.0f) {
            j2.r(this.n);
            j2.cn = this.n;
        }
        if (this.o != null) {
            j2.b(j2.eo + this.o.a, j2.ep + this.o.b);
            j2.eq += this.o.c;
            j2.cK = true;
        }
        return true;
    }
}
