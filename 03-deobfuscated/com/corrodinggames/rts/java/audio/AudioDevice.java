/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio;

public interface AudioDevice {
    public boolean isMono();

    public void writeSamples(short[] var1, int var2, int var3);

    public void writeSamples(float[] var1, int var2, int var3);

    public int getLatency();

    public void dispose();

    public void setVolume(float var1);
}
