/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;
public class RangeValue
implements Comparable {
    public float a;
    public float b;
    public float c;
    public float d;

    public RangeValue(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public int a(RangeValue a2) {
        if (this.a == a2.a) {
            return 0;
        }
        return this.a > a2.a ? 1 : -1;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((RangeValue) object);
    }
}
