/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.d.e;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.al;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ArrayList;

public strictfp class ak {
    public long a;
    public ArrayList b = new ArrayList();
    public al c = new al(this, "Unit Pos");
    public al d = new al(this, "Unit Dir", false);
    public al e = new al(this, "Unit Hp");
    public al f = new al(this, "Unit Id");
    public al g = new al(this, "Waypoints");
    public al h = new al(this, "Waypoints Pos");
    public al i = new al(this, "Team Credits");
    public al j = new al(this, "UnitPaths");
    public al k = new al(this, "Unit Count");
    public al l = new al(this, "Team Info", false);
    public al m = new al(this, "Team 1 Credits", false);
    public al n = new al(this, "Team 2 Credits", false);
    public al o = new al(this, "Team 3 Credits", false);
    public al p = new al(this, "Command center2", false);
    public al q = new al(this, "Command center3", false);

    public void a() {
        for (al al2 : this.b) {
            al2.b = 0L;
        }
    }

    public void b() {
        this.a = 0L;
        this.a();
        for (bq bq2 : w.er) {
            Object object;
            if (!(bq2 instanceof y)) continue;
            y y2 = (y)bq2;
            this.a = (long)((float)this.a + y2.eo * 1000.0f);
            this.a = (long)((float)this.a + y2.ep * 1000.0f);
            this.a = (long)((float)this.a + y2.cu * 1.0f);
            this.a += y2.eh;
            this.c.b += (long)Float.floatToRawIntBits(y2.eo);
            this.c.b += (long)Float.floatToRawIntBits(y2.ep);
            this.d.b += (long)Float.floatToRawIntBits(y2.cg);
            this.e.b = (long)((float)this.e.b + y2.cu);
            this.f.b += y2.eh;
            if (bq2 instanceof e) {
                object = (e)y2;
                this.p.b = (long)((float)this.p.b + ((e)object).f * 2.0f);
                this.q.b += (long)((e)object).h;
            }
            if ((object = y2.ar()) != null) {
                this.g.b += ((au)object).j();
                this.h.b = (long)((float)this.h.b + ((au)object).g() * 1000.0f);
            }
            this.j.b += y2.aL();
        }
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            bq bq2;
            bq2 = com.corrodinggames.rts.game.n.k(i2);
            if (bq2 == null) continue;
            this.i.b += (long)((int)((n)bq2).o);
            if (i2 == 0) {
                this.m.b += (long)((int)((n)bq2).o);
            }
            if (i2 == 1) {
                this.n.b += (long)((int)((n)bq2).o);
            }
            if (i2 == 2) {
                this.o.b += (long)((int)((n)bq2).o);
            }
            this.k.b += (long)((n)bq2).w();
            this.l.b = this.l.b + (long)(i2 + ((n)bq2).x * 100 + ((n)bq2).r * 1000 + (((n)bq2).w ? i2 : 0) * 10000);
        }
    }
}
