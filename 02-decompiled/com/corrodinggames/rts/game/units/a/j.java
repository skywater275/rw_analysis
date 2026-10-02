/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import android.graphics.Rect;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.k;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public class j
extends s {
    public k a;
    static ArrayList b = new ArrayList();
    static Rect c;

    public j() {
        this(k.a);
    }

    public j(k k2) {
        super("c_6_" + k2.name());
        this.a = k2;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    public ar w() {
        return null;
    }

    @Override
    public u e() {
        return u.j;
    }

    @Override
    public t f() {
        return t.a;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return "Ping Map" + this.a.a();
    }

    @Override
    public String b() {
        return this.a.b();
    }

    public String K() {
        return this.a.c();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public boolean s() {
        return true;
    }

    public static j a(c c2) {
        for (s s2 : b) {
            if (!s2.d(c2)) continue;
            return (j)s2;
        }
        return null;
    }

    @Override
    public ArrayList q(am am2) {
        return b;
    }

    @Override
    public e j() {
        return com.corrodinggames.rts.gameFramework.d.c.s[9].i;
    }

    @Override
    public Rect v() {
        int n2 = 7 + this.a.ordinal();
        c.a(29 * n2, 0, 29 * n2 + 28, 28);
        return c;
    }

    @Override
    public /* synthetic */ as i() {
        return this.w();
    }

    static {
        for (k k2 : k.values()) {
            b.add(new j(k2));
        }
        c = new Rect();
    }
}
