/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.a.a;
import a.a.a.c;
import a.a.a.g;
import a.a.h;
import java.io.IOException;

class m
extends Thread {
    final /* synthetic */ h a;

    public m(h h2) {
        this.a = h2;
        super("ReliableSocket");
        this.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            a.a.a.h h2;
            while ((h2 = h.f(this.a)) != null) {
                if (h2 instanceof g) {
                    this.a.a((g)h2);
                } else if (h2 instanceof c) {
                    h.a(this.a, (c)h2);
                } else if (!(h2 instanceof a)) {
                    h.a(this.a, h2);
                }
                this.a.c(h2);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
