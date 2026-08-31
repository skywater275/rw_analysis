/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio;

import com.corrodinggames.rts.java.audio.Music$OnCompletionListener;

public interface Music {
    public void play();

    public void pause();

    public void stop();

    public boolean isPlaying();

    public void setLooping(boolean var1);

    public boolean isLooping();

    public void setVolume(float var1);

    public float getVolume();

    public void setPan(float var1, float var2);

    public void setPosition(float var1);

    public float getPosition();

    public void dispose();

    public void setOnCompletionListener(Music$OnCompletionListener var1);
}
