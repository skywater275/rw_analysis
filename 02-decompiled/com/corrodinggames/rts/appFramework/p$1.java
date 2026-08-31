/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.gameFramework.j.g;
import java.util.Comparator;

final class p$1
implements Comparator {
    p$1() {
    }

    public int a(g g2) {
        if (g2.d()) {
            return 0;
        }
        if (g2.x && g2.s.equals("chat")) {
            return 1;
        }
        if (g2.a) {
            return 2;
        }
        if (g2.s.equals("battleroom")) {
            if (g2.v != -1 && g2.v < g2.w) {
                if (g2.x) {
                    if (g2.v != 0) {
                        return 3;
                    }
                    return 4;
                }
                if (g2.h && !g2.x) {
                    return 7;
                }
            } else {
                if (g2.x) {
                    return 6;
                }
                if (g2.h && !g2.x) {
                    return 8;
                }
            }
            return 9;
        }
        return 99;
    }

    public int a(g g2, g g3) {
        int n;
        Integer n2 = this.a(g2);
        Integer n3 = this.a(g3);
        if (!g2.g()) {
            n2 = n2 + 20;
        }
        if (!g3.g()) {
            n3 = n3 + 20;
        }
        if ((n = n2.compareTo(n3)) != 0) {
            return n;
        }
        return g2.q.compareTo(g3.q);
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((g)object, (g)object2);
    }
}
