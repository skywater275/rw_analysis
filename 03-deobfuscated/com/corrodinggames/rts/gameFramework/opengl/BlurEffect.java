/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import com.corrodinggames.rts.gameFramework.opengl.BlendMode;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.DefaultBlendMode;
import com.corrodinggames.rts.gameFramework.opengl.BlendCallback;
import com.corrodinggames.rts.gameFramework.opengl.k;
import com.corrodinggames.rts.gameFramework.opengl.x;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.List;

public class BlurEffect
extends DefaultBlendMode {
    protected List a;
    private List b;
    private GLObject c;
    private GLObject d;

    private void a(GLObject b2) {
        this.a();
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            this.b.add(new x(b2.b(), b2.c(), false));
        }
    }

    private void a() {
        for (x x2 : (java.util.Collection<x>) (java.util.Collection) this.b) {
            x2.j();
        }
        this.b.clear();
    }

    public GLObject a(GLObject b2, k k2, BlendCallback j2) {
        if (b2 instanceof x ? !((x)b2).k() : this.d == b2 && this.c != null) {
            return this.c;
        }
        if (this.b.size() != this.a.size() || this.d != b2) {
            this.a(b2);
        }
        this.d = b2;
        Object b3 = b2;
        int n2 = this.b.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            x x2 = (x)this.b.get(i2);
            BlendMode af2 = (BlendMode) this.a.get(i2);
            k2.c(x2);
            j2.a((GLObject) b3, af2, i2 == 0);
            k2.d();
            b3 = x2;
            GlobalState.e("FilterGroup: renderTarget");  // 02b b/i.java L64: l.e
        }
        this.c = (GLObject) b3;
        return (GLObject) b3;
    }
}
