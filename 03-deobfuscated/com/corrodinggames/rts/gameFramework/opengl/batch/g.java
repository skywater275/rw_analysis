/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.opengl.batch;

import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class g {
    public final int a;
    public final int b;
    public final int c;
    public final IntBuffer d;
    public final ShortBuffer e;
    public int f;
    public int g;
    final int[] h;
    private int i;
    private int j;

    public g(int n, int n2) {
        this.a = 2;
        this.b = this.a + 2;
        this.c = this.b * 4;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(n * this.c);
        byteBuffer.order(ByteOrder.nativeOrder());
        this.d = byteBuffer.asIntBuffer();
        if (n2 > 0) {
            byteBuffer = ByteBuffer.allocateDirect(n2 * 2);
            byteBuffer.order(ByteOrder.nativeOrder());
            this.e = byteBuffer.asShortBuffer();
        } else {
            this.e = null;
        }
        this.f = 0;
        this.g = 0;
        this.h = new int[n * this.c / 4];
        this.i = com.corrodinggames.rts.gameFramework.opengl.batch.a.b.a();
        this.j = com.corrodinggames.rts.gameFramework.opengl.batch.a.a.a();
    }

    public void a(float[] fArray, int n, int n2) {
        this.d.clear();
        int n3 = n + n2;
        int n4 = n;
        int n5 = 0;
        while (n4 < n3) {
            this.h[n5] = Float.floatToRawIntBits(fArray[n4]);
            ++n4;
            ++n5;
        }
        this.d.put(this.h, 0, n2);
        this.d.flip();
        this.f = n2 / this.b;
    }

    public void a(short[] sArray, int n, int n2) {
        this.e.clear();
        this.e.put(sArray, n, n2);
        this.e.flip();
        this.g = n2;
    }

    public void a() {
        this.d.position(0);
        GLES20.glVertexAttribPointer((int)this.j, (int)this.a, (int)5126, (boolean)false, (int)this.c, (Buffer)this.d);
        GLES20.glEnableVertexAttribArray((int)this.j);
        this.d.position(this.a);
        GLES20.glVertexAttribPointer((int)this.i, (int)2, (int)5126, (boolean)false, (int)this.c, (Buffer)this.d);
        GLES20.glEnableVertexAttribArray((int)this.i);
    }

    public void a(int n, int n2, int n3) {
        if (this.e != null) {
            this.e.position(n2);
            GLES20.glDrawElements((int)n, (int)n3, (int)5123, (Buffer)this.e);
        } else {
            GLES20.glDrawArrays((int)n, (int)n2, (int)n3);
        }
    }

    public void b() {
        GLES20.glDisableVertexAttribArray((int)this.i);
    }
}
