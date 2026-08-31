/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.util.Log;
import com.corrodinggames.rts.gameFramework.MusicController;

class MusicController$1
extends Thread {
    final /* synthetic */ MusicController a;

    MusicController$1(MusicController am2) {
        this.a = am2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        if (this.a.j) {
            Log.a("RustedWarfare", "Music:pause() unsynchronized");
            this.a.g();
        } else {
            Object object = this.a.b;
            synchronized (object) {
                Log.a("RustedWarfare", "Music:pause() synchronized");
                this.a.g();
            }
        }
    }
}
