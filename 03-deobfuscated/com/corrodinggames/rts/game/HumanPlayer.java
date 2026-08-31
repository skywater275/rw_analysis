/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;
import com.corrodinggames.rts.game.units.DecorUnit;

import com.corrodinggames.rts.game.PlayerState;

public strictfp class HumanPlayer
extends PlayerState {
    public HumanPlayer(int n2) {
        super(n2);
    }

    public HumanPlayer(int n2, boolean bl) {
        super(n2, bl);
    }

    public HumanPlayer(int n2, boolean bl, String string) {
        super(n2, bl);
        this.v = string;
    }


    public void a(float f2) {
        super.a(f2);
    }
}
