/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.StatsSample;
import java.util.ArrayList;

public strictfp class StatsTimeline
extends ArrayList {
    public int a(int n) {
        if (this.isEmpty()) {
            return 0;
        }
        int n2 = ((StatsSample) this.get((int)0)).b;
        for (StatsSample bh2 : (java.util.Collection<StatsSample>) (java.util.Collection) this) {
            if (bh2.a > n) {
                return n2;
            }
            n2 = bh2.b;
        }
        return n2;
    }
}
