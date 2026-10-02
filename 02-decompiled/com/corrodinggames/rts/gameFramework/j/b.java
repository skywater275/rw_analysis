/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.a;
import com.corrodinggames.rts.gameFramework.j.c;

public class b {
    int a;
    String b;
    String c;
    int d;
    long e;
    final /* synthetic */ a f;

    b(a a2, int n, String string, String string2, c c2) {
        this.f = a2;
        this.a = n;
        this.b = string;
        this.c = string2;
        if (c2 != null) {
            this.d = c2.c;
        }
        this.e = System.nanoTime();
    }

    public String a() {
        String string = this.b != null ? this.b + ": " + this.c : this.c;
        return string;
    }

    public String b() {
        String string = "";
        if (this.b != null) {
            int n2 = -1;
            if (this.a != -1) {
                n2 = n.i(this.a);
            }
            string = "<strong> <font color='" + com.corrodinggames.rts.gameFramework.f.h(n2) + "'>" + this.f.a(this.b) + ": </font></strong>";
        }
        String[] stringArray = this.c.split("\n");
        boolean bl = true;
        for (String string2 : stringArray) {
            if (string2.trim().equals("")) continue;
            if (bl) {
                bl = false;
            } else {
                string = string + "<br/>";
            }
            string = string + this.f.a(string2);
        }
        return string;
    }
}
