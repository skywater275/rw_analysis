/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.TextureCache;

public class AxisTrigger
extends TextureCache {
    int e = -1;
    int f = -1;
    boolean g;
    int h = -1;
    float i;
    boolean j = false;


    public boolean a() {
        if (this.b()) {
            if (!this.c) {
                this.c = true;
                return true;
            }
            return false;
        }
        if (this.c) {
            this.c = false;
        }
        return false;
    }


    public boolean b() {
        return this.e() > 0.5f;
    }

    public float e() {
        return this.a(false);
    }

    public float a(boolean bl) {
        float f;
        if (this.h != -1) {
            f = ac.b.a(this.h, this.e) ? 0.0f : 1.0f;
        } else {
            f = ac.b.b(this.e, this.f);
            float f2 = f = this.g ? -f : f;
        }
        if (bl) {
            return f;
        }
        if (!this.j && Math.abs(f - this.i) > 0.001f) {
            this.j = true;
        }
        if (!this.j) {
            return 0.0f;
        }
        return f;
    }


    public String c() {
        return "controller";
    }


    public boolean d() {
        return false;
    }
}
