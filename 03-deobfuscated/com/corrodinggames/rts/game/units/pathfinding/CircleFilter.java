/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.UnitFilter;

public final class CircleFilter
extends UnitFilter {
    public RectF boundsRect = new RectF();
    public float centerX;
    public float centerY;
    public float radius;
    public float radiusSquared;

    public void a(float f, float f2, float f3, float f4) {
        this.centerX = f;
        this.centerY = f3;
        this.radius = f2;
        this.radiusSquared = f4;
        this.boundsRect.a(f, f2, f3, f4);
    }

    @Override
    public final boolean a(UnitInstance am2) {
        float f2 = am2.eo;
        float f3 = am2.ep;
        return this.centerX <= f2 && f2 <= this.centerY && this.radius <= f3 && f3 <= this.radiusSquared;
    }
}
