/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;

import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class ShaderUniform {
    public String uniformName;
    public int uniformLocation = -1;
    public boolean isInteger;
    public boolean isArray;
    public float[] floatValues = new float[1];
    public Texture textureRef;
    public boolean needsUpdate;

    public void a(float f) {
        if (this.floatValues.length != 1) {
            this.floatValues = new float[1];
        }
        if (this.floatValues[0] == f) {
            return;
        }
        this.floatValues[0] = f;
        this.isInteger = true;
    }

    public void a(float f, float f2) {
        if (this.floatValues.length != 2) {
            this.floatValues = new float[2];
        }
        if (this.floatValues[0] == f && this.floatValues[1] == f2) {
            return;
        }
        this.floatValues[0] = f;
        this.floatValues[1] = f2;
        this.isInteger = true;
    }

    public void a(float f, float f2, float f3, float f4) {
        if (this.floatValues.length != 4) {
            this.floatValues = new float[4];
        }
        if (this.floatValues[0] == f && this.floatValues[1] == f2 && this.floatValues[2] == f3 && this.floatValues[3] == f4) {
            return;
        }
        this.floatValues[0] = f;
        this.floatValues[1] = f2;
        this.floatValues[2] = f3;
        this.floatValues[3] = f4;
        this.isInteger = true;
    }

    public void a(Texture e2) {
        if (this.textureRef != e2) {
            this.textureRef = e2;
            this.isInteger = true;
        }
    }

    public void b(Texture e2) {
        this.needsUpdate = true;
        if (this.textureRef != e2) {
            this.textureRef = e2;
            this.isInteger = true;
        }
    }
}
