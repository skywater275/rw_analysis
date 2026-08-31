/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.j;

import android.os.Build;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.o.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class v
implements Runnable {
    String a;
    String b;

    v() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        long l2 = l.V();
        l l3 = l.B();
        l.b("SendErrorReport", "Starting");
        try {
            String string;
            ArrayList arrayList = new ArrayList(2);
            n.a(arrayList, "action", "error_report");
            n.a(arrayList, "game_version", Integer.toString(l3.c(false)));
            n.a(arrayList, "game_version_internal", Integer.toString(l3.c(true)));
            n.a(arrayList, "game_version_string", l3.r());
            n.a(arrayList, "package_name", l3.l());
            n.a(arrayList, "installation_source", l3.m());
            String string2 = "" + Build.VERSION.SDK_INT;
            if (l.av()) {
                string2 = "s:0;";
                if (com.corrodinggames.rts.gameFramework.o.a.a().e()) {
                    string2 = "s:1;";
                }
            }
            if (l.av() || l.aZ) {
                string = System.getProperty("os.name") + " - " + System.getProperty("os.version");
                n.a(arrayList, "system_version", string);
            }
            n.a(arrayList, "sdk_version", string2);
            n.a(arrayList, "device_model", l3.G());
            n.a(arrayList, "build_version", l3.H());
            n.a(arrayList, "release_version", f.a(l.as));
            n.a(arrayList, "dedicated_server", f.a(l.aU));
            string = "NA";
            if (l3.bX != null) {
                string = l3.bX.aR;
            }
            n.a(arrayList, "private_token", string);
            n.a(arrayList, "private_token_2", f.b(f.b(string)));
            n.a(arrayList, "message", this.a);
            n.a(arrayList, "stacktrace", this.b);
            l.b("SendErrorReport", "making request");
            BufferedReader bufferedReader = n.a(arrayList);
            String string3 = bufferedReader.readLine();
            if (string3 == null || !string3.contains("CORRODINGGAMES")) {
                l.b("StartCreateOnMasterServer", "Error bad header returned from the master server: " + string3);
                return;
            }
            l.b("SendErrorReport", "Send trace successfully");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            float f2 = (float)(l.V() - l2) / 1000000.0f;
            l.b("SendErrorReport", "took: " + f2 + " seconds");
        }
    }
}
