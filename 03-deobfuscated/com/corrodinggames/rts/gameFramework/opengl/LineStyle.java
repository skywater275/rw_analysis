/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Paint;
import android.graphics.Paint$Style;

public class LineStyle {
    private float lineWidth;
    private int lineColor;
    private Paint$Style lineStyle;

    public void a(int n) {
        this.lineColor = n;
    }

    public int a() {
        return this.lineColor;
    }

    public void a(float f) {
        this.lineWidth = f;
    }

    public float b() {
        return this.lineWidth;
    }

    public void a(Paint$Style style) {
        this.lineStyle = style;
    }

    public Paint$Style c() {
        return this.lineStyle;
    }
}
