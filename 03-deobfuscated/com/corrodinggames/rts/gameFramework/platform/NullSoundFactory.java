/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import android.content.Context;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.platform.NullSound;
import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;

public class NullSoundFactory
extends SoundFactory {
    @Override
    public void a(Context context) {
    }

    @Override
    public Sound a(int n) {
        String string = com.corrodinggames.rts.gameFramework.GameUtils.a(com.corrodinggames.rts.R$raw.class, n);
        NullSound g2 = new NullSound(string, this);
        return g2;
    }

    @Override
    public Sound a(String string, AssetStream j2, boolean bl) {
        NullSound g2 = new NullSound(string, this);
        return g2;
    }

    public static Sound b() {
        NullSound g2 = new NullSound("Null (from out of memory)", null);
        return g2;
    }

    public static Sound a(String string) {
        NullSound g2 = new NullSound("Null sound - " + string, null);
        return g2;
    }
}
