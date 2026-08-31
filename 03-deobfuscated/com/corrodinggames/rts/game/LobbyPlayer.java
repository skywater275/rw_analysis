/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.PlayerState;
import java.util.ArrayList;
import java.util.Iterator;

public class LobbyPlayer
extends PlayerState {
    public float bG;
    ArrayList bq;

    public LobbyPlayer(int n2) {
    }


    public void al() {
        Iterator iterator = this.bq.iterator();
        while (iterator.hasNext()) {
            com.corrodinggames.rts.game.units.projectiles.WallBuilding d2 = (com.corrodinggames.rts.game.units.projectiles.WallBuilding) iterator.next();
            d2.b();
        }
    }

    public void a(float f2) {
        super.a(f2);
    }
}
