/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.opengl.GLES20;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import com.corrodinggames.rts.gameFramework.opengl.GLUniform;

class UniformLocation
extends GLUniform {
    public UniformLocation(String string) {
        super(string);
    }

    @Override
    public void a(int n2) {
        if (this.uniformLocation != n2) {
            this.uniformName = GLES20.glGetUniformLocation((int)n2, (String)this.uniformType);
            this.uniformLocation = n2;
            GLRenderer.r();
            if (this.uniformName == -1) {
                Log.d(GLRenderer.s(), "loadHandle: Failed to find: " + this.uniformType);
            }
        }
    }
}
