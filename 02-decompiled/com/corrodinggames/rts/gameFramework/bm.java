/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bo;
import java.util.ArrayList;

public strictfp class bm
extends bo {
    public bm(ArrayList arrayList) {
        for (bo bo2 : arrayList) {
            this.a += bo2.a;
            this.b += bo2.b;
            this.c += bo2.c;
            this.d += bo2.d;
            this.e += bo2.e;
            this.f += bo2.f;
            this.g += bo2.g;
            this.h += bo2.h;
            this.i += bo2.i;
            this.j = Math.max(this.j, bo2.j);
            this.k += bo2.k;
            this.l.a(bo2.l);
        }
    }
}
