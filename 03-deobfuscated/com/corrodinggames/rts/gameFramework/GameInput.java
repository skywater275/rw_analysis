/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.MusicFactory;
import com.corrodinggames.rts.gameFramework.GameTimer;

public class GameInput
extends GameTimer {
    MusicFactory a;

    public GameInput(String string, MusicFactory an2) {
        super(string, an2);
        this.a = an2;
    }
}
