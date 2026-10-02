/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.h;
import com.corrodinggames.rts.gameFramework.l;
import java.io.OutputStream;

public class j
extends OutputStream {
    boolean a = true;
    final /* synthetic */ h b;

    public j(h h2) {
        this.b = h2;
    }

    @Override
    public void write(int n2) {
        l.g("SteamSocketOutputStream: Slow write: " + n2);
        byte[] byArray = new byte[]{(byte)n2};
        this.write(byArray);
    }

    @Override
    public void write(byte[] byArray, int n2, int n3) {
        if (this.b.c) {
            l.e("cannot write steam socket closed");
            return;
        }
        l.e("Forwarded message to client: " + this.b.b + " with old method");
    }

    @Override
    public void write(byte[] byArray) {
        this.write(byArray, 0, byArray.length);
    }
}
