/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.Waypoint;

strictfp class aq
extends Waypoint {  // 02b f/aq extends au (v19.133f4 NetworkPacket 幻觉修正)
    public aq(String string) {
        super(-1000.0f, -1000.0f);
        this.g = string;
    }


    public boolean a(Waypoint au2) {
        return false;
    }


    public void b(Waypoint au2) {
    }


    public String a() {
        return this.g;
    }
}
