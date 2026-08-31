/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$ThisActionTargetReference
extends UnitReference {
    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitInstance am2 = CustomUnitType.selectedUnitRef;
        if (am2 != null) {
            return am2;
        }
        PointF pointF = CustomUnitType.sharedRenderPoint;
        if (pointF != null) {
            UnitType y3 = PlayerState.i.t;
            y3.cg = 0.0f;
            y3.eo = pointF.a;
            y3.ep = pointF.b;
            y3.eq = 0.0f;
            return y3;
        }
        return null;
    }

    @Override
    public String getClassDebugName() {
        return "ThisActionTarget";
    }
}
