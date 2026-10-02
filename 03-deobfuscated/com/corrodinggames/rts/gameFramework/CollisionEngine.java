/*
 * Decompiled with CFR 0.152.
 * 02 原稿: gameFramework/a.java (v19.109 重建 — 碰撞分组引擎)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.CollisionGroup;
import java.util.ArrayList;

public strictfp class CollisionEngine {
    public CollisionGroup a;
    public CollisionGroup b;
    public CollisionGroup c;
    public CollisionGroup d;
    public CollisionGroup e;
    public CollisionGroup f;
    public CollisionGroup g;
    public CollisionGroup h;
    public CollisionGroup i;
    public CollisionGroup j;
    public CollisionGroup k;
    public CollisionGroup l;
    public CollisionGroup m;
    ArrayList n = new ArrayList();

    public CollisionEngine() {
        this.a = this.a((byte)1);
        this.b = this.a((byte)2);
        this.c = this.a((byte)3);
        this.d = this.a((byte)4);
        this.e = this.a((byte)10);
        this.f = this.a((byte)11);
        this.g = this.a((byte)13);
        this.h = this.a((byte)21);
        this.i = this.a((byte)35);
        this.j = this.a((byte)40);
        this.k = this.a((byte)45);
        this.l = this.a((byte)52);
        this.m = this.a((byte)60);
        CollisionGroup b2 = null;
        b2 = this.a;
        b2.a(b2);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)11));
        b2.a(this.a((byte)13));
        b2.a(this.a((byte)21));
        b2 = this.k;
        b2.a(this.a((byte)52));
        b2 = this.m;
        b2.a(b2);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)11));
        b2.a(this.a((byte)13));
        b2.a(this.a((byte)21));
        b2 = this.a((byte)10);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
        b2.a(this.a((byte)40));
        b2 = this.a((byte)11);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
        b2.a(this.a((byte)40));
        b2 = this.a((byte)3);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
        b2 = this.a((byte)4);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
        b2 = this.a((byte)13);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
        b2 = this.a((byte)21);
        b2.a(this.a((byte)3));
        b2.a(this.a((byte)4));
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
        b2 = this.i;
        b2.a(this.a((byte)10));
        b2.a(this.a((byte)13));
    }

    public CollisionGroup a(byte by) {
        for (CollisionGroup b2 : (java.util.Collection<CollisionGroup>) (java.util.Collection) this.n) {
            if (b2.a != by) continue;
            return b2;
        }
        CollisionGroup b3 = new CollisionGroup();
        b3.a = by;
        this.n.add(b3);
        return b3;
    }
}
