/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public class bt {
    boolean a = true;
    int b;
    double c;
    double d;
    long e;
    String f;

    public bt(String string) {
        this.f = string;
    }

    public bt(String string, boolean bl) {
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
            string = string + "peak:" + com.corrodinggames.rts.gameFramework.f.a(this.d, 2) + "ms ";
            string = string + "avg:" + com.corrodinggames.rts.gameFramework.f.a(this.c / (double)this.b, 2) + "ms ";
            string = string + "total:" + com.corrodinggames.rts.gameFramework.f.a(this.c, 2) + "ms ";
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
            l.e(l.a(this.f + " - " + this.c(), "\u001b[36m"));
            this.f();
        }
    }

    public void f() {
        this.b = 0;
        this.c = 0.0;
        this.d = 0.0;
    }
}
