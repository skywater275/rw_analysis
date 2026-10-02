/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.RangeValue;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;
public class KeyframePoint
extends RangeValue {
    public CustomVisuals e;
    public float f;
    public float g;
    public boolean h;

    public KeyframePoint(float f, float f2) {
        super(f, f2);
    }

    public void a(ModUnitRegistry l2, String string, String string2) throws bo {
        try {
            if (string.equalsIgnoreCase("x")) {
                this.f = Float.parseFloat(string2);
                return;
            }
            if (string.equalsIgnoreCase("y")) {
                this.g = Float.parseFloat(string2);
                return;
            }
        }
        catch (NumberFormatException numberFormatException) {
            throw new bo("Failed to parse float:" + string2);
        }
        if (string.equalsIgnoreCase("name")) {
            this.e = l2.a(string2, (CustomVisuals) null);
            return;
        }
        throw new bo("Unknown event key:" + string + " on animation");
    }

    /* 覆写 Object.finalize() (声明 throws Throwable), 抛 checked bo 合法 */
    public void finalize() throws bo {
        this.h = true;
        if (this.e == null) {
            throw new bo("Animation effect missing key 'name'");
        }
    }

    public void a(CustomUnitType j2) {
        if (this.e != null) {
            float f2 = j2.eo;
            float f3 = j2.ep;
            this.e.a(f2 += this.f, f3 += this.g, j2.eq, j2.cg, j2);
        }
    }

}
