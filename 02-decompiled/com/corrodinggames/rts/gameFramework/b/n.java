/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.opengl.GLES11Ext
 *  android.opengl.GLES20
 *  android.opengl.GLUtils
 *  android.opengl.Matrix
 */
package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.a.b;
import com.corrodinggames.rts.gameFramework.b.ab;
import com.corrodinggames.rts.gameFramework.b.ac;
import com.corrodinggames.rts.gameFramework.b.af;
import com.corrodinggames.rts.gameFramework.b.ag;
import com.corrodinggames.rts.gameFramework.b.aj;
import com.corrodinggames.rts.gameFramework.b.h;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.m;
import com.corrodinggames.rts.gameFramework.b.p;
import com.corrodinggames.rts.gameFramework.b.q;
import com.corrodinggames.rts.gameFramework.b.r;
import com.corrodinggames.rts.gameFramework.b.t;
import com.corrodinggames.rts.gameFramework.b.u;
import com.corrodinggames.rts.gameFramework.b.v;
import com.corrodinggames.rts.gameFramework.b.w;
import com.corrodinggames.rts.gameFramework.b.y;
import com.corrodinggames.rts.gameFramework.b.z;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class n
implements k {
    public ac a;
    private static final String G = n.class.getSimpleName();
    private static final float[] H = new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    private static final float[] I = new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    private Map J;
    private float[] K;
    private int L;
    int b;
    int c;
    float[] d;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private h S;
    private m T;
    q[] e;
    q[] f;
    q[] g;
    private final w U;
    private int[] V;
    private ArrayList W;
    private final float[] X;
    private final float[] Y;
    private final RectF Z;
    private final RectF aa;
    private final int[] ab;
    private static final u ac = new t();
    boolean h;
    boolean i;
    y j;
    aj k;
    com.corrodinggames.rts.gameFramework.b.f l;
    float[] m;
    r n;
    public int o;
    com.corrodinggames.rts.gameFramework.utility.m p;
    static RectF q;
    int r;
    boolean s;
    boolean t;
    int u;
    ae v;
    public int w;
    static int x;
    static int y;
    ab z;
    public static boolean A;
    static int B;
    com.corrodinggames.rts.gameFramework.utility.m C;
    p D;
    public static com.corrodinggames.rts.gameFramework.m.k E;
    FloatBuffer F;

    private int a(int n2, int n3, q[] qArray, int[] nArray) {
        int n4 = GLES20.glCreateProgram();
        com.corrodinggames.rts.gameFramework.b.n.r();
        if (n4 == 0) {
            throw new RuntimeException("Cannot create GL program: " + GLES20.glGetError());
        }
        GLES20.glAttachShader((int)n4, (int)n2);
        com.corrodinggames.rts.gameFramework.b.n.r();
        GLES20.glAttachShader((int)n4, (int)n3);
        com.corrodinggames.rts.gameFramework.b.n.r();
        GLES20.glLinkProgram((int)n4);
        com.corrodinggames.rts.gameFramework.b.n.r();
        GLES20.glGetProgramiv((int)n4, (int)35714, (int[])nArray, (int)0);
        if (nArray[0] != 1) {
            Log.d(G, "======= ERROR =========");
            Log.d(G, "Could not link program: ");
            String string = GLES20.glGetProgramInfoLog((int)n4);
            Log.d(G, string);
            GLES20.glDeleteProgram((int)n4);
            n4 = 0;
            throw new RuntimeException("Cannot link GL program: " + string);
        }
        com.corrodinggames.rts.gameFramework.b.n.a(qArray, n4);
        return n4;
    }

    private static void a(q[] qArray, int n2) {
        for (int i = 0; i < qArray.length; ++i) {
            qArray[i].a(n2);
        }
    }

    private static String a(int n2, String string) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean bl = false;
        String[] stringArray = string.split("\n");
        int n3 = stringArray.length;
        for (int i = 0; i < n3; ++i) {
            String string2;
            String string3 = string2 = stringArray[i];
            if (!bl && string2.contains("version")) {
                bl = true;
                continue;
            }
            if (bl) {
                // empty if block
            }
            if (!string2.equals(string3)) {
                com.corrodinggames.rts.gameFramework.l.e("Changing: " + string3 + " to " + string2);
            }
            stringBuffer.append(string2);
            stringBuffer.append("\n");
        }
        if (!bl) {
            // empty if block
        }
        return stringBuffer.toString();
    }

    private static int b(int n2, String string) {
        int n3 = GLES20.glCreateShader((int)n2);
        if (string == null) {
            throw new RuntimeException("Shader Compilation Failed: shaderCode==null");
        }
        string = com.corrodinggames.rts.gameFramework.b.n.a(n2, string);
        GLES20.glShaderSource((int)n3, (String)string);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glCompileShader((int)n3);
        com.corrodinggames.rts.gameFramework.b.n.q();
        int[] nArray = new int[1];
        GLES20.glGetShaderiv((int)n3, (int)35713, (int[])nArray, (int)0);
        if (nArray[0] == 0) {
            String string2 = GLES20.glGetShaderInfoLog((int)n3);
            String string3 = "Shader Compilation Failed: " + string2;
            Log.d(G, string3);
            throw new RuntimeException(string3);
        }
        return n3;
    }

    public void a(int n2, int n3) {
        this.b = n2;
        this.c = n3;
        com.corrodinggames.rts.gameFramework.b.n.q();
        Matrix.setIdentityM((float[])this.K, (int)this.L);
        Matrix.orthoM((float[])this.d, (int)0, (float)0.0f, (float)n2, (float)0.0f, (float)n3, (float)-1.0f, (float)1.0f);
        com.corrodinggames.rts.gameFramework.b.n.q();
        if (this.t() == null) {
            this.M = n2;
            this.N = n3;
            Matrix.translateM((float[])this.K, (int)this.L, (float)0.0f, (float)n3, (float)0.0f);
            Matrix.scaleM((float[])this.K, (int)this.L, (float)1.0f, (float)-1.0f, (float)1.0f);
            com.corrodinggames.rts.gameFramework.b.n.q();
        }
        GLES20.glViewport((int)0, (int)0, (int)this.b, (int)this.c);
        this.g();
    }

    public void a(int n2, q[] qArray) {
        com.corrodinggames.rts.gameFramework.b.n.q();
        this.f();
        com.corrodinggames.rts.gameFramework.b.n.q();
        this.a(n2);
        GLES20.glUniformMatrix4fv((int)qArray[5].a, (int)1, (boolean)false, (float[])this.d, (int)0);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    public void g() {
        q[] qArray;
        if (this.O != 0) {
            qArray = this.e;
            this.a(this.O, qArray);
        }
        if (this.P != 0) {
            qArray = this.f;
            this.a(this.P, qArray);
        }
        if (this.Q != 0) {
            qArray = this.g;
            this.a(this.Q, qArray);
        }
    }

    public void a(float[] fArray) {
        GLES20.glClearColor((float)fArray[1], (float)fArray[2], (float)fArray[3], (float)fArray[0]);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glClear((int)16384);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    public float h() {
        return 1.0f;
    }

    public void a(float f2, float f3) {
        int n2 = this.L;
        float[] fArray = this.K;
        int n3 = n2 + 12;
        fArray[n3] = fArray[n3] + (fArray[n2 + 0] * f2 + fArray[n2 + 4] * f3);
        int n4 = n2 + 13;
        fArray[n4] = fArray[n4] + (fArray[n2 + 1] * f2 + fArray[n2 + 5] * f3);
        int n5 = n2 + 14;
        fArray[n5] = fArray[n5] + (fArray[n2 + 2] * f2 + fArray[n2 + 6] * f3);
        int n6 = n2 + 15;
        fArray[n6] = fArray[n6] + (fArray[n2 + 3] * f2 + fArray[n2 + 7] * f3);
    }

    public void a(float f2, float f3, float f4) {
        Matrix.scaleM((float[])this.K, (int)this.L, (float)f2, (float)f3, (float)f4);
    }

    public void a(float f2) {
        if (f2 == 0.0f) {
            return;
        }
        float[] fArray = this.K;
        int n2 = this.L;
        this.a(fArray, n2, f2);
    }

    public final void a(float[] fArray, int n2, float f2) {
        float f3 = com.corrodinggames.rts.gameFramework.f.j(f2);
        float f4 = com.corrodinggames.rts.gameFramework.f.k(f2);
        if (f2 == 90.0f) {
            f3 = 1.0f;
            f4 = 0.0f;
        }
        float[] fArray2 = fArray;
        int n3 = n2;
        float f5 = fArray2[0 + n3];
        float f6 = fArray2[1 + n3];
        float f7 = fArray2[2 + n3];
        float f8 = fArray2[3 + n3];
        float f9 = fArray2[4 + n3];
        float f10 = fArray2[5 + n3];
        float f11 = fArray2[6 + n3];
        float f12 = fArray2[7 + n3];
        float f13 = f5 * f4 + f9 * f3;
        float f14 = f6 * f4 + f10 * f3;
        float f15 = f7 * f4 + f11 * f3;
        float f16 = f8 * f4 + f12 * f3;
        int n4 = 0 + n2;
        fArray[n4 + 0] = f13;
        fArray[n4 + 1] = f14;
        fArray[n4 + 2] = f15;
        fArray[n4 + 3] = f16;
        f13 = f5 * -f3 + f9 * f4;
        f14 = f6 * -f3 + f10 * f4;
        f15 = f7 * -f3 + f11 * f4;
        f16 = f8 * -f3 + f12 * f4;
        n4 = 4 + n2;
        fArray[n4 + 0] = f13;
        fArray[n4 + 1] = f14;
        fArray[n4 + 2] = f15;
        fArray[n4 + 3] = f16;
    }

    public float[] i() {
        float[] fArray = this.K;
        int n2 = this.L;
        float[] fArray2 = this.m;
        for (int i = 0; i < 16; ++i) {
            fArray2[i] = fArray[n2 + i];
        }
        return fArray2;
    }

    @Override
    public void b() {
        boolean bl = true;
        if (bl) {
            int n2 = this.L;
            this.L += 16;
            if (this.K.length <= this.L + 16) {
                this.K = Arrays.copyOf(this.K, this.K.length * 2);
            }
            for (int i = 0; i < 16; ++i) {
                this.K[this.L + i] = this.K[n2 + i];
            }
        }
        this.j();
    }

    @Override
    public void c() {
        this.L -= 16;
        if (this.L < 0) {
            com.corrodinggames.rts.gameFramework.l.g("restore: error mCurrentMatrixIndex: " + this.L);
            this.L = 0;
        }
        this.k();
    }

    public void j() {
        com.corrodinggames.rts.gameFramework.utility.m m2 = this.p;
        ++this.o;
        if (this.o >= m2.a) {
            m2.add(new r());
        }
        r r2 = (r)m2.get(this.o);
        this.n.a(r2);
        this.n = r2;
    }

    public void k() {
        com.corrodinggames.rts.gameFramework.utility.m m2 = this.p;
        --this.o;
        if (this.o < 0) {
            com.corrodinggames.rts.gameFramework.l.g("popTransformStack: error currentTransformIndex: " + this.o);
            m2.set(0, new r());
            this.o = 0;
        }
        this.n = (r)m2.get(this.o);
        this.a(false);
    }

    public void a(boolean bl) {
        RectF rectF = this.n.a;
        if (q == rectF && !bl) {
            return;
        }
        this.e();
        if (rectF != null) {
            GLES20.glEnable((int)3089);
            GLES20.glScissor((int)((int)rectF.a), (int)((int)((float)this.N - rectF.d)), (int)((int)rectF.b()), (int)((int)rectF.c()));
        } else {
            GLES20.glDisable((int)3089);
        }
        q = rectF;
    }

    @Override
    public void a(float f2, float f3, float f4, v v2, h h2) {
        this.a(h2);
        this.a(5, 0, 4, f2, f3, 2.0f * f4, 2.0f * f4, v2.a(), 0.0f);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, v v2, h h2) {
        this.n();
        com.corrodinggames.rts.gameFramework.b.n.q();
        this.k.b(1);
        if (v2 == null) {
            this.k.a(-1);
        } else {
            int n2 = v2.a();
            this.k.a(n2);
        }
        float f6 = v2.b();
        if (f6 > 0.0f) {
            float f7 = this.K[this.L];
            this.k.a(f6 * f7);
        } else {
            this.k.a(1.0f);
        }
        com.corrodinggames.rts.gameFramework.b.n.q();
        float[] fArray = this.i();
        this.k.a(f2, f3, fArray);
        this.k.a(f4, f5, fArray);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    private void a(int n2, int n3, int n4, float f2, float f3, float f4, float f5, int n5, float f6) {
        this.a(n3, n5, f6);
        if (this.T != null) {
            this.T.a(this.O, this.S);
        }
        this.a(this.e, n2, n4, f2, f3, f4, f5, null);
    }

    public void a(int n2) {
        if (this.r != n2) {
            GLES20.glUseProgram((int)n2);
            com.corrodinggames.rts.gameFramework.b.n.q();
            this.t = false;
            this.r = n2;
        }
    }

    private void a(int n2, int n3, float f2) {
        this.f();
        this.a(this.O);
        if (f2 > 0.0f) {
            float f3 = this.K[this.L];
            GLES20.glLineWidth((float)(f2 * f3));
            com.corrodinggames.rts.gameFramework.b.n.q();
        } else {
            GLES20.glLineWidth((float)1.0f);
            com.corrodinggames.rts.gameFramework.b.n.q();
        }
        float[] fArray = this.b(n3);
        this.b(true);
        GLES20.glBlendColor((float)fArray[0], (float)fArray[1], (float)fArray[2], (float)fArray[3]);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glUniform4fv((int)this.e[2].a, (int)1, (float[])fArray, (int)0);
        this.b(this.e, n2);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    float[] b(int n2) {
        float f2 = (float)(n2 >>> 24 & 0xFF) * 0.003921569f * this.h();
        float f3 = (float)(n2 >>> 16 & 0xFF) * 0.003921569f * f2;
        float f4 = (float)(n2 >>> 8 & 0xFF) * 0.003921569f * f2;
        float f5 = (float)(n2 & 0xFF) * 0.003921569f * f2;
        this.Y[0] = f3;
        this.Y[1] = f4;
        this.Y[2] = f5;
        this.Y[3] = f2;
        return this.Y;
    }

    private void b(boolean bl) {
        if (bl) {
            if (!this.s) {
                GLES20.glEnable((int)3042);
                com.corrodinggames.rts.gameFramework.b.n.q();
                this.s = true;
            }
        } else if (this.s) {
            GLES20.glDisable((int)3042);
            com.corrodinggames.rts.gameFramework.b.n.q();
            this.s = false;
        }
    }

    public void l() {
        this.b((ae)null);
        this.t = false;
        this.r = -1;
        this.z = null;
        this.s = false;
        com.corrodinggames.rts.gameFramework.b.n.r();
        this.f();
    }

    private void b(q[] qArray, int n2) {
        if (!this.t || this.u != n2) {
            GLES20.glBindBuffer((int)34962, (int)this.R);
            com.corrodinggames.rts.gameFramework.b.n.q();
            GLES20.glVertexAttribPointer((int)qArray[0].a, (int)2, (int)5126, (boolean)false, (int)8, (int)(n2 * 8));
            com.corrodinggames.rts.gameFramework.b.n.q();
            GLES20.glBindBuffer((int)34962, (int)0);
            com.corrodinggames.rts.gameFramework.b.n.q();
            this.t = true;
            this.u = n2;
        }
    }

    private void a(q[] qArray, int n2, int n3, float f2, float f3, float f4, float f5, com.corrodinggames.rts.gameFramework.b.l l2) {
        this.a(qArray, f2, f3, f4, f5, l2);
        int n4 = qArray[0].a;
        GLES20.glEnableVertexAttribArray((int)n4);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glDrawArrays((int)n2, (int)0, (int)n3);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glDisableVertexAttribArray((int)n4);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    private void a(q[] qArray, float f2, float f3, float f4, float f5, com.corrodinggames.rts.gameFramework.b.l l2) {
        if (l2 != null) {
            GLES20.glUniformMatrix4fv((int)qArray[1].a, (int)1, (boolean)false, (float[])l2.a(this.M, this.N, f2, f3, f4, f5), (int)0);
            com.corrodinggames.rts.gameFramework.b.n.q();
            return;
        }
        Matrix.translateM((float[])this.X, (int)0, (float[])this.K, (int)this.L, (float)f2, (float)f3, (float)0.0f);
        Matrix.scaleM((float[])this.X, (int)0, (float)f4, (float)f5, (float)1.0f);
        GLES20.glUniformMatrix4fv((int)qArray[1].a, (int)1, (boolean)false, (float[])this.X, (int)0);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.b.b b2, int n2, int n3, int n4, int n5, af af2, com.corrodinggames.rts.gameFramework.b.l l2) {
        if (n4 <= 0 || n5 <= 0) {
            return;
        }
        this.a(b2.g(), af2);
        ag.a(b2, this.Z);
        this.aa.a(n2, n3, n2 + n4, n3 + n5);
        b2.a(this.Z);
        b2.a(this.Z, this.aa);
        this.a(b2, this.Z, this.aa, l2);
    }

    public void a(ae ae2) {
        if (this.v == ae2) {
            return;
        }
        if (ae2 == null && this.v != null && this.v.a()) {
            if (this.v.b()) {
                this.e();
                this.o();
            }
            return;
        }
        this.b(ae2);
    }

    public void b(ae ae2) {
        if (this.v == ae2) {
            return;
        }
        this.f();
        if (ae2 != null) {
            z z2;
            if (ae2.n == 0) {
                this.d(ae2);
            }
            if (ae2.n == 0) {
                this.v = null;
                return;
            }
            this.j.j = z2 = (z)ae2.q;
        }
        if (ae2 == null) {
            this.j.j = this.j.i;
        }
        this.v = ae2;
    }

    public void c(ae ae2) {
        for (com.corrodinggames.rts.gameFramework.m.af af2 : ae2.p) {
            if (!af2.c && af2.f == null) continue;
            af2.c = false;
            if (af2.b == -1) {
                af2.b = GLES20.glGetUniformLocation((int)ae2.n, (String)af2.a);
                if (af2.b == -1 && !af2.d) {
                    af2.d = true;
                    ae2.b("Unknown parameter: " + af2.a);
                    return;
                }
            }
            if (af2.f != null) {
                Bitmap bitmap = af2.f.b();
                com.corrodinggames.rts.gameFramework.b.b b2 = this.l.a(bitmap, af2.f);
                if (af2.g) {
                    GLES20.glUniform2f((int)af2.b, (float)b2.e, (float)b2.f);
                    continue;
                }
                int n2 = b2.a();
                GLES20.glActiveTexture((int)33985);
                GLES20.glBindTexture((int)3553, (int)n2);
                GLES20.glUniform1i((int)af2.b, (int)1);
                GLES20.glActiveTexture((int)33984);
                continue;
            }
            if (af2.e.length == 1) {
                GLES20.glUniform1f((int)af2.b, (float)af2.e[0]);
                continue;
            }
            if (af2.e.length == 2) {
                GLES20.glUniform2f((int)af2.b, (float)af2.e[0], (float)af2.e[1]);
                continue;
            }
            if (af2.e.length == 4) {
                GLES20.glUniform4f((int)af2.b, (float)af2.e[0], (float)af2.e[1], (float)af2.e[2], (float)af2.e[3]);
                continue;
            }
            ae2.b("Unhandled parameter size: " + af2.a + " - " + af2.e.length);
        }
    }

    public boolean d(ae ae2) {
        if (ae2.o != 0) {
            return false;
        }
        if (ae2.n != 0 && !ae2.m) {
            return true;
        }
        com.corrodinggames.rts.gameFramework.b.n.r();
        ae2.m = false;
        ae2.b("== Compiling shader ==");
        z z2 = new z();
        ae2.q = z2;
        try {
            ae2.n = this.a(z2.f, this.j.h.a(), ae2.f);
        }
        catch (RuntimeException runtimeException) {
            ae2.c("Failed to compile shader: " + runtimeException.getMessage());
            runtimeException.printStackTrace();
            ae2.o = 1;
        }
        if (ae2.o != 0 && ae2.n == 0) {
            ae2.c("Shader program_handle == 0");
            ae2.o = 1;
        }
        com.corrodinggames.rts.gameFramework.b.n.r();
        return true;
    }

    public void a(com.corrodinggames.rts.gameFramework.b.b b2, RectF rectF, RectF rectF2, af af2, com.corrodinggames.rts.gameFramework.b.l l2) {
        if (rectF2.b() <= 0.0f || rectF2.c() <= 0.0f) {
            return;
        }
        this.a(b2.g(), af2);
        this.Z.a(rectF);
        this.aa.a(rectF2);
        b2.a(this.Z);
        b2.a(this.Z, this.aa);
        this.a(b2, this.Z, this.aa, l2);
    }

    private void a(com.corrodinggames.rts.gameFramework.b.b b2, RectF rectF, RectF rectF2, com.corrodinggames.rts.gameFramework.b.l l2) {
        this.m();
        this.j.a(this.w);
        this.j.a(b2, rectF, rectF2, this.i());
    }

    public void m() {
        if (!this.h) {
            if (this.i) {
                this.f();
            }
            this.b(true);
            this.j.a(this.v);
            this.h = true;
        }
    }

    public void n() {
        if (!this.i) {
            if (this.h) {
                this.f();
            }
            this.b(true);
            this.k.a(null);
            this.i = true;
        }
    }

    @Override
    public void f() {
        if (this.h) {
            this.j.d();
            this.h = false;
        }
        if (this.i) {
            this.k.c();
            this.i = false;
        }
    }

    @Override
    public void e() {
        if (this.h) {
            this.j.c();
        }
        if (this.i) {
            this.k.b();
        }
    }

    public void o() {
        this.j.b();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean a(com.corrodinggames.rts.gameFramework.b.b b2) {
        boolean bl = b2.i();
        if (bl) {
            com.corrodinggames.rts.gameFramework.b.n.c(b2.e, b2.f);
            w w2 = this.U;
            synchronized (w2) {
                this.U.a(b2.a());
            }
        }
        return bl;
    }

    public static void b(int n2, int n3) {
        ++x;
        y += n2 * n3 * 4;
    }

    public static void c(int n2, int n3) {
        --x;
        y -= n2 * n3 * 4;
    }

    @Override
    public void d() {
        com.corrodinggames.rts.gameFramework.b.b b2 = (com.corrodinggames.rts.gameFramework.b.b)this.W.remove(this.W.size() - 1);
        com.corrodinggames.rts.gameFramework.b.b b3 = this.t();
        this.a(b2, b3);
        this.c();
        this.l();
    }

    @Override
    public void c(com.corrodinggames.rts.gameFramework.b.b b2) {
        this.b();
        com.corrodinggames.rts.gameFramework.b.b b3 = this.t();
        this.W.add(b2);
        this.a(b3, b2);
    }

    private com.corrodinggames.rts.gameFramework.b.b t() {
        return (com.corrodinggames.rts.gameFramework.b.b)this.W.get(this.W.size() - 1);
    }

    private void a(com.corrodinggames.rts.gameFramework.b.b b2, com.corrodinggames.rts.gameFramework.b.b b3) {
        this.f();
        if (b2 == null && b3 != null) {
            if (b3.g() == 3553) {
                GLES20.glGenFramebuffers((int)1, (int[])this.V, (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
                GLES20.glBindFramebuffer((int)36160, (int)this.V[0]);
                com.corrodinggames.rts.gameFramework.b.n.q();
            } else {
                GLES11Ext.glGenFramebuffersOES((int)1, (int[])this.V, (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
                GLES11Ext.glBindFramebufferOES((int)36160, (int)this.V[0]);
                com.corrodinggames.rts.gameFramework.b.n.q();
            }
        } else if (b2 != null && b3 == null) {
            if (b2.g() == 3553) {
                GLES20.glBindFramebuffer((int)36160, (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
                GLES20.glDeleteFramebuffers((int)1, (int[])this.V, (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
            } else {
                GLES11Ext.glBindFramebufferOES((int)36160, (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
                GLES11Ext.glDeleteFramebuffersOES((int)1, (int[])this.V, (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
            }
        }
        if (b3 == null) {
            this.a(this.M, this.N);
        } else {
            this.a(b3.b(), b3.c());
            if (!b3.i()) {
                b3.b(this);
            }
            if (b3.g() == 3553) {
                GLES20.glFramebufferTexture2D((int)36160, (int)36064, (int)b3.g(), (int)b3.a(), (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
                com.corrodinggames.rts.gameFramework.b.n.u();
            } else {
                GLES11Ext.glFramebufferTexture2DOES((int)36160, (int)36064, (int)b3.g(), (int)b3.a(), (int)0);
                com.corrodinggames.rts.gameFramework.b.n.q();
                com.corrodinggames.rts.gameFramework.b.n.v();
            }
        }
    }

    private static void u() {
        int n2 = GLES20.glCheckFramebufferStatus((int)36160);
        if (n2 != 36053) {
            String string = "";
            switch (n2) {
                case 36054: {
                    string = "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT";
                    break;
                }
                case 36057: {
                    string = "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS";
                    break;
                }
                case 36055: {
                    string = "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT";
                    break;
                }
                case 36061: {
                    string = "GL_FRAMEBUFFER_UNSUPPORTED";
                }
            }
            throw new RuntimeException(string + ":" + Integer.toHexString(n2));
        }
    }

    private static void v() {
        int n2 = GLES11Ext.glCheckFramebufferStatusOES((int)36160);
        if (n2 != 36053) {
            String string = "";
            switch (n2) {
                case 36054: {
                    string = "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT";
                    break;
                }
                case 36057: {
                    string = "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS";
                    break;
                }
                case 36055: {
                    string = "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT";
                    break;
                }
                case 36061: {
                    string = "GL_FRAMEBUFFER_UNSUPPORTED";
                }
            }
            throw new RuntimeException(string + ":" + Integer.toHexString(n2));
        }
    }

    @Override
    public void d(com.corrodinggames.rts.gameFramework.b.b b2) {
        int n2 = b2.g();
        this.b(b2);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glTexParameteri((int)n2, (int)10242, (int)33071);
        GLES20.glTexParameteri((int)n2, (int)10243, (int)33071);
        int n3 = b2.h();
        GLES20.glTexParameterf((int)n2, (int)10241, (float)n3);
        GLES20.glTexParameterf((int)n2, (int)10240, (float)n3);
    }

    public void a(com.corrodinggames.rts.gameFramework.b.b b2, int n2) {
        if (b2.h() != n2 && b2.a() != -1) {
            this.b(b2);
            b2.b(n2);
        }
    }

    @Override
    public void b(com.corrodinggames.rts.gameFramework.b.b b2) {
        if (this.z == b2) {
            // empty if block
        }
        this.e();
        int n2 = b2.g();
        GLES20.glBindTexture((int)n2, (int)b2.a());
        this.z = b2;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.b.b b2, int n2, int n3, int n4) {
        int n5 = b2.g();
        this.b(b2);
        com.corrodinggames.rts.gameFramework.b.n.q();
        int n6 = b2.d();
        int n7 = b2.e();
        GLES20.glTexImage2D((int)n5, (int)0, (int)n4, (int)n6, (int)n7, (int)0, (int)n2, (int)n3, null);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.b.b b2, Bitmap bitmap, int n2) {
        int n3 = b2.g();
        this.b(b2);
        com.corrodinggames.rts.gameFramework.b.n.q();
        if (n2 == 0) {
            GLUtils.texImage2D((int)n3, (int)0, (Bitmap)bitmap, (int)0);
        } else {
            GLUtils.texImage2D((int)n3, (int)0, (int)n2, (Bitmap)bitmap, (int)0);
        }
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.b.b b2, int n2, int n3, Bitmap bitmap, int n4, int n5) {
        int n6 = b2.g();
        this.b(b2);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLUtils.texSubImage2D((int)n6, (int)0, (int)n2, (int)n3, (Bitmap)bitmap, (int)n4, (int)n5);
    }

    public static void a(String string, Throwable throwable) {
        if (B > 1000) {
            return;
        }
        ++B;
        if (throwable != null) {
            Log.b(G, string, throwable);
        } else {
            Log.d(G, string);
        }
    }

    public static void p() {
        int n2 = GLES20.glGetError();
        for (int i = 255; n2 != 0 && i > 0; --i) {
            n2 = GLES20.glGetError();
        }
        if (n2 != 0) {
            com.corrodinggames.rts.gameFramework.b.n.a("clearGlError: Failed to clear", null);
        }
    }

    public static void q() {
        int n2;
        if (A && (n2 = GLES20.glGetError()) != 0) {
            Throwable throwable = new Throwable();
            com.corrodinggames.rts.gameFramework.b.n.a("GL error: " + n2, throwable);
            com.corrodinggames.rts.gameFramework.b.n.p();
        }
    }

    public static void r() {
        int n2 = GLES20.glGetError();
        if (n2 != 0) {
            Throwable throwable = new Throwable();
            com.corrodinggames.rts.gameFramework.b.n.a("GL error: " + n2, throwable);
            com.corrodinggames.rts.gameFramework.b.n.p();
        }
    }

    @Override
    public u a() {
        return ac;
    }

    @Override
    public void a(int n2, int n3, int n4, int n5) {
        float f2;
        float f3 = f2 = this.K[this.L];
        this.n.a = new RectF();
        this.n.a.a = n2;
        this.n.a.c = n4;
        this.n.a.b = n3;
        this.n.a.d = n5;
        this.n.a.a *= f2;
        this.n.a.c *= f2;
        this.n.a.b *= f3;
        this.n.a.d *= f3;
        this.a(false);
    }

    private void a(h h2) {
        if (h2 == null) {
            throw new NullPointerException("draw shape filter is null.");
        }
        this.S = h2;
        if (this.J.containsKey(h2)) {
            int n2 = this.O;
            this.O = (Integer)this.J.get(h2);
            com.corrodinggames.rts.gameFramework.b.n.a(this.e, this.O);
            if (n2 != this.O) {
                this.a(this.O, this.e);
            }
            return;
        }
        this.O = this.a(this.e, h2.a(), h2.b());
        this.a(this.O, this.e);
        this.J.put(h2, this.O);
    }

    private void a(int n2, af af2) {
        if (af2 == null) {
            throw new NullPointerException("Texture filter is null.");
        }
    }

    public int a(q[] qArray, String string, String string2) {
        int n2 = com.corrodinggames.rts.gameFramework.b.n.b(35633, string);
        int n3 = com.corrodinggames.rts.gameFramework.b.n.b(35632, string2);
        return this.a(n2, n3, qArray, this.ab);
    }

    public p a(int n2, boolean bl, boolean bl2) {
        com.corrodinggames.rts.gameFramework.l.e("Loading new font size:" + n2 + " bold:" + bl + " unicode:" + bl2);
        try {
            int n3 = 3;
            int n4 = 2;
            p p2 = new p(this);
            p2.a = n2;
            p2.b = bl;
            Paint paint = new Paint();
            paint.c(true);
            paint.a(true);
            if (!bl) {
                paint.a(Typeface.a(Typeface.c, 0));
            } else {
                paint.a(Typeface.a(Typeface.c, 1));
            }
            p2.c = new b(null);
            p2.c.a(paint, n2, 3, 2);
            p2.c.a(true);
            p2.c.a(12);
            this.C.add(p2);
            return p2;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.e, (Throwable)outOfMemoryError);
            p p3 = new p(this);
            p3.a = n2;
            p3.b = bl;
            if (this.D != null) {
                p3.c = this.D.c;
            }
            this.C.add(p3);
            return p3;
        }
    }

    @Override
    public void a(String string, float f2, float f3, Paint paint) {
        boolean bl;
        int n2;
        this.f();
        boolean bl2 = false;
        Typeface typeface = paint.i();
        if (typeface != null) {
            bl2 = typeface.a();
        }
        if ((n2 = (int)paint.k()) > 42) {
            n2 = 42;
        }
        if (n2 < 10) {
            n2 = 10;
        }
        if (n2 % 2 != 0) {
            // empty if block
        }
        if (bl = com.corrodinggames.rts.gameFramework.f.n(string)) {
            n2 = 24;
        }
        if (this.D == null) {
            this.D = this.a(24, false, true);
        }
        p p2 = null;
        for (p p3 : this.C) {
            if (p3.a != n2 || p3.b != bl2) continue;
            p2 = p3;
        }
        if (p2 == null) {
            p2 = this.a(n2, bl2, bl);
        }
        if (p2.c == null) {
            com.corrodinggames.rts.gameFramework.b.n.a("font.glText==null", null);
            return;
        }
        b b2 = p2.c;
        com.corrodinggames.rts.gameFramework.b.n.r();
        int n3 = paint.e();
        float f4 = (float)Color.a(n3) * 0.003921569f;
        float f5 = (float)Color.b(n3) * 0.003921569f * f4;
        float f6 = (float)Color.c(n3) * 0.003921569f * f4;
        float f7 = (float)Color.d(n3) * 0.003921569f * f4;
        float f8 = paint.k();
        if (f8 != (float)p2.a) {
            // empty if block
        }
        float f9 = f8 / (float)p2.a;
        b2.a(f5, f6, f7, f4, this.d);
        b2.a(f9);
        com.corrodinggames.rts.gameFramework.b.n.r();
        if (paint.j() == Paint$Align.b) {
            float f10 = b2.a(string);
            int n4 = (int)(f10 * 0.5f);
            b2.a(string, f2 - (float)n4, (float)this.c - f3);
        } else {
            b2.a(string, f2, (float)this.c - f3, 0.0f);
        }
        com.corrodinggames.rts.gameFramework.b.n.r();
        b2.d();
        this.l();
    }

    @Override
    public void a(float[] fArray, int n2, int n3, v v2, h h2) {
        if (this.F == null || this.F.capacity() < n3 * 4) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(n3 * 4 + 10);
            byteBuffer.order(ByteOrder.nativeOrder());
            this.F = byteBuffer.asFloatBuffer();
        }
        this.F.clear();
        this.F.put(fArray, n2, n3);
        this.F.flip();
        this.F.position(0);
        float f2 = v2.b();
        int n4 = v2.a();
        if (f2 == 0.0f) {
            f2 = 1.0f;
        }
        this.a(h2);
        this.a(0, n4, f2);
        if (this.T != null) {
            this.T.a(this.O, this.S);
        }
        q[] qArray = this.e;
        int n5 = qArray[0].a;
        GLES20.glEnableVertexAttribArray((int)n5);
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glVertexAttribPointer((int)n5, (int)2, (int)5126, (boolean)false, (int)0, (Buffer)this.F);
        float f3 = 1.0f;
        float f4 = 1.0f;
        this.a(qArray, 0.0f, 0.0f, 1.0f, 1.0f, null);
        GLES20.glDrawArrays((int)0, (int)0, (int)(n3 / 2));
        com.corrodinggames.rts.gameFramework.b.n.q();
        GLES20.glDisableVertexAttribArray((int)n5);
        com.corrodinggames.rts.gameFramework.b.n.q();
    }

    @Override
    public void a(Bitmap bitmap) {
        if (this.a != null) {
            this.a.b(bitmap);
        }
    }

    static /* synthetic */ String s() {
        return G;
    }

    static {
        A = false;
        B = 0;
    }
}
