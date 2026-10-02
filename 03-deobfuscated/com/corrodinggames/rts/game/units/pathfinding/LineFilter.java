/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.UnitFilter;

public final class LineFilter
extends UnitFilter {
    public RectF boundsRect = new RectF();
    public float startX;
    public float startY;
    public float endX;
    public float endY;

    @Override
    public final boolean a(UnitInstance am2) {
        float f2 = am2.cj;
        float f3 = am2.eo;
        float f4 = am2.ep;
        return this.startX - f2 <= f3 && f3 <= this.startY + f2 && this.endX - f2 <= f4 && f4 <= this.endY + f2;
    }
}
