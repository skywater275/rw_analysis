/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.e.f$1;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.Collections;

public strictfp final class f {
    public static final f a = new f().a();
    public final m b = new m();
    boolean c;

    public f a() {
        this.c = true;
        return this;
    }

    public void b() {
        this.b.clear();
    }

    public boolean c() {
        if (this.b.a == 0) {
            return true;
        }
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.b == 0.0) continue;
            return false;
        }
        return true;
    }

    public double a(a a2) {
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a != a2) continue;
            return e2.b;
        }
        return 0.0;
    }

    public double b(a a2) {
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a == a2) {
                n2 = (int)((double)n2 + e2.b);
            }
            if (e2.a.v != a2) continue;
            n2 = (int)((double)n2 + e2.b);
        }
        return n2;
    }

    public void a(f f2) {
        this.b();
        this.b(f2);
    }

    public void a(a a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a != a2) continue;
            e2.b = d;
            return;
        }
        e e3 = new e(a2);
        e3.b = d;
        this.b.add(e3);
    }

    public void a(double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            e2.b *= d;
        }
    }

    public void b(a a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        if (d == 0.0) {
            return;
        }
        m m2 = this.b;
        int n = m2.a;
        Object[] objectArray = m2.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a != a2) continue;
            e2.b += d;
            return;
        }
        e e3 = new e(a2);
        e3.b = d;
        m2.add(e3);
    }

    public void c(a a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        if (d == 0.0) {
            return;
        }
        m m2 = this.b;
        int n = m2.a;
        Object[] objectArray = m2.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a != a2) continue;
            e2.b += d;
            return;
        }
        e e3 = new e(a2);
        e3.b = d;
        m2.add(e3);
    }

    public void d(a a2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        this.b(a2, -d);
    }

    public void a(b b2, double d, double d2) {
        if ((double)b2.b >= d && (double)b2.b <= d2) {
            this.c(com.corrodinggames.rts.game.units.custom.e.a.D, b2.b);
        }
        this.a(b2.k, d, d2);
    }

    public void b(b b2, double d, double d2) {
        if ((double)b2.b >= d && (double)b2.b <= d2) {
            this.c(com.corrodinggames.rts.game.units.custom.e.a.D, -b2.b);
        }
        this.b(b2.k, d, d2);
    }

    public void a(b b2) {
        this.c(com.corrodinggames.rts.game.units.custom.e.a.D, b2.b);
        this.b(b2.k);
    }

    public void b(f f2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            this.b(e2.a, e2.b);
        }
    }

    public void a(f f2, double d, double d2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (!(e2.b >= d) || !(e2.b <= d2)) continue;
            this.b(e2.a, e2.b);
        }
    }

    public void a(f f2, double d) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            this.b(e2.a, e2.b * d);
        }
    }

    public void c(f f2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            this.d(e2.a, e2.b);
        }
    }

    public void b(f f2, double d, double d2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        int n = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n; ++i) {
            e e2 = (e)objectArray[i];
            if (!(e2.b >= d) || !(e2.b <= d2)) continue;
            this.d(e2.a, e2.b);
        }
    }

    public static f a(f f2, f f3) {
        f f4 = new f();
        f4.b(f2);
        f4.b(f3);
        return f4;
    }

    public static f b(f f2, f f3) {
        f f4 = new f();
        f4.b(f2);
        f4.c(f3);
        return f4;
    }

    public static f b(f f2, double d) {
        f f3 = new f();
        f3.a(f2, d);
        return f3;
    }

    public static f d(f f2) {
        f f3 = new f();
        f3.b(f2);
        return f3;
    }

    public static int a(f f2, am am2) {
        int n2 = 9999;
        int n3 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n3; ++i) {
            e e2 = (e)objectArray[i];
            if (!(e2.b > 0.0)) continue;
            double d = e2.a.t ? am2.bX.c(e2.a) : am2.a(e2.a);
            int n4 = (int)(d / e2.b);
            n2 = com.corrodinggames.rts.gameFramework.f.c(n2, n4);
        }
        return n2;
    }

    public static boolean b(f f2, am am2) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            double d = e2.a.t ? am2.bX.c(e2.a) : am2.a(e2.a);
            if (!(e2.b > d)) continue;
            return false;
        }
        return true;
    }

    public static boolean a(f f2, am am2, double d) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            double d2 = e2.a.t ? am2.bX.c(e2.a) : am2.a(e2.a);
            if (!(e2.b * d > d2)) continue;
            return false;
        }
        return true;
    }

    public static void c(f f2, am am2) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            f f3;
            e e2 = (e)objectArray[i];
            if (e2.a.t) {
                f3 = am2.bX.V();
                f3.d(e2.a, e2.b);
                continue;
            }
            f3 = am2.df();
            f3.d(e2.a, e2.b);
        }
    }

    public static void b(f f2, am am2, double d) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            f f3;
            e e2 = (e)objectArray[i];
            if (e2.a.t) {
                f3 = am2.bX.V();
                f3.d(e2.a, e2.b * d);
                continue;
            }
            f3 = am2.df();
            f3.d(e2.a, e2.b * d);
        }
    }

    public static void d(f f2, am am2) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            f f3;
            e e2 = (e)objectArray[i];
            if (e2.a.t) {
                f3 = am2.bX.V();
                f3.b(e2.a, e2.b);
                continue;
            }
            f3 = am2.df();
            f3.b(e2.a, e2.b);
        }
    }

    public static void c(f f2, am am2, double d) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            f f3;
            e e2 = (e)objectArray[i];
            if (e2.a.t) {
                f3 = am2.bX.V();
                f3.b(e2.a, e2.b * d);
                continue;
            }
            f3 = am2.df();
            f3.b(e2.a, e2.b * d);
        }
    }

    public static boolean a(f f2, am am2, am am3) {
        boolean bl = false;
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            double d;
            e e2 = (e)objectArray[i];
            a a2 = e2.a;
            double d2 = e2.b;
            if (d2 == 0.0) continue;
            double d3 = a2.a(am2);
            double d4 = a2.a(am3);
            if (d2 >= 0.0) {
                if (d3 <= 0.0) continue;
                d = com.corrodinggames.rts.gameFramework.f.a(d3, d2);
                a2.b(am2, -d);
                a2.b(am3, d);
                bl = true;
                continue;
            }
            if (d4 <= 0.0) continue;
            d2 = -d2;
            d = com.corrodinggames.rts.gameFramework.f.a(d4, d2);
            a2.b(am3, -d);
            a2.b(am2, d);
            bl = true;
        }
        return bl;
    }

    public String a(boolean bl, boolean bl2, int n2, boolean bl3, boolean bl4) {
        ae ae2 = new ae();
        this.a(ae2, bl, bl2, n2, bl3, bl4, null, 0);
        return ae2.a();
    }

    public void a(ae ae2, boolean bl, boolean bl2, int n2, boolean bl3, boolean bl4, am am2, int n3) {
        int n4 = this.b.a;
        if (n4 == 0) {
            return;
        }
        String string = bl ? "\n" : " | ";
        int n5 = 0;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n4; ++i) {
            double d;
            e e2 = (e)objectArray[i];
            if (!(e2.b > 0.0) && !bl4 || n5 >= n2) continue;
            a a2 = e2.a;
            if (!bl3 && a2.a()) continue;
            boolean bl5 = false;
            if (a2.y != null && a2.z) {
                bl5 = true;
                int n6 = ae2.c() - 2;
                if (n6 < 2) {
                    n6 = 2;
                }
                ae2.a(a2.y, n6 * 3, n6);
            }
            String string2 = a2.a(e2.b, false, bl5) + string;
            boolean bl6 = false;
            int n7 = 0;
            if (a2.m != null && a2.n) {
                bl6 = true;
                n7 = a2.m;
            }
            if (am2 != null && (d = a2.a(am2)) < e2.b) {
                bl6 = true;
                n7 = n3;
            }
            if (bl6) {
                ae2.a(string2, n7);
            } else {
                ae2.b(string2);
            }
            ++n5;
        }
    }

    public void a(as as2) {
        if (this.b.a == 0) {
            as2.c(-1);
            return;
        }
        as2.c(0);
        as2.a((short)this.b.a);
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            as2.c(e2.a.b);
            as2.a(e2.b);
        }
    }

    public void a(k k2) {
        if (this.c) {
            throw new RuntimeException("StoredResources are locked");
        }
        byte by = k2.d();
        if (by == -1) {
            return;
        }
        int n2 = k2.v();
        this.b.clear();
        for (int i = 0; i < n2; ++i) {
            a a2 = com.corrodinggames.rts.game.units.custom.e.a.b(k2.l());
            double d = k2.h();
            if (a2 == null || d == 0.0) continue;
            e e2 = new e(a2, d);
            this.b.add(e2);
        }
    }

    public int d() {
        int n2 = 0;
        int n3 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n3; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.b == 0.0) continue;
            ++n2;
        }
        return n2;
    }

    public boolean e(f f2) {
        if (this.d() != f2.d()) {
            return false;
        }
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            double d = f2.a(e2.a);
            if (com.corrodinggames.rts.gameFramework.f.b(e2.b, d)) continue;
            return false;
        }
        return true;
    }

    public boolean f(f f2) {
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            double d;
            e e2 = (e)objectArray[i];
            if (!(e2.b > 0.0) || !((d = f2.b(e2.a)) > 0.0)) continue;
            return true;
        }
        return false;
    }

    public f a(am am2) {
        f f2 = new f();
        int n2 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            double d = e2.a.t ? am2.bX.c(e2.a) : am2.a(e2.a);
            if (!(d < e2.b)) continue;
            double d2 = e2.b - d;
            f2.b(e2.a, d2);
        }
        if (f2.c()) {
            return a;
        }
        return f2;
    }

    public String a(am am2, String string, int n2, boolean bl) {
        String string2 = null;
        int n3 = 0;
        int n4 = this.b.a;
        Object[] objectArray = this.b.a();
        for (int i = 0; i < n4; ++i) {
            double d;
            e e2 = (e)objectArray[i];
            if (!bl && e2.a.a() || !((d = e2.a.t ? am2.bX.c(e2.a) : am2.a(e2.a)) < e2.b)) continue;
            double d2 = e2.b - d;
            String string3 = e2.a.i();
            string2 = string2 == null ? string3 : string2 + string + string3;
            if (++n3 > n2) break;
        }
        return string2;
    }

    public void g(f f2) {
        this.b();
        this.b(f2);
    }

    public void c(a a2) {
        m m2 = this.b;
        int n2 = m2.a;
        Object[] objectArray = m2.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a != a2) continue;
            return;
        }
        e e3 = new e(a2);
        e3.b = 0.0;
        m2.add(e3);
    }

    public void e() {
        Collections.sort(this.b, new f$1(this));
    }
}
