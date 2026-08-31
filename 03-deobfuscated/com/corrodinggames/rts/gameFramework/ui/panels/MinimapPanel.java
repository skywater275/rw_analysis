/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;
import com.corrodinggames.rts.gameFramework.OSEnum;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.ui.panels.ChatPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.UnitInfoPanel;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class MinimapPanel
extends UnitInfoPanel {
    ChatPanel b = ChatPanel.j;  // 02b f/a/h L14 (v19.133f2: OSEnum 幻觉名修正)


    public void a(float f, float f2) {
        super.a(f, f2);
        TextureManagerInterface y2 = this.d();
        RectF rectF = this.a(new RectF(), f, f2);
        this.b.a(y2, rectF);
    }
}
