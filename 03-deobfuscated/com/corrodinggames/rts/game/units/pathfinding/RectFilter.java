/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.UnitFilter;
import com.corrodinggames.rts.gameFramework.GameUtils;

public final class RectFilter
extends UnitFilter {
    public RectF boundsRect = new RectF();
    public float minX;
    public float minY;
    public float maxX;
    public float maxY;
    public float expandX;
    public float expandY;
    public float resultCount;

    @Override
    public final boolean a(UnitInstance am2) {
        float f2 = am2.eo;
        float f3 = am2.ep;
        if (this.minX <= f2 && f2 <= this.minY && this.maxX <= f3 && f3 <= this.maxY) {
            float f4 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.expandX, this.expandY, f2, f3);
            return f4 < this.resultCount;
        }
        return false;
    }
}
