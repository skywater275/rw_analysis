/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.j;
import com.corrodinggames.rts.game.units.y;

public abstract class i
extends j {
    public abstract int excludeTeam(y var1);

    public abstract n onlyEnemiesOfTeam(y var1);

    public n onlyTeam(y y2) {
        return null;
    }

    public void setup(y y2, float f) {
    }

    public am getResult() {
        return null;
    }
}
