/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.StatsHistory;
import com.corrodinggames.rts.gameFramework.Attachment;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public strictfp class StatsRecord {
    public int unitsBuilt;
    public int unitsDestroyed;
    public int unitsKilled;  // 02b bo.c (f/e L48 "Units Killed" 铁证)
    public int buildingsKilled;  // 02b bo.d (f/e L50)
    public int experimentalsKilled;  // 02b bo.e (f/e L52)
    public int unitsLost;  // 02b bo.f (f/e L56)
    public int buildingsLost;  // 02b bo.g (f/e L58)
    public int experimentalsLost;  // 02b bo.h (f/e L60)
    public int killsCount;
    public int deathsCount;
    public long totalPlayTimeMs;
    public StatsHistory modRegistryRef = new StatsHistory();
    private static final byte m = (byte)bp.b.ordinal();

    /* 02b bo.java L23: as2.e/modRegistryRef.a 抛 IOException */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // 02b bo.java L23: a(j.as) (PacketBuilder 为幻觉名)
        as2.c(m);
        as2.e();
        as2.a(this.unitsBuilt);
        as2.a(this.unitsDestroyed);
        as2.a(this.unitsKilled);
        as2.a(this.buildingsKilled);
        as2.a(this.experimentalsKilled);
        as2.a(this.unitsLost);
        as2.a(this.buildingsLost);
        as2.a(this.experimentalsLost);
        as2.a(this.killsCount);
        as2.a(this.deathsCount);
        as2.a(this.totalPlayTimeMs);
        this.modRegistryRef.a(as2);
    }

    public void a(InputNetStream k2) {  // 02b bo.java L40: a(j.k)
        byte by = k2.d();
        k2.a("stats start");
        this.unitsBuilt = k2.readInt();
        this.unitsDestroyed = k2.readInt();
        this.unitsKilled = k2.readInt();
        this.buildingsKilled = k2.readInt();
        this.experimentalsKilled = k2.readInt();
        this.unitsLost = k2.readInt();
        this.buildingsLost = k2.readInt();
        this.experimentalsLost = k2.readInt();
        this.killsCount = k2.readInt();
        this.deathsCount = k2.readInt();
        this.totalPlayTimeMs = k2.i();
        if (by >= bp.b.ordinal()) {
            this.modRegistryRef.a(k2);
        }
    }
}
