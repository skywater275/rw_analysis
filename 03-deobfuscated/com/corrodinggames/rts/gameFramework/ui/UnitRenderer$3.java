/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer;

final class UnitRenderer$3
extends UnitRenderer {
    void al$3() {
    }


    public boolean a(UnitType y2) {
        return y2.r() != null && y2.r().p() && y2.cN == null;
    }
}
