/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.s;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.List;

class u
implements Runnable {
    int a;
    List b;
    s c;
    String d;
    boolean e;

    public u(List list, s s2, String string, boolean bl, int n) {
        this.a = n;
        this.b = list;
        this.c = s2;
        this.d = string;
        this.e = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        Object object;
        block10: {
            l l2 = l.B();
            try {
                if (n.a) {
                    l.b("LoadFromMasterServer", this.a + ": Started doSingleRequest");
                }
                object = n.a((List)this.b, (String)this.d, (boolean)this.e).a;
                if (n.a) {
                    l.b("LoadFromMasterServer", this.a + ": Ended doSingleRequest");
                }
                this.c.a((BufferedReader)object, this.a, this.d);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                String string = f.a(exception, true);
                if (exception instanceof UnknownHostException) {
                    string = "DNS lookup failed, check your internet connection";
                }
                if (string != null && string.contains("Cleartext HTTP traffic")) {
                    string = string + " ( Broken apk file? - " + l2.l() + ")";
                }
                this.c.d = "#" + this.a + ": " + string;
                String string2 = "Error getting game list from server #" + this.a;
                l.e(string2);
                if (!l2.p()) break block10;
                l2.a("Error getting game list from server #" + this.a, 1);
            }
        }
        object = this.c;
        synchronized (object) {
            --this.c.f;
            if (this.c.f == 0) {
                this.c.a();
            }
        }
    }
}
