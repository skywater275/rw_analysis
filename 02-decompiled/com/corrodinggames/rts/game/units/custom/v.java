/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;

public strictfp class v {
    String a;
    String b;
    String c;
    as d;
    boolean e;
    public boolean f;

    public void a() {
        if (!this.e) {
            this.d = l.s(this.c);
            if (this.d == null) {
                com.corrodinggames.rts.gameFramework.l.e("AllUnitTypes: " + l.E());
                if (this.f) {
                    throw new bo("Could not find unit type:" + this.c + " used on:" + this.a + " in section:" + this.b + " (Note: Prefix with 'unitref' if not using a unit type here)");
                }
                throw new bo("Could not find unit type:" + this.c + " used on:" + this.a + " in section:" + this.b);
            }
        }
    }

    public void b() {
    }

    public as c() {
        return this.d;
    }

    public String d() {
        if (this.e) {
            if (this.d != null) {
                return this.d.i();
            }
            return "(Error: known type is null)";
        }
        return this.c;
    }
}
