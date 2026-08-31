/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;

import com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.LeaderboardPanel;

public class b
extends LeaderboardPanel {
    public b() {
        this.c = ChatPanel.j;
    }

    @Override
    public void a(float f, float f2) {
        this.c = this.u ? ChatPanel.k : ChatPanel.j;
        super.a(f, f2);
    }
}
