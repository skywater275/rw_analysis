/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.opengl.GLES20;

abstract class GLUniform {
    public int uniformName = -1;
    protected final String uniformType;
    public int uniformLocation = -1;

    public GLUniform(String string) {
        this.uniformType = string;
    }

    public abstract void a(int var1);

    public void a(float[] fArray) {
        GLES20.glUniformMatrix4fv((int)this.uniformName, (int)1, (boolean)false, (float[])fArray, (int)0);
    }
}
