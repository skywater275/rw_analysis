/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ag;
import com.corrodinggames.rts.gameFramework.l;
import java.io.IOException;
import java.net.Socket;

public strictfp class an
implements Runnable {
    String a;
    boolean b;
    boolean c;
    Thread d;
    public String e;
    Runnable f;
    public Socket g;
    boolean h = false;

    public an(String string, boolean bl, Runnable runnable) {
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
            this.g = ad.b(this.a, this.b);
        }
        catch (IOException iOException) {
            String string;
            this.e = string = iOException.getMessage();
            iOException.printStackTrace();
            return;
        }
        catch (ag ag2) {
            l.e("Cancelled connectSocketToServer");
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
