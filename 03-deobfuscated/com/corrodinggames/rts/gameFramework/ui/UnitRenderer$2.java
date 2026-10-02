/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer;

final class UnitRenderer$2
extends UnitRenderer {
    void al$2() {
    }


    public boolean a(UnitType y2) {
        return y2.aj() && !y2.u() && y2.cN == null;
    }
}
