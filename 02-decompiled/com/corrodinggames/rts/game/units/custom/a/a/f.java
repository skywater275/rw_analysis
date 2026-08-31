/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.regex.Pattern;

public class f
extends a {
    aj a;
    aj b;
    aj c;
    aj d;
    aj e;
    aj f;
    static final Pattern g = Pattern.compile("%\\{([^\\]]*?)\\}");

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        aj aj2 = ag.a(l2, ab2, string, "showMessageToPlayer", null);
        aj aj3 = ag.a(l2, ab2, string, "showMessageToAllPlayers", null);
        aj aj4 = ag.a(l2, ab2, string, "showMessageToAllEnemyPlayers", null);
        aj aj5 = ag.a(l2, ab2, string, "showQuickWarLogToPlayer", null);
        aj aj6 = ag.a(l2, ab2, string, "showQuickWarLogToAllPlayers", null);
        aj aj7 = ag.a(l2, ab2, string, "debugMessage", null);
        if (aj2 != null || aj3 != null || aj4 != null || aj5 != null || aj6 != null || aj7 != null) {
            f f2 = new f();
            f2.a = aj2;
            f2.b = aj3;
            f2.c = aj4;
            f2.d = aj5;
            f2.e = aj6;
            f2.f = aj7;
            d2.ac.add(f2);
        }
    }

    public String a(j j2, String string) {
        if (string == null) {
            string = null;
        }
        return string;
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.a != null && j2.bX == l2.bs) {
            ad.a(null, this.a(j2, this.a.b(j2)));
        }
        if (this.b != null) {
            ad.a(null, this.a(j2, this.b.b(j2)));
        }
        if (this.c != null && l2.bs != null && j2.bX.c(l2.bs)) {
            ad.a(null, this.a(j2, this.c.b(j2)));
        }
        if (this.d != null && j2.bX == l2.bs) {
            l2.bS.i.a(this.a(j2, this.d.b(j2)));
        }
        if (this.e != null) {
            l2.bS.i.a(this.a(j2, this.e.b(j2)));
        }
        if (this.f != null && l2.bv && l2.bl) {
            String string = j2.dt().i() + "(" + j2.eh + ") Debug: " + this.a(j2, this.f.b(j2));
            ad.a(null, string);
        }
        return true;
    }
}
