/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.khronos.opengles.GL10
 *  javax.microedition.khronos.opengles.GL11
 */
package com.corrodinggames.rts.gameFramework.m;

import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

class ac {
    private FloatBuffer b;
    private FloatBuffer c;
    private FloatBuffer d;
    private IntBuffer e;
    private IntBuffer f;
    private IntBuffer g;
    private CharBuffer h;
    private Buffer i;
    private Buffer j;
    private Buffer k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private int t;

    void a(int n, int n2, float f, float f2) {
        if (n < 0 || n >= this.m) {
            throw new IllegalArgumentException("i");
        }
        if (n2 < 0 || n2 >= this.n) {
            throw new IllegalArgumentException("j");
        }
        int n3 = this.m * n2 + n;
        int n4 = n3 * 2;
        if (this.l == 5126) {
            this.c.put(n4, f);
            this.c.put(n4 + 1, f2);
        } else {
            this.f.put(n4, (int)(f * 65536.0f));
            this.f.put(n4 + 1, (int)(f2 * 65536.0f));
        }
    }

    void a(int n, int n2, float f, float f2, float f3, float f4, float f5, float[] fArray) {
        if (n < 0 || n >= this.m) {
            throw new IllegalArgumentException("i");
        }
        if (n2 < 0 || n2 >= this.n) {
            throw new IllegalArgumentException("j");
        }
        int n3 = this.m * n2 + n;
        int n4 = n3 * 3;
        int n5 = n3 * 2;
        int n6 = n3 * 4;
        if (this.l == 5126) {
            this.b.put(n4, f);
            this.b.put(n4 + 1, f2);
            this.b.put(n4 + 2, f3);
            this.c.put(n5, f4);
            this.c.put(n5 + 1, f5);
            if (fArray != null) {
                this.d.put(n6, fArray[0]);
                this.d.put(n6 + 1, fArray[1]);
                this.d.put(n6 + 2, fArray[2]);
                this.d.put(n6 + 3, fArray[3]);
            }
        } else {
            this.e.put(n4, (int)(f * 65536.0f));
            this.e.put(n4 + 1, (int)(f2 * 65536.0f));
            this.e.put(n4 + 2, (int)(f3 * 65536.0f));
            this.f.put(n5, (int)(f4 * 65536.0f));
            this.f.put(n5 + 1, (int)(f5 * 65536.0f));
            if (fArray != null) {
                this.g.put(n6, (int)(fArray[0] * 65536.0f));
                this.g.put(n6 + 1, (int)(fArray[1] * 65536.0f));
                this.g.put(n6 + 2, (int)(fArray[2] * 65536.0f));
                this.g.put(n6 + 3, (int)(fArray[3] * 65536.0f));
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
        if (!this.p) {
            gL10.glVertexPointer(3, this.l, 0, this.i);
            if (bl) {
                gL10.glTexCoordPointer(2, this.l, 0, this.j);
            }
            if (bl2) {
                gL10.glColorPointer(4, this.l, 0, this.k);
            }
            gL10.glDrawElements(4, this.o, 5123, (Buffer)this.h);
        } else {
            GL11 gL11 = (GL11)gL10;
            gL11.glBindBuffer(34962, this.q);
            gL11.glVertexPointer(3, this.l, 0, 0);
            if (bl) {
                gL11.glBindBuffer(34962, this.s);
                gL11.glTexCoordPointer(2, this.l, 0, 0);
            }
            if (bl2) {
                gL11.glBindBuffer(34962, this.t);
                gL11.glColorPointer(4, this.l, 0, 0);
            }
            gL11.glBindBuffer(34963, this.r);
            gL11.glDrawElements(4, this.o, 5123, 0);
            gL11.glBindBuffer(34962, 0);
            gL11.glBindBuffer(34963, 0);
        }
    }

    public static void a(GL10 gL10) {
        gL10.glDisableClientState(32884);
    }
}
