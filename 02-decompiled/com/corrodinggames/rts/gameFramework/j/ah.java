/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public strictfp class ah
implements Cloneable {
    public ai a = ai.a;
    public String b = "[z;p10]Crossing Large (10p).tmx";
    public int c = 0;
    public int d = 2;
    public boolean e = true;
    public int f = 1;
    public int g = 1;
    public float h = 1.0f;
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public boolean l;
    public boolean m = false;
    public boolean n = false;
    public boolean o = true;
    public boolean p = false;
    public int q;

    public void a() {
        this.a = ai.a;
        this.b = "[z;p10]Crossing Large (10p).tmx";
    }

    public String b() {
        String string = "";
        String string2 = "\n";
        string = string + "startingCredits: " + this.c + string2;
        string = string + "fogMode: " + this.d + string2;
        string = string + "revealedMap: " + this.e + string2;
        string = string + "aiDifficulty: " + this.f + string2;
        string = string + "startingUnits: " + this.g + string2;
        string = string + "incomeMultiplier: " + this.h + string2;
        string = string + "noNukes: " + this.i + string2;
        string = string + "sharedControl: " + this.l + string2;
        string = string + "allowSpectators: " + this.o + string2;
        string = string + "lockedRoom: " + this.p + string2;
        string = string + "randomSeed: " + this.q + string2;
        return string;
    }

    public ah c() {
        try {
            return (ah)super.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new RuntimeException(cloneNotSupportedException);
        }
    }

    public void a(as as2) {
        as2.c(4);
        as2.a(this.d);
        as2.a(this.c);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h);
        as2.a(this.i);
        as2.a(this.j);
        as2.a(this.l);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.p);
        as2.a(this.q);
    }

    public void a(k k2) {
        byte by = k2.d();
        this.d = k2.f();
        this.c = k2.f();
        this.e = k2.e();
        this.f = k2.f();
        this.g = k2.f();
        this.h = k2.g();
        this.i = k2.e();
        this.j = k2.e();
        this.l = k2.e();
        if (by >= 1) {
            this.m = k2.e();
        }
        if (by >= 2) {
            this.n = k2.e();
        }
        if (by >= 3) {
            this.o = k2.e();
            this.p = k2.e();
        }
        if (by >= 4) {
            this.q = k2.f();
        }
    }

    public /* synthetic */ Object clone() {
        return this.c();
    }
}
