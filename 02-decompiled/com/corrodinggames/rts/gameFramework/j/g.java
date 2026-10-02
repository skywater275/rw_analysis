/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.appFramework.i;
import com.corrodinggames.rts.gameFramework.l;

public class g {
    public boolean a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public boolean h;
    public long i = -1L;
    public String j;
    public String k;
    public int l;
    public boolean m;
    public String n;
    public long o;
    public int p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public int v = -1;
    public int w = 8;
    public boolean x;
    public boolean y;
    public String z;
    public int A;

    public boolean a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string = l2.bX.bw;
        if (string != null) {
            return string.equals(this.b);
        }
        return false;
    }

    public String b() {
        String string = this.c();
        if (string != null) {
            String string2 = "";
            String string3 = this.f;
            if (string3 != null) {
                string3 = string3.replace("\\n", "\n");
            }
            string2 = string2 + string3 + "\n";
            string2 = string2 + "Url: " + string + "\n";
            return string2;
        }
        String string4 = "";
        if (this.a) {
            string4 = string4 + "Lan: " + this.d + ":" + this.g + "\n";
        }
        string4 = string4 + "User: " + this.n + "\n";
        String string5 = com.corrodinggames.rts.appFramework.i.e(this.q);
        string4 = string4 + "Map: " + string5 + "\n";
        if (this.m) {
            string4 = string4 + "Password Required\n";
        }
        if (!this.h && !this.a) {
            string4 = string4 + "Port: not open (Connecting over the internet may fail)\n";
        }
        string4 = "ANY".equalsIgnoreCase(this.k) ? string4 + "Version: " + this.k + "\n" : string4 + "Version: v" + this.k + (this.g() ? "" : " (different game version!)") + "\n";
        if (this.z != null && !this.z.equals("")) {
            string4 = string4 + "Mods Needed: " + this.z + "\n";
        }
        return string4;
    }

    public String c() {
        return this.e;
    }

    public boolean d() {
        return this.e != null;
    }

    public String e() {
        if (this.A == 0) {
            return this.c + ":" + this.g;
        }
        return "get|" + this.b.replace("|", ".") + "|" + this.A + "|" + this.m + "|" + this.g;
    }

    public String f() {
        return this.d + ":" + this.g;
    }

    public boolean g() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.x && "ANY".equals(this.k)) {
            return true;
        }
        if (this.x && this.k != null && this.k.contains("+") && l2.c(true) >= this.l) {
            return true;
        }
        return l2.c(true) == this.l;
    }
}
