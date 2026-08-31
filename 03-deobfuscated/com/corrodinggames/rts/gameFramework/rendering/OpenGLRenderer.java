/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.BitmapFactory$Options
 *  android.opengl.GLSurfaceView$Renderer
 *  javax.microedition.khronos.egl.EGLConfig
 *  javax.microedition.khronos.opengles.GL10
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.GL10Renderer;
import com.corrodinggames.rts.gameFramework.rendering.GLTextureRegion;
import com.corrodinggames.rts.gameFramework.rendering.c;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer
implements GLSurfaceView.Renderer {
    private static BitmapFactory.Options q = new BitmapFactory.Options();
    public boolean a;
    GL10 b;
    float c;
    float d;
    int e;
    int f;
    c[] g;
    GL10Renderer h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    String o;
    long p;

    public void onDrawFrame(GL10 gL10) {
        if (this.e == -1) {
            Log.d("RustedWarfare", "---- render: no buffer is ready!");
            return;
        }
        long l = System.currentTimeMillis();
        int n = (int)(l - this.p);
        this.p = l;
        this.l += n;
        ++this.m;
        if (this.m == 10) {
            this.n = 10000 / this.l;
            this.l = 0;
            this.m = 0;
            this.o = this.n + "fps";
            Log.d("RustedWarfare", "render:" + this.o + ", this renders has " + this.g[this.e].b + " draws");
        }
        this.f = this.e;
        gL10.glClear(16640);
        gL10.glMatrixMode(5888);
        GL10Renderer.a(gL10, true, false);
        c c2 = this.g[this.f];
        this.i = -1;
        this.j = -1;
        this.k = -1;
        for (int i = 0; i < c2.b; ++i) {
            GLTextureRegion b2 = c2.a[i];
            b2.a(gL10);
        }
        GL10Renderer.a(gL10);
        this.f = -1;
    }

    public void onSurfaceChanged(GL10 gL10, int n, int n2) {
        Log.d("RustedWarfare", "2d gl onSurfaceChanged:" + n + "," + n2);
        this.b = gL10;
        this.c = n2;
        this.d = n;
        gL10.glViewport(0, 0, n, n2);
        gL10.glMatrixMode(5889);
        gL10.glLoadIdentity();
        gL10.glOrthof(0.0f, (float)n, 0.0f, (float)n2, 0.0f, 1.0f);
        gL10.glShadeModel(7424);
        gL10.glEnable(3042);
        gL10.glBlendFunc(770, 771);
        gL10.glColor4x(65536, 65536, 65536, 65536);
        gL10.glEnable(3553);
    }

    public void onSurfaceCreated(GL10 gL10, EGLConfig eGLConfig) {
        Log.d("RustedWarfare", "2d gl onSurfaceCreated");
        this.b = gL10;
        gL10.glHint(3152, 4353);
        gL10.glClearColor(0.3f, 0.3f, 0.5f, 1.0f);
        gL10.glShadeModel(7424);
        gL10.glDisable(2929);
        gL10.glEnable(3553);
        gL10.glDisable(3024);
        gL10.glDisable(2896);
        gL10.glClear(16640);
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2 != null) {
            // empty if block
        }
        this.a = true;
    }
}
