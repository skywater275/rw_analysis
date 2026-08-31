/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.java.audio.Sound;
import com.corrodinggames.rts.java.o;
import com.corrodinggames.rts.java.p;

public class q
extends i {
    Sound a;
    final /* synthetic */ o b;

    public q(o o2, String string, h h2) {
        this.b = o2;
        super(string, h2);
    }

    @Override
    public void a(float f, float f2, int n, int n2, float f3) {
        p p2 = (p)this.b.c.a();
        if (p2 == null) {
            return;
        }
        p2.b = f;
        p2.c = f2;
        p2.d = n;
        p2.e = n2;
        p2.f = f3;
        p2.a = this;
        this.b.b.offer(p2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(float f2, float f3, int n2, int n3, float f4) {
        if (this.a == null) {
            l.e("Sound not loaded");
            return;
        }
        Object object = this.b.b();
        synchronized (object) {
            float f5 = 0.0f;
            float f6 = com.corrodinggames.rts.gameFramework.f.f(f2, f3);
            this.a.play(f6, f4, f5);
        }
    }

    @Override
    public int a() {
        return this.a.getBytesUsed();
    }
}
