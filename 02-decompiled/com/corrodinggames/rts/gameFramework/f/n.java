/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.l;

public class n {
    String a;
    String b;
    long c;
    String d;
    public int e = -1;
    public int f = -1;

    public int a() {
        return (int)(System.currentTimeMillis() - this.c);
    }

    public boolean b() {
        int n2 = 14000;
        l l2 = l.B();
        if (l2.a()) {
            return true;
        }
        return this.c + (long)n2 > System.currentTimeMillis();
    }
}
