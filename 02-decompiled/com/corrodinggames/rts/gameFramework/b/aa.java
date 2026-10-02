/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES20
 */
package com.corrodinggames.rts.gameFramework.b;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.y;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class aa {
    public final FloatBuffer a;
    public final ShortBuffer b;
    public int c;
    public int d;
    int[] e;
    int f;
    final /* synthetic */ y g;

    public void a(float[] fArray, int n, int n2) {
        this.a.clear();
        int n3 = n + n2;
        this.a.put(fArray, 0, n2);
        this.a.flip();
        this.d = n2;
    }

    public void a() {
        GLES20.glEnableVertexAttribArray((int)this.g.j.a.a);
        GLES20.glEnableVertexAttribArray((int)this.g.j.b.a);
        GLES20.glEnableVertexAttribArray((int)this.g.j.c.a);
    }

    public void b() {
        n.q();
        if (this.e == null) {
            this.e = new int[1];
            GLES20.glGenBuffers((int)1, (int[])this.e, (int)0);
            n.r();
        }
        ++this.f;
        if (this.f >= 1) {
            this.f = 0;
        }
        GLES20.glBindBuffer((int)34962, (int)this.e[this.f]);
        GLES20.glBufferData((int)34962, (int)(this.d * 4), (Buffer)this.a, (int)35040);
        GLES20.glVertexAttribPointer((int)this.g.j.a.a, (int)2, (int)5126, (boolean)false, (int)32, (int)0);
        n.q();
        GLES20.glVertexAttribPointer((int)this.g.j.b.a, (int)2, (int)5126, (boolean)false, (int)32, (int)8);
        n.q();
        GLES20.glVertexAttribPointer((int)this.g.j.c.a, (int)4, (int)5126, (boolean)false, (int)32, (int)16);
        n.q();
    }

    public void a(int n2, int n3, int n4) {
        if (this.b != null) {
            GLES20.glDrawElements((int)n2, (int)n4, (int)5123, (int)0);
        } else {
            GLES20.glDrawArrays((int)n2, (int)n3, (int)n4);
        }
    }

    public void c() {
        GLES20.glBindBuffer((int)34962, (int)0);
    }

    public void d() {
        GLES20.glBindBuffer((int)34963, (int)this.c);
        this.a();
    }

    public void e() {
        GLES20.glDisableVertexAttribArray((int)this.g.j.b.a);
        GLES20.glDisableVertexAttribArray((int)this.g.j.c.a);
        GLES20.glBindBuffer((int)34963, (int)0);
    }
}
