/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

public abstract class TextureCache {
    public int textureId = -1;
    public int b = 0;  // 02b af.b: 修饰符 (ctrl=1/shift=2/alt=4)
    protected boolean c;  // 02b af.c
    public boolean d;  // 02b af.d
    public int referenceCount = 0;
    protected boolean isLoaded;
    public boolean isDirty;

    public abstract boolean a();

    public boolean a(TextureCache af2) {
        if (this.referenceCount != af2.referenceCount) {
            return false;
        }
        return this.textureId == af2.textureId;
    }

    public abstract boolean b();

    public abstract String c();

    public abstract boolean d();
}
