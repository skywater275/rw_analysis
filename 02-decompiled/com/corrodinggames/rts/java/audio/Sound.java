/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio;

public interface Sound {
    public long play();

    public long play(float var1);

    public long play(float var1, float var2, float var3);

    public long loop();

    public long loop(float var1);

    public long loop(float var1, float var2, float var3);

    public void stop();

    public void pause();

    public void resume();

    public void dispose();

    public void stop(long var1);

    public void pause(long var1);

    public void resume(long var1);

    public void setLooping(long var1, boolean var3);

    public void setPitch(long var1, float var3);

    public void setVolume(long var1, float var3);

    public void setPan(long var1, float var3, float var4);

    public int getBytesUsed();
}
