/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.AssetLoader;

strictfp class ThreadPool
extends AssetLoader {
    public int c;
    final /* synthetic */ GlobalState d;

    public ThreadPool(GlobalState l2, int n) {
        super(l2);
        this.d = l2;
        this.c = n;
    }
}
