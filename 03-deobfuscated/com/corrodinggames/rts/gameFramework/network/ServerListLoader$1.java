/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.MusicController;
import com.corrodinggames.rts.gameFramework.network.GameServerInfo;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.network.ServerListLoader;
import com.corrodinggames.rts.gameFramework.network.PacketSerializer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.io.IOException;

class ServerListLoader$1
extends ServerListCallback {  // 02b j/s$1 extends j/s
    final /* synthetic */ int a;
    final /* synthetic */ GlobalState b;
    final /* synthetic */ ServerListLoader c;

    ServerListLoader$1(ServerListLoader q2, int n, GlobalState l2) {
        this.c = q2;
        this.a = n;
        this.b = l2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    /* 覆写 ServerListCallback.a (已 throws) */
    void a(BufferedReader bufferedReader, int n2, String string) throws IOException {
        String string2;
        GlobalState l2 = GlobalState.B();
        String string3 = bufferedReader.readLine();
        if (string3 == null || !string3.contains("CORRODINGGAMES")) {
            String string4 = n2 + ": Unknown header from the master server: '" + com.corrodinggames.rts.gameFramework.GameUtils.a(string3, 30) + "'";
            GlobalState.b("LoadFromMasterServer", string4);
            this.d = string4;
            try {
                String string5 = "";
                string5 = string5 + string3 + "\n";
                GlobalState.e("----------- Full response ----------");
                GlobalState.b("LoadFromMasterServer", "line:" + string3);
                String string6 = "";
                while ((string6 = bufferedReader.readLine()) != null) {
                    GlobalState.b("LoadFromMasterServer", "line:" + string6);
                    string5 = string5 + string6 + "\n";
                }
                GlobalState.e("------------------------------------");
                WebAPIClient.g = string5;  // 02b n.g (v19.133f2 修正)
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }
        GlobalState.b("LoadFromMasterServer", n2 + ": Starting load");
        int n3 = 0;
        while ((string2 = bufferedReader.readLine()) != null) {
            Object object;
            String string7;
            String[] stringArray = string2.split(",", -1);
            if (stringArray.length <= 21) {
                GlobalState.b("LoadFromMasterServer", n2 + ": columns.length too short at:" + stringArray.length);
                GlobalState.b("LoadFromMasterServer", n2 + ": short line is:" + string2);
                continue;
            }
            String string8 = stringArray[0];
            String string9 = stringArray[1];
            String string10 = stringArray[2];
            String string11 = stringArray[3];
            String string12 = stringArray[4];
            String string13 = stringArray[5];
            String string14 = stringArray[6];
            String string15 = stringArray[7];
            String string16 = stringArray[8];
            String string17 = stringArray[9];
            String string18 = stringArray[10];
            String string19 = stringArray[11];
            String string20 = stringArray[12];
            String string21 = stringArray[13];
            String string22 = stringArray[15];
            String string23 = stringArray[16];
            String string24 = stringArray[17];
            String string25 = stringArray[18];
            String string26 = stringArray[19];
            String string27 = stringArray[20];
            String string28 = stringArray[21];
            String string29 = null;
            String string30 = null;
            if (string11 != null && string11.startsWith("url:") && Boolean.parseBoolean(string24)) {
                string29 = string11.substring(4);
                string30 = string9;
                string7 = string29 + ";" + string30;
                object = com.corrodinggames.rts.gameFramework.GameUtils.c(string7);
                if (!((String)object).equals(string12)) {
                    GlobalState.e("Skipping " + string25);
                    continue;
                }
            }
            if (string25 == null || string25.trim().length() == 0) {
                string25 = string8;
            }
            try {
                string7 = string25;
                object = WebAPIClient.f;  // 02b n.f (v19.133f2 修正)
                synchronized (object) {
                    GameServerInfo g2 = WebAPIClient.c(string7);  // 02b q$1: n=WebAPIClient
                    g2.c = string11;
                    g2.d = string12;
                    g2.e = string29;
                    g2.f = string30;
                    g2.g = Integer.valueOf(string13);
                    g2.h = Boolean.parseBoolean(string14);
                    g2.m = Boolean.parseBoolean(string16);
                    g2.j = string10;
                    try {
                        g2.l = Integer.parseInt(g2.j);
                    }
                    catch (NumberFormatException numberFormatException) {
                        GlobalState.b("game_version_int:" + numberFormatException.getMessage());
                    }
                    g2.n = string15;
                    g2.q = string17;
                    g2.r = string18;
                    g2.s = string19;
                    g2.k = string20;
                    g2.a = Boolean.parseBoolean(string21);
                    g2.t = string22;
                    g2.u = string23;
                    g2.y = Boolean.parseBoolean(string26);
                    if ("".equals(string27)) {
                        string27 = null;
                    }
                    g2.z = string27;
                    if (!string28.trim().equals("")) {
                        g2.A = Integer.valueOf(string28);
                    }
                    try {
                        g2.v = Integer.parseInt(g2.t);
                    }
                    catch (NumberFormatException numberFormatException) {
                        GlobalState.b("game_player_count_int:" + numberFormatException.getMessage());
                    }
                    try {
                        g2.protocolVersion = Integer.parseInt(g2.u);
                    }
                    catch (NumberFormatException numberFormatException) {
                        GlobalState.b("game_max_player_count_int:" + numberFormatException.getMessage());
                    }
                    g2.x = Boolean.parseBoolean(string24);
                    if (g2.serverPort < this.a) {
                        g2.serverPort = this.a;
                    }
                    if (WebAPIClient.b(g2.b) == null) {  // 02b n.b(String) (v19.133f2 修正)
                        l2.bX.bi.add(g2);
                    }
                    ++n3;
                }
            }
            catch (NumberFormatException numberFormatException) {
                GlobalState.b("LoadFromMasterServer", n2 + ": failed to load server");
                numberFormatException.printStackTrace();
            }
        }
        GlobalState.b("LoadFromMasterServer", "[" + n2 + "]: Found " + n3 + " servers");
        if (n3 == 0) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        this.e = true;
        this.c.a.run();
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        WebAPIClient.a(this.a, n2);  // 02b q$1: n=WebAPIClient
        GlobalState.b("LoadFromMasterServer", n2 + ": Completed load from master server without error");
    }

    @Override
    void a() {
        if (!this.e) {
            this.b.bX.bh = this.d;  // 02b q$1.java L192: b.bX.bh (bX=NetEngine.bh String)
            this.c.a.run();
        }
    }
}
