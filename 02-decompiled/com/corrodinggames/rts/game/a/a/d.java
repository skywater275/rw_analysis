/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.a.b;
import com.corrodinggames.rts.game.a.a.c;
import com.corrodinggames.rts.game.a.f;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.game.units.y;

public class d
extends c {
    public final boolean b = true;
    static final g c = g.c("nukeLauncher");

    @Override
    public b a() {
        return com.corrodinggames.rts.game.a.a.b.b;
    }

    @Override
    public boolean c(a a2, y y2) {
        return this.a(y2);
    }

    public PointF d(a a2, y y2) {
        return a2.at();
    }

    public void e(a a2, y y2) {
        s s2 = f.a(a2, y2, e.f);
        if (s2 != null) {
            if (s2.b(y2) && s2.a((am)y2, false)) {
                PointF pointF = this.d(a2, y2);
                if (pointF != null) {
                    a2.c("nuke: launching at:" + pointF.a + ", " + pointF.b);
                    a2.a(y2, s2, pointF, null);
                } else {
                    a2.c("nuke: no target");
                }
            } else {
                a2.c("nuke: not ready");
            }
        }
    }

    public void f(a a2, y y2) {
        s s2;
        if (y2 instanceof l && ((l)((Object)y2)).dy() && (s2 = f.a(a2, y2, e.g)) != null && a2.a(s2.B(), (am)y2)) {
            a2.c("ai nuke building");
            a2.a(y2, s2);
        }
    }

    public boolean a(y y2) {
        return f.a(y2, c);
    }

    @Override
    public void b(float f2, a a2) {
        am[] amArray = this.a.a();
        int n2 = this.a.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            y y2 = (y)amArray[i2];
            this.f(a2, y2);
            this.e(a2, y2);
        }
    }
}
