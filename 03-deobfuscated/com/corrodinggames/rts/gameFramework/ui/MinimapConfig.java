/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.gameFramework.ui.panels.ChatInputPanel;

import com.corrodinggames.rts.gameFramework.ui.Minimap;
import com.corrodinggames.rts.gameFramework.ui.MinimapMode;

class MinimapConfig {
    public int minimapWidth;
    public int minimapHeight;
    public float worldScaleX = 1.0f;
    public float worldScaleY = 1.0f;
    public MinimapMode renderMode = MinimapMode.a;  // 02b f/p.java L12/L21: r.e (VersionInfo 为幻觉名)
    final /* synthetic */ Minimap f;  // 02b L14: o f (ChatInputPanel 为幻觉名)

    public MinimapConfig(Minimap o2) {
        this.f = o2;
    }
}
