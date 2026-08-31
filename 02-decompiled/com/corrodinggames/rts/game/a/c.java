/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import java.util.HashMap;

public class c {
    HashMap a = new HashMap();
    HashMap b = new HashMap();
    HashMap c = new HashMap();

    public Integer a(boolean bl, as as2, boolean bl2) {
        if (bl) {
            return (Integer)this.c.get(as2);
        }
        if (!bl2) {
            return (Integer)this.b.get(as2);
        }
        return (Integer)this.a.get(as2);
    }

    public void a(boolean bl, as as2, boolean bl2, Integer n) {
        if (bl) {
            this.c.put(as2, n);
        } else if (!bl2) {
            this.b.put(as2, n);
        } else {
            this.a.put(as2, n);
        }
    }

    public void a() {
        this.a.clear();
        this.b.clear();
    }

    public void a(as as2) {
        this.a.put(as2, null);
        this.b.put(as2, null);
    }

    public void a(y y2) {
        this.c.put(y2.dz, null);
    }

    public void b() {
        this.c.clear();
    }
}
