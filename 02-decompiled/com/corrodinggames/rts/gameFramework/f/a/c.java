/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f.a;

import com.corrodinggames.rts.gameFramework.f.a.d;

public class c {
    public int a;
    public int b;
    public d c;
    public int d = -1;

    public static c a(int n, int n2) {
        c c2 = new c();
        c2.a = n;
        c2.b = n2;
        c2.c = com.corrodinggames.rts.gameFramework.f.a.d.b;
        c2.d = 1;
        return c2;
    }

    public static c b(int n, int n2) {
        c c2 = new c();
        c2.a = n;
        c2.b = n2;
        c2.c = com.corrodinggames.rts.gameFramework.f.a.d.a;
        c2.d = 1;
        return c2;
    }

    public boolean a() {
        return this.c == com.corrodinggames.rts.gameFramework.f.a.d.b;
    }

    public boolean b() {
        return this.c == com.corrodinggames.rts.gameFramework.f.a.d.a;
    }
}
