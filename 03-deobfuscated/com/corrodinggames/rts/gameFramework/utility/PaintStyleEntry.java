/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.graphics.Paint$Style;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;

public strictfp final class PaintStyleEntry {
    public int styleType;
    public Paint$Style paintColor;
    public UniquePaint strokeWidth;

    public PaintStyleEntry(int n, Paint$Style paint$Style) {
        UniquePaint ag2 = new UniquePaint();
        ag2.b(n);
        ag2.a(paint$Style);
        this.strokeWidth = ag2;
        this.paintColor = paint$Style;
        this.styleType = n;
    }
}
