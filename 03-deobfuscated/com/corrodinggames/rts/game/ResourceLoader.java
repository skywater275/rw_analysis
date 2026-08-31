/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.PathState;
import com.corrodinggames.rts.game.units.DecorType2;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import java.io.IOException;

public strictfp class ResourceLoader {
    public com.corrodinggames.rts.gameFramework.rendering.Texture a;  // 02b j.java L12: m.e a
    com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface b;  // 02b j.java L13: y b (m.y)
    com.corrodinggames.rts.gameFramework.rendering.UniquePaint c;  // 02b j.java L14: ag c
    com.corrodinggames.rts.gameFramework.rendering.Shader d;  // 02b j.java L15: ae d
    Paint e = new Paint();
    Rect f = new Rect(-101, 0, -1, 100);
    boolean g;

    public ResourceLoader() {
        this.c = new UniquePaint();
    }

    public ResourceLoader(String string) {
        this();
        try {
            this.d = new Shader(string);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.c.a(this.d);
        if (this.d.o != 0) {
            this.g = true;
        }
    }

    public boolean a() {
        if (this.d != null && this.d.o != 0) {
            return true;
        }
        return this.g;
    }

    public void a(TextureManagerInterface y2) {
        this.a(y2, y2.m(), y2.n(), 10);
    }

    public void a(TextureManagerInterface y2, int n2, int n3, int n4) {
        if (this.g) {
            return;
        }
        if (this.a != null && (n2 > this.a.m() || n3 > this.a.l())) {
            this.a.o();
            this.a = null;
            this.b = null;
        }
        if (this.a == null) {
            try {
                this.a = y2.a(n2 + n4, n3 + n4, true);
                this.b = y2.a(this.a);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.g = true;
                GlobalState.a(ResourceDomainEnum.b, outOfMemoryError);
                return;
            }
        }
        this.b.a(n2, n3);
    }

    public void b() {
        GlobalState l2 = GlobalState.B();
        l2.bO.b(this.f, this.e);
        l2.bO.b(this.a, 0.0f, 0.0f, (Paint)this.c);
    }
}