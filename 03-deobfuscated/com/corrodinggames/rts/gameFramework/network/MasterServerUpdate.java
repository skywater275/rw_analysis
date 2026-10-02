/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class MasterServerUpdate
implements Runnable {
    void aa() {
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        GlobalState l2 = GlobalState.B();
        try {
            ArrayList arrayList = new ArrayList(2);
            WebAPIClient.a(arrayList, "action", "update");
            String string = l2.bX.aS;
            if (string == null) {
                GlobalState.b("startUpdateOnMasterServer", "No game id");
                return;
            }
            WebAPIClient.a(arrayList, "id", string);
            WebAPIClient.a(arrayList, "private_token", l2.bX.aR);
            if (GlobalState.aU()) {
                WebAPIClient.a(arrayList, "check_port", "false");
            }
            WebAPIClient.b(arrayList);
            BufferedReader bufferedReader = WebAPIClient.a(arrayList);
            String string2 = bufferedReader.readLine();
            if (string2 == null || !string2.contains("CORRODINGGAMES")) {
                GlobalState.b("startUpdateOnMasterServer", "Error bad header returned from the master server: " + string2);
                return;
            }
            String string3 = bufferedReader.readLine();
            if (!"GAME UPDATED".equals(string3)) {
                GlobalState.b("startUpdateOnMasterServer", "Update server response was:" + string3);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
