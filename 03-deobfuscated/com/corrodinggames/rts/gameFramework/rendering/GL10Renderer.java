/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.khronos.opengles.GL10
 *  javax.microedition.khronos.opengles.GL11
 */
package com.corrodinggames.rts.gameFramework.rendering;

import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

class GL10Renderer {
    private FloatBuffer vertexBuffer;
    private FloatBuffer texCoordBuffer;
    private FloatBuffer colorBuffer;
    private IntBuffer indexBufferA;
    private IntBuffer indexBufferB;
    private IntBuffer indexBufferC;
    private CharBuffer charBuffer;
    private Buffer bufferA;
    private Buffer bufferB;
    private Buffer bufferC;
    private int currentTexId;
    private int currentBlendSrc;
    private int currentBlendDst;
    private int viewportWidth;
    private boolean isInit;
    private int drawCallCount;
    private int vertexCount;
    private int texCoordCount;
    private int triangleCount;

    void a(int n, int n2, float f, float f2) {
        if (n < 0 || n >= this.currentBlendSrc) {
            throw new IllegalArgumentException("i");
        }
        if (n2 < 0 || n2 >= this.currentBlendDst) {
            throw new IllegalArgumentException("j");
        }
        int n3 = this.currentBlendSrc * n2 + currentBlendDst;
        int n4 = n3 * 2;
        if (this.currentTexId == 5126) {
            this.texCoordBuffer.put(n4, f);
            this.texCoordBuffer.put(n4 + 1, f2);
        } else {
            this.indexBufferB.put(n4, (int)(f * 65536.0f));
            this.indexBufferB.put(n4 + 1, (int)(f2 * 65536.0f));
        }
    }

    void a(int n, int n2, float f, float f2, float f3, float f4, float f5, float[] fArray) {
        if (n < 0 || n >= this.currentBlendSrc) {
            throw new IllegalArgumentException("i");
        }
        if (n2 < 0 || n2 >= this.currentBlendDst) {
            throw new IllegalArgumentException("j");
        }
        int n3 = this.currentBlendSrc * n2 + currentBlendDst;
        int n4 = n3 * 3;
        int n5 = n3 * 2;
        int n6 = n3 * 4;
        if (this.currentTexId == 5126) {
            this.vertexBuffer.put(n4, f);
            this.vertexBuffer.put(n4 + 1, f2);
            this.vertexBuffer.put(n4 + 2, f3);
            this.texCoordBuffer.put(n5, f4);
            this.texCoordBuffer.put(n5 + 1, f5);
            if (fArray != null) {
                this.colorBuffer.put(n6, fArray[0]);
                this.colorBuffer.put(n6 + 1, fArray[1]);
                this.colorBuffer.put(n6 + 2, fArray[2]);
                this.colorBuffer.put(n6 + 3, fArray[3]);
            }
        } else {
            this.indexBufferA.put(n4, (int)(f * 65536.0f));
            this.indexBufferA.put(n4 + 1, (int)(f2 * 65536.0f));
            this.indexBufferA.put(n4 + 2, (int)(f3 * 65536.0f));
            this.indexBufferB.put(n5, (int)(f4 * 65536.0f));
            this.indexBufferB.put(n5 + 1, (int)(f5 * 65536.0f));
            if (fArray != null) {
                this.indexBufferC.put(n6, (int)(fArray[0] * 65536.0f));
                this.indexBufferC.put(n6 + 1, (int)(fArray[1] * 65536.0f));
                this.indexBufferC.put(n6 + 2, (int)(fArray[2] * 65536.0f));
                this.indexBufferC.put(n6 + 3, (int)(fArray[3] * 65536.0f));
            }
        }
    }

    public static void a(GL10 gL10, boolean bl, boolean bl2) {
        gL10.glEnableClientState(32884);
        if (bl) {
            gL10.glEnableClientState(32888);
            gL10.glEnable(3553);
        } else {
            gL10.glDisableClientState(32888);
            gL10.glDisable(3553);
        }
        if (bl2) {
            gL10.glEnableClientState(32886);
        } else {
            gL10.glDisableClientState(32886);
        }
    }

    public void b(GL10 gL10, boolean bl, boolean bl2) {
        if (!this.isInit) {
            gL10.glVertexPointer(3, this.currentTexId, 0, this.bufferA);
            if (bl) {
                gL10.glTexCoordPointer(2, this.currentTexId, 0, this.bufferB);
            }
            if (bl2) {
                gL10.glColorPointer(4, this.currentTexId, 0, this.bufferC);
            }
            gL10.glDrawElements(4, this.viewportWidth, 5123, (Buffer)this.charBuffer);
        } else {
            GL11 gL11 = (GL11)gL10;
            gL11.glBindBuffer(34962, this.drawCallCount);
            gL11.glVertexPointer(3, this.currentTexId, 0, 0);
            if (bl) {
                gL11.glBindBuffer(34962, this.texCoordCount);
                gL11.glTexCoordPointer(2, this.currentTexId, 0, 0);
            }
            if (bl2) {
                gL11.glBindBuffer(34962, this.triangleCount);
                gL11.glColorPointer(4, this.currentTexId, 0, 0);
            }
            gL11.glBindBuffer(34963, this.vertexCount);
            gL11.glDrawElements(4, this.viewportWidth, 5123, 0);
            gL11.glBindBuffer(34962, 0);
            gL11.glBindBuffer(34963, 0);
        }
    }

    public static void a(GL10 gL10) {
        gL10.glDisableClientState(32884);
    }
}
