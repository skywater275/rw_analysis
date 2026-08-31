/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.Minimap;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

class Minimap$1
extends DrawCommand {
    final /* synthetic */ Minimap a;

    Minimap$1(Minimap o2) {
        this.a = o2;
    }

    @Override
    public void a(TextureManagerInterface y2) {
        this.a.a(y2, 0, 0, 0.0f, 1.0f);
    }
}
