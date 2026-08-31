/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class aa
implements Runnable {
    aa() {
    }

    @Override
    public void run() {
        l.aq();
        l l2 = l.B();
        try {
            ArrayList arrayList = new ArrayList(2);
            n.a(arrayList, "action", "update");
            String string = l2.bX.aS;
            if (string == null) {
                l.b("startUpdateOnMasterServer", "No game id");
                return;
            }
            n.a(arrayList, "id", string);
            n.a(arrayList, "private_token", l2.bX.aR);
            if (l.ax()) {
                n.a(arrayList, "check_port", "false");
            }
            n.b(arrayList);
            BufferedReader bufferedReader = n.a(arrayList);
            String string2 = bufferedReader.readLine();
            if (string2 == null || !string2.contains("CORRODINGGAMES")) {
                l.b("startUpdateOnMasterServer", "Error bad header returned from the master server: " + string2);
                return;
            }
            String string3 = bufferedReader.readLine();
            if (!"GAME UPDATED".equals(string3)) {
                l.b("startUpdateOnMasterServer", "Update server response was:" + string3);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
