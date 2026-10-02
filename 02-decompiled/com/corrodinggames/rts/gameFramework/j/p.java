/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.ClientProtocolException
 *  org.apache.http.message.BasicNameValuePair
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.l;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

class p
implements Runnable {
    p() {
    }

    @Override
    public void run() {
        l.aq();
        l l2 = l.B();
        l.b("GetOwnInfoRunnable", "Starting getOwnInfoFromMasterServer");
        try {
            String string;
            ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>(2);
            arrayList.add(new BasicNameValuePair("action", "self_info"));
            n.a(arrayList, "port", Integer.toString(l2.bX.m));
            n.a(arrayList, "id", l2.bX.aS);
            aq.a.b(l2.bX.aS, arrayList);
            aq.a.c(l2.bX.aS, arrayList);
            BufferedReader bufferedReader = n.a(arrayList);
            String string2 = bufferedReader.readLine();
            if (string2 == null || !string2.contains("CORRODINGGAMES")) {
                l.b("GetOwnInfoRunnable", "Error bad header returned from the master server: " + string2);
                return;
            }
            while ((string = bufferedReader.readLine()) != null) {
                String[] stringArray = string.split(",");
                if (stringArray.length <= 1) {
                    l.b("GetOwnInfoRunnable", "columns.length too short at:" + stringArray.length);
                    continue;
                }
                String string3 = stringArray[0];
                String string4 = stringArray[1];
                try {
                    l.b("GetOwnInfoRunnable", "got info");
                    l2.bX.a(true, string3, (Boolean)Boolean.parseBoolean(string4));
                }
                catch (NumberFormatException numberFormatException) {
                    l.b("GetOwnInfoRunnable", "failed to load server");
                    numberFormatException.printStackTrace();
                }
            }
            com.corrodinggames.rts.appFramework.p.l();
            l.b("GetOwnInfoRunnable", "Completed load from master server without error");
        }
        catch (ClientProtocolException clientProtocolException) {
            l2.bX.a(false, null, null);
            clientProtocolException.printStackTrace();
        }
        catch (IOException iOException) {
            l2.bX.a(false, null, null);
            iOException.printStackTrace();
        }
        catch (Exception exception) {
            l2.bX.a(false, null, null);
            l.a("GetOwnInfoRunnable Failed", exception);
        }
    }
}
