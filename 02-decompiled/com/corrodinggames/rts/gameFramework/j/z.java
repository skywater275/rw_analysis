/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class z
implements Runnable {
    z() {
    }

    @Override
    public void run() {
        l.aq();
        l l2 = l.B();
        l.b("startRemoveOnMasterServer", "Starting remove");
        try {
            ArrayList arrayList = new ArrayList(2);
            n.a(arrayList, "action", "remove");
            String string = l2.bX.aS;
            if (string == null) {
                l.b("startRemoveOnMasterServer", "No game id");
                return;
            }
            n.a(arrayList, "id", string);
            n.a(arrayList, "private_token", l2.bX.aR);
            BufferedReader bufferedReader = n.a(arrayList);
            String string2 = bufferedReader.readLine();
            if (string2 == null || !string2.contains("CORRODINGGAMES")) {
                l.b("startRemoveOnMasterServer", "Error bad header returned from the master server: " + string2);
                return;
            }
            String string3 = bufferedReader.readLine();
            l.b("startRemoveOnMasterServer", "Remove server response was:" + string3);
            l.b("startRemoveOnMasterServer", "Completed load from master server without error");
        }
        catch (IOException iOException) {
            l.b("startRemoveOnMasterServer", "Remove failed");
            iOException.printStackTrace();
        }
    }
}
