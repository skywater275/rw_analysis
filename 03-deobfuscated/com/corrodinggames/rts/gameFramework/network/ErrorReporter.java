/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.network;

import android.os.Build;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.steamworks.SteamEngine;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class ErrorReporter
implements Runnable {
    String a;
    String b;

    void v() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        long l2 = GlobalState.V();
        GlobalState l3 = GlobalState.B();
        GlobalState.b("SendErrorReport", "Starting");
        try {
            String string;
            ArrayList arrayList = new ArrayList(2);
            WebAPIClient.a(arrayList, "action", "error_report");
            WebAPIClient.a(arrayList, "game_version", Integer.toString(l3.c(false)));
            WebAPIClient.a(arrayList, "game_version_internal", Integer.toString(l3.c(true)));
            WebAPIClient.a(arrayList, "game_version_string", l3.r());
            WebAPIClient.a(arrayList, "package_name", l3.l());  // GlobalState.l() 抽象补后
            WebAPIClient.a(arrayList, "installation_source", l3.m());  // GlobalState.m() 抽象补后
            String string2 = "" + Build.VERSION.SDK_INT;
            if (GlobalState.av()) {
                string2 = "s:0;";
                if (com.corrodinggames.rts.gameFramework.steamworks.SteamEngine.a().e()) {  // 02b steamworks/a.a().e()
                    string2 = "s:1;";
                }
            }
            if (GlobalState.av() || com.corrodinggames.rts.gameFramework.GlobalState.aZ) {  // 02b l.aZ
                string = System.getProperty("os.name") + " - " + System.getProperty("os.version");
                WebAPIClient.a(arrayList, "system_version", string);
            }
            WebAPIClient.a(arrayList, "sdk_version", string2);
            WebAPIClient.a(arrayList, "device_model", l3.G());
            WebAPIClient.a(arrayList, "build_version", l3.H());
            WebAPIClient.a(arrayList, "release_version", com.corrodinggames.rts.gameFramework.GameUtils.a(GlobalState.as));  // 02b f.a(boolean)
            WebAPIClient.a(arrayList, "dedicated_server", com.corrodinggames.rts.gameFramework.GameUtils.a(GlobalState.aU()));  // 02b f.a(l.aU)
            string = "NA";
            if (l3.bX != null) {
                string = l3.bX.aR;
            }
            WebAPIClient.a(arrayList, "private_token", string);
            WebAPIClient.a(arrayList, "private_token_2", com.corrodinggames.rts.gameFramework.GameUtils.b(com.corrodinggames.rts.gameFramework.GameUtils.b(string)));
            WebAPIClient.a(arrayList, "message", this.a);
            WebAPIClient.a(arrayList, "stacktrace", this.b);
            GlobalState.b("SendErrorReport", "making request");
            BufferedReader bufferedReader = WebAPIClient.a(arrayList);
            String string3 = bufferedReader.readLine();
            if (string3 == null || !string3.contains("CORRODINGGAMES")) {
                GlobalState.b("StartCreateOnMasterServer", "Error bad header returned from the master server: " + string3);
                return;
            }
            GlobalState.b("SendErrorReport", "Send trace successfully");
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            float f2 = (float)(GlobalState.V() - l2) / 1000000.0f;
            GlobalState.b("SendErrorReport", "took: " + f2 + " seconds");
        }
    }
}
