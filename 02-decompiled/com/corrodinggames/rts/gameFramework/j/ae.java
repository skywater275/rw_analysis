/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.l;
import java.io.IOException;

public strictfp class ae {
    public String b;
    public int c;
    public boolean d;
    public String e;
    public String f;
    public String g;

    public void a(String string) {
        l l2 = l.B();
        if (this.d) {
            as as2;
            try {
                as2 = new as();
                as2.c(1);
                as2.a(this.c);
                as2.c(string);
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            au au2 = as2.b(118);
            l2.bX.d(au2);
            return;
        }
        if (l2.bX.C) {
            l.a("Cannot enter a password when we are a server");
            return;
        }
        l2.bX.n = string;
        l2.bX.X();
    }

    public void a() {
        l l2 = l.B();
        l2.bX.b("exited password");
        l2.bX.K();
    }
}
