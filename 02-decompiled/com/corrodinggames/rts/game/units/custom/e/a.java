/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.c;
import com.corrodinggames.rts.game.units.custom.e.a.e;
import com.corrodinggames.rts.game.units.custom.e.a.f;
import com.corrodinggames.rts.game.units.custom.e.b;
import com.corrodinggames.rts.game.units.custom.e.d;
import com.corrodinggames.rts.game.units.custom.l;
import java.util.ArrayList;
import java.util.Locale;

public strictfp class a {
    public boolean a;
    protected String b;
    protected bb c;
    protected bb d;
    protected boolean e;
    protected boolean f;
    protected bb g;
    protected bb h;
    public a i;
    public boolean j;
    public boolean k;
    public boolean l = true;
    Integer m;
    public boolean n;
    public boolean o;
    public boolean p;
    public b q = com.corrodinggames.rts.game.units.custom.e.b.a;
    boolean r;
    float s;
    protected boolean t;
    protected boolean u;
    a v;
    public boolean w;
    public float x;
    public com.corrodinggames.rts.gameFramework.m.e y;
    public boolean z;
    static ArrayList A = new ArrayList();
    static ArrayList B = new ArrayList();
    public static ArrayList C = new ArrayList();
    public static final a D = com.corrodinggames.rts.game.units.custom.e.a.a(new c());
    public static final a E = com.corrodinggames.rts.game.units.custom.e.a.a(new com.corrodinggames.rts.game.units.custom.e.a.d());
    public static final a F = com.corrodinggames.rts.game.units.custom.e.a.a(new com.corrodinggames.rts.game.units.custom.e.a.b());
    public static final a G;
    public static final a H;

    public boolean a() {
        return this.r;
    }

    public float b() {
        return this.s;
    }

    public boolean c() {
        return this.u;
    }

    public boolean d() {
        return this.t;
    }

    public static void e() {
        for (Object object : A) {
            ((a)object).g();
        }
        ArrayList arrayList = new ArrayList();
        for (a a2 : A) {
            if (!a2.a) continue;
            arrayList.add(a2);
        }
        B = arrayList;
    }

    public static ArrayList f() {
        return B;
    }

    public void g() {
        if (this.u) {
            this.a = true;
            return;
        }
        d d2 = null;
        for (l l2 : com.corrodinggames.rts.game.units.custom.l.d) {
            d d3 = l2.a(this);
            if (d3 == null || d2 != null && !(d2.c < d3.c)) continue;
            d2 = d3;
        }
        boolean bl2 = this.a = d2 != null;
        if (d2 != null) {
            this.c = d2.g;
            this.d = d2.h;
            this.e = d2.i;
            this.f = d2.j;
            this.m = d2.d;
            this.n = d2.e;
            this.o = d2.o;
            this.p = d2.p;
            this.q = d2.r;
            this.g = d2.t;
            this.h = d2.u;
            this.i = d2.w;
            this.j = d2.y;
            this.l = d2.q;
            this.k = d2.x;
            this.r = d2.l;
            this.s = d2.m;
            this.v = d2.A;
            this.w = d2.k;
            this.x = d2.s;
            this.y = d2.B;
            this.z = d2.C;
        }
    }

    public Integer h() {
        return this.m;
    }

    public String i() {
        if (this.c == null) {
            return this.b;
        }
        return this.c.b();
    }

    public String j() {
        if (this.d != null) {
            return this.d.b();
        }
        return this.i();
    }

    public String a(double d2, boolean bl2) {
        String string = this.o ? "" + (int)d2 : com.corrodinggames.rts.gameFramework.f.c(d2);
        string = com.corrodinggames.rts.game.units.custom.e.a.a(string, this.q);
        return this.a(bl2) + string + this.b(bl2);
    }

    public static String a(String string, b b2) {
        String string2;
        if (b2 == com.corrodinggames.rts.game.units.custom.e.b.a) {
            return string;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bQ.disableDigitGrouping) {
            return string;
        }
        String string3 = string;
        String string4 = "";
        String string5 = "";
        int n2 = string3.indexOf(".");
        if (n2 != -1) {
            string5 = string3.substring(n2);
            string3 = string3.substring(0, n2);
        }
        if (string3.length() <= 3) {
            return string;
        }
        if (b2 == com.corrodinggames.rts.game.units.custom.e.b.b) {
            string2 = " ";
        } else if (b2 == com.corrodinggames.rts.game.units.custom.e.b.c) {
            string2 = ",";
        } else {
            throw new RuntimeException("Unhandled grouping style: " + (Object)((Object)b2));
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n3 = string3.length() % 3;
        if (n3 != 0) {
            stringBuilder.append(string3.substring(0, n3));
        }
        for (int i2 = n3; i2 < string3.length(); i2 += 3) {
            if (i2 != 0) {
                stringBuilder.append(string2);
            }
            stringBuilder.append(string3.substring(i2, i2 + 3));
        }
        if (string5 == "") {
            return stringBuilder.toString();
        }
        return stringBuilder.toString() + string5;
    }

    public static String a(long l2, b b2) {
        if (b2 == com.corrodinggames.rts.game.units.custom.e.b.a) {
            return "" + l2;
        }
        if (b2 == com.corrodinggames.rts.game.units.custom.e.b.b) {
            return String.format(Locale.US, "%,d", l2).replace(",", " ");
        }
        if (b2 == com.corrodinggames.rts.game.units.custom.e.b.c) {
            return String.format(Locale.US, "%,d", l2);
        }
        throw new RuntimeException("Unhandled grouping style: " + (Object)((Object)b2));
    }

    public String a(boolean bl2) {
        if (this.g != null) {
            return this.g.b();
        }
        if (bl2 && this.e) {
            return "";
        }
        return this.i() + ": ";
    }

    public String b(boolean bl2) {
        if (this.h != null) {
            return this.h.b();
        }
        return "";
    }

    public com.corrodinggames.rts.gameFramework.m.e k() {
        return this.y;
    }

    protected a() {
    }

    public static a a(String string) {
        string = string.toLowerCase(Locale.ENGLISH);
        for (a a2 : C) {
            if (!a2.b.equalsIgnoreCase(string)) continue;
            return a2;
        }
        return null;
    }

    public static a a(a a2) {
        for (a a3 : A) {
            if (!a3.b.equals(a2.b)) continue;
            throw new RuntimeException("Built in resource already exists:" + a2.b);
        }
        a a4 = a2;
        A.add(a4);
        C.add(a4);
        return a4;
    }

    public static a a(String string, boolean bl2, boolean bl3) {
        for (a a2 : A) {
            if (!a2.b.equals(string)) continue;
            return a2;
        }
        a a3 = new a();
        a3.b = string;
        a3.u = bl2;
        a3.t = bl3;
        A.add(a3);
        return a3;
    }

    public static a b(String string) {
        for (a a2 : A) {
            if (!a2.b.equals(string)) continue;
            return a2;
        }
        return null;
    }

    private String a(double d2) {
        String string = com.corrodinggames.rts.gameFramework.f.a(d2, 1);
        return com.corrodinggames.rts.game.units.custom.e.a.a(string, this.q);
    }

    public String a(double d2, boolean bl2, boolean bl3) {
        String string = bl3 && this.f ? "" : this.j() + ": ";
        if (this == D) {
            string = "$";
        }
        if (bl2) {
            if (d2 > 0.0) {
                return "+" + string + this.a(d2);
            }
            return "-" + string + this.a(-d2);
        }
        if (d2 > 0.0) {
            return string + this.a(d2);
        }
        return string + this.a(d2);
    }

    public String toString() {
        return "resource(" + this.b + ")";
    }

    public double a(am am2) {
        if (this.t) {
            return am2.bX.c(this);
        }
        return am2.a(this);
    }

    public void a(am am2, double d2) {
        if (this.t) {
            am2.bX.V().a(this, d2);
        } else {
            am2.df().a(this, d2);
        }
    }

    public void b(am am2, double d2) {
        if (this.t) {
            am2.bX.V().b(this, d2);
        } else {
            am2.df().b(this, d2);
        }
    }

    static {
        H = com.corrodinggames.rts.game.units.custom.e.a.a(new f());
        G = com.corrodinggames.rts.game.units.custom.e.a.a(new e());
    }
}
