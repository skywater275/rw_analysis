/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.StatsSample;
import com.corrodinggames.rts.gameFramework.StatsTimeline;
import com.corrodinggames.rts.gameFramework.StatsCategory;
import com.corrodinggames.rts.gameFramework.ui.LineGraphStyle;
import com.corrodinggames.rts.gameFramework.ui.IntArray;
import java.util.ArrayList;

public class StatsGraph {
    private StatsCategory a;
    private int b;
    private int c;
    private int d;
    private ArrayList e = new ArrayList();

    public StatsGraph(StatsCategory bj2, ArrayList arrayList) {
        this.a = bj2;
        ArrayList<StatsTimeline> arrayList2 = new ArrayList<StatsTimeline>();
        for (LineGraphStyle aa2 : (java.util.Collection<LineGraphStyle>) (java.util.Collection) arrayList) {
            StatsTimeline bi2 = aa2.a.a(bj2);
            arrayList2.add(bi2);
            for (StatsSample bh2 : (java.util.Collection<StatsSample>) (java.util.Collection) bi2) {
                if (bh2.b > this.b) {
                    this.b = bh2.b;
                }
                if (bh2.b < this.c) {
                    this.c = bh2.b;
                }
                if (bh2.a <= this.d) continue;
                this.d = bh2.a;
            }
        }
        this.a(arrayList2);
    }

    private void a(ArrayList arrayList) {  // 02b f/ab.java L50: a(ArrayList)
        int n2;
        int n3 = arrayList.size();
        IntArray ad2 = new IntArray(n3);
        int[] nArray = new int[n3];
        int n4 = 0;
        do {
            if (++n4 > 1000000) {
                throw new RuntimeException("loopIndex: " + n4);
            }
            int n5 = 1;
            for (n2 = 0; n2 < n3; ++n2) {
                StatsTimeline bi2 = (StatsTimeline) arrayList.get(n2);
                if (nArray[n2] >= bi2.size()) continue;
                StatsSample bh2 = (StatsSample) bi2.get(nArray[n2]);
                if (bh2.a > IntArray.a(ad2)) continue;
                ad2.a(n2, bh2.b);
                int n6 = n2;
                nArray[n6] = nArray[n6] + 1;
                n5 = 0;
            }
            n2 = n5;
            int n7 = Integer.MAX_VALUE;
            if (n5 == 0) continue;
            this.e.add(ad2);
            for (int i = 0; i < n3; ++i) {
                StatsTimeline bi3 = (StatsTimeline) arrayList.get(i);
                if (nArray[i] >= bi3.size()) continue;
                StatsSample bh3 = (StatsSample) bi3.get(nArray[i]);
                if (bh3.a >= n7) continue;
                n7 = bh3.a;
                n2 = 0;
            }
            ad2 = new IntArray(n7, ad2);
        } while (n2 == 0);
    }

    static /* synthetic */ int a(StatsGraph ab2) {
        return ab2.b;
    }

    static /* synthetic */ int b(StatsGraph ab2) {
        return ab2.c;
    }

    static /* synthetic */ StatsCategory c(StatsGraph ab2) {
        return ab2.a;
    }

    static /* synthetic */ int d(StatsGraph ab2) {
        return ab2.d;
    }

    static /* synthetic */ ArrayList e(StatsGraph ab2) {
        return ab2.e;
    }
}
