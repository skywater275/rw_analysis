/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.f.a;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ArrayList;
import java.util.Iterator;

public class am
extends bq {
    private final a i;
    public ArrayList a = new ArrayList();
    public float b;
    public long c;
    public float d;
    public float e;
    public float f;
    public boolean g;
    public boolean h;

    public am(a a2, boolean bl) {
        this.i = a2;
        this.g = bl;
    }

    public void a() {
        com.corrodinggames.rts.game.units.am am2 = null;
        for (com.corrodinggames.rts.game.units.am am3 : this.a) {
            boolean bl;
            if (am3.bV || am3.cN != null || !(bl = this.i.a.j(am3)) || !am3.cf()) continue;
            am2 = am3;
        }
        if (this.c > l.V() - 700L && am2 != null) {
            this.i.b.b(am2.eo, am2.ep);
        }
        this.c = l.V();
    }

    public void b() {
        this.a.clear();
    }

    public void c() {
        for (w w2 : w.er) {
            if (!(w2 instanceof y)) continue;
            y y2 = (y)w2;
            if (!y2.cG || this.a.contains(y2)) continue;
            this.a.add(y2);
        }
    }

    @Override
    public void a(as as2) {
        this.d();
        as2.a(this.b);
        as2.a(this.c);
        int n2 = this.a.size();
        as2.a(n2);
        for (com.corrodinggames.rts.game.units.am am2 : this.a) {
            as2.a(am2);
        }
        as2.c(0);
    }

    public void a(k k2) {
        this.b = k2.g();
        this.c = k2.i();
        this.a.clear();
        int n2 = k2.f();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.am am2 = k2.o();
            if (am2 == null) continue;
            this.a.add(am2);
        }
        k2.d();
    }

    public void d() {
        if (this.a.size() == 0) {
            return;
        }
        Iterator iterator = this.a.iterator();
        while (iterator.hasNext()) {
            com.corrodinggames.rts.game.units.am am2 = (com.corrodinggames.rts.game.units.am)iterator.next();
            if (!am2.bV) continue;
            iterator.remove();
        }
    }

    public void e() {
        if (this.a.size() == 0) {
            return;
        }
        ArrayList<com.corrodinggames.rts.game.units.am> arrayList = new ArrayList<com.corrodinggames.rts.game.units.am>();
        for (com.corrodinggames.rts.game.units.am am2 : this.a) {
            com.corrodinggames.rts.game.units.am am3 = w.a(am2.eh, true);
            if (am3 == null || am3.bV) continue;
            arrayList.add(am3);
        }
        this.a = arrayList;
    }
}
