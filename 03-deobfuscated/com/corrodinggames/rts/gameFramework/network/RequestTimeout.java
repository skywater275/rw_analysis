/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.WebAPIClient;
import java.util.TimerTask;

class RequestTimeout
extends TimerTask {
    int a;

    RequestTimeout(int n2) {  // 02b j/o.java 构造 (int)
        this.a = n2;
    }

    void o(int n) {
        this.a = n;
    }

    @Override
    public void run() {
        WebAPIClient.a(this.a, -1);  // 02b j/n.a(int,int)
    }
}
