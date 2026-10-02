/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.java.audio.backend.AudioManager;

public class l {
    public boolean a;
    AudioManager b;  // 02b l.java L8: final i b
    int c;
    int d;
    boolean e = true;

    public l(AudioManager i2) {
        this.b = i2;
        this.b();
    }

    public void b() {
        this.d = -2;
        this.c = -1;
        if (this.b.g) {
            this.a = true;
        } else {
            this.c();
        }
    }

    void c() {
        this.a = false;
        long[] lArray = this.b.b;
        int n = this.b.d + this.b.e;
        while (++this.c < n) {
            if (lArray[this.c] == 0L) continue;
            this.a = true;
            break;
        }
    }

    public void remove() {
        if (this.d == -1 && this.b.g) {
            this.b.f = null;
            this.b.g = false;
        } else {
            if (this.d < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            if (this.d >= this.b.d) {
                this.b.d(this.d);
                this.c = this.d - 1;
                this.c();
            } else {
                this.b.b[this.d] = 0L;
                this.b.c[this.d] = null;
            }
        }
        this.d = -2;
        --this.b.sampleRate;
    }
}
