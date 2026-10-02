/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.d;

import android.graphics.Color;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.d.a;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class b
extends a
implements Comparable {
    private static final com.corrodinggames.rts.game.units.custom.e.f m = new com.corrodinggames.rts.game.units.custom.e.f().a();
    public static final b a = com.corrodinggames.rts.game.units.custom.d.b.a(0);
    public int b;
    public float c;
    public float d;
    public float e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public com.corrodinggames.rts.game.units.custom.e.f k = m;
    static final int l = Color.a(255, 0, 100, 0);

    public int a() {
        return this.b;
    }

    public int b() {
        if (this.k == m) {
            return this.b;
        }
        int n = this.b;
        int n2 = this.k.b.a;
        Object[] objectArray = this.k.b.a();
        for (int i = 0; i < n2; ++i) {
            float f2;
            e e2 = (e)objectArray[i];
            if (!(e2.b > 0.0) || (f2 = e2.a.b()) == 0.0f) continue;
            n += (int)((double)f2 * e2.b);
        }
        return n;
    }

    public static b a(b b2, b b3) {
        b b4 = new b();
        b4.b = b2.b + b3.b;
        b4.c = b2.c + b3.c;
        b4.d = b2.d + b3.d;
        b4.e = b2.e + b3.e;
        b4.f = b2.f + b3.f;
        if (!b2.k.c() || !b3.k.c()) {
            b4.k = com.corrodinggames.rts.game.units.custom.e.f.a(b2.k, b3.k);
        }
        return b4;
    }

    public static b a(b b2, float f2) {
        b b3 = new b();
        b3.b = (int)((float)b2.b * f2);
        b3.c = b2.c * f2;
        b3.d = b2.d * f2;
        b3.e = b2.e * f2;
        b3.f = (int)((float)b2.f * f2);
        if (!b2.k.c()) {
            b3.k = com.corrodinggames.rts.game.units.custom.e.f.b(b2.k, f2);
        }
        return b3;
    }

    public static b a(int n) {
        b b2 = new b();
        b2.b = n;
        return b2;
    }

    public static b a(l l2, ab ab2, String string, String string2, boolean bl) {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null && !bl) {
            throw new RuntimeException("Could not find " + string2 + " in configuration file under:" + string);
        }
        try {
            b b2 = com.corrodinggames.rts.game.units.custom.d.b.b(l2, string3);
            return b2;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static b a(l l2, ab ab2, String string, String string2, b b2) {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return b2;
        }
        try {
            b b3 = com.corrodinggames.rts.game.units.custom.d.b.b(l2, string3);
            return b3;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static b b(l l2, ab ab2, String string, String string2, b b2) {
        String string3 = ab2.b(string, string2, (String)null);
        if (string3 == null) {
            return b2;
        }
        try {
            b b3 = com.corrodinggames.rts.game.units.custom.d.b.a(l2, string3);
            return b3;
        }
        catch (bo bo2) {
            throw new bo("[" + string + "]" + string2 + ": " + bo2.getMessage());
        }
    }

    public static void b(int n) {
        if (n < 0 || n > 31) {
            throw new bo("Flag id must be between 0-31 (is:" + n + ")");
        }
    }

    public static int a(int n, String string) {
        if (string.contains("-")) {
            String[] stringArray = com.corrodinggames.rts.gameFramework.f.c(string, '-');
            if (stringArray.length != 2) {
                throw new bo("Unexpected flag id: " + string);
            }
            int n2 = Integer.parseInt(stringArray[0]);
            int n3 = Integer.parseInt(stringArray[1]);
            com.corrodinggames.rts.game.units.custom.d.b.b(n2);
            com.corrodinggames.rts.game.units.custom.d.b.b(n3);
            if (n3 < n2) {
                throw new bo("end<start in flag id: " + string);
            }
            for (int i = n2; i <= n3; ++i) {
                n |= 1 << i;
            }
            return n;
        }
        int n4 = Integer.parseInt(string);
        com.corrodinggames.rts.game.units.custom.d.b.b(n4);
        return n |= 1 << n4;
    }

    public static b a(l l2, String string) {
        b b2 = com.corrodinggames.rts.game.units.custom.d.b.b(l2, string);
        if (b2 != null && b2.f != 0) {
            throw new bo("Ammo not supported on streaming price:" + string);
        }
        return b2;
    }

    public static b b(l l2, String string) {
        if (string == null) {
            return a;
        }
        b b2 = new b();
        for (String string2 : string.split(",|\\|")) {
            String string3;
            String string4;
            if ((string2 = string2.trim()).equals("")) continue;
            String[] stringArray = string2.split("=|:");
            if (stringArray.length == 1) {
                string4 = "credits";
                string3 = stringArray[0];
            } else if (stringArray.length == 2) {
                string4 = stringArray[0].trim();
                string3 = stringArray[1].trim();
            } else {
                throw new bo("Unknown price format:" + string);
            }
            boolean bl = false;
            try {
                if (string4.equals("credits")) {
                    int n2;
                    bl = true;
                    b2.b = n2 = Integer.parseInt(string3);
                    continue;
                }
                if (string4.equals("energy")) {
                    float f2;
                    b2.c = f2 = Float.parseFloat(string3);
                    continue;
                }
                if (string4.equals("hp")) {
                    float f3;
                    b2.d = f3 = Float.parseFloat(string3);
                    continue;
                }
                if (string4.equals("shield")) {
                    float f4;
                    b2.e = f4 = Float.parseFloat(string3);
                    continue;
                }
                if (string4.equals("ammo")) {
                    int n3;
                    bl = true;
                    b2.f = n3 = Integer.parseInt(string3);
                    continue;
                }
                if (string4.equals("hasFlag")) {
                    bl = true;
                    b2.i = com.corrodinggames.rts.game.units.custom.d.b.a(b2.i, string3);
                    continue;
                }
                if (string4.equals("hasMissingFlag")) {
                    bl = true;
                    b2.j = com.corrodinggames.rts.game.units.custom.d.b.a(b2.j, string3);
                    continue;
                }
                if (string4.equals("setFlag")) {
                    bl = true;
                    b2.g = com.corrodinggames.rts.game.units.custom.d.b.a(b2.g, string3);
                    continue;
                }
                if (string4.equals("unsetFlag")) {
                    bl = true;
                    b2.h = com.corrodinggames.rts.game.units.custom.d.b.a(b2.h, string3);
                    continue;
                }
                com.corrodinggames.rts.game.units.custom.e.a a2 = l2.k(string4);
                if (a2 != null) {
                    float f5 = Float.parseFloat(string3);
                    if (b2.k == m) {
                        b2.k = new com.corrodinggames.rts.game.units.custom.e.f();
                    }
                    b2.k.a(a2, (double)f5);
                    continue;
                }
                throw new bo("Unknown price type:" + string4);
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                String string5 = "Bad price number:" + string3 + " in " + string;
                if (bl) {
                    string5 = string5 + " (Hint: A whole number was expected)";
                }
                throw new bo(string5);
            }
        }
        if (b2.k != m) {
            b2.k.a();
        }
        if (!b2.d()) {
            return a;
        }
        return b2;
    }

    public int a(am am2, boolean bl) {
        int n2;
        int n3 = 9999;
        if (!bl && this.b > 0) {
            n2 = (int)(am2.bX.o / (double)this.b);
            n3 = com.corrodinggames.rts.gameFramework.f.c(n3, n2);
        }
        if (this.c > 0.0f) {
            n2 = (int)(am2.cB / this.c);
            n3 = com.corrodinggames.rts.gameFramework.f.c(n3, n2);
        }
        if (this.d > 0.0f) {
            n2 = (int)(am2.cu / this.d);
            n3 = com.corrodinggames.rts.gameFramework.f.c(n3, n2);
        }
        if (this.e > 0.0f) {
            n2 = (int)(am2.cx / this.e);
            n3 = com.corrodinggames.rts.gameFramework.f.c(n3, n2);
        }
        if (this.f > 0) {
            n2 = am2.cE / this.f;
            n3 = com.corrodinggames.rts.gameFramework.f.c(n3, n2);
        }
        if (!this.k.c()) {
            n2 = com.corrodinggames.rts.game.units.custom.e.f.a(this.k, am2);
            n3 = com.corrodinggames.rts.gameFramework.f.c(n3, n2);
        }
        if (!this.f(am2)) {
            n3 = 0;
        }
        return n3;
    }

    @Override
    public boolean b(am am2, double d2) {
        if (this.b > 0 && !am2.bX.a((double)this.b * d2)) {
            return false;
        }
        if (this.c > 0.0f && (double)am2.cB < (double)this.c * d2) {
            return false;
        }
        if (this.d > 0.0f && (double)am2.cu < (double)this.d * d2) {
            return false;
        }
        if (this.e > 0.0f && (double)am2.cx < (double)this.e * d2) {
            return false;
        }
        if (this.f > 0 && (double)am2.cE < (double)this.f * d2) {
            return false;
        }
        if (!this.f(am2)) {
            return false;
        }
        return this.k.c() || com.corrodinggames.rts.game.units.custom.e.f.a(this.k, am2, d2);
    }

    @Override
    public boolean b(am am2) {
        if (this.b > 0 && !am2.bX.a((double)this.b)) {
            return false;
        }
        if (this.c > 0.0f && am2.cB < this.c) {
            return false;
        }
        if (this.d > 0.0f && am2.cu < this.d) {
            return false;
        }
        if (this.e > 0.0f && am2.cx < this.e) {
            return false;
        }
        if (this.f > 0 && am2.cE < this.f) {
            return false;
        }
        if (!this.f(am2)) {
            return false;
        }
        return this.k.c() || com.corrodinggames.rts.game.units.custom.e.f.b(this.k, am2);
    }

    public boolean a(am am2, am am3) {
        boolean bl = false;
        if (!this.k.c() && com.corrodinggames.rts.game.units.custom.e.f.a(this.k, am2, am3)) {
            bl = true;
        }
        return bl;
    }

    public static void d(am am2) {
        if (am2.cB < 0.0f) {
            am2.cB = 0.0f;
        }
        if (am2.cB > am2.bd()) {
            am2.cB = am2.bd();
        }
        if (am2.cx < 0.0f) {
            am2.cx = 0.0f;
        }
        if (am2.cx > am2.cA) {
            am2.cx = am2.cA;
        }
        if (am2.cu > am2.cv) {
            am2.cu = am2.cv;
        }
        if (am2.cE < 0) {
            am2.cE = 0;
        }
    }

    public void e(am am2) {
        if (this.h != 0) {
            am2.cF &= ~this.h;
        }
        if (this.g != 0) {
            am2.cF |= this.g;
        }
    }

    public int c(int n2) {
        if (this.h != 0) {
            n2 &= ~this.h;
        }
        if (this.g != 0) {
            n2 |= this.g;
        }
        return n2;
    }

    public static boolean a(int n2, int n3) {
        int n4 = 1 << n3;
        return (n2 & n4) != 0;
    }

    public boolean f(am am2) {
        if (this.i != 0 && !com.corrodinggames.rts.game.units.custom.d.b.b(am2.cF, this.i)) {
            return false;
        }
        return this.j == 0 || !com.corrodinggames.rts.game.units.custom.d.b.c(am2.cF, this.j);
    }

    public static boolean b(int n2, int n3) {
        return (n3 & n2) == n3;
    }

    public static boolean c(int n2, int n3) {
        return (n3 & n2) != 0;
    }

    @Override
    public void a(am am2) {
        am2.bX.o -= (double)this.b;
        am2.cB -= this.c;
        am2.cu -= this.d;
        am2.cx -= this.e;
        am2.cE -= this.f;
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.e.f.c(this.k, am2);
        }
        com.corrodinggames.rts.game.units.custom.d.b.d(am2);
    }

    @Override
    public void a(am am2, double d2) {
        am2.bX.o -= (double)this.b * d2;
        am2.cB = (float)((double)am2.cB - (double)this.c * d2);
        am2.cu = (float)((double)am2.cu - (double)this.d * d2);
        am2.cx = (float)((double)am2.cx - (double)this.e * d2);
        am2.cE = (int)((double)am2.cE - (double)this.f * d2);
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.e.f.b(this.k, am2, d2);
        }
        com.corrodinggames.rts.game.units.custom.d.b.d(am2);
    }

    public void g(am am2) {
        if (this.b > 0) {
            am2.bX.b(this.b);
        } else {
            am2.bX.o += (double)this.b;
        }
        am2.cB += this.c;
        am2.cu += this.d;
        am2.cx += this.e;
        am2.cE += this.f;
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.e.f.d(this.k, am2);
        }
        com.corrodinggames.rts.game.units.custom.d.b.d(am2);
    }

    public void h(am am2) {
        am2.bX.o += (double)this.b;
        am2.cB += this.c;
        am2.cu += this.d;
        am2.cx += this.e;
        am2.cE += this.f;
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.e.f.d(this.k, am2);
        }
        com.corrodinggames.rts.game.units.custom.d.b.d(am2);
    }

    public void a(am am2, double d2, boolean bl) {
        if (bl) {
            am2.bX.o += (double)this.b * d2;
        }
        am2.cB = (float)((double)am2.cB + (double)this.c * d2);
        am2.cu = (float)((double)am2.cu + (double)this.d * d2);
        am2.cx = (float)((double)am2.cx + (double)this.e * d2);
        am2.cE = (int)((double)am2.cE + (double)this.f * d2);
        this.e(am2);
        if (!this.k.c()) {
            com.corrodinggames.rts.game.units.custom.e.f.c(this.k, am2, d2);
        }
        com.corrodinggames.rts.game.units.custom.d.b.d(am2);
    }

    public boolean c() {
        if (this == a) {
            return true;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            return false;
        }
        return this.k.c();
    }

    public boolean d() {
        if (this == a) {
            return false;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            return true;
        }
        if (this.g != 0 || this.h != 0 || this.i != 0 || this.j != 0) {
            return true;
        }
        return !this.k.c();
    }

    public boolean e() {
        if (this == a) {
            return false;
        }
        if (this.b != 0 || this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            return true;
        }
        return this.g != 0 || this.h != 0;
    }

    public String a(boolean bl, boolean bl2, int n2, boolean bl3) {
        ae ae2 = new ae();
        this.a(ae2, bl, bl2, n2, bl3);
        return ae2.a();
    }

    public void a(ae ae2, boolean bl, boolean bl2, int n2, boolean bl3, am am2, int n3) {
        this.b(ae2, bl, bl2, n2, bl3, am2, n3);
    }

    private void a(ae ae2, boolean bl, boolean bl2, int n2, boolean bl3) {
        this.b(ae2, bl, bl2, n2, bl3, null, 0);
    }

    private void b(ae ae2, boolean bl, boolean bl2, int n2, boolean bl3, am am2, int n3) {
        String string = bl ? "\n" : " | ";
        int n4 = 0;
        if (this.b > 0 && n4 < n2) {
            double d2;
            int n5 = l;
            if (am2 != null && (d2 = am2.bX.o) < (double)this.b) {
                n5 = n3;
            }
            ae2.a("$" + this.b + string, n5);
            ++n4;
        }
        if (bl2) {
            if (this.c > 0.0f && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.f.g(this.c) + " energy" + string);
                ++n4;
            }
            if (this.d > 0.0f && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.f.g(this.d) + " hp" + string);
                ++n4;
            }
            if (this.e > 0.0f && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.f.g(this.e) + " shield" + string);
                ++n4;
            }
            if (this.f > 0 && n4 < n2) {
                ae2.b(com.corrodinggames.rts.gameFramework.f.g((float)this.f) + " ammo" + string);
                ++n4;
            }
        }
        if (!this.k.c()) {
            this.k.a(ae2, bl, bl2, n2 - n4, bl3, false, am2, n3);
        }
        ae2.a(string);
    }

    public b i(am am2) {
        b b2 = new b();
        if (this.b > 0 && am2.bX.o < (double)this.b) {
            b2.b = this.b - (int)am2.bX.o;
        }
        if (!this.k.c()) {
            b2.k = this.k.a(am2);
        }
        return b2;
    }

    public String a(am am2, int n2, boolean bl) {
        String string;
        String string2 = null;
        String string3 = ", ";
        int n3 = 0;
        if (this.b > 0 && n3 < n2 && am2.bX.o < (double)this.b) {
            if (string2 == null) {
                string2 = "";
            }
            string2 = string2 + "credits" + string3;
            ++n3;
        }
        if (!this.k.c() && (string = this.k.a(am2, string3, n2, bl)) != null) {
            if (string2 == null) {
                string2 = "";
            }
            string2 = string2 + string;
        }
        if (string2 == null) {
            return null;
        }
        string2 = com.corrodinggames.rts.gameFramework.f.a(string2, string3);
        return string2;
    }

    public int a(b b2) {
        return this.b - b2.b;
    }

    public static void a(as as2, b b2) {
        as2.a(b2 != null);
        if (b2 != null) {
            b2.a(as2);
        }
    }

    public void a(as as2) {
        boolean bl = false;
        boolean bl2 = false;
        if (this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.f != 0) {
            bl = true;
        }
        if (this.g != 0 || this.h != 0 || this.i != 0 || this.j != 0) {
            bl = true;
        }
        if (!this.k.c()) {
            bl2 = true;
        }
        byte by = 0;
        if (bl) {
            by = (byte)(by | 1);
        }
        if (bl2) {
            by = (byte)(by | 2);
        }
        as2.c(by);
        as2.a(this.b);
        if (bl) {
            as2.a(this.c);
            as2.a(this.d);
            as2.a(this.e);
            as2.a(this.f);
            as2.a(this.g);
            as2.a(this.h);
            as2.a(this.i);
            as2.a(this.j);
        }
        if (bl2) {
            this.k.a(as2);
        }
    }

    public static b a(k k2) {
        boolean bl = k2.e();
        if (bl) {
            return com.corrodinggames.rts.game.units.custom.d.b.b(k2);
        }
        return null;
    }

    public static b b(k k2) {
        b b2 = new b();
        byte by = k2.d();
        boolean bl = com.corrodinggames.rts.game.units.custom.d.b.b(by, 1);
        boolean bl2 = com.corrodinggames.rts.game.units.custom.d.b.b(by, 2);
        b2.b = k2.f();
        if (bl) {
            b2.c = k2.g();
            b2.d = k2.g();
            b2.e = k2.g();
            b2.f = k2.f();
            b2.g = k2.f();
            b2.h = k2.f();
            b2.i = k2.f();
            b2.j = k2.f();
        }
        if (bl2) {
            b2.k = new com.corrodinggames.rts.game.units.custom.e.f();
            b2.k.a(k2);
        }
        return b2;
    }

    public boolean b(am am2, boolean bl) {
        if (this.c(am2, bl)) {
            this.d(am2, bl);
            return true;
        }
        return false;
    }

    public boolean c(am am2, boolean bl) {
        if (this.b > 0 && !am2.bX.g(this.b)) {
            return false;
        }
        if (bl) {
            return an.c(am2, this);
        }
        return this.b(am2);
    }

    public void d(am am2, boolean bl) {
        am2.bX.p -= (double)this.b;
        am2.bX.q = 0;
        if (bl) {
            an.a(am2, this);
        }
    }

    public void e(am am2, boolean bl) {
        am2.bX.p += (double)this.b;
        am2.bX.q = 0;
        if (bl) {
            an.b(am2, this);
        }
    }

    public static boolean b(b b2, b b3) {
        if (b3 == b2) {
            return true;
        }
        if (b3 == null || b2 == null) {
            return false;
        }
        return b3.b(b2);
    }

    public boolean b(b b2) {
        if (this.b != b2.b) {
            return false;
        }
        if (this.d != b2.d) {
            return false;
        }
        if (this.e != b2.e) {
            return false;
        }
        if (this.f != b2.f) {
            return false;
        }
        if (this.k.c() != b2.k.c()) {
            return false;
        }
        return this.k.c() || b2.k.c() || this.k.e(b2.k);
    }

    public boolean c(b b2) {
        if (this.b > 0 && b2.b > 0) {
            return true;
        }
        if (this.d > 0.0f && b2.d > 0.0f) {
            return true;
        }
        if (this.e > 0.0f && b2.e > 0.0f) {
            return true;
        }
        if (this.f > 0 && b2.f > 0) {
            return true;
        }
        return !this.k.c() && !b2.k.c() && this.k.f(b2.k);
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((b)object);
    }
}
