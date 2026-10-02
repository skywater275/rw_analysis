/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public class SteamControllerMotionData {
    float[] data = new float[10];

    public float getRotQuatX() {
        return this.data[0];
    }

    public float getRotQuatY() {
        return this.data[1];
    }

    public float getRotQuatZ() {
        return this.data[2];
    }

    public float getRotQuatW() {
        return this.data[3];
    }

    public float getPosAccelX() {
        return this.data[4];
    }

    public float getPosAccelY() {
        return this.data[5];
    }

    public float getPosAccelZ() {
        return this.data[6];
    }

    public float getRotVelX() {
        return this.data[7];
    }

    public float getRotVelY() {
        return this.data[8];
    }

    public float getRotVelZ() {
        return this.data[9];
    }
}
