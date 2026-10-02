/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.StatsSample;
import com.corrodinggames.rts.gameFramework.StatsTimeline;
import com.corrodinggames.rts.gameFramework.StatsCategory;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public strictfp class StatsHistory {
    private int a = -1;
    private StatsTimeline[] b = new StatsTimeline[bj.values().length];

    public StatsHistory() {
        this.a();
    }

    public void a() {  // 02b bn.java L19
        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = new StatsTimeline();
        }
    }

    public void a(InputNetStream k2) {  // 02b bn.java a(j.k)
        boolean bl = k2.readBoolean();
        if (bl) {
            k2.a("History");
            k2.d();
            this.a = k2.readInt();
            boolean bl2 = k2.readBoolean();
            int n2 = k2.d();
            this.a();
            for (int i = 0; i < n2; ++i) {
                int n3 = 0;
                int n4 = 0;
                int n5 = k2.v();
                for (int j = 0; j < n5; ++j) {
                    int n6;
                    int n7;
                    if (bl2) {
                        n7 = k2.readInt() + n3;
                        n6 = k2.readInt() + n4;
                        n3 = n7;
                        n4 = n6;
                    } else {
                        n7 = k2.readInt();
                        n6 = k2.readInt();
                    }
                    if (i >= this.b.length) continue;
                    this.b[i].add(new StatsSample(n7, n6));
                }
            }
        }
    }

    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {  // 02b bn.java a(j.as) (PacketBuilder 为幻觉名)
        boolean bl = true;
        as2.a(bl);
        if (bl) {
            as2.e();
            as2.c(0);
            as2.a(this.a);
            boolean bl2 = true;
            as2.a(bl2);
            as2.c(this.b.length);
            int n2 = 0;
            for (StatsTimeline bi2 : this.b) {
                int n3 = bi2.size();
                as2.a((short)n3);
                int n4 = 0;
                int n5 = 0;
                for (int i = 0; i < n3; ++i) {
                    ++n2;
                    StatsSample bh2 = (StatsSample) bi2.get(i);
                    if (bl2) {
                        int n6 = bh2.a;
                        int n7 = bh2.b;
                        as2.a(n6 - n4);
                        as2.a(n7 - n5);
                        n4 = n6;
                        n5 = n7;
                        continue;
                    }
                    as2.a(bh2.a);
                    as2.a(bh2.b);
                }
            }
            GlobalState.e("TeamHistory(" + this.a + "): totalValues written:" + n2);
        }
    }

    public void a(com.corrodinggames.rts.game.PlayerState n2, int n3, boolean bl) {  // 02b bn.java L106: a(n,int,boolean)
        for (StatsCategory bj2 : StatsCategory.values()) {
            int n4 = bj2.e.a(n2);
            StatsTimeline bi2 = this.b[bj2.ordinal()];
            if (!bi2.isEmpty() && !bl && ((StatsSample) bi2.get((int)(bi2.size() - 1))).b == n4) continue;
            bi2.add(new StatsSample(n3, n4));
        }
    }

    public void a(int n2) {  // 02b bn.java L121
        this.a = n2;
    }

    public int b() {
        return this.a;
    }

    public StatsTimeline a(StatsCategory bj2) {  // 02b bn.java L129
        return this.b[bj2.ordinal()];
    }

    public boolean c() {
        if (this.a < 0) {
            return false;
        }
        for (StatsTimeline bi2 : this.b) {
            if (bi2.size() <= 1) continue;
            return true;
        }
        return false;
    }

    public int a(StatsCategory bj2, int n2) {  // 02b bn.java L151
        return this.b[bj2.ordinal()].a(n2);
    }

    public void a(StatsHistory bn2) {  // 02b bn.java L155
        for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = this.a(this.b[i], bn2.b[i]);
        }
    }

    private StatsTimeline a(StatsTimeline bi2, StatsTimeline bi3) {  // 02b bn.java L162
        if (bi2.isEmpty()) {
            bi2.addAll(bi3);
            return bi2;
        }
        StatsTimeline bi4 = new StatsTimeline();
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (StatsSample bh2 : (java.util.Collection<StatsSample>) (java.util.Collection) bi2) {
            int n5 = bh2.a;
            int n6 = bh2.b;
            if (n2 < bi3.size()) {
                StatsSample bh3 = (StatsSample) bi3.get(n2);
                while (bh3.a < n5) {
                    n4 = bh3.b;
                    bi4.add(new StatsSample(bh3.a, n3 + n4));
                    if (++n2 >= bi3.size()) continue;
                    bh3 = (StatsSample) bi3.get(n2);
                }
                if (bh3.a == n5) {
                    n4 = bh3.b;
                    n3 = n6;
                    bi4.add(new StatsSample(n5, n3 + n4));
                    ++n2;
                    continue;
                }
                if (bh3.a <= n5) continue;
                n3 = n6;
                bi4.add(new StatsSample(n5, n3 + n4));
                continue;
            }
            n3 = n6;
            bi4.add(new StatsSample(n5, n3 + n4));
        }
        while (n2 < bi3.size()) {
            StatsSample bh4 = (StatsSample) bi3.get(n2);
            n4 = bh4.b;
            bi4.add(new StatsSample(bh4.a, n3 + n4));
            ++n2;
        }
        return bi4;
    }
}
