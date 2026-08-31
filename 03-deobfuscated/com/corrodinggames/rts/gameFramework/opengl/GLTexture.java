/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 *  android.opengl.GLUtils
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.corrodinggames.rts.gameFramework.opengl.TextureConfig;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.k;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.opengl.TextureIdProvider;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.HashMap;

public abstract class GLTexture
extends GLObject {
    private static HashMap bitmapCache = new HashMap();
    private static TextureConfig cacheKey = new TextureConfig(null);
    private boolean contentValid = true;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private static int u;
    protected Bitmap m;
    private int v;
    int filterMode = 9729;

    protected GLTexture() {
        this(false);
    }

    protected GLTexture(boolean bl) {
        super(null, 0, 0);
        if (bl) {
            this.a(true);
            this.v = 1;
        }
    }

    private static Bitmap a(boolean bl, Bitmap$Config config, int n) {
        TextureConfig ai2 = cacheKey;
        ai2.filterMode = bl;
        ai2.wrapMode = config;
        ai2.generateMipmaps = n;
        Bitmap bitmap = (Bitmap)bitmapCache.get(ai2);
        if (bitmap == null) {
            bitmap = bl ? Bitmap.a(1, n, config) : Bitmap.a(n, 1, config);
            bitmapCache.put(ai2.a(), bitmap);
        }
        return bitmap;
    }

    private Bitmap n() {
        if (this.m == null) {
            this.m = this.k();
            int filterMode = this.m.b() + this.v * 2;
            int n2 = this.m.c() + this.v * 2;
            if (this.c == -1) {
                this.a(filterMode, n2);
            }
        }
        return this.m;
    }

    private void o() {
        this.a(this.m);
        this.m = null;
    }


    public int b() {
        if (this.c == -1) {
            this.n();
        }
        return this.c;
    }


    public int c() {
        if (this.c == -1) {
            this.n();
        }
        return this.d;
    }

    protected abstract Bitmap k();

    protected abstract void a(Bitmap var1);

    public void l() {
        if (this.m != null) {
            this.o();
        }
        this.contentValid = false;
    }

    public boolean m() {
        return this.i() && this.contentValid;
    }

    public void d(k k2) {
        if (!this.i()) {
            if (this.t && ++u > 100) {
                return;
            }
            this.e(k2);
        } else if (!this.contentValid) {
            int n2;
            Bitmap bitmap = this.n();
            int n3 = GLUtils.getInternalFormat((Bitmap)bitmap);
            try {
                n2 = GLUtils.getType((Bitmap)bitmap);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                com.corrodinggames.rts.gameFramework.GlobalState.a("updateContent: GLUtils.getType failed, defaulting to GL_UNSIGNED_BYTE", (Throwable)illegalArgumentException);
                n2 = 5121;
            }
            k2.a(this, this.v, this.v, bitmap, n3, n2);
            this.o();
            this.contentValid = true;
        }
    }


    public void b(int n2) {
        if (this.filterMode != n2) {
            if (this.contentValid) {
                int n3 = this.g();
                GLES20.glTexParameterf((int)n3, (int)10241, (float)n2);
                GLES20.glTexParameterf((int)n3, (int)10240, (float)n2);
            }
            this.filterMode = n2;
        }
    }


    public int h() {
        return this.filterMode;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void e(k k2) {
        block10: {
            Bitmap bitmap = this.n();
            if (bitmap == null) {
                this.b = -1;
                throw new RuntimeException("Texture load fail, no bitmap");
            }
            try {
                Bitmap bitmap2;
                int n2;
                int n3 = bitmap.b();
                int n4 = bitmap.c();
                int n5 = n3 + this.v * 2;
                int n6 = n4 + this.v * 2;
                int n7 = this.getint();
                int n8 = this.getint2();
                this.a = k2.a().a();
                this.contentValid = true;
                k2.d(this);
                if (n3 == n7 && n4 == n8) {
                    int n9 = 0;
                    k2.a(this, bitmap, n9);
                    com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
                    break block10;
                }
                int n10 = GLUtils.getInternalFormat((Bitmap)bitmap);
                try {
                    n2 = GLUtils.getType((Bitmap)bitmap);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    com.corrodinggames.rts.gameFramework.GlobalState.a("uploadToCanvas: GLUtils.getType failed, defaulting to GL_UNSIGNED_BYTE", (Throwable)illegalArgumentException);
                    n2 = 5121;
                }
                Bitmap$Config config = bitmap.d();
                int n11 = n10;
                k2.a(this, n10, n2, n11);
                com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
                k2.a(this, this.v, this.v, bitmap, n10, n2);
                com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
                if (this.v > 0) {
                    bitmap2 = GLTexture.a(true, config, n8);
                    k2.a(this, 0, 0, bitmap2, n10, n2);
                    bitmap2 = GLTexture.a(false, config, n7);
                    k2.a(this, 0, 0, bitmap2, n10, n2);
                }
                if (this.v <= 0) break block10;
                if (this.v + n3 < n7) {
                    bitmap2 = GLTexture.a(true, config, n8);
                    k2.a(this, this.v + n3, 0, bitmap2, n10, n2);
                }
                if (this.v + n4 < n8) {
                    bitmap2 = GLTexture.a(false, config, n7);
                    k2.a(this, 0, this.v + n4, bitmap2, n10, n2);
                }
            }
            finally {
                this.o();
            }
        }
        this.a(k2);
        this.b = 1;
        this.contentValid = true;
    }


    public boolean c(k k2) {
        this.d(k2);
        ++this.i;
        return this.m();
    }


    public void b(k k2) {
        TextureIdProvider u2 = k2.a();
        this.a = u2.a();
        int n2 = 3553;
        if (n2 == 3553) {
            k2.a(this, 6408, 5121, 6408);
        }
        k2.d(this);
        this.b = 1;
        this.a(k2);
    }


    public int g() {
        return 3553;
    }


    public void j() {
        super.j();
        if (this.m != null) {
            this.o();
        }
    }
}
