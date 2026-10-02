/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

public class ad {
    private int a;
    private int[] b;
    private int c = -1;

    public ad(int n) {
        this.a = 0;
        this.b = new int[n];
    }

    public ad(int n, ad ad2) {
        this.a = n;
        this.b = new int[ad2.b.length];
        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = ad2.b[i];
        }
    }

    public void a(int n, int n2) {
        this.b[n] = n2;
    }

    public float a(int n) {
        if (this.c < 0) {
            this.c = 0;
            for (int i = 0; i < this.b.length; ++i) {
                if (this.b[i] <= 0) continue;
                this.c += this.b[i];
            }
        }
        if (this.c == 0 || this.b[n] <= 0) {
            return 0.0f;
        }
        return (float)this.b[n] / (float)this.c;
    }

    static /* synthetic */ int a(ad ad2) {
        return ad2.a;
    }
}
