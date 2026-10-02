/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.opengl.ImageData
 *  org.newdawn.slick.opengl.Texture
 *  org.newdawn.slick.util.MiscUtils
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.s;
import java.nio.ByteBuffer;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.MiscUtils;

public class t
implements ImageData {
    int a;
    private int c;
    private int d;
    private int e;
    private int f;
    private byte[] g;
    final /* synthetic */ s b;

    public t(s s2, Image image) {
        this.b = s2;
        Texture texture = image.getTexture();
        this.g = texture.getTextureData();
        this.a = texture.hasAlpha() ? 32 : 24;
        this.c = texture.getImageWidth();
        this.d = texture.getImageHeight();
        this.e = texture.getTextureWidth();
        this.f = texture.getTextureHeight();
    }

    public int getDepth() {
        return this.a;
    }

    public int getWidth() {
        return this.c;
    }

    public int getHeight() {
        return this.d;
    }

    public int getTexWidth() {
        return this.e;
    }

    public int getTexHeight() {
        return this.f;
    }

    public ByteBuffer getImageBufferData() {
        ByteBuffer byteBuffer = MiscUtils.createByteBuffer((int)this.g.length);
        byteBuffer.put(this.g);
        byteBuffer.flip();
        return byteBuffer;
    }

    public byte[] a() {
        return this.g;
    }
}
