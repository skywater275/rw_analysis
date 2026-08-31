/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.aq;
import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.m;
import com.corrodinggames.rts.java.n;

public class l
extends aq {
    volatile boolean a;
    public OpenALAudio b;
    boolean c = false;

    public Object f() {
        return this.b;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(float f2) {
        Object object = this.f();
        synchronized (object) {
            if (this.a) {
                return;
            }
            long l2 = br.a();
            this.b.update();
            double d = br.a(l2);
            if (d > 16.0) {
                com.corrodinggames.rts.gameFramework.l.e("music poll took:" + br.a(d));
            }
        }
        super.a(f2);
    }

    @Override
    public void a(int n2) {
    }

    public l(OpenALAudio openALAudio) {
        this.b = openALAudio;
    }

    @Override
    public ar a(String string) {
        return new m(string, this);
    }

    @Override
    public as a() {
        n n2 = new n(this);
        return n2;
    }

    @Override
    public void a(am am2) {
        this.e = am2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void b() {
        Object object = this.f();
        synchronized (object) {
            this.a = true;
        }
    }

    @Override
    public boolean c() {
        return true;
    }

    @Override
    public boolean d() {
        return true;
    }

    @Override
    public int e() {
        return 100;
    }
}
