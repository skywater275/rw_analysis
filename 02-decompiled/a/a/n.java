/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.h;
import java.io.IOException;
import java.util.ArrayList;

class n
implements Runnable {
    final /* synthetic */ h a;

    private n(h h2) {
        this.a = h2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        ArrayList arrayList = h.g(this.a);
        synchronized (arrayList) {
            for (a.a.a.h h2 : h.g(this.a)) {
                try {
                    h.c(this.a, h2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
    }
}
