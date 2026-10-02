/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.message.BasicNameValuePair
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import com.corrodinggames.rts.gameFramework.network.RequestTimeout;

import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.message.BasicNameValuePair;

class ServerListLoader
implements Runnable {
    Runnable a;

    void q(Runnable runnable) {
        this.a = runnable;
    }

    @Override
    public void run() {
        GlobalState.initIntegrityAndCrashHandler();
        GlobalState l2 = GlobalState.B();
        GlobalState.b("LoadFromMasterServer", "Starting load");
        int n2 = WebAPIClient.e++;  // 02b j/q: n.e++
        try {
            Timer timer = new Timer();
            timer.schedule((TimerTask)new RequestTimeout(n2), 5000L);
            ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>(2);
            arrayList.add(new BasicNameValuePair("action", "list"));
            arrayList.add(new BasicNameValuePair("game_version", Integer.toString(l2.c(true))));
            arrayList.add(new BasicNameValuePair("game_version_beta", com.corrodinggames.rts.gameFramework.GameUtils.a(l2.n())));  // 02b f.a
            boolean bl = false;
            WebAPIClient.a(arrayList, false, new ServerListLoader$1(this, n2, l2));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            l2.a("Error getting game list from server", 1);  // 02b j/q: a(String,int) (PerformanceMonitor$1 为幻觉)
        }
    }
}
