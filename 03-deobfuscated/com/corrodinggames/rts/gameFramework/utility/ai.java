/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.graphics.PointF;

public strictfp final class ai {
    public float a;
    public float b;  // 02b ai.java L8: b (b 为幻觉名)
    public float c;  // 02b L9: c (c 为幻觉名)

    public void a(PointF pointF) {
        this.a = pointF.a;
        this.b = pointF.b;
        this.c = 0.0f;
    }
}
