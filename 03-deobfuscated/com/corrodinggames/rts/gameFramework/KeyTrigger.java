/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.KeyBindingManager;
import com.corrodinggames.rts.gameFramework.TextureCache;

public class KeyTrigger
extends TextureCache {
    int e;


    public boolean a(TextureCache af2) {
        if (!(af2 instanceof KeyTrigger)) {
            return false;
        }
        KeyTrigger ag2 = (KeyTrigger) af2;
        if (this.e != ag2.e) {
            return false;
        }
        return super.a(af2);
    }


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


    public boolean b() {
        return ac.b.a(this.e, this.b, false);
    }


    public String c() {
        if (this.e == 0) {
            return "";
        }
        return ac.b.c(this.e, this.b);
    }


    public boolean d() {
        return this.e == 0;
    }
}
