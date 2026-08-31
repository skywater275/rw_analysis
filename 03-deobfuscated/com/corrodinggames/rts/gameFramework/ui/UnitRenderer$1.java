/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer;

final class UnitRenderer$1
extends UnitRenderer {
    void al$1() {
    }


    public boolean a(UnitType y2) {
        return y2.aj() && !y2.u() && y2.cN == null && y2.aq();
    }
}
