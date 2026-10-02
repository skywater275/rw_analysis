/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.k.l;
import com.corrodinggames.rts.gameFramework.utility.ak;
import java.util.HashMap;

public strictfp final class i {
    private final l q;
    ao a;
    public final int b;
    public final int c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public short[] g;
    public HashMap h;
    public int i;
    public byte[] j;
    public int k = -99;
    public int l = 0;
    public boolean m;
    Point n = new Point();
    public boolean o;
    public int p;

    i(l l2, ao ao2, int n, int n2) {
        this.b = n;
        this.c = n2;
        this.q = l2;
        this.a = ao2;
        this.d = new byte[n * n2];
        this.q.u.add(this);
        this.q.v = this.q.u.toArray(new i[0]);
        this.a();
    }

    void a() {
        this.d();
        this.c(null);
        this.e();
    }

    void a(y y2) {
        double d;
        short s2;
        short s3;
        short s4;
        if (y2 != null) {
            ++this.l;
            if (this.l > 50) {
                if (!this.m) {
                    com.corrodinggames.rts.gameFramework.l.e("buildAndReplaceClearanceCost being skipped");
                }
                this.m = true;
                return;
            }
        }
        if (y2 != null) {
            this.b(y2);
            return;
        }
        long l2 = 0L;
        if (y2 == null) {
            l2 = br.a();
        }
        b b2 = this.q.q;
        byte[] byArray = this.j;
        if (this.j == null) {
            y2 = null;
        }
        this.j = new byte[this.b * this.c];
        short s5 = 0;
        short s6 = 0;
        short s7 = this.q.s;
        short s8 = this.q.t;
        if (y2 != null) {
            if (byArray != null) {
                com.corrodinggames.rts.gameFramework.f.a(byArray, this.j);
            }
            b2.a(y2.eo, y2.ep);
            Rect rect = y2.cc();
            s4 = (short)b2.T;
            s3 = (short)b2.U;
            s5 = (short)(s4 - 5 + rect.a);
            s6 = (short)(s3 - 5 + rect.b);
            s7 = (short)(s4 + 5 + rect.c);
            s8 = (short)(s3 + 5 + rect.d);
        }
        if (s5 < 0) {
            s5 = 0;
        }
        if (s6 < 0) {
            s6 = 0;
        }
        if (s7 > this.q.s) {
            s7 = this.q.s;
        }
        if (s8 > this.q.t) {
            s8 = this.q.t;
        }
        for (s2 = s5; s2 < s7; s2 = (short)(s2 + 1)) {
            for (s4 = s6; s4 < s8; s4 = (short)(s4 + 1)) {
                s3 = 0;
                if (this.d[s2 * this.c + s4] == -1) {
                    s3 = 1;
                }
                if (this.e[s2 * this.c + s4] == -1) {
                    s3 = 1;
                }
                this.j[s2 * this.c + s4] = s3 != 0 ? 0 : 4;
            }
        }
        for (s2 = s5; s2 < s7; s2 = (short)(s2 + 1)) {
            for (s4 = s6; s4 < s8; s4 = (short)(s4 + 1)) {
                if (this.j[s2 * this.c + s4] != 0) continue;
                this.a(b2, s2, s4, this.j);
            }
        }
        for (s2 = s5; s2 < s7; s2 = (short)(s2 + 1)) {
            this.a(b2, s2, (short)-1, this.j);
            this.a(b2, s2, (short)(this.q.t + 1), this.j);
        }
        for (s2 = s6; s2 < s8; s2 = (short)(s2 + 1)) {
            this.a(b2, (short)-1, s2, this.j);
            this.a(b2, (short)(this.q.s + 1), s2, this.j);
        }
        if (y2 == null && (d = (double)br.a(l2)) > 30.0) {
            com.corrodinggames.rts.gameFramework.l.e("buildAndReplaceClearanceCostNew took:" + br.a(d) + " for:" + (Object)((Object)this.a));
        }
    }

    final void a(b b2, short s2, short s3, byte[] byArray) {
        int n2 = s2 - 3;
        int n3 = s3 - 3;
        int n4 = s2 + 3;
        int n5 = s3 + 3;
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 > b2.C - 1) {
            n4 = b2.C - 1;
        }
        if (n5 > b2.D - 1) {
            n5 = b2.D - 1;
        }
        for (int i2 = n2; i2 <= n4; ++i2) {
            for (int i3 = n3; i3 <= n5; ++i3) {
                int n6;
                byte by = byArray[i2 * this.c + i3];
                if (by == 0 || (n6 = com.corrodinggames.rts.gameFramework.f.a(s2, (int)s3, i2, i3)) >= by) continue;
                byArray[i2 * this.c + i3] = (byte)n6;
            }
        }
    }

    void b(y y2) {
        short s2;
        long l2 = 0L;
        if (y2 == null) {
            l2 = br.a();
        }
        b b2 = this.q.q;
        byte[] byArray = this.j;
        if (this.j == null) {
            y2 = null;
        }
        this.j = new byte[this.b * this.c];
        short s3 = 0;
        short s4 = 0;
        short s5 = this.q.s;
        short s6 = this.q.t;
        if (y2 != null) {
            if (byArray != null) {
                com.corrodinggames.rts.gameFramework.f.a(byArray, this.j);
            }
            b2.a(y2.eo, y2.ep);
            Rect rect = y2.cc();
            s2 = (short)b2.T;
            short s7 = (short)b2.U;
            s3 = (short)(s2 - 5 + rect.a);
            s4 = (short)(s7 - 5 + rect.b);
            s5 = (short)(s2 + 5 + rect.c);
            s6 = (short)(s7 + 5 + rect.d);
        }
        if (s3 < 0) {
            s3 = 0;
        }
        if (s4 < 0) {
            s4 = 0;
        }
        if (s5 > this.q.s) {
            s5 = this.q.s;
        }
        if (s6 > this.q.t) {
            s6 = this.q.t;
        }
        for (short s8 = s3; s8 < s5; s8 = (short)(s8 + 1)) {
            for (s2 = s4; s2 < s6; s2 = (short)(s2 + 1)) {
                this.j[s8 * this.c + s2] = this.a(b2, s8, s2);
            }
        }
        if (y2 == null) {
            double d = br.a(l2);
            com.corrodinggames.rts.gameFramework.l.e("buildAndReplaceClearanceCost took:" + br.a(d) + " for:" + (Object)((Object)this.a));
        }
    }

    final byte a(b b2, short s2, short s3) {
        if (this.d[s2 * this.c + s3] == -1) {
            return 0;
        }
        int n2 = s2 - 3;
        int n3 = s3 - 3;
        int n4 = s2 + 3;
        int n5 = s3 + 3;
        int n6 = 4;
        for (int i2 = n2; i2 <= n4; ++i2) {
            for (int i3 = n3; i3 <= n5; ++i3) {
                int n7;
                boolean bl = false;
                if (b2.c(i2, i3)) {
                    if (this.d[i2 * this.c + i3] == -1) {
                        bl = true;
                    }
                    if (this.e[i2 * this.c + i3] == -1) {
                        bl = true;
                    }
                } else {
                    bl = true;
                }
                if (!bl || (n7 = com.corrodinggames.rts.gameFramework.f.a(s2, (int)s3, i2, i3)) >= n6) continue;
                n6 = n7;
            }
        }
        return (byte)n6;
    }

    void b() {
        short s2 = this.b;
        short s3 = this.c;
        this.g = new short[s2 * s3];
        this.h = new HashMap();
        short s4 = 1;
        for (short s5 = 0; s5 < s2; s5 = (short)((short)(s5 + 1))) {
            for (short s6 = 0; s6 < s3; s6 = (short)((short)(s6 + 1))) {
                if (this.g[s5 * s3 + s6] != 0) continue;
                if (s4 <= 0) {
                    Log.d("RustedWarfare", "warning buildIsolatedGroups looped, ending");
                    return;
                }
                int n2 = this.a(s5, s6, s4);
                if (n2 <= 0) continue;
                this.h.put(s4, n2);
                if (this.i < n2) {
                    this.i = n2;
                }
                s4 = (short)(s4 + 1);
            }
        }
    }

    int a(short s2, short s3, short s4) {
        int n2 = this.c;
        b b2 = this.q.q;
        short[] sArray = this.g;
        byte[] byArray = this.d;
        if (byArray[s2 * n2 + s3] == -1) {
            sArray[s2 * n2 + s3] = -1;
            return 0;
        }
        if (s4 == 0) {
            throw new RuntimeException("id cannot be 0 is will cause can endless loop");
        }
        int n3 = 0;
        com.corrodinggames.rts.gameFramework.utility.g g2 = new com.corrodinggames.rts.gameFramework.utility.g();
        ak ak2 = new ak(s2, s3);
        g2.add(ak2);
        while (!g2.isEmpty()) {
            int n4;
            ak ak3 = (ak)g2.a();
            short s5 = ak3.a;
            short s6 = ak3.b;
            if (!b2.c(s5, s6) || sArray[n4 = s5 * n2 + s6] != 0 || byArray[n4] == -1) continue;
            sArray[n4] = s4;
            ++n3;
            g2.add(new ak((short)(s5 - 1), s6));
            g2.add(new ak((short)(s5 + 1), s6));
            g2.add(new ak(s5, (short)(s6 - 1)));
            g2.add(new ak(s5, (short)(s6 + 1)));
        }
        return n3;
    }

    boolean c() {
        return !this.a.equals((Object)ao.d) && !this.a.equals((Object)ao.a);
    }

    public static int a(b b2) {
        e e2 = b2.u;
        int n2 = 0;
        for (int i2 = 0; i2 < e2.n; ++i2) {
            for (int i3 = 0; i3 < e2.o; ++i3) {
                g g2 = e2.a(i2, i3);
                if (g2 == null) continue;
                int n3 = 0;
                n3 += g2.e ? 1 : 0;
                n3 += g2.h ? 2 : 0;
                n3 += g2.k ? 4 : 0;
                n3 += g2.g ? 8 : 0;
                n2 += (n3 += g2.f ? 16 : 0) * (i2 + i3);
            }
        }
        return n2;
    }

    void d() {
        int n2;
        int n3;
        b b2 = this.q.q;
        byte[] byArray = this.d;
        short[] sArray = b2.u.a();
        g[] gArray = b2.B;
        ao ao2 = this.a;
        int n4 = this.b;
        int n5 = this.c;
        boolean bl = this.c();
        if (!bl) {
            return;
        }
        boolean bl2 = ao2.equals((Object)ao.e) || ao2.equals((Object)ao.f) || ao2.equals((Object)ao.h);
        boolean bl3 = ao2.equals((Object)ao.f) || ao2.equals((Object)ao.g) || ao2.equals((Object)ao.h);
        boolean bl4 = ao2.equals((Object)ao.g) || ao2.equals((Object)ao.h);
        boolean bl5 = false;
        for (n3 = 0; n3 < n4; ++n3) {
            for (n2 = 0; n2 < n5; ++n2) {
                g g2;
                int n6 = n3 * n5 + n2;
                byArray[n6] = 0;
                short s2 = sArray[n6];
                g g3 = gArray[s2];
                if (g3 != null) {
                    if (g3.e && !bl2) {
                        byArray[n6] = -1;
                    }
                    if (g3.h && !bl3) {
                        byArray[n6] = -1;
                    }
                    if (g3.k && !bl4) {
                        byArray[n6] = -1;
                    }
                    if (g3.g && !bl5) {
                        byArray[n6] = -1;
                    }
                    if (ao2 == ao.e && !g3.e && !g3.f) {
                        byArray[n6] = -1;
                    }
                }
                if ((g2 = b2.e(n3, n2)) != null) {
                    if (ao2 == ao.b && g2.i) {
                        byArray[n6] = -1;
                    }
                    if (g2.k && !bl4) {
                        byArray[n6] = -1;
                    }
                    if (byArray[n6] == 0) {
                        byArray[n6] = g2.j;
                    }
                }
                if (g3 == null || byArray[n6] != 0) continue;
                byArray[n6] = g3.j;
            }
        }
        if (b2.x != null) {
            for (n3 = 0; n3 < n4; ++n3) {
                for (n2 = 0; n2 < n5; ++n2) {
                    g g4 = b2.x.a(n3, n2);
                    if (g4 == null) continue;
                    byArray[n3 * n5 + n2] = 0;
                    if (g4.e && !bl2) {
                        byArray[n3 * n5 + n2] = -1;
                    }
                    if (g4.h && !bl3) {
                        byArray[n3 * n5 + n2] = -1;
                    }
                    if (g4.k && !bl4) {
                        byArray[n3 * n5 + n2] = -1;
                    }
                    if (g4.g && !bl5) {
                        byArray[n3 * n5 + n2] = -1;
                    }
                    if (byArray[n3 * n5 + n2] == 0) {
                        byArray[n3 * n5 + n2] = g4.j;
                    }
                    if (ao2 != ao.e || g4.e || g4.f) continue;
                    byArray[n3 * n5 + n2] = -1;
                }
            }
        }
    }

    public void c(y y2) {
        if (y2 != null) {
            ++this.p;
            if (this.p > 50) {
                this.o = true;
                return;
            }
        }
        long l2 = com.corrodinggames.rts.gameFramework.l.V();
        byte[] byArray = this.e = new byte[this.b * this.c];
        if (this.a.equals((Object)ao.d)) {
            return;
        }
        b b2 = this.q.q;
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!am2.bI() || am2.bV) continue;
            Point point = am2.a(b2, this.n);
            int n3 = point.a;
            int n4 = point.b;
            Rect rect = this.a.equals((Object)ao.c) ? am2.cd() : am2.cc();
            for (int i3 = n3 + rect.a; i3 <= n3 + rect.c; ++i3) {
                for (int i4 = n4 + rect.b; i4 <= n4 + rect.d; ++i4) {
                    if (!b2.c(i3, i4)) continue;
                    byArray[i3 * this.c + i4] = -1;
                }
            }
        }
    }

    public void e() {
        long l2 = com.corrodinggames.rts.gameFramework.l.V();
        int n2 = this.c;
        this.f = new byte[this.b * n2];
        if (this.a.equals((Object)ao.d)) {
            return;
        }
        b b2 = this.q.q;
        int n3 = b2.p;
        int n4 = b2.q;
        am[] amArray = am.bE.a();
        int n5 = am.bE.size();
        for (int i2 = 0; i2 < n5; ++i2) {
            am am2 = amArray[i2];
            if (!am2.bT || am2.bI() || am2.cK || am2 instanceof al || am2.bV || am2.i() || am2.cN != null || am2.Q()) continue;
            int n6 = 2;
            b2.a(am2.eo, am2.ep);
            int n7 = b2.T;
            int n8 = b2.U;
            float f2 = am2.cj + 5.0f;
            float f3 = am2.cj + 10.0f;
            if (f3 < 10.0f) {
                n6 = 0;
            } else if (f3 < 20.0f) {
                n6 = 1;
            }
            for (int i3 = n7 - n6; i3 <= n7 + n6; ++i3) {
                for (int i4 = n8 - n6; i4 <= n8 + n6; ++i4) {
                    if (!b2.c(i3, i4)) continue;
                    b2.a(i3, i4);
                    float f4 = b2.T + n3;
                    float f5 = b2.U + n4;
                    float f6 = com.corrodinggames.rts.gameFramework.f.a(f4, f5, am2.eo, am2.ep);
                    int n9 = 6;
                    int n10 = i3 * n2 + i4;
                    if (f6 < f2 * f2) {
                        int n11 = n10;
                        this.f[n11] = (byte)(this.f[n11] + n9);
                    } else if (f6 < f3 * f3) {
                        int n12 = n10;
                        this.f[n12] = (byte)((double)this.f[n12] + (double)n9 * 0.333);
                    }
                    if (this.f[n10] >= -1) continue;
                    this.f[n10] = 127;
                }
            }
        }
    }

    public void a(boolean bl) {
        if (!bl) {
            return;
        }
        if (this.m) {
            this.l = 0;
            this.m = false;
            this.c(null);
            if (this.j != null) {
                this.a(null);
            }
        }
        this.l = 0;
    }
}
