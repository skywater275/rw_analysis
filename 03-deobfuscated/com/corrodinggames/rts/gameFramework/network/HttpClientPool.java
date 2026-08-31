/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.HttpClient
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 */
package com.corrodinggames.rts.gameFramework.network;

import android.net.http.AndroidHttpClient;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientPool {
    ReentrantLock a = new ReentrantLock();

    public HttpClient a() {
        return this.a(30000, false);
    }

    public HttpClient b() {
        return this.a(30000, true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public HttpClient a(int n2, boolean bl) {
        HttpClient androidHttpClient;  // 02b r.java L33: Object var4 (接口化)
        boolean bl2 = false;
        try {
            bl2 = this.a.tryLock(300L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        try {
            if (!bl2) {
                GlobalState.b("LoadFromMasterServer", "Could not get getNewHttpClient lock! another thread maybe stuck in getNewHttpClient!");
            }
            androidHttpClient = !bl ? AndroidHttpClient.a((String)null) : new DefaultHttpClient();  // 02b r.java L40 显式 cast
            HttpParams httpParams = androidHttpClient.getParams();
            HttpConnectionParams.setConnectionTimeout((HttpParams)httpParams, (int)n2);
            HttpConnectionParams.setSoTimeout((HttpParams)httpParams, (int)20000);
        }
        finally {
            if (bl2) {
                this.a.unlock();
            }
        }
        return androidHttpClient;
    }

    public void a(HttpClient httpClient) {
        if (httpClient instanceof AndroidHttpClient) {
            AndroidHttpClient androidHttpClient = (AndroidHttpClient)httpClient;
            androidHttpClient.a();
        }
    }
}
