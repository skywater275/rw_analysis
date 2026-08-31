/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.af;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class ThemeColorEntry
extends af {
    com.corrodinggames.rts.gameFramework.rendering.Texture a;  // 02b f/ah.java: m/e a (v19.133e)
    float b = 1.0f;
    int c;
    int d;
    final /* synthetic */ ThemeColors e;  // 02b f/ah.java: final ae e (v19.133e)

    public ThemeColorEntry(ThemeColors ae2) {
        this.e = ae2;
    }


    public int a(Paint paint) {
        return this.c;
    }
}
