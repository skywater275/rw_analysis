/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

public strictfp class ResourceRate {
    public float productionRate;
    public float consumptionRate;
    public float storageCapacity;
    public int resourceRef = 1;

    public float a() {
        return (this.consumptionRate + this.storageCapacity) / 60.0f;
    }
}
