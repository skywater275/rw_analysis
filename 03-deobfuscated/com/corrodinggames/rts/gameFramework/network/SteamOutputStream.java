/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.SteamSocket;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.OutputStream;

public class SteamOutputStream
extends OutputStream {
    boolean a = true;
    final /* synthetic */ SteamSocket b;  // 02b: h=SteamSocket (构造参数 h2 铁证)

    public SteamOutputStream(SteamSocket h2) {
        this.b = h2;
    }

    @Override
    public void write(int n2) {
        GlobalState.isKeyJustPressed("SteamSocketOutputStream: Slow write: " + n2);
        byte[] byArray = new byte[]{(byte)n2};
        this.write(byArray);
    }

    @Override
    public void write(byte[] byArray, int n2, int n3) {
        if (this.b.c) {
            GlobalState.e("cannot write steam socket closed");
            return;
        }
        GlobalState.e("Forwarded message to client: " + this.b.b + " with old method");
    }

    @Override
    public void write(byte[] byArray) {
        this.write(byArray, 0, byArray.length);
    }
}
