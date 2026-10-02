/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.newdawn.slick.opengl.ImageData
 */
package com.corrodinggames.rts.java.platform;

import java.nio.ByteBuffer;
import org.newdawn.slick.opengl.ImageData;

public class a
implements ImageData {
    ImageData a;
    ByteBuffer b;

    public a(ImageData imageData, ByteBuffer byteBuffer) {
        this.a = imageData;
        this.b = byteBuffer;
    }

    public int getDepth() {
        return this.a.getDepth();
    }

    public int getHeight() {
        return this.a.getHeight();
    }

    public ByteBuffer getImageBufferData() {
        return this.b;
    }

    public int getTexHeight() {
        return this.a.getTexHeight();
    }

    public int getTexWidth() {
        return this.a.getTexWidth();
    }

    public int getWidth() {
        return this.a.getWidth();
    }
}
