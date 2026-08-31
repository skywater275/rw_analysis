/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;

public strictfp class WorldGenerator {
    float a;
    Paint b;
    final /* synthetic */ GlobalState c;

    WorldGenerator(GlobalState l2) {
        this.c = l2;
    }

    void a() {
        float f2 = this.c.e(this.a);
        if (this.b.k() != f2) {
            if (this.b instanceof com.corrodinggames.rts.gameFramework.rendering.UniquePaint) {
                ((com.corrodinggames.rts.gameFramework.rendering.UniquePaint) this.b).c(f2);
            } else {
                this.b.b(f2);
            }
        }
    }
}
