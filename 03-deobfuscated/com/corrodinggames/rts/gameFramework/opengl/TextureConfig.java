/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;

class TextureConfig
implements Cloneable {
    public boolean filterMode;
    public Bitmap$Config wrapMode;
    public int generateMipmaps;

    private TextureConfig() {
    }

    TextureConfig(TextureConfig$1 textureConfig$1) {  // 02b b/ai.java: ai(ah$1) 合成构造
        this();
    }


    public int hashCode() {
        int n = this.wrapMode.hashCode() ^ this.generateMipmaps;
        return this.filterMode ? n : -n;
    }

    public boolean equals(Object object) {
        if (!(object instanceof TextureConfig)) {
            return false;
        }
        TextureConfig ai2 = (TextureConfig) object;
        return this.filterMode == ai2.filterMode && this.wrapMode == ai2.wrapMode && this.generateMipmaps == ai2.generateMipmaps;
    }

    public TextureConfig a() {
        try {
            return (TextureConfig) super.clone();
        }
        catch (CloneNotSupportedException cloneNotSupportedException) {
            throw new AssertionError((Object)cloneNotSupportedException);
        }
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }
}
