/*
 * 02b j/u.java 直译: 并行服务器请求单线程
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.List;

class RequestsParallelRunnable
implements Runnable {
    int a;
    List b;
    ServerListCallback c;
    String d;
    boolean e;

    public RequestsParallelRunnable(List list, ServerListCallback serverListCallback, String string, boolean bl, int n2) {
        this.a = n2;
        this.b = list;
        this.c = serverListCallback;
        this.d = string;
        this.e = bl;
    }

    @Override
    public void run() {
        GlobalState l2 = GlobalState.B();
        try {
            if (WebAPIClient.a) {
                GlobalState.b("LoadFromMasterServer", this.a + ": Started doSingleRequest");
            }
            BufferedReader bufferedReader = WebAPIClient.a(this.b, this.d, this.e).a;
            if (WebAPIClient.a) {
                GlobalState.b("LoadFromMasterServer", this.a + ": Ended doSingleRequest");
            }
            this.c.a(bufferedReader, this.a, this.d);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            String string = GameUtils.a(exception, true);
            if (exception instanceof UnknownHostException) {
                string = "DNS lookup failed, check your internet connection";
            }
            if (string != null && string.contains("Cleartext HTTP traffic")) {
                string = string + " ( Broken apk file? - " + l2.getVersion() + ")";  // 02b l.l() 对应待查 (PENDING)
            }
            this.c.d = "#" + this.a + ": " + string;
            String string2 = "Error getting game list from server #" + this.a;
            GlobalState.e(string2);
            if (l2.p()) {
                l2.a("Error getting game list from server #" + this.a, 1);
            }
        }
        ServerListCallback serverListCallback = this.c;
        synchronized (this.c) {
            --this.c.f;
            if (this.c.f == 0) {
                this.c.a();
            }
        }
    }
}
