/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.e;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Collections;

public abstract class d {
    String b;
    public ArrayList c = new ArrayList();
    private ArrayList a = new ArrayList();
    final /* synthetic */ a d;

    public d(a a2, String string) {
        this.d = a2;
        this.b = string;
        a2.bq.add(this);
    }

    public boolean b(as as2) {
        for (e e2 : this.c) {
            if (e2.a != as2) continue;
            return true;
        }
        return false;
    }

    public abstract boolean a(as var1);

    public float c(as as2) {
        return 10.0f;
    }

    public ArrayList a() {
        Collections.shuffle(this.a);
        return this.a;
    }

    public void b() {
        this.c = new ArrayList();
        float f2 = 0.0f;
        for (as as2 : ar.ae) {
            if (!this.a(as2)) continue;
            float f3 = this.c(as2);
            f2 += f3;
            this.c.add(new e(this, as2, f3));
        }
        this.a = new ArrayList(this.c);
        if (this.c.size() == 0) {
            l.e("AI: rebuildUnitMix: no units in unitMix:" + this.b);
        }
    }

    public as c() {
        return this.a(null, -1);
    }

    public as a(ao ao2) {
        return this.a(ao2, -1);
    }

    public boolean a(as as2, ao ao2) {
        if (ao2 == null) {
            return true;
        }
        ao ao3 = as2.o();
        if (ao3 == ao.g) {
            ao3 = ao.b;
        }
        if (ao3 == ao.h) {
            ao3 = ao.f;
        }
        return ao3 == ao2;
    }

    public as a(ao ao2, int n2) {
        if (this.c.size() == 0) {
            l.e("AI: getRandomUnitType: no units in unitMix:" + this.b);
            return null;
        }
        float f2 = 0.0f;
        int n3 = 0;
        for (e e2 : this.c) {
            if (!this.a(e2.a, ao2) || n2 != -1 && e2.a.c() > n2) continue;
            f2 += e2.b;
            ++n3;
        }
        if (n3 == 0) {
            return null;
        }
        float f3 = f.c(0.0f, f2);
        float f4 = 0.0f;
        for (e e3 : this.c) {
            if (!this.a(e3.a, ao2) || n2 != -1 && e3.a.c() > n2 || !((f4 += e3.b) > f3)) continue;
            return e3.a;
        }
        l.e("Did not find getRandomUnit, this should only happen very rarely, name:" + this.b + " unitMix.size:" + this.c.size() + " minPrice:" + n2 + " movementType:" + (Object)((Object)ao2) + " totalUnits:" + n3);
        return ((e)this.c.get((int)(this.c.size() - 1))).a;
    }
}
