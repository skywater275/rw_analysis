/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;
import com.corrodinggames.rts.gameFramework.opengl.DrawCall;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.opengl.GLRenderer;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class DrawCallBuffer {
    public FloatBuffer a;
    public ShortBuffer b;
    public int c;
    int[] d;
    int e;
    /* synthetic */ DrawCall f;  // 02b b/al.java: final aj f

    public void setValue(float[] fArray, int n, int n2) {
        this.a.clear();
        int n3 = n + n2;
        this.a.put(fArray, 0, n2);
        this.a.flip();
        this.c = n2;
    }

    public void setValue() {
        GLES20.glEnableVertexAttribArray((int)this.f.h.a.uniformName);
        GLES20.glEnableVertexAttribArray((int)this.f.h.b.uniformName);
    }

    public void uploadToVbo() {
        GLRenderer.q();
        if (this.d == null) {
            this.d = new int[1];
            GLES20.glGenBuffers((int)1, (int[])this.d, (int)0);
            GLRenderer.r();
        }
        ++this.e;
        if (this.e >= 1) {
            this.e = 0;
        }
        GLES20.glBindBuffer((int)34962, (int)this.d[this.e]);
        GLES20.glBufferData((int)34962, (int)(this.c * 4), (Buffer)this.a, (int)35040);
        GLES20.glVertexAttribPointer((int)this.f.h.a.uniformName, (int)2, (int)5126, (boolean)false, (int)32, (int)0);
        GLRenderer.q();
        GLRenderer.q();
        GLES20.glVertexAttribPointer((int)this.f.h.b.uniformName, (int)4, (int)5126, (boolean)false, (int)32, (int)16);
        GLRenderer.q();
    }

    public void setValue(int n2, int n3, int n4) {
        if (this.b != null) {
            this.b.position(n3);
            GLES20.glDrawElements((int)n2, (int)n4, (int)5123, (Buffer)this.b);
        } else {
            GLES20.glDrawArrays((int)n2, (int)n3, (int)n4);
        }
    }

    public void reset() {
        GLES20.glBindBuffer((int)34962, (int)0);
    }

    public void d() {  // 02b b/al.java: 绑定索引缓冲 + 启用顶点属性
        GLES20.glBindBuffer((int)34963, (int)this.c);
        this.a();
    }

    public void c() {
        this.setValue();
    }

    public void e() {
        GLES20.glDisableVertexAttribArray((int)this.f.h.b.uniformName);
    }

    // === 02b b/al.java 混淆名桥接 (03 DrawCall 调用点使用) ===
    public void a(float[] var1, int var2, int var3) {  // 02b a(float[],int,int)
        this.setValue(var1, var2, var3);
    }

    public void a() {  // 02b a()
        this.setValue();
    }

    public void b() {  // 02b b()
        this.uploadToVbo();
    }

    public void a(int var1, int var2, int var3) {  // 02b a(int,int,int)
        this.setValue(var1, var2, var3);
    }
}
