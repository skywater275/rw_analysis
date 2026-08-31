/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.ThemeFontEntry;

public class ThemePaint
extends ThemeFontEntry {
    public Paint a;
    public int b;
    final /* synthetic */ ThemeColors c;  // 02b f/ag.java: final ae c (v19.133e)

    ThemePaint(ThemeColors ae2, String string, Paint paint) {
        super(ae2, string);
        this.c = ae2;
        this.b = 0;
        this.a = paint;
    }

    ThemePaint(ThemeColors ae2, String string, Paint paint, int n) {
        super(ae2, string);
        this.c = ae2;
        this.b = 0;
        this.a = paint;
        this.b = n;
    }

    @Override
    public Paint b(Paint paint) {
        if (this.a == null) {
            if (this.b != 0) {
                ThemeColors.f.a(paint);  // 02b f/ae static f (v19.133e)
                ThemeColors.f.b(this.b);  // 02b f/ae static f (v19.133e)
                return ThemeColors.f;
            }
            return paint;
        }
        if (this.b != 0) {
            ThemeColors.f.a(this.a);
            ThemeColors.f.b(this.b);  // 02b f/ae static f (v19.133e)
            return ThemeColors.f;
        }
        return this.a;
    }

    public ThemePaint a(String string) {
        ThemePaint ag2 = new ThemePaint(this.c, string, this.a, this.b);
        return ag2;
    }


    public ThemeFontEntry b(String string) {  // 02b f/ag.java: 合成 ai b(String) (v19.133e)
        return this.a(string);
    }
}
