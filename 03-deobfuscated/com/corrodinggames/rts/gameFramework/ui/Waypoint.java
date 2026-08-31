/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.GameUtils;

strictfp abstract class Waypoint
implements Comparable {
    long c;
    long d = 5000L;
    float e;
    float f;
    String g;
    boolean h;
    boolean i;

    public Waypoint(float f, float f2) {
        this.e = f;
        this.f = f2;
    }

    public int c(Waypoint au2) {
        return (int)(au2.c - this.c);
    }

    public boolean a(Waypoint au2) {
        if (this.c + this.b() < System.currentTimeMillis()) {
            return false;
        }
        float f2 = GameUtils.a(this.e, this.f, au2.e, au2.f);
        return !(f2 > 90000.0f);
    }

    protected long b() {
        return 5000L;
    }

    public abstract void b(Waypoint var1);

    public abstract String a();

    public /* synthetic */ int compareTo(Object object) {
        return this.c((Waypoint) object);
    }
}
