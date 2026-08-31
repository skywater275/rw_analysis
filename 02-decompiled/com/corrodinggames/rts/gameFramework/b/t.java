/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.b;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.u;

public class t
implements u {
    private final int[] a = new int[1];

    @Override
    public int a() {
        GLES20.glGenTextures((int)1, (int[])this.a, (int)0);
        n.q();
        return this.a[0];
    }
}
