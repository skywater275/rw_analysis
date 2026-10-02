/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;

public class NullSound
extends Sound {
    public NullSound(String string, SoundFactory h2) {
        super(string, h2);
    }

    @Override
    public void a(float f, float f2, int n, int n2, float f3) {
    }

    @Override
    public int a() {
        return 0;
    }
}
