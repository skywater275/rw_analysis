/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;

public class l {
    Paint a;
    String b;
    int c = -1;
    float d;
    Paint e;
    String f;
    int g = -1;
    float h;

    public l(String string, Paint paint, String string2, Paint paint2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.a = paint;
        this.b = string;
        this.d = l2.bO.b(string, paint);
        this.e = paint2;
        this.f = string2;
        this.h = l2.bO.b(string2, paint2);
    }
}
