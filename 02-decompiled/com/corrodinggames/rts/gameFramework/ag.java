/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.af;

public class ag
extends af {
    int e;

    @Override
    public boolean a(af af2) {
        if (!(af2 instanceof ag)) {
            return false;
        }
        ag ag2 = (ag)af2;
        if (this.e != ag2.e) {
            return false;
        }
        return super.a(af2);
    }

    @Override
    public boolean a() {
        if (ac.b.a(this.e, this.b, false)) {
            if (!this.c) {
                this.c = true;
                return true;
            }
            return false;
        }
        if (ac.b.a(this.e, this.b, true)) {
            this.c = true;
        } else if (this.c) {
            this.c = false;
        }
        return false;
    }

    @Override
    public boolean b() {
        return ac.b.a(this.e, this.b, false);
    }

    @Override
    public String c() {
        if (this.e == 0) {
            return "";
        }
        return ac.b.c(this.e, this.b);
    }

    @Override
    public boolean d() {
        return this.e == 0;
    }
}
