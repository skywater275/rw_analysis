/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;

import com.corrodinggames.rts.gameFramework.pathfinding.PathNode;

public class TerrainCost {
    int a;
    int b;
    int c;
    int d;
    byte[] e;
    byte[] f;

    public TerrainCost(int n, int n2) {
        this.a = n;
        this.b = n2;
        this.e = new byte[n * n2];
        this.f = new byte[n * n2];
    }

    public final byte a(int n, int n2) {
        return this.e[n * this.b + n2];
    }

    public final byte a(PathNode p2) {
        return this.e[p2.a * this.b + p2.b];
    }

    public boolean b(PathNode p2) {
        return this.a(p2) <= 0;
    }

    public void a(PathNode p2, byte by) {
        this.e[p2.a * this.b + p2.b] = by;
    }

    public void a(PathNode p2, boolean bl) {
        this.f[p2.a * this.b + p2.b] = (byte)(bl ? 1 : 0);
    }

    public boolean c(PathNode p2) {
        return this.f[p2.a * this.b + p2.b] == 1;
    }
}
