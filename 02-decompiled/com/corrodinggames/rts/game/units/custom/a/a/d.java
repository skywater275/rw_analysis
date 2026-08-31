/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.o;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class d
extends a {
    boolean a;
    boolean b;
    o c;
    o d;
    boolean e;
    int f = Integer.MIN_VALUE;
    int g = Integer.MIN_VALUE;

    public static void a(l l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.a.d d2, String string3, boolean bl2) {
        boolean bl3 = ab2.a(string, string2 + "finishPlayingLastAnimation", (Boolean)false);
        boolean bl4 = ab2.a(string, string2 + "stopLastAnimation", (Boolean)false);
        o o2 = l2.a(ab2.b(string, string2 + "playAnimation", (String)null), (o)null);
        o o3 = l2.a(ab2.b(string, string2 + "playAnimationIfNotPlaying", (String)null), (o)null);
        if (o2 != null && o3 != null) {
            throw new RuntimeException("Cannot use playAnimation and playAnimationIfNotPlaying at same time");
        }
        if (bl4 && bl3) {
            throw new RuntimeException("Cannot use stopLastAnimation and finishPlayingLastAnimation at same time");
        }
        if (o2 != null || o3 != null || bl3 || bl4) {
            d d3 = new d();
            d3.a = bl3;
            d3.b = bl4;
            d3.c = o2;
            d3.d = o3;
            d3.e = ab2.a(string, string2 + "playAnimation_lowPriority", (Boolean)false);
            d2.ac.add(d3);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        int n3;
        if (this.a) {
            j2.b.b();
        }
        if (this.b) {
            j2.b.a();
        }
        if (this.c != null) {
            n3 = 15;
            if (this.e) {
                n3 = 4;
            }
            j2.b.a(this.c.b(), n3, true);
        }
        if (this.d != null) {
            f f2;
            n3 = 15;
            if (this.e) {
                n3 = 4;
            }
            if (!j2.b.a(f2 = this.d.b())) {
                j2.b.a(this.d.b(), n3, true);
            }
        }
        return true;
    }
}
