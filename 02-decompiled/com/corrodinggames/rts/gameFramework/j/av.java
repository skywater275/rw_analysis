/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import java.io.IOException;
import java.util.TimerTask;

class av
extends TimerTask {
    private final ad c;
    public boolean a = true;
    public long b = 0L;

    av(ad ad2) {
        this.c = ad2;
    }

    @Override
    public void run() {
        try {
            long l = System.currentTimeMillis();
            if (this.c.au != 0L && (l > this.c.au + 5L || l < this.c.au)) {
                this.c.au = 0L;
                this.c.Q();
            }
            if (l > this.b + 1000L || l < this.b) {
                this.b = l;
                if (this.a) {
                    as as2 = new as();
                    as2.a(System.currentTimeMillis());
                    as2.c(0);
                    au au2 = as2.b(108);
                    this.c.g(au2);
                } else {
                    this.c.P();
                }
                this.a = !this.a;
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
