/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.opengl.TextureIdProvider;

public class GLTextureGenerator
implements TextureIdProvider {
    private final int[] a = new int[1];

    @Override
    public int a() {
        GLES20.glGenTextures((int)1, (int[])this.a, (int)0);
        com.corrodinggames.rts.gameFramework.opengl.GLRenderer.q();
        return this.a[0];
    }
}
