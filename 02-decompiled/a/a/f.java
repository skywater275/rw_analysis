/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.b;
import a.a.e;
import a.a.h;
import a.a.s;
import java.util.ArrayList;

class f
implements s {
    final /* synthetic */ b a;

    private f(b b2) {
        this.a = b2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(h h2) {
        if (h2 instanceof e) {
            ArrayList arrayList = b.e(this.a);
            synchronized (arrayList) {
                while (b.e(this.a).size() > 50) {
                    try {
                        b.e(this.a).wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                b.e(this.a).add((e)h2);
                b.e(this.a).notify();
            }
        }
    }

    @Override
    public void b(h h2) {
    }

    @Override
    public void c(h h2) {
        if (h2 instanceof e) {
            b.a(this.a, ((e)h2).c());
        }
    }

    @Override
    public void d(h h2) {
        if (h2 instanceof e) {
            b.a(this.a, ((e)h2).c());
        }
    }

    @Override
    public void e(h h2) {
    }
}
