/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bh;
import com.corrodinggames.rts.gameFramework.bi;
import com.corrodinggames.rts.gameFramework.bj;
import com.corrodinggames.rts.gameFramework.f.aa;
import com.corrodinggames.rts.gameFramework.f.ad;
import java.util.ArrayList;

public class ab {
    private bj a;
    private int b;
    private int c;
    private int d;
    private ArrayList e = new ArrayList();

    public ab(bj bj2, ArrayList arrayList) {
        this.a = bj2;
        ArrayList<bi> arrayList2 = new ArrayList<bi>();
        for (aa aa2 : arrayList) {
            bi bi2 = aa2.a.a(bj2);
            arrayList2.add(bi2);
            for (bh bh2 : bi2) {
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

    private void a(ArrayList arrayList) {
        int n2;
        int n3 = arrayList.size();
        ad ad2 = new ad(n3);
        int[] nArray = new int[n3];
        int n4 = 0;
        do {
            if (++n4 > 1000000) {
                throw new RuntimeException("loopIndex: " + n4);
            }
            int n5 = 1;
            for (n2 = 0; n2 < n3; ++n2) {
                bi bi2 = (bi)arrayList.get(n2);
                if (nArray[n2] >= bi2.size()) continue;
                bh bh2 = (bh)bi2.get(nArray[n2]);
                if (bh2.a > ad.a(ad2)) continue;
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
                bi bi3 = (bi)arrayList.get(i);
                if (nArray[i] >= bi3.size()) continue;
                bh bh3 = (bh)bi3.get(nArray[i]);
                if (bh3.a >= n7) continue;
                n7 = bh3.a;
                n2 = 0;
            }
            ad2 = new ad(n7, ad2);
        } while (n2 == 0);
    }

    static /* synthetic */ int a(ab ab2) {
        return ab2.b;
    }

    static /* synthetic */ int b(ab ab2) {
        return ab2.c;
    }

    static /* synthetic */ bj c(ab ab2) {
        return ab2.a;
    }

    static /* synthetic */ int d(ab ab2) {
        return ab2.d;
    }

    static /* synthetic */ ArrayList e(ab ab2) {
        return ab2.e;
    }
}
