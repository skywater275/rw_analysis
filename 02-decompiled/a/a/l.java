/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.a.e;
import a.a.h;
import java.io.IOException;
import java.util.ArrayList;

class l
implements Runnable {
    final /* synthetic */ h a;

    private l(h h2) {
        this.a = h2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        ArrayList arrayList = h.g(this.a);
        synchronized (arrayList) {
            block6: {
                if (h.g(this.a).isEmpty()) {
                    try {
                        h.b(this.a, new e(h.h(this.a).a()));
                    }
                    catch (IOException iOException) {
                        if (!h.h()) break block6;
                        iOException.printStackTrace();
                    }
                }
            }
        }
    }
}
