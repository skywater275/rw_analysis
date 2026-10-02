/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.ProjectileType2;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.network.PacketSerializer;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.List;

class ServerListFetcher
implements Runnable {
    int a;
    List b;
    ServerListCallback c;  // 02b j/s (ProjectileType2 为幻觉名)
    String d;
    boolean e;

    public ServerListFetcher(List list, ServerListCallback s2, String string, boolean bl, int n) {
        this.a = n;
        this.b = list;
        this.c = s2;
        this.d = string;
        this.e = bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        Object object;
        block10: {
            GlobalState l2 = GlobalState.B();
            try {
                if (WebAPIClient.a) {
                    GlobalState.b("LoadFromMasterServer", this.a + ": Started doSingleRequest");
                }
                object = WebAPIClient.a((List)this.b, (String)this.d, (boolean)this.e).a;
                if (WebAPIClient.a) {
                    GlobalState.b("LoadFromMasterServer", this.a + ": Ended doSingleRequest");
                }
                this.c.a((BufferedReader)object, this.a, this.d);
            }
            catch (Exception exception) {
                exception.printStackTrace();
                String string = com.corrodinggames.rts.gameFramework.GameUtils.a(exception, true);
                if (exception instanceof UnknownHostException) {
                    string = "DNS lookup failed, check your internet connection";
                }
                if (string != null && string.contains("Cleartext HTTP traffic")) {
                    string = string + " ( Broken apk file? - " + l2.getVersion() + ")";  // 02b l.l() 对应待查
                }
                this.c.d = "#" + this.a + ": " + string;
                String string2 = "Error getting game list from server #" + this.a;
                GlobalState.e(string2);
                if (!l2.p()) break block10;
                l2.a("Error getting game list from server #" + this.a, 1);
            }
        }
        object = this.c;
        synchronized (object) {
            --this.c.f;
            if (this.c.f == 0) {
                this.c.a();
            }
        }
    }
}
