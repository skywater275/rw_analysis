/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.PeriodicTimer;
import com.corrodinggames.rts.gameFramework.StatsHistory;
import com.corrodinggames.rts.gameFramework.StatsRecord;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public strictfp class StatsManager {
    public static boolean a = true;
    StatsRecord b = new StatsRecord();
    StatsRecord[] c = new StatsRecord[PlayerState.e];
    int d;
    boolean e;
    public static PeriodicTimer f = new PeriodicTimer();

    /* 02b bg.java L23: as2.e/StatsRecord.a 抛 IOException (R8 移除 throws) */
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // 02b bg.java L23: a(j.as) (PacketBuilder 为幻觉名)
        as2.e("stats");
        as2.c(0);
        int n2 = PlayerState.c;
        as2.a(n2);
        for (int i = 0; i < n2; ++i) {
            this.c[i].a(as2);
        }
        as2.a("stats");
    }

    public void a(InputNetStream k2, boolean bl2) {
        k2.b("stats");
        byte by = k2.d();
        int n2 = k2.readInt();
        this.c = new StatsRecord[PlayerState.e];
        for (int i = 0; i < n2; ++i) {
            this.c[i] = new StatsRecord();
            this.c[i].a(k2);
        }
        k2.d("stats");
    }

    public void a() {
        this.b = new StatsRecord();
        this.c = new StatsRecord[PlayerState.e];
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = new StatsRecord();
        }
        this.d = 0;
        this.e = a;
    }

    public void b() {
        int n2 = GlobalState.B().by;
        if (this.e && this.d <= n2) {
            int n3 = 5000;
            if (n2 < 60000) {
                n3 = 1000;
            }
            if (n2 > 1800000) {
                n3 = 15000;
            }
            if (n2 > 3600000) {
                n3 = 30000;
            }
            n3 += n3;
            this.a(n2, false, false);
        }
    }

    private void a(int n2, boolean bl2, boolean bl3) {
        for (int j = 0; j < PlayerState.c; ++j) {
            PlayerState n3 = PlayerState.u(j);
            if (n3 == null) continue;
            StatsHistory bn2 = this.c[j].modRegistryRef;
            if (bl2 && !bn2.c()) continue;
            bn2.a(n3, n2, bl3);
            bn2.a(j);
        }
    }

    public void c() {
        this.e = false;
        this.a(GlobalState.B().by, true, true);
    }

    public ArrayList d() {
        ArrayList<StatsRecord> arrayList = new ArrayList<StatsRecord>();
        for (int j = 0; j < PlayerState.c; ++j) {
            if (!this.c[j].modRegistryRef.c()) continue;
            arrayList.add(this.c[j]);
        }
        return arrayList;
    }

    public StatsRecord a(UnitInstance am2) {  // 02b bg.java L115: a(am) (MusicController 为幻觉名)
        return this.a(am2.player);
    }

    public StatsRecord a(com.corrodinggames.rts.game.PlayerState n2) {  // 02b bg.java L119: a(n) (GameState 为幻觉名)
        int n3 = n2.k;
        if (n3 < 0 || n3 >= this.c.length) {
            return this.b;
        }
        StatsRecord bo2 = this.c[n3];
        if (bo2 == null) {
            return this.b;
        }
        return bo2;
    }

    public void a(UnitInstance am2, UnitInstance am3, float f2) {  // 02b bg.java L129: a(am,am,float)
        if (am2 != null) {
            boolean bl2 = am3.bV;
            StatsRecord bo2 = this.a(am2);
            StatsRecord bo3 = this.a(am3);
            if (bl2) {
                f.a(am2, am3);  // 02b bg.java L135: f.a(var1,var2) — f=静态 PeriodicTimer 字段 (MusicController 为幻觉名)
                if (am3.bI()) {
                    ++bo2.buildingsKilled;
                    ++bo3.buildingsLost;
                } else if (am3.dd()) {
                    ++bo2.experimentalsKilled;
                    ++bo3.experimentalsLost;
                } else {
                    ++bo2.unitsKilled;
                    ++bo3.unitsLost;
                }
            }
        }
        GlobalState l2 = GlobalState.B();
        if (am3.player == l2.bs) {
            l2.a(am3, f2);
        }
    }
}
