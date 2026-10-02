/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n;

import android.graphics.Color;
import android.graphics.Paint;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.n.b;
import com.corrodinggames.rts.gameFramework.n.e;
import com.corrodinggames.rts.gameFramework.utility.m;

public class a {
    public String a;
    public String b;
    public String c;
    public b d = new b();
    public b e = new b();
    public m f = new m();
    public e g;
    public boolean h;
    public boolean i;
    public boolean j;
    public int k;
    public int l;
    public boolean m;
    public int n = -1;
    public int o = Integer.MAX_VALUE;
    public int p;
    public int q = -1;
    public int r = -1;
    public int s = -1;
    public com.corrodinggames.rts.game.b.a t;
    public boolean u = false;
    public bp v;
    public float w;
    public float x;
    public n y;
    public bb z;
    public bb A;
    public Paint B;
    public boolean C;

    public void a(com.corrodinggames.rts.gameFramework.n.a.a a2) {
        this.f.add(a2);
    }

    public void a(String string) {
        this.t.b(string);
    }

    public String b(String string) {
        return this.t.b(string);
    }

    public String a(String string, String string2) {
        return this.t.a(string, string2);
    }

    public boolean c(String string) {
        return this.t.b(string) != null;
    }

    public int a(String string, int n2) {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return n2;
        }
        try {
            return Integer.parseInt(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw this.f(string + ": Unexpected integer value:'" + string2 + "'");
        }
    }

    public int b(String string, int n2) {
        double d;
        String string2;
        String string3 = string2 = this.b(string);
        if (string2 == null) {
            return n2;
        }
        double d2 = 1.0;
        if (string2.endsWith("ms")) {
            string2 = string2.substring(0, string2.length() - 2);
            d2 = 1.0;
        } else if (string2.endsWith("s")) {
            string2 = string2.substring(0, string2.length() - 1);
            d2 = 1000.0;
        } else {
            d2 = 1.0;
        }
        try {
            d = Double.parseDouble(string2);
        }
        catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            throw this.f(string + ": Unexpected time:'" + string3 + "'");
        }
        return (int)(d * d2);
    }

    public float a(String string, float f2) {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return f2;
        }
        try {
            return Float.parseFloat(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw this.f(string + ": Unexpected float value:'" + string2 + "'");
        }
    }

    public Integer d(String string) {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return null;
        }
        try {
            return Integer.parseInt(string2);
        }
        catch (NumberFormatException numberFormatException) {
            throw this.f(string + ": Unexpected integer value:'" + string2 + "'");
        }
    }

    public Boolean e(String string) {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return null;
        }
        if (string2.equalsIgnoreCase("true")) {
            return true;
        }
        if (string2.equalsIgnoreCase("false")) {
            return false;
        }
        throw this.f(string + ": Unexpected boolean value:'" + string2 + "'");
    }

    public boolean a(String string, String string2, boolean bl) {
        Boolean bl2 = this.e(string);
        if (bl2 != null) {
            return bl2;
        }
        bl2 = this.e(string2);
        if (bl2 != null) {
            return bl2;
        }
        return bl;
    }

    public boolean a(String string, boolean bl) {
        String string2 = this.a(string, (String)null);
        if (string2 == null) {
            return bl;
        }
        if (string2.equalsIgnoreCase("true")) {
            return true;
        }
        if (string2.equalsIgnoreCase("false")) {
            return false;
        }
        throw this.f(string + ": Unexpected boolean value:'" + string2 + "'");
    }

    public int c(String string, int n2) {
        String string2 = this.b(string);
        if (string2 == null) {
            return n2;
        }
        if (string2.equals("")) {
            throw this.f(string + ": Unknown color:" + string2);
        }
        try {
            return Color.a(string2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw this.f(string + ": Unknown color:" + string2);
        }
    }

    public bb a(String string, bb bb2) {
        return this.t.a(string, bb2);
    }

    public boolean a(am am2) {
        return this.t.a(am2);
    }

    public f f(String string) {
        return this.a(string, (Exception)null);
    }

    public f a(String string, Exception exception) {
        string = "MapTrigger-Error (" + this.a + " id:" + this.b + "): " + string;
        ad.g(string);
        if (exception == null) {
            return new f(string);
        }
        return new f(string, exception);
    }

    public void g(String string) {
        ad.g("MapTrigger-Error (" + this.a + " id:" + this.b + " type:" + (Object)((Object)this.g) + "): " + string);
    }

    public void h(String string) {
        com.corrodinggames.rts.gameFramework.l.e("MapTrigger-Debug (" + this.b + " type:" + (Object)((Object)this.g) + "): " + string);
    }

    public n a() {
        return this.y;
    }

    public int b() {
        return (int)this.t.j.d();
    }

    public int c() {
        return (int)this.t.j.e();
    }

    public boolean b(am am2) {
        ak ak2;
        n n2 = this.a();
        if (n2 != null && am2.bX != n2) {
            return false;
        }
        boolean bl = this.c("onlyIfEmpty");
        return !bl || !am2.cr() || !(am2 instanceof ak) || (ak2 = (ak)((Object)am2)).bB() <= 0;
    }

    public boolean d() {
        boolean bl;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        int n2 = l2.by;
        boolean bl2 = true;
        boolean bl3 = false;
        if (!this.m && this.r != -1) {
            if (this.r <= n2) {
                bl3 = true;
                this.m = true;
            } else {
                bl2 = false;
            }
        }
        if (this.d.a()) {
            if (this.d.b()) {
                bl3 = true;
            } else {
                bl2 = false;
            }
        }
        if (this.f.a > 0) {
            for (com.corrodinggames.rts.gameFramework.n.a.a a2 : this.f) {
                if (!a2.a(this)) continue;
                if (a2.b(this)) {
                    bl3 = true;
                    continue;
                }
                bl2 = false;
            }
        }
        if (this.h) {
            bl = bl3 && bl2;
        } else {
            bl = bl3;
            if (bl2) {
                bl = true;
            }
        }
        if (bl) {
            if (this.n == -1) {
                this.n = n2;
            }
            if (this.s <= 0) {
                return true;
            }
            return n2 >= this.n + this.s;
        }
        this.n = -1;
        return false;
    }
}
