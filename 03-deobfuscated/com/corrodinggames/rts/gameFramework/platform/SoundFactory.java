/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;

import android.content.Context;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.util.HashMap;

public abstract class SoundFactory {
    HashMap h = new HashMap();

    public abstract Sound a(int var1);

    public abstract Sound a(String var1, AssetStream var2, boolean var3);

    public abstract void a(Context var1);

    public void a() {
    }
}
