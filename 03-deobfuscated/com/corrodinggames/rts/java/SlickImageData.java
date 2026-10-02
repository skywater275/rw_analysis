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

import com.corrodinggames.rts.java.SlickTexture;
import java.nio.ByteBuffer;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.util.MiscUtils;

public class SlickImageData
implements ImageData {
    int a;
    private int imageWidth;
    private int imageHeight;
    private int textureWidth;
    private int textureHeight;
    private byte[] pixelData;
    final /* synthetic */ SlickTexture b;

    public SlickImageData(SlickTexture s2, Image image) {
        this.b = s2;
        org.newdawn.slick.opengl.Texture texture = image.getTexture();
        this.pixelData = texture.getTextureData();
        this.a = texture.hasAlpha() ? 32 : 24;
        this.imageWidth = texture.getImageWidth();
        this.imageHeight = texture.getImageHeight();
        this.textureWidth = texture.getTextureWidth();
        this.textureHeight = texture.getTextureHeight();
    }

    public int getDepth() {
        return this.a;
    }

    public int getWidth() {
        return this.imageWidth;
    }

    public int getHeight() {
        return this.imageHeight;
    }

    public int getTexWidth() {
        return this.textureWidth;
    }

    public int getTexHeight() {
        return this.textureHeight;
    }

    public ByteBuffer getImageBufferData() {
        ByteBuffer byteBuffer = MiscUtils.createByteBuffer((int)this.pixelData.length);
        byteBuffer.put(this.pixelData);
        byteBuffer.flip();
        return byteBuffer;
    }

    public byte[] a() {
        return this.pixelData;
    }
}
