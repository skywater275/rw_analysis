/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class MasterServerRemove
implements Runnable {
    void z() {
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        GlobalState l2 = GlobalState.B();
        GlobalState.b("startRemoveOnMasterServer", "Starting remove");
        try {
            ArrayList arrayList = new ArrayList(2);
            WebAPIClient.a(arrayList, "action", "remove");
            String string = l2.bX.aS;
            if (string == null) {
                GlobalState.b("startRemoveOnMasterServer", "No game id");
                return;
            }
            WebAPIClient.a(arrayList, "id", string);
            WebAPIClient.a(arrayList, "private_token", l2.bX.aR);
            BufferedReader bufferedReader = WebAPIClient.a(arrayList);
            String string2 = bufferedReader.readLine();
            if (string2 == null || !string2.contains("CORRODINGGAMES")) {
                GlobalState.b("startRemoveOnMasterServer", "Error bad header returned from the master server: " + string2);
                return;
            }
            String string3 = bufferedReader.readLine();
            GlobalState.b("startRemoveOnMasterServer", "Remove server response was:" + string3);
            GlobalState.b("startRemoveOnMasterServer", "Completed load from master server without error");
        }
        catch (IOException iOException) {
            GlobalState.b("startRemoveOnMasterServer", "Remove failed");
            iOException.printStackTrace();
        }
    }
}
