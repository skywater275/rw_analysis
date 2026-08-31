/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.message.BasicNameValuePair
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.network.ServerResult;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.network.ServerResult;
import com.corrodinggames.rts.gameFramework.network.ServerStatus;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.message.BasicNameValuePair;

class MasterServerClient
implements Runnable {
    ServerResult a;
    String b;
    int c;
    String d;

    void ab() {
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        GlobalState l2 = GlobalState.B();
        GlobalState.b("getGameServerInfoFromMasterServer", "Starting getGameServerInfoFromMasterServer");
        String string = WebAPIClient.a(this.c);  // 02b n.a(int)
        boolean bl = false;
        String string2 = null;
        if (this.d != null) {
            string2 = com.corrodinggames.rts.gameFramework.GameUtils.c(this.b + this.d, 3);  // 02b f.c(String,int)
        }
        try {
            ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>(2);
            arrayList.add(new BasicNameValuePair("action", "get"));
            arrayList.add(new BasicNameValuePair("game_id", this.b));
            arrayList.add(new BasicNameValuePair("c", string));
            arrayList.add(new BasicNameValuePair("p_hash", string2));
            BufferedReader bufferedReader = WebAPIClient.a(arrayList);
            String string3 = bufferedReader.readLine();
            if (string3 == null || !string3.contains("CORRODINGGAMES")) {
                GlobalState.b("getGameServerInfoFromMasterServerRunnable", "Error bad header returned from the master server: " + string3);
                this.a.a("Unexpected header from master server", ServerStatus.b, null);  // 02b j/x=ServerStatus
                return;
            }
            String string4 = bufferedReader.readLine();
            if (string4 == null) {
                throw new IOException("Unexpected end of response");
            }
            if (string3.contains("[FAILED]")) {
                GlobalState.b("Got failed header with status:" + string4);
                String string5 = "Failed to get server connection data - unknown";
                ServerStatus x2 = ServerStatus.b;
                if (string4.startsWith("ERROR_OTHER")) {
                    String[] stringArray = string4.split(",");
                    if (stringArray.length >= 2) {
                        string5 = stringArray[1];
                    }
                    x2 = ServerStatus.b;
                } else if (string4.startsWith("ERROR_MISSING")) {
                    string5 = "Request missing required fields";
                } else if (!string4.startsWith("ERROR_WRONG_C")) {
                    if (string4.startsWith("ERROR_MISSING_PASSWORD")) {
                        string5 = "Missing password";
                        x2 = ServerStatus.b;
                    } else if (string4.startsWith("ERROR_WRONG_PASSWORD")) {
                        string5 = "Wrong password";
                        x2 = ServerStatus.a;
                    }
                }
                this.a.a(string5, x2, null);
                return;
            }
            String string6 = bufferedReader.readLine();
            if (string6 == null) {
                throw new IOException("Unexpected end of response");
            }
            String string7 = com.corrodinggames.rts.gameFramework.GameUtils.c("game_" + string).toLowerCase(Locale.ROOT);
            if (!string6.toLowerCase(Locale.ROOT).contains(string7)) {
                GlobalState.b("getGameServerInfoFromMasterServerRunnable", "Error bad header returned from the master server: " + string6);
                this.a.a("Unexpected return from master server", ServerStatus.b, null);
                return;
            }
            String string8 = bufferedReader.readLine();
            String string9 = bufferedReader.readLine();
            if (string9 == null) {
                throw new IOException("Unexpected end of response");
            }
            String[] stringArray = string9.split(",");
            if (stringArray.length <= 18) {
                throw new RuntimeException("getGameServerInfoFromMasterServerRunnable: columns.length too short at:" + stringArray.length);
            }
            String string10 = stringArray[3];
            String string11 = stringArray[4];
            String string12 = stringArray[5];
            String string13 = stringArray[6];
            String string14 = stringArray[7];
            String string15 = stringArray[8];
            String string16 = stringArray[9];
            String string17 = stringArray[10];
            String string18 = stringArray[11];
            String string19 = stringArray[12];
            String string20 = stringArray[13];
            String string21 = stringArray[15];
            String string22 = stringArray[16];
            String string23 = stringArray[17];
            String string24 = stringArray[18];
            GlobalState.b("getGameServerInfoFromMasterServerRunnable", "got ");
            GlobalState.b("getGameServerInfoFromMasterServerRunnable", "Completed get from master server without error");
            bl = true;
            this.a.a(string10 + ":" + string12);
        }
        catch (IOException iOException) {
            GlobalState.a("getGameServerInfoFromMasterServerRunnable Failed", iOException);
            this.a.a(iOException.getMessage(), ServerStatus.b, iOException);
            return;
        }
    }
}
