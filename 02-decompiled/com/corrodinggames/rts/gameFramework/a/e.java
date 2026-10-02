/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.a;

import android.content.Context;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.a.a;
import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.util.ArrayList;

public class e {
    ArrayList a = new ArrayList();
    public boolean b;
    public static h c = new a();
    public static i d;
    public static i e;
    public static i f;
    public static i g;
    public static i h;
    public static i i;
    public static i j;
    public static i k;
    public static i l;
    public static i m;
    public static i n;
    public static i o;
    public static i p;
    public static i q;
    public static i r;
    public static i s;
    public static i t;
    public static i u;
    public static i v;
    public static i w;
    public static i x;
    public static i y;
    public static i z;
    public static i A;
    public static i B;
    public static i C;
    public static i D;
    public static i E;
    public static i F;

    public boolean a(i i2, float f) {
        if (this.a.contains(i2)) {
            return false;
        }
        this.a.add(i2);
        return true;
    }

    public boolean a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return this.a(l2.bQ.masterVolume * l2.bQ.gameVolume);
    }

    public boolean a(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (f2 < 0.01f) {
            return false;
        }
        if (this.b) {
            return false;
        }
        return l2.bQ.enableSounds;
    }

    public static void b() {
    }

    public void a(Context context) {
        c.a(context);
        e = c.a(R$raw.attack);
        com.corrodinggames.rts.gameFramework.a.e.e.d = 0.2f;
        d = c.a(R$raw.attack2);
        f = c.a(R$raw.move);
        g = c.a(R$raw.click);
        h = c.a(R$raw.click_add);
        i = c.a(R$raw.click_remove);
        j = c.a(R$raw.warning);
        k = c.a(R$raw.message);
        m = c.a(R$raw.missile_fire);
        n = c.a(R$raw.missile_hit);
        o = c.a(R$raw.unit_explode);
        p = c.a(R$raw.buiding_explode);
        q = c.a(R$raw.tank_firing);
        r = c.a(R$raw.cannon_firing);
        s = c.a(R$raw.gun_fire);
        x = c.a(R$raw.lighting_burst);
        y = c.a(R$raw.plasma_fire);
        z = c.a(R$raw.plasma_fire2);
        t = c.a(R$raw.firing3);
        u = c.a(R$raw.firing4);
        v = c.a(R$raw.large_gun_fire1);
        w = c.a(R$raw.large_gun_fire2);
        A = c.a(R$raw.bug_die);
        B = c.a(R$raw.bug_attack);
        l = c.a(R$raw.interface_error);
        C = c.a(R$raw.nuke_explode);
        D = c.a(R$raw.nuke_launch);
        E = c.a(R$raw.laser_deflect);
        F = c.a(R$raw.laser_deflect2);
        c.a();
    }

    public i a(String string) {
        i i2 = (i)com.corrodinggames.rts.gameFramework.a.e.c.h.get(string);
        if (i2 == null) {
            throw new RuntimeException("Could not find sound:" + string);
        }
        return i2;
    }

    public void b(i i2, float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        f2 *= l2.bQ.masterVolume * l2.bQ.interfaceVolume;
        if (!this.a(f2 *= i2.d)) {
            return;
        }
        if ((double)f2 < 0.01) {
            return;
        }
        if (!this.a(i2, f2)) {
            return;
        }
        if (l2.aq) {
            f2 /= 20.0f;
        }
        i2.a(f2, f2, 1, 0, 1.0f);
    }

    public void c(i i2, float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        f2 *= l2.bQ.masterVolume * l2.bQ.gameVolume;
        if (!this.a(f2 *= i2.d)) {
            return;
        }
        if (l2.aq) {
            f2 /= 20.0f;
        }
        if (!this.a(i2, f2)) {
            return;
        }
        i2.a(f2, f2, 1, 0, 1.0f);
    }

    public void a(i i2, float f2, float f3, float f4) {
        this.a(i2, f2, 1.0f, f3, f4);
    }

    public void a(i i2, float f2, float f3, float f4, float f5) {
        float f6;
        if (!this.a()) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.aq) {
            f2 /= 20.0f;
        }
        if (l2.cb.j() && (double)l2.bt > 1.5) {
            f2 /= l2.bt;
        }
        int n2 = (int)(l2.cw + l2.cI);
        int n3 = (int)(l2.cx + l2.cJ);
        float f7 = com.corrodinggames.rts.gameFramework.f.a((float)n2, (float)n3, f4, f5);
        float f8 = l2.cI * 1.72f;
        if ((double)l2.cX < 0.5) {
            f2 *= 4.0f;
            f2 *= l2.cX * l2.cX;
        }
        if (f2 <= 1.0f && !i2.f && f7 > f8 * f8) {
            return;
        }
        float f9 = (float)Math.sqrt(f7);
        float f10 = 1.0f;
        if (f9 > l2.cI) {
            f10 = 1.0f - (f9 - l2.cI) / l2.cI;
        }
        if ((double)(f6 = f10 * f2) <= 0.05 && !i2.f) {
            return;
        }
        if (f6 > 1.0f) {
            f6 = 1.0f;
        }
        f6 *= l2.bQ.masterVolume * l2.bQ.gameVolume;
        if (!this.a(i2, f6 *= i2.d)) {
            return;
        }
        i2.a(f6, f6, 1, 0, f3);
    }

    public i a(String string, j j2, boolean bl) {
        try {
            return c.a(string, j2, bl);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.f, (Throwable)outOfMemoryError);
            return com.corrodinggames.rts.gameFramework.a.f.b();
        }
    }

    public i b(String string) {
        return com.corrodinggames.rts.gameFramework.a.f.a(string);
    }

    public void b(float f2) {
        this.a.clear();
    }
}
