/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.StatsRecord;
import java.util.ArrayList;

public strictfp class GameTimerTask
extends StatsRecord {
    public GameTimerTask(ArrayList arrayList) {
        for (StatsRecord bo2 : (java.util.Collection<StatsRecord>) (java.util.Collection) arrayList) {
            this.unitsBuilt += bo2.unitsBuilt;
            this.unitsDestroyed += bo2.unitsDestroyed;
            this.unitsKilled += bo2.unitsKilled;
            this.buildingsKilled += bo2.buildingsKilled;
            this.experimentalsKilled += bo2.experimentalsKilled;
            this.unitsLost += bo2.unitsLost;
            this.buildingsLost += bo2.buildingsLost;
            this.experimentalsLost += bo2.experimentalsLost;
            this.killsCount += bo2.killsCount;
            this.deathsCount = Math.max(this.deathsCount, bo2.deathsCount);
            this.totalPlayTimeMs += bo2.totalPlayTimeMs;
            this.modRegistryRef.a(bo2.modRegistryRef);
        }
    }
}
