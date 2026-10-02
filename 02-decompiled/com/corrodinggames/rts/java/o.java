/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import android.content.Context;
import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.ad;
import com.corrodinggames.rts.gameFramework.utility.j;
import com.corrodinggames.rts.java.audio.a.a;
import com.corrodinggames.rts.java.audio.a.c;
import com.corrodinggames.rts.java.audio.lwjgl.OpenALAudio;
import com.corrodinggames.rts.java.p;
import com.corrodinggames.rts.java.q;
import com.corrodinggames.rts.java.r;
import java.util.concurrent.LinkedBlockingQueue;

public class o
extends h {
    final int a = 15;
    LinkedBlockingQueue b = new LinkedBlockingQueue();
    ad c = new ad(15);
    r d;
    Context e;
    public OpenALAudio f;

    public Object b() {
        return this.f;
    }

    public o(OpenALAudio openALAudio) {
        for (int k = 0; k < 15; ++k) {
            this.c.a(new p());
        }
        this.f = openALAudio;
    }

    @Override
    public void a(Context context) {
        if (this.e != null) {
            l.e("SlickSoundFactory:setContext context already set");
            return;
        }
        this.e = context;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public i a(int n2) {
        String string = com.corrodinggames.rts.gameFramework.f.f(n2);
        q q2 = new q(this, string, this);
        String string2 = com.corrodinggames.rts.gameFramework.f.f(n2);
        if (string2 == null) {
            throw new RuntimeException("Failed to find sound for res id:" + n2);
        }
        Object object = this.b();
        synchronized (object) {
            a a2 = new a(string2);
            q2.a = this.f.newSound(a2);
        }
        return q2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public i a(String string, j j2, boolean bl) {
        o o2 = this;
        if (!bl) {
            o2 = null;
        }
        q q2 = new q(this, string, o2);
        try {
            Object object = this.b();
            synchronized (object) {
                q2.a = this.f.newSound(new a(j2, j2.d()));
            }
        }
        catch (c c2) {
            c2.printStackTrace();
            return null;
        }
        return q2;
    }

    @Override
    public void a() {
        if (this.d != null) {
            throw new RuntimeException("startThreads: soundThread!=null");
        }
        this.d = new r(this);
        this.d.start();
    }
}
