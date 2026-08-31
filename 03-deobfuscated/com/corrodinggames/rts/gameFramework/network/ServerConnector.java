/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.NetworkException;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;
import java.net.Socket;

public strictfp class ServerConnector
implements Runnable {
    String a;
    boolean b;
    boolean c;
    Thread d;
    public String e;
    Runnable f;
    public Socket g;
    boolean h = false;

    public ServerConnector(String string, boolean bl, Runnable runnable) {
        this.a = string;
        this.b = bl;
        this.f = runnable;
    }

    public boolean a() {
        if (!this.c) {
            return false;
        }
        this.h = true;
        return true;
    }

    public void b() {
        this.c = true;
        this.d = new Thread(this);
        this.d.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        try {
            this.g = NetEngine.m(this.a, this.b);  // 02b an.java: ad.b(String,boolean)=03 NetEngine.m 静态 Socket 工厂
        }
        catch (IOException iOException) {
            String string;
            this.e = string = iOException.getMessage();
            iOException.printStackTrace();
            return;
        }
        catch (NetworkException ag2) {
            GlobalState.e("Cancelled connectSocketToServer");
            this.e = "CANCELLED";
        }
        finally {
            this.c = false;
            if (this.h) {
                if (this.g != null) {
                    try {
                        this.g.close();
                        this.g = null;
                        this.e = "cancelled";
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            } else {
                this.f.run();
            }
        }
    }
}
