/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ByteSlot;
import java.util.ArrayList;

public strictfp class ByteIndexedMap {
    public ByteSlot a;
    public ByteSlot b;
    public ByteSlot c;
    public ByteSlot d;
    public ByteSlot e;
    public ByteSlot f;
    public ByteSlot g;
    public ByteSlot h;
    public ByteSlot i;
    public ByteSlot j;
    public ByteSlot k;
    public ByteSlot l;
    public ByteSlot m;
    ArrayList n = new ArrayList();

    public ByteIndexedMap() {
        this.a = this.getb((byte)1);
        this.b = this.getb((byte)2);
        this.c = this.getb((byte)3);
        this.d = this.getb((byte)4);
        this.e = this.getb((byte)10);
        this.f = this.getb((byte)11);
        this.g = this.getb((byte)13);
        this.h = this.getb((byte)21);
        this.i = this.getb((byte)35);
        this.j = this.getb((byte)40);
        this.k = this.getb((byte)45);
        this.l = this.getb((byte)52);
        this.m = this.getb((byte)60);
        ByteSlot b2 = null;
        b2 = this.a;
        b2.a(b2);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)11));
        b2.a(this.getb((byte)13));
        b2.a(this.getb((byte)21));
        b2 = this.k;
        b2.a(this.getb((byte)52));
        b2 = this.m;
        b2.a(b2);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)11));
        b2.a(this.getb((byte)13));
        b2.a(this.getb((byte)21));
        b2 = this.getb((byte)10);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
        b2.a(this.getb((byte)40));
        b2 = this.getb((byte)11);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
        b2.a(this.getb((byte)40));
        b2 = this.getb((byte)3);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
        b2 = this.getb((byte)4);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
        b2 = this.getb((byte)13);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
        b2 = this.getb((byte)21);
        b2.a(this.getb((byte)3));
        b2.a(this.getb((byte)4));
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
        b2 = this.i;
        b2.a(this.getb((byte)10));
        b2.a(this.getb((byte)13));
    }

    public ByteSlot getb(byte by) {
        for (ByteSlot b2 : (java.util.Collection<ByteSlot>) (java.util.Collection) this.n) {
            if (b2.a != by) continue;
            return b2;
        }
        ByteSlot b3 = new ByteSlot();
        b3.a = by;
        this.n.add(b3);
        return b3;
    }
}