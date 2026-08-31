/*
 * Decompiled with CFR 0.152.
 * 02b 原稿: java/g.java (v19.115c 重建 — 变换矩阵类, 原被错误映射为 GameLauncher)
 */
package com.corrodinggames.rts.java;

import android.graphics.RectF;

public final class Transform {
    float a;
    float b;
    float c = -90.0f;
    float d = 1.0f;
    float e = 1.0f;
    RectF f;
    float g;
    float h;

    public void a(Transform transform) {
        transform.a = this.a;
        transform.b = this.b;
        transform.c = this.c;
        transform.d = this.d;
        transform.e = this.e;
        transform.f = this.f;
        transform.g = this.g;
        transform.h = this.h;
    }
}
