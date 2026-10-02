/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.GamePhase;
import com.corrodinggames.rts.gameFramework.FrameCounter;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;

public final class ExtraManager {
    GlobalState a;
    public int extraCount = 0;
    public static int maxExtras = 40;
    public int enabledCount = 0;
    FrameCounter e = new FrameCounter(this);
    Paint f = new Paint();
    Rect g = new Rect();
    int h = -1;

    public ExtraManager(GlobalState l2) {
        this.a = l2;
    }

    public static final long a() {
        return System.nanoTime();
    }

    public static final float a(long l2) {
        return (float)(System.nanoTime() - l2) / 1000000.0f;
    }

    public static final double a(long l2, long l3) {
        return (double)(l3 - l2) / 1000000.0;
    }

    public static final void a(String string, long l2) {
        GlobalState.e(string + "" + br.a(br.a(l2)));
    }

    public final void a(GamePhase bs2) {
    }

    public final void b(GamePhase bs2) {
    }

    public static final String a(double d) {
        return "" + GameUtils.a(d, 3) + "ms";
    }

    public static final String b(double d) {
        return "" + d / 1000000.0 + "ms";
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void a(boolean bl, boolean bl2) {
    }
}
