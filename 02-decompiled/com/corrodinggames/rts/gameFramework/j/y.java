/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class y
implements Runnable {
    y() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        l.aq();
        long l2 = l.V();
        l l3 = l.B();
        l.b("StartCreateOnMasterServer", "Starting create");
        try {
            ArrayList arrayList = new ArrayList(2);
            n.a(arrayList, "action", "add");
            String string = "u_" + f.b();
            n.a(arrayList, "user_id", string);
            aq.a.a(string, arrayList);
            n.a(arrayList, "game_name", "Unnamed");
            n.a(arrayList, "game_version", Integer.toString(l3.c(true)));
            if (!l3.bX.v) {
                n.a(arrayList, "game_version_string", l3.v());
            } else {
                n.a(arrayList, "game_version_string", "ANY");
            }
            n.a(arrayList, "game_version_beta", f.a(l3.n()));
            String string2 = l3.bX.au();
            if (string2 != null) {
                n.a(arrayList, "game_mods", string2);
            }
            n.a(arrayList, "private_token", l3.bX.aR);
            n.a(arrayList, "private_token_2", f.b(f.b(l3.bX.aR)));
            n.a(arrayList, "confirm", f.b("a" + f.b(l3.bX.aR)));
            n.b(arrayList);
            BufferedReader bufferedReader = n.a(arrayList, 15);
            String string3 = bufferedReader.readLine();
            if (string3 == null || !string3.contains("CORRODINGGAMES")) {
                l.b("StartCreateOnMasterServer", "Error bad header returned from the master server: " + string3);
                return;
            }
            String string4 = bufferedReader.readLine();
            String[] stringArray = string4.split(",");
            if (stringArray.length < 1) {
                l.b("StartCreateOnMasterServer", "columns.length too short at:" + stringArray.length);
            }
            String string5 = stringArray[0];
            try {
                String string6 = string5;
                l.b("StartCreateOnMasterServer", "Created server is:" + string6);
                l3.bX.aS = string6;
            }
            catch (NumberFormatException numberFormatException) {
                l.b("StartCreateOnMasterServer", "failed to load server");
                numberFormatException.printStackTrace();
            }
            if (stringArray.length >= 2) {
                try {
                    aq.f = Integer.parseInt(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    aq.f = -1;
                }
            }
            l.b("StartCreateOnMasterServer", "Completed create from master server without error");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            float f2 = (float)(l.V() - l2) / 1000000.0f;
            l.b("StartCreateOnMasterServer", "create took: " + f2 + " seconds");
        }
    }
}
