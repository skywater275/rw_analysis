/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 *  android.opengl.GLUtils
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.k;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class FramebufferTexture
extends GLObject {
    int l = 9729;

    public FramebufferTexture(k k2, int n, int n2) {
        this.a(n, n2);
        this.a = k2.a().a();
        k2.d(this);
        k2.a(this, 6408, 5121, 6408);
    }

    public void a(k k2, Bitmap bitmap, int n2, int n3) {
        k2.f();
        int n4 = this.g();
        k2.b(this);
        GLRenderer.q();
        GLUtils.texSubImage2D((int)n4, (int)0, (int)n2, (int)n3, (Bitmap)bitmap, (int)6408, (int)5121);
    }


    protected boolean c(k k2) {
        return false;
    }


    public void b(k k2) {
        com.corrodinggames.rts.gameFramework.GlobalState.e("BackingTexture prepare TODO");
    }


    protected int g() {
        return 3553;
    }


    public void b(int n2) {
        if (this.l != n2) {
            int n3 = this.g();
            GLES20.glTexParameterf((int)n3, (int)10241, (float)n2);
            GLES20.glTexParameterf((int)n3, (int)10240, (float)n2);
            this.l = n2;
        }
    }


    public int h() {
        return this.l;
    }
}
