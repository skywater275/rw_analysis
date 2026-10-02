/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ExtraManager;

public class FrameCounter {
    public long[] frameTimes = new long[br.c];
    public long[] frameDurations = new long[br.c];
    public float[] fpsValues = new float[br.c];
    public long[] updateTimes = new long[br.c];
    public long[] renderTimes = new long[br.c];
    final /* synthetic */ ExtraManager f;

    public FrameCounter(ExtraManager br2) {
        this.f = br2;
    }
}
