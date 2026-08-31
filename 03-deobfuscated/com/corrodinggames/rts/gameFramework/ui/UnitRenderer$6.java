/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer;

final class UnitRenderer$6
extends UnitRenderer {
    void al$6() {
    }


    public boolean a(UnitType y2) {
        return y2.r() == UnitRegistry.c && y2.cN == null;
    }
}
