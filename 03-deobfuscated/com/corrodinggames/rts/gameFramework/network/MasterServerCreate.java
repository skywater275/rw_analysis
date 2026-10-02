/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.SecurityHasher;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class MasterServerCreate
implements Runnable {
    void y() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        long l2 = GlobalState.V();
        GlobalState l3 = GlobalState.B();
        GlobalState.b("StartCreateOnMasterServer", "Starting create");
        try {
            ArrayList arrayList = new ArrayList(2);
            WebAPIClient.a(arrayList, "action", "add");
            String string = "u_" + com.corrodinggames.rts.gameFramework.GameUtils.b();  // 02b f.b()
            WebAPIClient.a(arrayList, "user_id", string);
            MasterServerAuth.a.a(string, arrayList);  // 02b j/aq
            WebAPIClient.a(arrayList, "game_name", "Unnamed");
            WebAPIClient.a(arrayList, "game_version", Integer.toString(l3.c(true)));
            if (!l3.bX.v) {
                WebAPIClient.a(arrayList, "game_version_string", l3.v());
            } else {
                WebAPIClient.a(arrayList, "game_version_string", "ANY");
            }
            WebAPIClient.a(arrayList, "game_version_beta", com.corrodinggames.rts.gameFramework.GameUtils.a(l3.n()));  // 02b f.a
            String string2 = l3.bX.au();
            if (string2 != null) {
                WebAPIClient.a(arrayList, "game_mods", string2);
            }
            WebAPIClient.a(arrayList, "private_token", l3.bX.aR);
            WebAPIClient.a(arrayList, "private_token_2", com.corrodinggames.rts.gameFramework.GameUtils.b(com.corrodinggames.rts.gameFramework.GameUtils.b(l3.bX.aR)));  // 02b f.b
            WebAPIClient.a(arrayList, "confirm", com.corrodinggames.rts.gameFramework.GameUtils.b("a" + com.corrodinggames.rts.gameFramework.GameUtils.b(l3.bX.aR)));
            WebAPIClient.b(arrayList);
            BufferedReader bufferedReader = WebAPIClient.a(arrayList, 15);
            String string3 = bufferedReader.readLine();
            if (string3 == null || !string3.contains("CORRODINGGAMES")) {
                GlobalState.b("StartCreateOnMasterServer", "Error bad header returned from the master server: " + string3);
                return;
            }
            String string4 = bufferedReader.readLine();
            String[] stringArray = string4.split(",");
            if (stringArray.length < 1) {
                GlobalState.b("StartCreateOnMasterServer", "columns.length too short at:" + stringArray.length);
            }
            String string5 = stringArray[0];
            try {
                String string6 = string5;
                GlobalState.b("StartCreateOnMasterServer", "Created server is:" + string6);
                l3.bX.aS = string6;
            }
            catch (NumberFormatException numberFormatException) {
                GlobalState.b("StartCreateOnMasterServer", "failed to load server");
                numberFormatException.printStackTrace();
            }
            if (stringArray.length >= 2) {
                try {
                    MasterServerAuth.f = Integer.parseInt(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    MasterServerAuth.f = -1;
                }
            }
            GlobalState.b("StartCreateOnMasterServer", "Completed create from master server without error");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        finally {
            float f2 = (float)(GlobalState.V() - l2) / 1000000.0f;
            GlobalState.b("StartCreateOnMasterServer", "create took: " + f2 + " seconds");
        }
    }
}
