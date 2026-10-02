/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.n;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class h
extends o {
    ArrayList F = new ArrayList();
    ArrayList G = new ArrayList();

    public int l() {
        return this.F.size();
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public h(a a2) {
        super(a2);
    }

    public boolean m() {
        for (o o2 : this.R.bm) {
            if (!(o2 instanceof n)) continue;
            n n2 = (n)o2;
            if (n2.m != this) continue;
            return true;
        }
        return false;
    }

    public void n() {
        Iterator iterator = this.F.iterator();
        while (iterator.hasNext()) {
            y y2 = (y)iterator.next();
            if (y2 != null && !y2.bV) continue;
            if (y2 != null && y2.aB == this) {
                y2.aB = null;
            }
            if (y2 != null) {
                this.G.remove(y2);
            }
            iterator.remove();
        }
    }

    public void o() {
        Iterator iterator = this.G.iterator();
        while (iterator.hasNext()) {
            y y2 = (y)iterator.next();
            if (y2 != null && !y2.bV && y2.cN == null && y2.cO == null) continue;
            iterator.remove();
        }
    }

    @Override
    public void p() {
        this.q();
        this.G.clear();
        super.p();
    }

    protected void a(y y2) {
        if (y2.aB != null) {
            y2.aB.b(y2);
        }
        if (y2.bX != null && y2.bX != this.R) {
            l.g("unit.team:" + y2.bX.k + ", ai:" + this.R.k);
        }
        this.F.add(y2);
        y2.aB = this;
    }

    public void b(y y2) {
        this.F.remove(y2);
        this.G.remove(y2);
        if (y2.aB == this) {
            y2.aB = null;
        }
    }

    public void q() {
        for (y y2 : this.F) {
            if (y2 == null || y2.aB != this) continue;
            y2.aB = null;
        }
        this.F.clear();
    }

    public void b(float f2) {
    }

    public abstract void c(float var1);
}
