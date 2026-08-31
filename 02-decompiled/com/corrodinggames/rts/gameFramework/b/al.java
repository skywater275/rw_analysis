/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.b;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.aj;
import com.corrodinggames.rts.gameFramework.b.n;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class al {
    public final FloatBuffer a;
    public final ShortBuffer b;
    public int c;
    int[] d;
    int e;
    final /* synthetic */ aj f;

    public void a(float[] fArray, int n, int n2) {
        this.a.clear();
        int n3 = n + n2;
        this.a.put(fArray, 0, n2);
        this.a.flip();
        this.c = n2;
    }

    public void a() {
        GLES20.glEnableVertexAttribArray((int)this.f.h.a.a);
        GLES20.glEnableVertexAttribArray((int)this.f.h.b.a);
    }

    public void b() {
        n.q();
        if (this.d == null) {
            this.d = new int[1];
            GLES20.glGenBuffers((int)1, (int[])this.d, (int)0);
            n.r();
        }
        ++this.e;
        if (this.e >= 1) {
            this.e = 0;
        }
        GLES20.glBindBuffer((int)34962, (int)this.d[this.e]);
        GLES20.glBufferData((int)34962, (int)(this.c * 4), (Buffer)this.a, (int)35040);
        GLES20.glVertexAttribPointer((int)this.f.h.a.a, (int)2, (int)5126, (boolean)false, (int)32, (int)0);
        n.q();
        n.q();
        GLES20.glVertexAttribPointer((int)this.f.h.b.a, (int)4, (int)5126, (boolean)false, (int)32, (int)16);
        n.q();
    }

    public void a(int n2, int n3, int n4) {
        if (this.b != null) {
            this.b.position(n3);
            GLES20.glDrawElements((int)n2, (int)n4, (int)5123, (Buffer)this.b);
        } else {
            GLES20.glDrawArrays((int)n2, (int)n3, (int)n4);
        }
    }

    public void c() {
        GLES20.glBindBuffer((int)34962, (int)0);
    }

    public void d() {
        this.a();
    }

    public void e() {
        GLES20.glDisableVertexAttribArray((int)this.f.h.b.a);
    }
}
