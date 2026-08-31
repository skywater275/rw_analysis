/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint$Cap;
import com.corrodinggames.rts.gameFramework.bn;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;

public class aa {
    bn a;
    String b;
    int c;
    ag[] d;
    ag[] e;

    public ag a(int n, boolean bl) {
        int n2 = n / 25;
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 10) {
            n2 = 10;
        }
        if (bl) {
            return this.e[n2];
        }
        return this.d[n2];
    }

    public aa(bn bn2, String string, int n2) {
        this.a = bn2;
        this.b = string;
        this.c = n2;
        this.d = new ag[11];
        this.e = new ag[11];
        for (int j = 0; j < 11; ++j) {
            int n3 = j * 25;
            if (j == 10) {
                n3 = 255;
            }
            this.d[j] = new ag();
            this.d[j].a(2.0f);
            if (l.aZ) {
                this.d[j].a(3.0f);
            }
            this.d[j].a(Paint$Cap.b);
            this.d[j].b(n2);
            this.d[j].c(n3);
            this.e[j] = new ag();
            this.e[j].b(-13162713);
            this.e[j].c(n3);
            this.e[j].a(5.0f);
            this.e[j].a(Paint$Cap.b);
        }
    }
}
