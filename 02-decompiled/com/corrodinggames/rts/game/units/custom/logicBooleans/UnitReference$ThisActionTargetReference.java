/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import android.graphics.PointF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.y;

public class UnitReference$ThisActionTargetReference
extends UnitReference {
    @Override
    public am getSingleRaw(y y2) {
        am am2 = j.dN;
        if (am2 != null) {
            return am2;
        }
        PointF pointF = j.dM;
        if (pointF != null) {
            y y3 = n.i.t;
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
