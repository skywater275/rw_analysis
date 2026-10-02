/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

public strictfp final class UnitAttachment {
    public boolean attachType;
    public boolean offsetX;
    public boolean offsetY;
    boolean rotationAngle;
    public float scaleValue;
    public float f;

    void ad() {
    }

    public void a() {
        this.attachType = false;
        this.offsetX = false;
        this.offsetY = false;
        this.scaleValue = 0.0f;
        this.f = 0.0f;
        this.rotationAngle = true;
    }
}
