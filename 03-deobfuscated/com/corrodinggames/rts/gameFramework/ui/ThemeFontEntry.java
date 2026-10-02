/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.ui.ThemeColors;
import com.corrodinggames.rts.gameFramework.ui.af;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class ThemeFontEntry
extends af {
    String d;
    final /* synthetic */ ThemeColors e;  // 02b f/ai.java: final ae e (v19.133e)


    public int a(Paint paint) {
        GlobalState l2 = GlobalState.B();
        Paint paint2 = this.b(paint);
        int n2 = l2.bO.b(this.d, paint2);
        if (GlobalState.at()) {
            // empty if block
        }
        return n2;
    }

    public Paint b(Paint paint) {
        return paint;
    }

    ThemeFontEntry(ThemeColors ae2, String string) {
        this.e = ae2;
        this.d = string;
    }

    public ThemeFontEntry b(String string) {
        ThemeFontEntry ai2 = new ThemeFontEntry(this.e, string);
        return ai2;
    }
}
