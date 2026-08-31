/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.platform;
import com.corrodinggames.rts.gameFramework.OSEnum;

import android.content.Context;
import com.corrodinggames.rts.R$raw;
import com.corrodinggames.rts.gameFramework.platform.AndroidSoundFactory;
import com.corrodinggames.rts.gameFramework.platform.SoundFactory;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import com.corrodinggames.rts.gameFramework.utility.AssetStream;
import java.util.ArrayList;

public class SoundRegistry {
    ArrayList a = new ArrayList();
    public boolean b;
    public static SoundFactory c = new AndroidSoundFactory();
    public static Sound d;
    public static Sound e;
    public static Sound f;
    public static Sound g;
    public static Sound h;
    public static Sound i;
    public static Sound j;
    public static Sound k;
    public static Sound l;
    public static Sound m;
    public static Sound n;
    public static Sound o;
    public static Sound p;
    public static Sound q;
    public static Sound r;
    public static Sound s;
    public static Sound t;
    public static Sound u;
    public static Sound v;
    public static Sound w;
    public static Sound x;
    public static Sound y;
    public static Sound z;
    public static Sound A;
    public static Sound B;
    public static Sound C;
    public static Sound D;
    public static Sound E;
    public static Sound F;

    public boolean a(Sound i2, float f) {
        if (this.a.contains(i2)) {
            return false;
        }
        this.a.add(i2);
        return true;
    }

    public boolean a() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        return this.a(l2.bQ.masterVolume * l2.bQ.gameVolume);
    }

    public boolean a(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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
        e.d = 0.2f;
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

    public Sound a(String string) {
        Sound i2 = (Sound) c.h.get(string);
        if (i2 == null) {
            throw new RuntimeException("Could not find sound:" + string);
        }
        return i2;
    }

    public void b(Sound i2, float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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

    public void c(Sound i2, float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
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

    public void a(Sound i2, float f2, float f3, float f4) {
        this.a(i2, f2, 1.0f, f3, f4);
    }

    public void a(Sound i2, float f2, float f3, float f4, float f5) {
        float f6;
        if (!this.a()) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.aq) {
            f2 /= 20.0f;
        }
        if (l2.cb.j() && (double)l2.bt > 1.5) {
            f2 /= l2.bt;
        }
        int n2 = (int)(l2.cw + l2.cI);
        int n3 = (int)(l2.cx + l2.cJ);
        float f7 = GameUtils.a((float)n2, (float)n3, f4, f5);
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

    public Sound a(String string, AssetStream j2, boolean bl) {
        try {
            return c.a(string, j2, bl);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.GlobalState.a(com.corrodinggames.rts.gameFramework.ResourceDomainEnum.f, (Throwable)outOfMemoryError);
            return com.corrodinggames.rts.gameFramework.platform.NullSoundFactory.b();
        }
    }

    public Sound b(String string) {
        return com.corrodinggames.rts.gameFramework.platform.NullSoundFactory.a(string);
    }

    public void b(float f2) {
        this.a.clear();
    }
}
