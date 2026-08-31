/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.l;

public class m
extends ar {
    l a;
    Music c;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public m(String string, l l2) {
        super(string, l2);
        this.a = l2;
        Object object = l2.f();
        synchronized (object) {
            this.a = l2;
            String string2 = com.corrodinggames.rts.gameFramework.e.a.e(string);
            this.c = string2.contains(".rwmod") ? l2.b.newMusic(new a(com.corrodinggames.rts.gameFramework.e.a.k(string), string2)) : l2.b.newMusic(new a(string2));
        }
    }
}
