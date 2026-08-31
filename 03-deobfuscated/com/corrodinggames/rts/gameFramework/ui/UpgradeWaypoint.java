/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.ui.BuildWaypoint;
import com.corrodinggames.rts.gameFramework.steam.Localization;

strictfp class UpgradeWaypoint
extends BuildWaypoint {
    public UpgradeWaypoint(float f, float f2, UnitTypeHandle as2) {  // 02b f/at 构造 (v19.133f4 修正)
        super(f, f2, as2);
    }


    public String a() {
        if (this.g == null) {
            this.g = String.format(com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.log.upgradeCompleted", new Object[0]), this.a.e(), this.b);
        }
        return this.g;
    }
}
