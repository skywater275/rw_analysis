/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.ui.Minimap;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class LineBuffer
extends com.corrodinggames.rts.gameFramework.rendering.DrawCommand {
    float[] a;
    int b = 0;
    Paint c;
    int d;
    boolean e;
    private final RectF f = new RectF();

    LineBuffer(int n, Paint paint) {  // 02b f/s.java L17: 构造器 (s 为幻觉名)
        this.d = n;
        this.a = new float[n * 2];
        this.c = paint;
    }

    public final void a(float f, float f2) {
        this.a[this.b] = f;
        this.a[this.b + 1] = f2;
        this.b += 2;
    }


    public void a(TextureManagerInterface y2) {
        if (!this.e) {
            y2.a(this.a, 0, this.b, this.c);
        } else {
            RectF rectF = this.f;
            float f = this.c.g();
            for (int i = 0; i < this.b; ++i) {
                float f2 = this.a[i];
                float f3 = this.a[i + 1];
                rectF.a = f2;
                rectF.b = f3;
                rectF.c = f2 + f;
                rectF.d = f3 + f;
                y2.a(rectF, this.c);
            }
        }
        Minimap.a(this);
    }
}
