/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.Minimap;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

class UpdateChecker$1
extends com.corrodinggames.rts.gameFramework.rendering.DrawCommand {  // 02b f/o$1 extends m/m (v19.133f4 修正)
    final /* synthetic */ Minimap a;  // v19.133f4: ChatInputPanel 幻觉修正 (构造 Minimap 赋值)

    UpdateChecker$1(Minimap o2) {
        this.a = o2;
    }


    public void a(TextureManagerInterface y2) {  // 02b m/y (v19.133f4 修正)
        this.a.a(y2, 0, 0, 0.0f, 1.0f);
    }
}
