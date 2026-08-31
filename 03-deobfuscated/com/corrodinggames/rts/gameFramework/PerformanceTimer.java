/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ExtraManager;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class PerformanceTimer {
    boolean a = true;
    int b;
    double c;
    double d;
    long e;
    String f;

    public PerformanceTimer(String string) {
        this.f = string;
    }

    public PerformanceTimer(String string, boolean bl) {
        this.f = string;
        this.a = bl;
    }

    public void a() {
        if (!this.a) {
            return;
        }
        this.e = this.e != 0L ? Long.MIN_VALUE : br.a();
    }

    public void b() {
        if (!this.a) {
            return;
        }
        long l2 = br.a();
        double d = br.a(this.e, l2);
        this.c += d;
        ++this.b;
        if (d > this.d) {
            this.d = d;
        }
        this.e = 0L;
    }

    public String c() {
        if (!this.a) {
            return "{ Not enabled }";
        }
        String string = "{ ";
        if (this.b > 0) {
            string = string + "#" + this.b + " = ";
            string = string + "peak:" + GameUtils.a(this.d, 2) + "ms ";
            string = string + "avg:" + GameUtils.a(this.c / (double)this.b, 2) + "ms ";
            string = string + "total:" + GameUtils.a(this.c, 2) + "ms ";
        } else {
            string = string + "#0 = NA";
        }
        string = string + "}";
        return string;
    }

    public void d() {
        if (!this.a) {
            return;
        }
        this.b();
        this.e();
    }

    public void e() {
        if (!this.a) {
            return;
        }
        if (this.b > 0) {
            GlobalState.e(GlobalState.a(this.f + " - " + this.c(), "\u001b[36m"));
            this.f();
        }
    }

    public void f() {
        this.b = 0;
        this.c = 0.0;
        this.d = 0.0;
    }
}
