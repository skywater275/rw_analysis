/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.Waypoint;
import com.corrodinggames.rts.gameFramework.steam.Localization;

strictfp class AttackWaypoint
extends Waypoint {
    private boolean a;

    public AttackWaypoint(float f, float f2, boolean bl) {
        super(f, f2);
        this.a = bl;
    }


    public boolean a(Waypoint au2) {
        if (super.a(au2) && au2 instanceof AttackWaypoint) {
            AttackWaypoint as2 = (AttackWaypoint) au2;
            return as2.a == this.a;
        }
        return false;
    }


    public void b(Waypoint au2) {
    }


    protected long b() {
        return 20000L;
    }


    public String a() {
        if (this.g == null) {
            this.g = this.a ? com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.log.baseDamaged", new Object[0]) : com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.log.unitDamaged", new Object[0]);
        }
        return this.g;
    }
}
