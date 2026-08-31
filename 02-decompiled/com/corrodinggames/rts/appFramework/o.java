/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.l;

public class o {
    String a;
    int b = 0;
    int c = 0;
    n d = null;
    n e = null;
    int f;
    int g;

    o(String string) {
        this.a = string;
    }

    public void a(n n2, int n3) {
        if (n3 < this.b || this.d == null) {
            this.b = n3;
            this.d = n2;
            this.f = 1;
        } else if (n3 == this.b) {
            ++this.f;
        }
        if (n3 > this.c || this.e == null) {
            this.c = n3;
            this.e = n2;
            this.g = 1;
        } else if (n3 == this.c) {
            ++this.g;
        }
    }

    public boolean a() {
        l l2 = l.B();
        if (this.b == this.c) {
            return false;
        }
        if (this.d == null && this.e == null) {
            return false;
        }
        if (this.g == 1) {
            String string = "Warning: Uneven map - Player " + (this.e.k + 1) + " on team " + this.e.h() + ": " + this.a + " is " + this.c + " vs " + this.b;
            l2.bS.h.a(null, string);
            return true;
        }
        String string = "Warning: Uneven map - " + this.g + " players including player " + (this.e.k + 1) + " on team " + (this.e.r + 1) + ": " + this.a + " is " + this.c + " vs " + this.b;
        l2.bS.h.a(null, string);
        return true;
    }
}
