/*
 * v19.133c 渲染层战役: 按 02b b/n.java 全文整写直译 (F23 方法序整写重建)
 * 方法名保持 02b 混淆名 (a/b/c/d/e/f/g/h/i/j/k/l/m/n/o/p/q/r/s/t/u/v + 重载),
 * 外部调用点 (BitmapDrawer/OpenGLRenderer 等) 按混淆名匹配。
 * 字段名保留 03 现有语义名, 类型按 02b 映射修正:
 *   j: GameSaver -> DrawBatch (02b y), k: AbstractInputConfig -> DrawCall (02b aj),
 *   n: VersionInfo -> DrawRegion (02b r), ac: ResourceDomainEnum -> TextureIdProvider (02b u),
 *   S: ShaderSource (02b h), T: ShaderBinder (02b m), U: IntStack (02b w),
 *   D: BatchEntry (02b p), E: BitmapDrawer (02b m/k), z: ab (02b ab)
 */
package com.corrodinggames.rts.gameFramework.opengl;

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
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.ResourceDomainEnum;
import com.corrodinggames.rts.gameFramework.opengl.ag;
import com.corrodinggames.rts.gameFramework.opengl.BlendMode;
import com.corrodinggames.rts.gameFramework.opengl.DrawBatch;
import com.corrodinggames.rts.gameFramework.opengl.DrawCall;
import com.corrodinggames.rts.gameFramework.opengl.DrawRegion;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.GLUniform;
import com.corrodinggames.rts.gameFramework.opengl.GLTextureGenerator;
import com.corrodinggames.rts.gameFramework.opengl.IntStack;
import com.corrodinggames.rts.gameFramework.opengl.LineStyle;
import com.corrodinggames.rts.gameFramework.opengl.ShaderBinder;
import com.corrodinggames.rts.gameFramework.opengl.ShaderLayout;
import com.corrodinggames.rts.gameFramework.opengl.ShaderSource;
import com.corrodinggames.rts.gameFramework.opengl.TextureIdProvider;
import com.corrodinggames.rts.gameFramework.opengl.TransformCallback;
import com.corrodinggames.rts.gameFramework.opengl.batch.b;
import com.corrodinggames.rts.gameFramework.rendering.BitmapDrawer;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class GLRenderer
implements k {
    public ac a;
    private static final String G = GLRenderer.class.getSimpleName();
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
    private ShaderSource S;  // 02b b/n.java: private h S (b/h=ShaderSource)
    private ShaderBinder T;  // 02b b/n.java: private m T (b/m=ShaderBinder)
    GLUniform[] e;
    GLUniform[] f;
    GLUniform[] g;
    private IntStack U;  // 02b b/n.java: private final w U (b/w=IntStack)
    private int[] V;
    private ArrayList W;
    private float[] X;
    private float[] Y;
    private RectF Z;
    private RectF tempRect2;  // 02b b/n.java: private final RectF aa
    private int[] linkStatus;  // 02b b/n.java: private final int[] ab
    private static final TextureIdProvider ac = new GLTextureGenerator();  // 02b b/n.java: private static final u ac = new t()
    boolean h;
    boolean i;
    DrawBatch j;  // 02b b/n.java: y j (b/y=DrawBatch)
    DrawCall k;  // 02b b/n.java: aj k (b/aj=DrawCall)
    com.corrodinggames.rts.gameFramework.opengl.f l;
    float[] m;
    DrawRegion n;  // 02b b/n.java: r n (b/r=DrawRegion)
    public int o;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList p;
    static RectF q;
    int r;
    boolean s;
    boolean t;
    int u;
    Shader v;
    public int w;
    static int x;
    static int y;
    ab z;
    public static boolean A = false;
    static int B = 0;
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList C;
    BatchEntry D;
    public static com.corrodinggames.rts.gameFramework.rendering.BitmapDrawer E;
    FloatBuffer F;

    private int a(int var1, int var2, GLUniform[] var3, int[] var4) {  // 02b L103
        int var5 = GLES20.glCreateProgram();
        r();
        if (var5 == 0) {
            throw new RuntimeException("Cannot create GL program: " + GLES20.glGetError());
        } else {
            GLES20.glAttachShader(var5, var1);
            r();
            GLES20.glAttachShader(var5, var2);
            r();
            GLES20.glLinkProgram(var5);
            r();
            GLES20.glGetProgramiv(var5, 35714, var4, 0);
            if (var4[0] != 1) {
                Log.d(G, "======= ERROR =========");
                Log.d(G, "Could not link program: ");
                String var6 = GLES20.glGetProgramInfoLog(var5);
                Log.d(G, var6);
                GLES20.glDeleteProgram(var5);
                throw new RuntimeException("Cannot link GL program: " + var6);
            } else {
                a(var3, var5);
                return var5;
            }
        }
    }

    private static void a(GLUniform[] var0, int var1) {  // 02b L131
        for (int var2 = 0; var2 < var0.length; ++var2) {
            var0[var2].a(var1);
        }
    }

    private static String a(int var0, String var1) {  // 02b L138
        StringBuffer var2 = new StringBuffer();
        boolean var3 = false;
        String[] var4 = var1.split("\n");
        int var5 = var4.length;
        for (int var6 = 0; var6 < var5; ++var6) {
            String var7 = var4[var6];
            if (!var3 && var7.contains("version")) {
                var3 = true;
            } else {
                if (!var7.equals(var7)) {
                    GlobalState.e("Changing: " + var7 + " to " + var7);
                }
                var2.append(var7);
                var2.append("\n");
            }
        }
        return var2.toString();
    }

    private static int b(int var0, String var1) {  // 02b L169
        int var2 = GLES20.glCreateShader(var0);
        if (var1 == null) {
            throw new RuntimeException("Shader Compilation Failed: shaderCode==null");
        } else {
            var1 = a(var0, var1);
            GLES20.glShaderSource(var2, var1);
            q();
            GLES20.glCompileShader(var2);
            q();
            int[] var3 = new int[1];
            GLES20.glGetShaderiv(var2, 35713, var3, 0);
            if (var3[0] == 0) {
                String var4 = GLES20.glGetShaderInfoLog(var2);
                String var5 = "Shader Compilation Failed: " + var4;
                Log.d(G, var5);
                throw new RuntimeException(var5);
            } else {
                return var2;
            }
        }
    }

    public void a(int var1, int var2) {  // 02b L192: 视口+正交矩阵
        this.b = var1;
        this.c = var2;
        q();
        Matrix.setIdentityM(this.K, this.L);
        Matrix.orthoM(this.d, 0, 0.0f, (float)var1, 0.0f, (float)var2, -1.0f, 1.0f);
        q();
        if (this.n == null) {
            this.M = var1;
            this.N = var2;
            Matrix.translateM(this.K, this.L, 0.0f, (float)var2, 0.0f);
            Matrix.scaleM(this.K, this.L, 1.0f, -1.0f, 1.0f);
            q();
        }
        GLES20.glViewport(0, 0, this.b, this.c);
        this.g();
    }

    public void a(int var1, GLUniform[] var2) {  // 02b L211
        q();
        this.f();
        q();
        this.a(var1);
        GLES20.glUniformMatrix4fv(var2[5].uniformName, 1, false, this.d, 0);
        q();
    }

    public void g() {  // 02b L220: 绑定程序
        GLUniform[] var1;
        if (this.O != 0) {
            var1 = this.e;
            this.a(this.O, var1);
        }
        if (this.P != 0) {
            var1 = this.f;
            this.a(this.P, var1);
        }
        if (this.Q != 0) {
            var1 = this.g;
            this.a(this.Q, var1);
        }
    }

    public void a(float[] var1) {  // 02b L239: 清屏
        GLES20.glClearColor(var1[1], var1[2], var1[3], var1[0]);
        q();
        GLES20.glClear(16384);
        q();
    }

    public float h() {  // 02b L246
        return 1.0f;
    }

    public void a(float var1, float var2) {  // 02b L250: 矩阵平移
        int var3 = this.L;
        float[] var4 = this.K;
        var4[var3 + 12] += var4[var3 + 0] * var1 + var4[var3 + 4] * var2;
        var4[var3 + 13] += var4[var3 + 1] * var1 + var4[var3 + 5] * var2;
        var4[var3 + 14] += var4[var3 + 2] * var1 + var4[var3 + 6] * var2;
        var4[var3 + 15] += var4[var3 + 3] * var1 + var4[var3 + 7] * var2;
    }

    public void a(float var1, float var2, float var3) {  // 02b L259: 矩阵缩放
        Matrix.scaleM(this.K, this.L, var1, var2, var3);
    }

    public void a(float var1) {  // 02b L263: 矩阵旋转
        if (var1 != 0.0f) {
            float[] var2 = this.K;
            int var3 = this.L;
            this.a(var2, var3, var1);
        }
    }

    public final void a(float[] var1, int var2, float var3) {  // 02b L271: 旋转矩阵
        float var4 = GameUtils.j(var3);
        float var5 = GameUtils.k(var3);
        if (var3 == 90.0f) {
            var4 = 1.0f;
            var5 = 0.0f;
        }
        float var8 = var1[0 + var2];
        float var9 = var1[1 + var2];
        float var10 = var1[2 + var2];
        float var11 = var1[3 + var2];
        float var12 = var1[4 + var2];
        float var13 = var1[5 + var2];
        float var14 = var1[6 + var2];
        float var15 = var1[7 + var2];
        float var16 = var8 * var5 + var12 * var4;
        float var17 = var9 * var5 + var13 * var4;
        float var18 = var10 * var5 + var14 * var4;
        float var19 = var11 * var5 + var15 * var4;
        int var20 = 0 + var2;
        var1[var20 + 0] = var16;
        var1[var20 + 1] = var17;
        var1[var20 + 2] = var18;
        var1[var20 + 3] = var19;
        var16 = var8 * -var4 + var12 * var5;
        var17 = var9 * -var4 + var13 * var5;
        var18 = var10 * -var4 + var14 * var5;
        var19 = var11 * -var4 + var15 * var5;
        var20 = 4 + var2;
        var1[var20 + 0] = var16;
        var1[var20 + 1] = var17;
        var1[var20 + 2] = var18;
        var1[var20 + 3] = var19;
    }

    public float[] i() {  // 02b L307: 当前矩阵
        float[] var1 = this.K;
        int var2 = this.L;
        float[] var3 = this.m;
        for (int var4 = 0; var4 < 16; ++var4) {
            var3[var4] = var1[var2 + var4];
        }
        return var3;
    }

    public void b() {  // 02b L319: 压栈
        boolean var1 = true;
        if (var1) {
            int var2 = this.L;
            this.L += 16;
            if (this.K.length <= this.L + 16) {
                this.K = Arrays.copyOf(this.K, this.K.length * 2);
            }
            for (int var3 = 0; var3 < 16; ++var3) {
                this.K[this.L + var3] = this.K[var2 + var3];
            }
        }
        this.j();
    }

    public void c() {  // 02b L336: 弹栈
        this.L -= 16;
        if (this.L < 0) {
            GlobalState.g("restore: error mCurrentMatrixIndex: " + this.L);
            this.L = 0;
        }
        this.k();
    }

    public void j() {  // 02b L346: 变换栈推进
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList var1 = this.p;
        ++this.o;
        if (this.o >= var1.a) {
            var1.add(new DrawRegion());
        }
        DrawRegion var2 = (DrawRegion)var1.get(this.o);
        this.n.a(var2);
        this.n = var2;
    }

    public void k() {  // 02b L358: 变换栈回退
        com.corrodinggames.rts.gameFramework.utility.CustomArrayList var1 = this.p;
        --this.o;
        if (this.o < 0) {
            GlobalState.g("popTransformStack: error currentTransformIndex: " + this.o);
            var1.set(0, new DrawRegion());
            this.o = 0;
        }
        this.n = (DrawRegion)var1.get(this.o);
        this.a(false);
    }

    public void a(boolean var1) {  // 02b L371: 裁剪
        RectF var2 = this.n.a;
        if (q != var2 || var1) {
            this.e();
            if (var2 != null) {
                GLES20.glEnable(3089);
                GLES20.glScissor((int)var2.a, (int)((float)this.N - var2.d), (int)var2.b(), (int)var2.c());
            } else {
                GLES20.glDisable(3089);
            }
            q = var2;
        }
    }

    public void a(float var1, float var2, float var3, LineStyle var4, ShaderSource var5) {  // 02b L386: 圆
        this.a(var5);
        this.a(5, 0, 4, var1, var2, 2.0f * var3, 2.0f * var3, var4.a(), 0.0f);
    }

    public void a(float var1, float var2, float var3, float var4, LineStyle var5, ShaderSource var6) {  // 02b L391: 矩形
        this.n();
        q();
        this.k.b(1);
        if (var5 == null) {
            this.k.a(-1);
        } else {
            int var7 = var5.a();
            this.k.a(var7);
        }
        float var9 = var5.b();
        if (var9 > 0.0f) {
            float var8 = this.K[this.L];
            this.k.a(var9 * var8);
        } else {
            this.k.a(1.0f);
        }
        q();
        float[] var10 = this.i();
        this.k.a(var1, var2, var10);
        this.k.a(var3, var4, var10);
        q();
    }

    private void a(int var1, int var2, int var3, float var4, float var5, float var6, float var7, int var8, float var9) {  // 02b L417
        this.a(var2, var8, var9);
        if (this.T != null) {
            this.T.a(this.O, this.S);
        }
        this.a(this.e, var1, var3, var4, var5, var6, var7, (TransformCallback)null);
    }

    public void a(int var1) {  // 02b L426: 使用程序
        if (this.r != var1) {
            GLES20.glUseProgram(var1);
            q();
            this.t = false;
            this.r = var1;
        }
    }

    private void a(int var1, int var2, float var3) {  // 02b L436
        this.f();
        this.a(this.O);
        if (var3 > 0.0f) {
            float var4 = this.K[this.L];
            GLES20.glLineWidth(var3 * var4);
            q();
        } else {
            GLES20.glLineWidth(1.0f);
            q();
        }
        float[] var5 = this.b(var2);
        this.b(true);
        GLES20.glBlendColor(var5[0], var5[1], var5[2], var5[3]);
        q();
        GLES20.glUniform4fv(this.e[2].uniformName, 1, var5, 0);
        this.b(this.e, var1);
        q();
    }

    float[] b(int var2) {  // 02b L457: 颜色分解
        float var3 = (float)(var2 >>> 24 & 255) * 0.003921569f * this.h();
        float var4 = (float)(var2 >>> 16 & 255) * 0.003921569f * var3;
        float var5 = (float)(var2 >>> 8 & 255) * 0.003921569f * var3;
        float var6 = (float)(var2 & 255) * 0.003921569f * var3;
        this.Y[0] = var4;
        this.Y[1] = var5;
        this.Y[2] = var6;
        this.Y[3] = var3;
        return this.Y;
    }

    private void b(boolean var1) {  // 02b L469: 混合开关
        if (var1) {
            if (!this.s) {
                GLES20.glEnable(3042);
                q();
                this.s = true;
            }
        } else if (this.s) {
            GLES20.glDisable(3042);
            q();
            this.s = false;
        }
    }

    public void l() {  // 02b L484: 重置
        this.b((Shader)null);
        this.t = false;
        this.r = -1;
        this.z = null;
        this.s = false;
        r();
        this.f();
    }

    private void b(GLUniform[] var1, int var2) {  // 02b L494
        if (!this.t || this.u != var2) {
            GLES20.glBindBuffer(34962, this.R);
            q();
            GLES20.glVertexAttribPointer(var1[0].uniformName, 2, 5126, false, 8, var2 * 8);
            q();
            GLES20.glBindBuffer(34962, 0);
            q();
            this.t = true;
            this.u = var2;
        }
    }

    private void a(GLUniform[] var1, int var2, int var3, float var4, float var5, float var6, float var7, TransformCallback var8) {  // 02b L508
        this.a(var1, var4, var5, var6, var7, var8);
        int var9 = var1[0].uniformName;
        GLES20.glEnableVertexAttribArray(var9);
        q();
        GLES20.glDrawArrays(var2, 0, var3);
        q();
        GLES20.glDisableVertexAttribArray(var9);
        q();
    }

    private void a(GLUniform[] var1, float var2, float var3, float var4, float var5, TransformCallback var6) {  // 02b L519
        if (var6 != null) {
            GLES20.glUniformMatrix4fv(var1[1].uniformName, 1, false, var6.a(this.M, this.N, var2, var3, var4, var5), 0);
            q();
        } else {
            Matrix.translateM(this.X, 0, this.K, this.L, var2, var3, 0.0f);
            Matrix.scaleM(this.X, 0, var4, var5, 1.0f);
            GLES20.glUniformMatrix4fv(var1[1].uniformName, 1, false, this.X, 0);
            q();
        }
    }

    public void a(GLObject var1, int var2, int var3, int var4, int var5, BlendMode var6, TransformCallback var7) {  // 02b L531: 纹理绘制
        if (var4 > 0 && var5 > 0) {
            this.a(var1.g(), var6);
            ag.a(var1, this.Z);
            this.tempRect2.a((float)var2, (float)var3, (float)(var2 + var4), (float)(var3 + var5));
            var1.a(this.Z);
            var1.a(this.Z, this.tempRect2);
            this.a(var1, this.Z, this.tempRect2, var7);
        }
    }

    public void a(Shader var1) {  // 02b L542: 设置着色器
        if (this.v != var1) {
            if (var1 == null && this.v != null && this.v.a()) {
                if (this.v.b()) {
                    this.e();
                    this.o();
                }
            } else {
                this.b(var1);
            }
        }
    }

    public void b(Shader var1) {  // 02b L556: 内部设置着色器
        if (this.v != var1) {
            this.f();
            if (var1 != null) {
                if (var1.n == 0) {
                    this.d(var1);
                }
                if (var1.n == 0) {
                    this.v = null;
                    return;
                }
                ShaderLayout var2 = (ShaderLayout)var1.q;
                this.j.j = var2;
            }
            if (var1 == null) {
                this.j.j = this.j.i;
            }
            this.v = var1;
        }
    }

    public void c(Shader var1) {  // 02b L581: 更新 uniform
        com.corrodinggames.rts.gameFramework.rendering.ShaderUniform[] var2 = var1.p;
        int var3 = var2.length;
        for (int var4 = 0; var4 < var3; ++var4) {
            com.corrodinggames.rts.gameFramework.rendering.ShaderUniform var5 = var2[var4];
            if (var5.isInteger || var5.textureRef != null) {
                var5.isInteger = false;
                if (var5.uniformLocation == -1) {
                    var5.uniformLocation = GLES20.glGetUniformLocation(var1.n, var5.uniformName);
                    if (var5.uniformLocation == -1 && !var5.isArray) {
                        var5.isArray = true;
                        var1.b("Unknown parameter: " + var5.uniformName);
                        return;
                    }
                }
                if (var5.textureRef != null) {
                    Bitmap var6 = var5.textureRef.b();
                    GLObject var7 = this.l.a(var6, var5.textureRef);
                    if (var5.needsUpdate) {
                        GLES20.glUniform2f(var5.uniformLocation, (float)var7.e, (float)var7.f);
                    } else {
                        int var8 = var7.a();
                        GLES20.glActiveTexture(33985);
                        GLES20.glBindTexture(3553, var8);
                        GLES20.glUniform1i(var5.uniformLocation, 1);
                        GLES20.glActiveTexture(33984);
                    }
                } else if (var5.floatValues.length == 1) {
                    GLES20.glUniform1f(var5.uniformLocation, var5.floatValues[0]);
                } else if (var5.floatValues.length == 2) {
                    GLES20.glUniform2f(var5.uniformLocation, var5.floatValues[0], var5.floatValues[1]);
                } else if (var5.floatValues.length == 4) {
                    GLES20.glUniform4f(var5.uniformLocation, var5.floatValues[0], var5.floatValues[1], var5.floatValues[2], var5.floatValues[3]);
                } else {
                    var1.b("Unhandled parameter size: " + var5.uniformName + " - " + var5.floatValues.length);
                }
            }
        }
    }

    public boolean d(Shader var1) {  // 02b L624: 编译着色器
        if (var1.o != 0) {
            return false;
        } else if (var1.n != 0 && !var1.m) {
            return true;
        } else {
            r();
            var1.m = false;
            var1.b("== Compiling shader ==");
            ShaderLayout var2 = new ShaderLayout();
            var1.q = var2;
            try {
                var1.n = this.a(var2.f, this.j.h.a(), var1.f);
            } catch (RuntimeException var4) {
                var1.c("Failed to compile shader: " + var4.getMessage());
                var4.printStackTrace();
                var1.o = 1;
            }
            if (var1.o != 0 && var1.n == 0) {
                var1.c("Shader program_handle == 0");
                var1.o = 1;
            }
            r();
            return true;
        }
    }

    public void a(GLObject var1, RectF var2, RectF var3, BlendMode var4, TransformCallback var5) {  // 02b L654
        if (var3.b() > 0.0f && var3.c() > 0.0f) {
            this.a(var1.g(), var4);
            this.Z.a(var2);
            this.tempRect2.a(var3);
            var1.a(this.Z);
            var1.a(this.Z, this.tempRect2);
            this.a(var1, this.Z, this.tempRect2, var5);
        }
    }

    private void a(GLObject var1, RectF var2, RectF var3, TransformCallback var4) {  // 02b L665
        this.m();
        this.j.a(this.w);
        this.j.a(var1, var2, var3, this.i());
    }

    public void m() {  // 02b L671: 开始纹理批
        if (!this.h) {
            if (this.i) {
                this.f();
            }
            this.b(true);
            this.j.a(this.v);
            this.h = true;
        }
    }

    public void n() {  // 02b L684: 开始形状批
        if (!this.i) {
            if (this.h) {
                this.f();
            }
            this.b(true);
            this.k.a((Shader)null);
            this.i = true;
        }
    }

    public void f() {  // 02b L697: 结束批
        if (this.h) {
            this.j.d();
            this.h = false;
        }
        if (this.i) {
            this.k.c();
            this.i = false;
        }
    }

    public void e() {  // 02b L710: 刷新批
        if (this.h) {
            this.j.c();
        }
        if (this.i) {
            this.k.b();
        }
    }

    public void o() {  // 02b L721: 冲刷
        this.j.b();
    }

    public boolean a(GLObject var1) {  // 02b L725
        boolean var2 = var1.i();
        if (var2) {
            c(var1.e, var1.f);
            IntStack var3 = this.U;
            synchronized (this.U) {
                this.U.a(var1.a());
            }
        }
        return var2;
    }

    public static void b(int var0, int var1) {  // 02b L738: 纹理统计
        ++x;
        y += var0 * var1 * 4;
    }

    public static void c(int var0, int var1) {  // 02b L743: 纹理统计回退
        --x;
        y -= var0 * var1 * 4;
    }

    public void d() {  // 02b L748: 弹出帧缓冲
        GLObject var1 = (GLObject)this.W.remove(this.W.size() - 1);
        GLObject var2 = this.t();
        this.a(var1, var2);
        this.c();
        this.l();
    }

    public void c(GLObject var1) {  // 02b L756: 压入帧缓冲
        this.b();
        GLObject var2 = this.t();
        this.W.add(var1);
        this.a(var2, var1);
    }

    private GLObject t() {  // 02b L763
        return (GLObject)this.W.get(this.W.size() - 1);
    }

    private void a(GLObject var1, GLObject var2) {  // 02b L767: 帧缓冲绑定
        this.f();
        if (var1 == null && var2 != null) {
            if (var2.g() == 3553) {
                GLES20.glGenFramebuffers(1, this.V, 0);
                q();
                GLES20.glBindFramebuffer(36160, this.V[0]);
                q();
            } else {
                GLES11Ext.glGenFramebuffersOES(1, this.V, 0);
                q();
                GLES11Ext.glBindFramebufferOES(36160, this.V[0]);
                q();
            }
        } else if (var1 != null && var2 == null) {
            if (var1.g() == 3553) {
                GLES20.glBindFramebuffer(36160, 0);
                q();
                GLES20.glDeleteFramebuffers(1, this.V, 0);
                q();
            } else {
                GLES11Ext.glBindFramebufferOES(36160, 0);
                q();
                GLES11Ext.glDeleteFramebuffersOES(1, this.V, 0);
                q();
            }
        }
        if (var2 == null) {
            this.a(this.M, this.N);
        } else {
            this.a(var2.b(), var2.c());
            if (!var2.i()) {
                var2.b(this);
            }
            if (var2.g() == 3553) {
                GLES20.glFramebufferTexture2D(36160, 36096, var2.g(), var2.a(), 0);
                q();
                u();
            } else {
                GLES11Ext.glFramebufferTexture2DOES(36160, 36096, var2.g(), var2.a(), 0);
                q();
                v();
            }
        }
    }

    private static void u() {  // 02b L816: 帧缓冲状态检查 (GLES20)
        int var0 = GLES20.glCheckFramebufferStatus(36160);
        if (var0 != 36053) {
            String var1 = "";
            switch (var0) {
                case 36054:
                    var1 = "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT";
                    break;
                case 36055:
                    var1 = "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT";
                case 36056:
                case 36058:
                case 36059:
                case 36060:
                default:
                    break;
                case 36057:
                    var1 = "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS";
                    break;
                case 36061:
                    var1 = "GL_FRAMEBUFFER_UNSUPPORTED";
            }
            throw new RuntimeException(var1 + ":" + Integer.toHexString(var0));
        }
    }

    private static void v() {  // 02b L843: 帧缓冲状态检查 (OES)
        int var0 = GLES11Ext.glCheckFramebufferStatusOES(36160);
        if (var0 != 36053) {
            String var1 = "";
            switch (var0) {
                case 36054:
                    var1 = "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT";
                    break;
                case 36055:
                    var1 = "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT";
                case 36056:
                case 36058:
                case 36059:
                case 36060:
                default:
                    break;
                case 36057:
                    var1 = "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS";
                    break;
                case 36061:
                    var1 = "GL_FRAMEBUFFER_UNSUPPORTED";
            }
            throw new RuntimeException(var1 + ":" + Integer.toHexString(var0));
        }
    }

    public void d(GLObject var1) {  // 02b L870: 纹理参数
        int var2 = var1.g();
        this.b(var1);
        q();
        GLES20.glTexParameteri(var2, 10242, 33071);
        GLES20.glTexParameteri(var2, 10243, 33071);
        int var3 = var1.h();
        GLES20.glTexParameterf(var2, 10241, (float)var3);
        GLES20.glTexParameterf(var2, 10240, (float)var3);
    }

    public void a(GLObject var1, int var2) {  // 02b L881: 过滤模式
        if (var1.h() != var2 && var1.a() != -1) {
            this.b(var1);
            var1.b(var2);
        }
    }

    public void b(GLObject var1) {  // 02b L889: 绑定纹理
        this.e();
        int var2 = var1.g();
        GLES20.glBindTexture(var2, var1.a());
        this.z = var1;
    }

    public void a(GLObject var1, int var2, int var3, int var4) {  // 02b L900
        int var5 = var1.g();
        this.b(var1);
        q();
        int var6 = var1.d();
        int var7 = var1.e();
        GLES20.glTexImage2D(var5, 0, var4, var6, var7, 0, var2, var3, (Buffer)null);
    }

    public void a(GLObject var1, Bitmap var2, int var3) {  // 02b L909
        int var4 = var1.g();
        this.b(var1);
        q();
        if (var3 == 0) {
            GLUtils.texImage2D(var4, 0, var2, 0);
        } else {
            GLUtils.texImage2D(var4, 0, var3, var2, 0);
        }
    }

    public void a(GLObject var1, int var2, int var3, Bitmap var4, int var5, int var6) {  // 02b L921
        int var7 = var1.g();
        this.b(var1);
        q();
        GLUtils.texSubImage2D(var7, 0, var2, var3, var4, var5, var6);
    }

    public static void a(String var0, Throwable var1) {  // 02b L928
        if (B <= 1000) {
            ++B;
            if (var1 != null) {
                Log.b(G, var0, var1);
            } else {
                Log.d(G, var0);
            }
        }
    }

    public static void p() {  // 02b L940: 清 GL 错误
        int var0 = 255;
        int var1;
        for (var1 = GLES20.glGetError(); var1 != 0 && var0 > 0; var1 = GLES20.glGetError()) {
            --var0;
        }
        if (var1 != 0) {
            a("clearGlError: Failed to clear", (Throwable)null);
        }
    }

    public static void q() {  // 02b L954: GL 错误检查
        if (A) {
            int var0 = GLES20.glGetError();
            if (var0 != 0) {
                Throwable var1 = new Throwable();
                a("GL error: " + var0, var1);
                p();
            }
        }
    }

    public static void r() {  // 02b L966
        int var0 = GLES20.glGetError();
        if (var0 != 0) {
            Throwable var1 = new Throwable();
            a("GL error: " + var0, var1);
            p();
        }
    }

    public TextureIdProvider a() {  // 02b L976
        return ac;
    }

    public void a(int var1, int var2, int var3, int var4) {  // 02b L980: 裁剪矩形
        float var5 = this.K[this.L];
        this.n.a = new RectF();
        this.n.a.a = (float)var1;
        this.n.a.c = (float)var3;
        this.n.a.b = (float)var2;
        this.n.a.d = (float)var4;
        this.n.a.a *= var5;
        this.n.a.c *= var5;
        this.n.a.b *= var5;
        this.n.a.d *= var5;
        this.a(false);
    }

    private void a(ShaderSource var1) {  // 02b L994: 形状过滤器
        if (var1 == null) {
            throw new NullPointerException("draw shape filter is null.");
        } else {
            this.S = var1;
            if (this.J.containsKey(var1)) {
                int var2 = this.O;
                this.O = ((Integer)this.J.get(var1)).intValue();
                a(this.e, this.O);
                if (var2 != this.O) {
                    this.a(this.O, this.e);
                }
            } else {
                this.O = this.a(this.e, var1.a(), var1.b());
                this.a(this.O, this.e);
                this.J.put(var1, Integer.valueOf(this.O));
            }
        }
    }

    private void a(int var1, BlendMode var2) {  // 02b L1015: 纹理过滤器空检查
        if (var2 == null) {
            throw new NullPointerException("Texture filter is null.");
        }
    }

    public int a(GLUniform[] var1, String var2, String var3) {  // 02b L1021: 编译程序
        int var4 = b(35633, var2);
        int var5 = b(35632, var3);
        return this.a(var4, var5, var1, this.linkStatus);
    }

    public BatchEntry a(int var1, boolean var2, boolean var3) {  // 02b L1027: 加载字体
        GlobalState.e("Loading new font size:" + var1 + " bold:" + var2 + " unicode:" + var3);
        try {
            BatchEntry var6 = new BatchEntry(this);
            var6.a = var1;
            var6.b = var2;
            Paint var7 = new Paint();
            var7.c(true);
            var7.a(true);
            if (!var2) {
                var7.a(Typeface.a(Typeface.c, 0));
            } else {
                var7.a(Typeface.a(Typeface.c, 1));
            }
            var6.c = new com.corrodinggames.rts.gameFramework.opengl.batch.b((android.content.res.AssetManager)null);
            var6.c.a(var7, var1, 3, 2);
            var6.c.a(true);
            var6.c.a((int)12);
            this.C.add(var6);
            return var6;
        } catch (OutOfMemoryError var8) {
            GlobalState.a(ResourceDomainEnum.e, (Throwable)var8);
            BatchEntry var5 = new BatchEntry(this);
            var5.a = var1;
            var5.b = var2;
            if (this.D != null) {
                var5.c = this.D.c;
            }
            this.C.add(var5);
            return var5;
        }
    }

    public void a(String var1, float var2, float var3, Paint var4) {  // 02b L1065: 文本
        this.f();
        boolean var5 = false;
        Typeface var6 = var4.i();
        if (var6 != null) {
            var5 = var6.a();
        }
        int var7 = (int)var4.k();
        if (var7 > 42) {
            var7 = 42;
        }
        if (var7 < 10) {
            var7 = 10;
        }
        boolean var8 = false;
        int var23;
        int var21 = var1.length();
        for (int var22 = 0; var22 < var21; var22 += Character.charCount(var23)) {
            var23 = var1.codePointAt(var22);
            if (var23 > 128) {
                var8 = true;
                break;
            }
        }
        if (var8) {
            var7 = 24;
        }
        if (this.D == null) {
            this.D = this.a(24, false, true);
        }
        BatchEntry var9 = null;
        Iterator var10 = this.C.iterator();
        while (var10.hasNext()) {
            BatchEntry var11 = (BatchEntry)var10.next();
            if (var11.a == var7 && var11.b == var5) {
                var9 = var11;
            }
        }
        if (var9 == null) {
            var9 = this.a(var7, var5, var8);
        }
        if (var9.c == null) {
            a("font.glText==null", (Throwable)null);
        } else {
            com.corrodinggames.rts.gameFramework.opengl.batch.b var20 = var9.c;
            r();
            int var24 = var4.e();
            float var12 = (float)Color.a(var24) * 0.003921569f;
            float var13 = (float)Color.b(var24) * 0.003921569f * var12;
            float var14 = (float)Color.c(var24) * 0.003921569f * var12;
            float var15 = (float)Color.d(var24) * 0.003921569f * var12;
            float var16 = var4.k();
            if (var16 != (float)var9.a) {
            }
            float var17 = var16 / (float)var9.a;
            var20.a(var13, var14, var15, var12, this.d);
            var20.a(var17);
            r();
            if (var4.j() == Paint$Align.b) {
                float var18 = var20.a(var1);
                int var19 = (int)(var18 * 0.5f);
                var20.a(var1, var2 - (float)var19, (float)this.c - var3);
            } else {
                var20.a(var1, var2, (float)this.c - var3, 0.0f);
            }
            r();
            var20.d();
            this.l();
        }
    }

    public void a(float[] var1, int var2, int var3, LineStyle var4, ShaderSource var5) {  // 02b L1142: 顶点批
        if (this.F == null || this.F.capacity() < var3 * 4) {
            ByteBuffer var6 = ByteBuffer.allocateDirect(var3 * 4 + 10);
            var6.order(ByteOrder.nativeOrder());
            this.F = var6.asFloatBuffer();
        }
        this.F.clear();
        this.F.put(var1, var2, var3);
        this.F.flip();
        this.F.position(0);
        float var12 = var4.b();
        int var7 = var4.a();
        if (var12 == 0.0f) {
            var12 = 1.0f;
        }
        this.a(var5);
        this.a(0, var7, var12);
        if (this.T != null) {
            this.T.a(this.O, this.S);
        }
        GLUniform[] var8 = this.e;
        int var9 = var8[0].uniformName;
        GLES20.glEnableVertexAttribArray(var9);
        q();
        GLES20.glVertexAttribPointer(var9, 2, 5126, false, 0, this.F);
        float var10 = 1.0f;
        float var11 = 1.0f;
        this.a(var8, 0.0f, 0.0f, 1.0f, 1.0f, (TransformCallback)null);
        GLES20.glDrawArrays(0, 0, var3 / 2);
        q();
        GLES20.glDisableVertexAttribArray(var9);
        q();
    }

    public void a(Bitmap var1) {  // 02b L1179
        if (this.a != null) {
            this.a.b(var1);
        }
    }

    // $FF: synthetic method
    static String s() {  // 02b L1186
        return G;
    }

    static {
        A = false;
        B = 0;
    }
}
