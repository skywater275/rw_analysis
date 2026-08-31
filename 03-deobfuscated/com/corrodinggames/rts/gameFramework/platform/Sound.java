/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.GameUtils;

public abstract class Sound {
    public float d = 1.0f;
    public String e;
    public boolean f = false;
    public boolean g;

    public Sound(String string, SoundFactory h2) {
        this.e = com.corrodinggames.rts.gameFramework.GameUtils.g(string);
        if (h2 != null) {
            h2.h.put(this.e, this);
        }
    }

    public abstract void a(float var1, float var2, int var3, int var4, float var5);

    public abstract int a();
}
