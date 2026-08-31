/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.message.BasicNameValuePair
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.o;
import com.corrodinggames.rts.gameFramework.j.q$1;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.message.BasicNameValuePair;

class q
implements Runnable {
    Runnable a;

    q(Runnable runnable) {
        this.a = runnable;
    }

    @Override
    public void run() {
        l.aq();
        l l2 = l.B();
        l.b("LoadFromMasterServer", "Starting load");
        int n2 = n.e++;
        try {
            Timer timer = new Timer();
            timer.schedule((TimerTask)new o(n2), 5000L);
            ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>(2);
            arrayList.add(new BasicNameValuePair("action", "list"));
            arrayList.add(new BasicNameValuePair("game_version", Integer.toString(l2.c(true))));
            arrayList.add(new BasicNameValuePair("game_version_beta", f.a(l2.n())));
            boolean bl = false;
            n.a(arrayList, false, new q$1(this, n2, l2));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            l2.a("Error getting game list from server", 1);
        }
    }
}
