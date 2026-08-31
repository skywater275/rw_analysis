/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.g;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.g.a;
import com.corrodinggames.rts.game.units.g.b;
import com.corrodinggames.rts.game.units.g.c;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;

public class e
extends a {
    int b;
    com.corrodinggames.rts.game.units.a.c c = com.corrodinggames.rts.game.units.a.c.a;

    public e() {
    }

    public e(int n2, com.corrodinggames.rts.game.units.a.c c2) {
        super(n2);
        int n3;
        this.c = c2;
        l l2 = l.B();
        this.b = n3 = l2.by;
    }

    @Override
    public b b() {
        return com.corrodinggames.rts.game.units.g.b.b;
    }

    public boolean a(com.corrodinggames.rts.game.units.a.c c2) {
        if (this.c == com.corrodinggames.rts.game.units.a.c.a) {
            return true;
        }
        return this.c == c2;
    }

    public float c() {
        int n2 = this.a - this.b;
        if (n2 <= 0) {
            return 1.0f;
        }
        l l2 = l.B();
        int n3 = l2.by;
        int n4 = this.a - n3;
        return (float)n4 / (float)n2;
    }

    public static void a(y y2, com.corrodinggames.rts.game.units.a.c c2, int n2) {
        l l2 = l.B();
        int n3 = l2.by + n2;
        e e2 = new e(n3, c2);
        com.corrodinggames.rts.game.units.g.c.a(y2, e2);
    }

    public static int a(am am2, com.corrodinggames.rts.game.units.a.c c2) {
        if (!(am2 instanceof y)) {
            return 0;
        }
        y y2 = (y)am2;
        m m2 = y2.bp;
        if (m2 == null) {
            return 0;
        }
        e e2 = e.b(am2, c2);
        if (e2 == null) {
            return 0;
        }
        int n2 = e2.d();
        return n2;
    }

    public int d() {
        l l2 = l.B();
        int n2 = l2.by;
        int n3 = this.a - n2;
        return n3;
    }

    public static e b(am am2, com.corrodinggames.rts.game.units.a.c c2) {
        if (!(am2 instanceof y)) {
            return null;
        }
        y y2 = (y)am2;
        m m2 = y2.bp;
        if (m2 == null) {
            return null;
        }
        l l2 = l.B();
        int n2 = l2.by;
        e e2 = null;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            e e3;
            a a2 = (a)objectArray[i2];
            if (!(a2 instanceof e) || !(e3 = (e)a2).a(c2) || e3.a <= n2) continue;
            n2 = e3.a;
            e2 = e3;
        }
        if (e2 == null) {
            return null;
        }
        return e2;
    }

    @Override
    public void a(y y2, as as2) {
        com.corrodinggames.rts.game.units.a.c.a(as2, this.c);
        as2.a(this.b);
        super.a(y2, as2);
    }

    @Override
    public void a(y y2, k k2) {
        this.c = com.corrodinggames.rts.game.units.a.c.a(k2);
        if (this.c == null) {
            this.c = com.corrodinggames.rts.game.units.a.c.a;
        }
        this.b = k2.f();
        super.a(y2, k2);
    }

    public static void c(am am2, com.corrodinggames.rts.game.units.a.c c2) {
        if (!(am2 instanceof y)) {
            return;
        }
        y y2 = (y)am2;
        m m2 = y2.bp;
        if (m2 == null) {
            return;
        }
        l l2 = l.B();
        int n2 = l2.by;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            a a2 = (a)objectArray[i2];
            if (!(a2 instanceof e)) continue;
            e e2 = (e)a2;
            if (c2 != com.corrodinggames.rts.game.units.a.c.a && !e2.a(c2)) continue;
            e2.a = n2 - 1;
        }
    }
}
