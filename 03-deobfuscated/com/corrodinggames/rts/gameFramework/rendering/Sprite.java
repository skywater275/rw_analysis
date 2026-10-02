/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureFrame;
import com.corrodinggames.rts.gameFramework.rendering.CustomColorFilter;
import com.corrodinggames.rts.gameFramework.rendering.w;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import java.util.ArrayList;
import java.util.HashMap;

public class Sprite {
    public Texture a;
    public TextureManagerInterface b;
    public Paint c;
    int d = 0;
    boolean e;
    int f;
    boolean g = false;
    int h = 0;
    int i = 0;
    int j = 0;
    int k = 1;
    HashMap l = new HashMap();
    ArrayList m = new ArrayList();

    public Sprite(int n, int n2) {
        this.getRegion(n, n2);
    }

    public void getRegion(int n2, int n3) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        com.corrodinggames.rts.gameFramework.GlobalState.e("Creating BitmapOrTextureAlias: " + n2 + "x" + n3);
        this.a = l2.bO.a(n2, n3, true);
        this.b = l2.bO.b(this.a);
        this.c = new UniquePaint();
        this.c.a(new CustomColorFilter(w.b));
    }

    public void getRegion(Texture e2, int n2, int n3) {
        this.b.clearScreen(e2, (float)n2, (float)n3, this.c);
        this.b.p();
    }

    public void getRegion() {
        this.b.a(0, PorterDuff.Mode.CLEAR);
    }

    public void packRegion() {
        this.d = 0;
        this.e = false;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.l.clear();
        this.getRegion();
    }

    public void tick() {
        ++this.f;
        if (this.e && this.f > 600) {
            this.g = true;
            this.m.clear();
        }
    }

    public void rebuild() {
        if (this.g) {
            this.g = false;
            this.packRegion();
            for (Texture e2 : (java.util.Collection<Texture>) (java.util.Collection) this.m) {
                this.getRegion(e2);
            }
            this.m.clear();
        }
    }

    public TextureFrame getRegion(Texture e2) {
        TextureFrame g2 = (TextureFrame) this.l.get(e2);
        if (g2 != null) {
            if (this.g) {
                this.m.add(e2);
            }
            if (g2.f != e2.e) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("BitmapOrTextureAlias: Image was updated: " + e2.a());
                this.l.remove(e2);
            } else {
                return g2;
            }
        }
        if ((g2 = this.packRegion(e2)) != null) {
            return g2;
        }
        return null;
    }

    public TextureFrame packRegion(Texture e2) {
        int n2 = e2.m();
        int n3 = e2.l();
        int n4 = this.a.m();
        int n5 = this.a.l();
        if (this.h + n2 > n4) {
            this.h = 0;
            this.i += this.j + this.k;
            this.j = 0;
        }
        if (this.i + n3 > n5) {
            if (!this.e) {
                this.e = true;
            }
            return null;
        }
        TextureFrame g2 = new TextureFrame();
        g2.a = this.a;
        int n6 = this.h;
        int n7 = this.i;
        this.h += n2 + this.k;
        if (this.j < n3) {
            this.j = n3;
        }
        this.getRegion(e2, n6, n7);
        g2.b = n6;
        g2.c = n7;
        g2.d = n2;
        g2.e = n3;
        g2.f = e2.e;
        ++this.d;
        this.l.put(e2, g2);
        return g2;
    }
}