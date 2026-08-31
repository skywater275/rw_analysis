/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui.panels;

import com.corrodinggames.rts.gameFramework.ui.panels.UnitInfoPanel;
import com.corrodinggames.rts.gameFramework.ui.panels.m;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class g
extends UnitInfoPanel {
    public g() {
    }

    public g(m m2) {  // v19.133f3: StatsPanel 幻觉名修正
        this.x = m2;
    }


    public void a(float f, float f2) {
        super.a(f, f2);
    }


    public void b() {
        super.b();
        TextureManagerInterface y2 = this.d();
        this.i = this.z;
        this.j = this.y;
        this.i += this.m + this.n;
        this.j += this.k + this.l;
    }
}
