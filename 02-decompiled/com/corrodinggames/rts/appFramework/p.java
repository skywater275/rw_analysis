/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import android.os.Handler;
import com.corrodinggames.rts.appFramework.b;
import com.corrodinggames.rts.appFramework.p$1;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Collections;

public class p
extends b {
    static p c;
    final Handler d;
    private Runnable e;

    public static void l() {
        if (c != null) {
            p.c.d.a(p.c.e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ArrayList m() {
        Object object = n.f;
        synchronized (object) {
            l l2 = l.B();
            ArrayList<g> arrayList = new ArrayList<g>();
            for (g g2 : l2.bX.bi) {
                arrayList.add(g2);
            }
            Collections.sort(arrayList, new p$1());
            return arrayList;
        }
    }
}
