/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Font
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.Slick2DRenderer;
import org.newdawn.slick.Font;

class FontKey {
    int a;
    boolean b;
    boolean c;
    Font d;
    int e;
    String[] f = new String[30];
    final /* synthetic */ Slick2DRenderer g;

    FontKey(Slick2DRenderer e2) {
        this.g = e2;
    }

    public FontKey a() {
        FontKey f2 = new FontKey(this.g);
        f2.a = this.a;
        f2.b = this.b;
        f2.c = this.c;
        return f2;
    }

    public String toString() {
        return "FontKey:(size:" + this.a + ",  bold:" + this.b + " fallback:" + this.c + ")";
    }

    boolean a(String string) {
        if (string == null) {
            return true;
        }
        boolean bl = Slick2DRenderer.a(string);
        if (!bl) {
            return true;
        }
        for (int i = 0; i < this.f.length; ++i) {
            String string2 = this.f[i];
            if (string2 == null || !string2.equals(string)) continue;
            return true;
        }
        return false;
    }

    void b(String string) {
        this.f[this.e] = string;
        ++this.e;
        if (this.e >= this.f.length) {
            this.e = 0;
        }
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }
}
