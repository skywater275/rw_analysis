/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.ar;
import com.corrodinggames.rts.gameFramework.as;
import com.corrodinggames.rts.java.audio.Music;
import com.corrodinggames.rts.java.l;
import com.corrodinggames.rts.java.m;

public class n
extends as {
    m a;
    l b;
    Music c;
    boolean d = false;
    boolean e = false;
    boolean f = false;

    public n(l l2) {
        this.b = l2;
    }

    @Override
    public void a(ar ar2) {
        this.a = (m)ar2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(boolean bl) {
        Object object = this.b.f();
        synchronized (object) {
            this.d = true;
            this.e = bl;
            this.f = false;
            com.corrodinggames.rts.gameFramework.l.e("Queued:" + this.a.b);
            if (this.c != null) {
                com.corrodinggames.rts.gameFramework.l.e("startPlaying: Stopping old music");
                this.c.stop();
            }
            this.c = this.a.c;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void f() {
        if (this.f) {
            return;
        }
        Object object = this.b.f();
        synchronized (object) {
            if (this.c != null) {
                com.corrodinggames.rts.gameFramework.l.e("Now playing:" + this.a.b);
                if (this.e) {
                    this.c.setVolume(this.c.getVolume());
                    this.c.setLooping(true);
                    this.c.play();
                } else {
                    this.c.setVolume(this.c.getVolume());
                    this.c.play();
                }
                this.f = true;
            } else {
                com.corrodinggames.rts.gameFramework.l.e("realPlay: playingMusic==null");
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a() {
        Object object = this.b.f();
        synchronized (object) {
            if (this.c != null) {
                this.c.pause();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void b() {
        Object object = this.b.f();
        synchronized (object) {
            if (this.c != null && !this.c.isPlaying()) {
                this.c.play();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void d() {
        Object object = this.b.f();
        synchronized (object) {
            if (this.c != null) {
                this.c.stop();
                this.f = false;
                this.d = false;
                this.c = null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void e() {
        Object object = this.b.f();
        synchronized (object) {
            if (this.c != null) {
                this.c.stop();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean c() {
        Object object = this.b.f();
        synchronized (object) {
            if (this.f && this.c != null) {
                return this.c.isPlaying();
            }
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(float f2) {
        Object object = this.b.f();
        synchronized (object) {
            if (this.c != null) {
                if (f2 > 0.05f && !this.f && this.d) {
                    this.f();
                }
                this.c.setVolume(f2);
            } else {
                com.corrodinggames.rts.gameFramework.l.e("setVolume: playingMusic==null");
            }
        }
    }
}
