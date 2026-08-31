/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer;

final class UnitRenderer$4
extends UnitRenderer {
    void al$4() {
    }


    public boolean a(UnitType y2) {
        return y2.r() == UnitRegistry.J && y2.V() < 3 && y2.cN == null;
    }
}
