/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.Minimap;

class MinimapMarker {
    public boolean isVisible;
    public int markerX;
    public int markerY;
    public float markerRadius;
    public float markerValue;
    final /* synthetic */ Minimap f;  // 02b q.java L13: o f (ChatInputPanel 为幻觉名)

    MinimapMarker(Minimap o2, float f, int n, int n2, boolean bl) {
        this.f = o2;
        this.markerRadius = f;
        this.markerX = n;
        this.markerY = n2;
        this.isVisible = bl;
    }
}
