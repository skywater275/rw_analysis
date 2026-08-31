/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL13
 *  org.lwjgl.opengl.GL14
 *  org.lwjgl.opengl.GL20
 *  org.newdawn.slick.Color
 *  org.newdawn.slick.Font
 *  org.newdawn.slick.Graphics
 *  org.newdawn.slick.Image
 *  org.newdawn.slick.ImageBuffer
 *  org.newdawn.slick.SlickException
 *  org.newdawn.slick.UnicodeFont
 *  org.newdawn.slick.font.GlyphPage
 *  org.newdawn.slick.font.effects.ColorEffect
 *  org.newdawn.slick.imageout.ImageOut
 *  org.newdawn.slick.opengl.ImageData
 *  org.newdawn.slick.opengl.ImageIOImageData
 *  org.newdawn.slick.opengl.PNGImageData
 *  org.newdawn.slick.opengl.Texture
 *  org.newdawn.slick.opengl.TextureImpl
 *  org.newdawn.slick.opengl.renderer.LineStripRenderer
 *  org.newdawn.slick.opengl.renderer.Renderer
 *  org.newdawn.slick.opengl.renderer.SGL
 *  org.newdawn.slick.util.FastTrig
 *  org.newdawn.slick.util.ResourceLoader
 */
package com.corrodinggames.rts.java;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.af;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.v;
import com.corrodinggames.rts.gameFramework.m.w;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.utility.j;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.java.Main;
import com.corrodinggames.rts.java.a;
import com.corrodinggames.rts.java.f;
import com.corrodinggames.rts.java.g;
import com.corrodinggames.rts.java.s;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.GlyphPage;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.opengl.PNGImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.LineStripRenderer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.FastTrig;
import org.newdawn.slick.util.ResourceLoader;

public final class e
implements y {
    public boolean a = true;
    public boolean b;
    public static final org.newdawn.slick.Color c = new org.newdawn.slick.Color(0, 0, 0, 255);
    public static final org.newdawn.slick.Color d = new org.newdawn.slick.Color(0, 0, 0, 255);
    public static final org.newdawn.slick.Color e = new org.newdawn.slick.Color(0, 0, 0, 255);
    public Graphics f;
    public com.corrodinggames.rts.gameFramework.m.e g;
    public int h;
    public int i;
    public com.corrodinggames.rts.gameFramework.m.f j;
    public static Graphics k = null;
    static e l = null;
    public static ae m = null;
    private static SGL W = Renderer.get();
    final Rect n = new Rect();
    final Rect o = new Rect();
    final RectF p = new RectF();
    final PointF q = new PointF();
    public static a r;
    public static a s;
    public static a t;
    ArrayList u = new ArrayList();
    int v = -1;
    Paint w = null;
    s x = null;
    boolean y;
    final Paint z = new Paint();
    public static final org.newdawn.slick.Color A;
    static float B;
    f C = new f(this);
    byte[] D = new byte[4];
    static ArrayList E;
    int F = 0;
    RectF G = new RectF();
    static Paint H;
    static Paint I;
    static RectF J;
    static RectF K;
    public float L = 1.0f;
    static RectF M;
    FloatBuffer N = BufferUtils.createFloatBuffer((int)3);
    float[] O = new float[0];
    int P = -1;
    float Q;
    float R;
    float S;
    private static LineStripRenderer X;
    g T = new g();
    m U = new m();
    m V = new m();

    public static void c() {
        W = Renderer.get();
    }

    public org.newdawn.slick.Color t() {
        return c;
    }

    public e c(com.corrodinggames.rts.gameFramework.m.e e2) {
        e e3 = this.d(e2);
        e3.j = this.j;
        return e3;
    }

    public e d(com.corrodinggames.rts.gameFramework.m.e e2) {
        e e3 = new e();
        s s2 = this.e(e2);
        try {
            e3.f = s2.C().getGraphics();
            e3.g = e2;
            if (e2 != null) {
                e3.h = e2.m();
                e3.i = e2.l();
            }
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
        return e3;
    }

    @Override
    public int m() {
        if (this.g != null) {
            return this.h;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return (int)l2.cl;
    }

    @Override
    public int n() {
        if (this.g != null) {
            return this.i;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return (int)l2.cm;
    }

    @Override
    public void a(int n2, int n3) {
        this.h = n2;
        this.i = n3;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public void a(Context context) {
    }

    @Override
    public void b() {
        r = new a((s)this.a(R$drawable.error_outmem));
        r.a("Out of memory");
        s = new a((s)this.a(R$drawable.error_general));
        s.a("General Error");
        t = new a((s)this.a(R$drawable.error_toolargethumb));
        s.a("Too Large Thumbnail Error");
        if (!com.corrodinggames.rts.gameFramework.l.aC) {
            this.j = new com.corrodinggames.rts.gameFramework.m.f(1024, 1024);
        }
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.l d() {
        return null;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.l l2) {
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.a a2) {
    }

    public static boolean a(String string) {
        for (int j = 0; j < string.length(); ++j) {
            int n2 = string.codePointAt(j);
            if (n2 <= 255) continue;
            return true;
        }
        return false;
    }

    Font a(f f2, String string, boolean bl) {
        int n2;
        f f3 = this.a(f2, bl);
        if (f3.a(string)) {
            return f3.d;
        }
        UnicodeFont unicodeFont = (UnicodeFont)f3.d;
        int n3 = 0;
        for (Object e2 : unicodeFont.getGlyphPages()) {
            GlyphPage glyphPage = (GlyphPage)e2;
            n3 += glyphPage.getGlyphs().size();
        }
        for (n2 = 0; n2 < string.length(); ++n2) {
            char c2 = string.charAt(n2);
            boolean bl2 = false;
            if (bl2) continue;
        }
        n2 = unicodeFont.getGlyphPages().size();
        unicodeFont.addGlyphs(string);
        try {
            unicodeFont.loadGlyphs();
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
        int n4 = 0;
        for (Object e3 : unicodeFont.getGlyphPages()) {
            GlyphPage glyphPage = (GlyphPage)e3;
            n4 += glyphPage.getGlyphs().size();
        }
        int n5 = unicodeFont.getGlyphPages().size();
        if (n3 != n4) {
            com.corrodinggames.rts.gameFramework.l.e("new glypth, " + n4 + " " + f3.toString() + " for text:" + string);
        }
        f3.b(string);
        return f3.d;
    }

    f a(f f2, boolean bl) {
        java.awt.Font font;
        InputStream inputStream;
        for (f f3 : this.u) {
            if (f3.a != f2.a || f3.b != f2.b || f3.c != f2.c) continue;
            return f3;
        }
        f2 = f2.a();
        com.corrodinggames.rts.gameFramework.l.e("New font:" + f2.a + " bold:" + f2.b);
        if (bl) {
            // empty if block
        }
        Object object = "font/Roboto-Regular.ttf";
        if (f2.b) {
            object = "font/Roboto-Bold.ttf";
        }
        if (f2.c) {
            object = "font/DroidSansFallback.ttf";
        }
        boolean bl2 = false;
        try {
            inputStream = ResourceLoader.getResourceAsStream((String)object);
            font = java.awt.Font.createFont(0, inputStream);
            font = font.deriveFont((float)f2.a);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        catch (FontFormatException fontFormatException) {
            throw new RuntimeException(fontFormatException);
        }
        inputStream = new UnicodeFont(font);
        inputStream.addAsciiGlyphs();
        java.awt.Color color = new java.awt.Color(255, 255, 255);
        inputStream.getEffects().add(new ColorEffect(color));
        try {
            inputStream.loadGlyphs();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.a, (Throwable)outOfMemoryError);
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
        com.corrodinggames.rts.gameFramework.l.e("loadGlyphs");
        f2.d = inputStream;
        this.u.add(f2);
        return f2;
    }

    public void a(Paint paint, String string) {
        this.a(paint, true, string, null, null);
    }

    public void b(Paint paint) {
        this.a(paint, false, null, null, null);
    }

    public void a(Paint paint, s s2, com.corrodinggames.rts.gameFramework.m.e e2) {
        this.a(paint, false, null, s2, e2);
    }

    public void u() {
        this.y();
        Graphics.setCurrent((Graphics)this.f);
        this.b(true);
        this.b = true;
        B = -1.0f;
        org.newdawn.slick.Color.setRebindRequired();
        this.w = this.z;
        l = this;
    }

    public void a(Paint paint, boolean bl, String string, s s2, com.corrodinggames.rts.gameFramework.m.e e2) {
        int n2;
        boolean bl2;
        boolean bl3;
        boolean bl4 = false;
        if (k != this.f) {
            this.u();
            bl4 = true;
            k = this.f;
        }
        if ((paint == null || paint instanceof ag) && this.w == paint && this.x == s2 && !bl) {
            ae ae2 = null;
            if (this.a) {
                if (paint != null && paint instanceof ag) {
                    ae2 = ((ag)paint).q();
                }
                if (e2 != null && ae2 == null) {
                    ae2 = e2.B();
                }
            }
            if (m == ae2) {
                boolean bl5;
                if (m != null && (bl5 = m.a(paint, e2))) {
                    this.f.flushBuffer();
                    this.b(m);
                }
                return;
            }
        }
        this.w = paint;
        this.x = s2;
        boolean bl6 = bl3 = s2 == null && !bl;
        if (this.v != Graphics.MODE_NORMAL) {
            this.v = Graphics.MODE_NORMAL;
            this.f.setDrawMode(this.v);
        }
        if (bl4 && this.g != null) {
            W.glEnable(3042);
            W.glColorMask(true, true, true, true);
            GL14.glBlendFuncSeparate((int)770, (int)771, (int)770, (int)1);
        }
        if (paint == null) {
            bl2 = false;
            this.a(org.newdawn.slick.Color.white);
            if (bl3) {
                this.a(1.0f);
            }
            if (bl) {
                this.f.resetFont();
            }
        } else {
            bl2 = paint.c();
        }
        if (this.a) {
            ae ae3 = null;
            if (paint != null && paint instanceof ag) {
                ae3 = ((ag)paint).q();
            }
            if (e2 != null && ae3 == null) {
                ae3 = e2.B();
            }
            if (m != ae3) {
                this.f.flushBuffer();
                if (ae3 == null) {
                    this.v();
                } else {
                    ae3.f();
                    n2 = this.c(ae3) ? 1 : 0;
                    if (n2 == 0) {
                        if (m != null) {
                            this.v();
                        }
                    } else {
                        ae3.a(paint, e2);
                        this.b(ae3);
                    }
                }
                m = ae3;
            } else if (m != null && (n2 = m.a(paint, e2)) != 0) {
                this.f.flushBuffer();
                this.b(m);
            }
        }
        if (s2 != null) {
            boolean bl7;
            boolean bl8 = bl7 = s2.E == 1;
            if (bl7 != bl2) {
                this.f.flushBuffer();
                n2 = bl2 ? 1 : 2;
                s2.C().setFilter(n2);
                s2.E = n2;
            }
        }
        if (paint != null) {
            ColorFilter colorFilter;
            boolean bl9 = true;
            ColorFilter colorFilter2 = paint.h();
            if (colorFilter2 != null) {
                if (colorFilter2 instanceof LightingColorFilter) {
                    colorFilter = (LightingColorFilter)colorFilter2;
                    if (colorFilter.a != 0 && colorFilter.a != -1) {
                        int n3 = colorFilter.a;
                        com.corrodinggames.rts.java.e.d.r = (float)Color.b(n3) * 0.003921569f;
                        com.corrodinggames.rts.java.e.d.g = (float)Color.c(n3) * 0.003921569f;
                        com.corrodinggames.rts.java.e.d.b = (float)Color.d(n3) * 0.003921569f;
                        com.corrodinggames.rts.java.e.d.a = (float)Color.a(n3) * 0.003921569f;
                        com.corrodinggames.rts.java.e.a(paint.e(), e);
                        com.corrodinggames.rts.java.e.d.r *= com.corrodinggames.rts.java.e.e.r;
                        com.corrodinggames.rts.java.e.d.g *= com.corrodinggames.rts.java.e.e.g;
                        com.corrodinggames.rts.java.e.d.b *= com.corrodinggames.rts.java.e.e.b;
                        com.corrodinggames.rts.java.e.d.a *= com.corrodinggames.rts.java.e.e.a;
                        this.a(d);
                        this.v = Graphics.MODE_ADD;
                        this.f.setDrawMode(this.v);
                        W.glEnable(3042);
                        W.glColorMask(true, true, true, true);
                        W.glBlendFunc(770, 1);
                        bl9 = false;
                    }
                } else if (colorFilter2 instanceof v) {
                    colorFilter = (v)colorFilter2;
                    if (((v)colorFilter).a == com.corrodinggames.rts.gameFramework.m.w.b) {
                        this.f(paint.e());
                        this.v = 99;
                        W.glEnable(3042);
                        W.glColorMask(true, true, true, true);
                        W.glBlendFunc(1, 1);
                        bl9 = false;
                    } else if (((v)colorFilter).a == com.corrodinggames.rts.gameFramework.m.w.c) {
                        this.f(paint.e());
                        this.v = 99;
                        W.glEnable(3042);
                        W.glColorMask(true, true, true, true);
                        W.glBlendFunc(774, 771);
                        bl9 = false;
                    }
                }
            }
            if (bl9) {
                this.f(paint.e());
            }
            if (bl3) {
                if (paint.g() != 0.0f) {
                    this.a(paint.g());
                } else {
                    this.a(1.0f);
                }
            }
            if (bl) {
                colorFilter = this.a(paint, string, true);
                this.f.setFont((Font)colorFilter);
            }
        }
    }

    public void v() {
        GL20.glUseProgram((int)0);
    }

    public void b(ae ae2) {
        for (af af2 : ae2.p) {
            int n2;
            if (!af2.c) continue;
            af2.c = false;
            if (af2.b == -1) {
                af2.b = GL20.glGetUniformLocation((int)ae2.n, (CharSequence)af2.a);
                if (af2.b == -1 && !af2.d) {
                    af2.d = true;
                    ae2.b("Unknown parameter: " + af2.a);
                    int n3 = GL20.glGetProgrami((int)ae2.n, (int)35718);
                    int n4 = GL20.glGetProgrami((int)ae2.n, (int)35719);
                    for (n2 = 0; n2 < n3; ++n2) {
                        String string = GL20.glGetActiveUniform((int)ae2.n, (int)n2, (int)n4);
                        ae2.b("Possible parameter: " + string);
                    }
                    return;
                }
            }
            if (af2.f != null) {
                s s2 = this.e(af2.f);
                Texture texture = s2.C().getTexture();
                if (af2.g) {
                    GL20.glUniform2f((int)af2.b, (float)texture.getTextureWidth(), (float)texture.getTextureHeight());
                    continue;
                }
                n2 = texture.getTextureID();
                ae2.b("Updating texture to:" + n2);
                GL20.glUniform1i((int)af2.b, (int)1);
                GL13.glActiveTexture((int)33985);
                GL11.glBindTexture((int)3553, (int)n2);
                GL13.glActiveTexture((int)33984);
                continue;
            }
            if (af2.e.length == 1) {
                GL20.glUniform1f((int)af2.b, (float)af2.e[0]);
                continue;
            }
            if (af2.e.length == 2) {
                GL20.glUniform2f((int)af2.b, (float)af2.e[0], (float)af2.e[1]);
                continue;
            }
            if (af2.e.length == 4) {
                GL20.glUniform4f((int)af2.b, (float)af2.e[0], (float)af2.e[1], (float)af2.e[2], (float)af2.e[3]);
                continue;
            }
            ae2.b("Unhandled parameter size: " + af2.a + " - " + af2.e.length);
        }
    }

    public boolean c(ae ae2) {
        if (ae2.o != 0) {
            return false;
        }
        if (ae2.n != 0 && !ae2.m) {
            GL20.glUseProgram((int)ae2.n);
            return true;
        }
        ae2.m = false;
        ae2.b("Compiling shader");
        ae2.g = this.a(ae2, 35633, ae2.e);
        ae2.h = this.a(ae2, 35632, ae2.f);
        if (ae2.o != 0) {
            return false;
        }
        ae2.n = GL20.glCreateProgram();
        if (ae2.n == 0) {
            ae2.c("could not create program; check ShaderProgram.isSupported()");
            return false;
        }
        GL20.glAttachShader((int)ae2.n, (int)ae2.g);
        GL20.glAttachShader((int)ae2.n, (int)ae2.h);
        GL20.glLinkProgram((int)ae2.n);
        int n2 = GL20.glGetProgrami((int)ae2.n, (int)35714);
        int n3 = GL20.glGetProgrami((int)ae2.n, (int)35716);
        String string = GL20.glGetProgramInfoLog((int)ae2.n, (int)n3);
        if (string != null && string.length() != 0) {
            ae2.d = string + "\n" + ae2.d;
        }
        if (ae2.d != null) {
            ae2.d = ae2.d.trim();
        }
        if (n2 == 0) {
            ae2.c(ae2.d.length() != 0 ? ae2.d : "Could not link program");
            return false;
        }
        GL20.glUseProgram((int)ae2.n);
        return true;
    }

    protected int a(ae ae2, int n2, String string) {
        int n3 = GL20.glCreateShader((int)n2);
        if (n3 == 0) {
            ae2.c("could not create shader object; check ShaderProgram.isSupported()");
        }
        GL20.glShaderSource((int)n3, (CharSequence)string);
        GL20.glCompileShader((int)n3);
        int n4 = GL20.glGetShaderi((int)n3, (int)35713);
        int n5 = GL20.glGetShaderi((int)n3, (int)35716);
        String string2 = this.e(n2);
        String string3 = GL20.glGetShaderInfoLog((int)n3, (int)n5);
        if (string3 != null && string3.length() != 0) {
            ae2.d = ae2.d + string2 + " compile log:\n" + string3 + "\n";
        }
        if (n4 == 0) {
            ae2.c(ae2.d.length() != 0 ? ae2.d : "Could not compile " + this.e(n2));
        }
        return n3;
    }

    private String e(int n2) {
        if (n2 == 35632) {
            return "FRAGMENT_SHADER";
        }
        if (n2 == 35633) {
            return "VERTEX_SHADER";
        }
        return "shader";
    }

    private void f(int n2) {
        com.corrodinggames.rts.java.e.a(n2, A);
        this.a(A);
    }

    private void a(org.newdawn.slick.Color color) {
        org.newdawn.slick.Color color2 = c;
        if (this.b) {
            this.b = false;
        } else if (color2.r == color.r && color2.g == color.g && color2.b == color.b && color2.a == color.a) {
            return;
        }
        color2.a = color.a;
        color2.r = color.r;
        color2.g = color.g;
        color2.b = color.b;
        this.f.setColor(color2);
    }

    public void a(float f2) {
        if (B != f2) {
            B = f2;
            this.f.setLineWidth(f2);
        }
    }

    public Font a(Paint paint, String string, boolean bl) {
        f f2 = this.C;
        f2.a = (int)paint.k();
        if (this.x()) {
            f2.a = (int)((float)f2.a * this.L);
        }
        Typeface typeface = paint.i();
        f2.b = false;
        if (typeface != null) {
            f2.b = typeface.a();
        }
        f2.c = false;
        boolean bl2 = com.corrodinggames.rts.java.e.a(string);
        if (bl2) {
            f2.c = true;
        }
        Font font = this.a(f2, string, bl);
        return font;
    }

    public static void a(ImageData imageData, ByteBuffer byteBuffer, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        int n9 = (n2 + n3 * imageData.getTexWidth()) * n8;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            byteBuffer.put(n9, (byte)n6);
            byteBuffer.put(n9 + 1, (byte)n5);
            byteBuffer.put(n9 + 2, (byte)n4);
            byteBuffer.put(n9 + 3, (byte)n7);
        } else {
            byteBuffer.put(n9, (byte)n4);
            byteBuffer.put(n9 + 1, (byte)n5);
            byteBuffer.put(n9 + 2, (byte)n6);
            byteBuffer.put(n9 + 3, (byte)n7);
        }
    }

    public static int a(ImageData imageData, ByteBuffer byteBuffer, int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7;
        int n8 = (n2 + n3 * imageData.getTexWidth()) * n4;
        if (n4 == 4) {
            // empty if block
        }
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            n7 = byteBuffer.get(n8) & 0xFF;
            n6 = byteBuffer.get(n8 + 1) & 0xFF;
            n5 = byteBuffer.get(n8 + 2) & 0xFF;
        } else {
            n5 = byteBuffer.get(n8) & 0xFF;
            n6 = byteBuffer.get(n8 + 1) & 0xFF;
            n7 = byteBuffer.get(n8 + 2) & 0xFF;
        }
        int n9 = n4 < 4 ? 255 : byteBuffer.get(n8 + 3) & 0xFF;
        return com.corrodinggames.rts.java.e.a(n9, n5, n6, n7);
    }

    public static final int a(int n2, int n3, int n4, int n5) {
        return n2 << 24 | n3 << 16 | n4 << 8 | n5;
    }

    public static org.newdawn.slick.Color a(int n2, org.newdawn.slick.Color color) {
        color.r = (float)(n2 >> 16 & 0xFF) * 0.003921569f;
        color.g = (float)(n2 >> 8 & 0xFF) * 0.003921569f;
        color.b = (float)(n2 & 0xFF) * 0.003921569f;
        color.a = (float)(n2 >>> 24) * 0.003921569f;
        return color;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e a(int n2) {
        return this.a(n2, true);
    }

    @Override
    public void e() {
        com.corrodinggames.rts.java.e.w();
    }

    public static void w() {
        if (E.size() == 0) {
            return;
        }
        for (s s2 : E) {
            s2.I();
        }
        E.clear();
    }

    public static void a(s s2) {
        E.add(s2);
        if (E.size() > 15) {
            com.corrodinggames.rts.java.e.w();
        }
    }

    public static s b(int n2, boolean bl) {
        String string = com.corrodinggames.rts.gameFramework.f.f(n2);
        try {
            FileInputStream fileInputStream = new FileInputStream(string);
            ImageData imageData = com.corrodinggames.rts.java.e.a(fileInputStream);
            ((InputStream)fileInputStream).close();
            return com.corrodinggames.rts.java.e.a(imageData, string);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.a, (Throwable)outOfMemoryError);
            if (r == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return r;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e a(int n2, boolean bl) {
        return com.corrodinggames.rts.java.e.b(n2, bl);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ImageData a(InputStream inputStream) {
        Object object;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);){
            try {
                bufferedInputStream.mark(Integer.MAX_VALUE);
                PNGImageData pNGImageData = new PNGImageData();
                pNGImageData.loadImage((InputStream)bufferedInputStream);
                object = pNGImageData;
            }
            catch (IOException iOException) {
                bufferedInputStream.reset();
                com.corrodinggames.rts.gameFramework.l.e("PNG load failed: " + iOException.getMessage());
                com.corrodinggames.rts.gameFramework.l.e("Attempting load with ImageIO..");
                ImageIOImageData imageIOImageData = new ImageIOImageData();
                ByteBuffer byteBuffer = imageIOImageData.loadImage((InputStream)bufferedInputStream, false, null);
                object = new com.corrodinggames.rts.java.a.a((ImageData)imageIOImageData, byteBuffer);
            }
        }
        return object;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e a(InputStream inputStream, boolean bl) {
        try {
            String string = null;
            if (inputStream instanceof j) {
                string = ((j)inputStream).d();
            } else {
                com.corrodinggames.rts.gameFramework.l.b("loadImage InputStream is not AssetInputStream");
                com.corrodinggames.rts.gameFramework.l.T();
            }
            ++this.F;
            ImageData imageData = com.corrodinggames.rts.java.e.a(inputStream);
            return com.corrodinggames.rts.java.e.a(imageData, string);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.a, (Throwable)outOfMemoryError);
            if (r == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return r;
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static s a(ImageData imageData, String string) {
        s s2 = new s();
        s2.a(imageData, string, false);
        com.corrodinggames.rts.java.e.a(s2);
        return s2;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e a(int n2, int n3, boolean bl) {
        s s2 = new s();
        try {
            s2.a(new Image(n2, n3), null);
            com.corrodinggames.rts.java.e.a(s2);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.b, (Throwable)outOfMemoryError);
            if (r == null) {
                throw new RuntimeException("outOfMemoryErrorImage==null", outOfMemoryError);
            }
            return r;
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
        return s2;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e b(int n2, int n3, boolean bl) {
        return com.corrodinggames.rts.java.e.a((ImageData)new ImageBuffer(n2, n3), null);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, float f2, float f3, float f4, Paint paint) {
        this.k();
        this.a(f4 + 90.0f, f2, f3);
        this.c(e2, f2 - (float)e2.r, f3 - (float)e2.s, paint);
        this.l();
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, Rect rect, float f2, float f3, float f4, Paint paint) {
        this.k();
        this.a(f4, f2, f3);
        this.G.a(f2 - (float)e2.r, f3 - (float)e2.s, e2.p, e2.q);
        this.a(e2, rect, this.G, paint);
        this.l();
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, Rect rect, Rect rect2, Paint paint) {
        this.G.a(rect2);
        this.a(e2, rect, this.G, paint);
    }

    @Override
    public void b(com.corrodinggames.rts.gameFramework.m.e e2, Rect rect, Rect rect2, Paint paint) {
        this.G.a(rect2);
        this.a(e2, rect, this.G, paint);
    }

    @Override
    public void a(Rect rect, Paint paint) {
        this.G.a(rect);
        this.a(this.G, paint);
    }

    @Override
    public void a(boolean bl) {
    }

    @Override
    public void f() {
    }

    private final s e(com.corrodinggames.rts.gameFramework.m.e e2) {
        s s2 = (s)e2.c();
        return s2;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, Rect rect, RectF rectF, Paint paint) {
        this.a(e2, rectF.a, rectF.b, rectF.c, rectF.d, rect.a, rect.b, rect.c, rect.d, paint);
    }

    private void c(com.corrodinggames.rts.gameFramework.m.e e2, float f2, float f3, Paint paint) {
        float f4 = e2.m();
        float f5 = e2.l();
        float f6 = f2 + f4;
        float f7 = f3 + f5;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = f4;
        float f11 = f5;
        this.a(e2, f2, f3, f6, f7, f8, f9, f10, f11, paint);
    }

    private void a(com.corrodinggames.rts.gameFramework.m.e e2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Paint paint) {
        com.corrodinggames.rts.gameFramework.m.g g2;
        float f10;
        float f11;
        float f12;
        g g3 = this.T;
        float f13 = f4 - f2;
        float f14 = f5 - f3;
        if (g3.c != -90.0f) {
            float f15 = f13 / 2.0f;
            float f16 = f14 / 2.0f;
            f12 = f2 + f15 - g3.g;
            f11 = f3 + f16 - g3.h;
            if (f12 != 0.0f || f11 != 0.0f) {
                f10 = 0.01f;
                if (f12 > 0.01f || f11 > 0.01f || f12 < -0.01f || f11 < -0.01f) {
                    PointF pointF = this.q;
                    pointF.a = f12;
                    pointF.b = f11;
                    com.corrodinggames.rts.java.e.a(g3.c + 180.0f, pointF);
                    float f17 = pointF.a + g3.g - f15;
                    float f18 = pointF.b + g3.h - f16;
                    f4 += f17 - f2;
                    f5 += f18 - f3;
                    f2 = f17;
                    f3 = f18;
                }
            }
        }
        s s2 = this.e(e2);
        boolean bl = false;
        if (this.j != null && s2.m() < 450 && s2.l() < 100 && (g2 = this.j.a(s2)) != null) {
            bl = true;
            s2 = this.e(g2.a);
            if (f6 < 0.0f) {
                f2 += -f6;
                f6 = 0.0f;
            }
            if (f7 < 0.0f) {
                f3 += -f7;
                f7 = 0.0f;
            }
            if (f8 > g2.d) {
                f4 += -(g2.d - f8);
                f8 = g2.d;
            }
            if (f9 > g2.e) {
                f5 += -(g2.e - f9);
                f9 = g2.e;
            }
            f6 += (float)g2.b;
            f8 += (float)g2.b;
            f7 += (float)g2.c;
            f9 += (float)g2.c;
        }
        f12 = f4 - f2;
        f11 = f5 - f3;
        f2 *= g3.d;
        f3 *= g3.e;
        f2 += g3.a;
        f3 += g3.b;
        f10 = (f12 *= g3.d) / 2.0f;
        float f19 = (f11 *= g3.e) / 2.0f;
        this.a(paint, s2, e2);
        Image image = s2.C();
        if (image == null) {
            s2.G();
            throw new RuntimeException("getSlickImage==null");
        }
        this.a(image, f2 + f10, f3 + f19, f12, f11, f6, f7, f8, f9, this.t(), g3.c);
    }

    private void a(Image image, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, org.newdawn.slick.Color color, float f10) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        Graphics.setCurrent((Graphics)this.f);
        image.startUse();
        if (color != null) {
            color.bind();
        }
        float f19 = f4;
        float f20 = f5;
        float f21 = f19 * 0.5f;
        float f22 = f20 * 0.5f;
        float f23 = f8 - f6;
        float f24 = f9 - f7;
        float f25 = image.getTextureWidth() / (float)image.getWidth();
        float f26 = image.getTextureHeight() / (float)image.getHeight();
        float f27 = f6 * f25;
        float f28 = f7 * f26;
        float f29 = f23 * f25;
        float f30 = f24 * f26;
        float f31 = f10 + 90.0f;
        if (f31 == 0.0f) {
            f18 = -f21 + f2;
            f17 = -f22 + f3;
            f16 = f21 + f2;
            f15 = -f22 + f3;
            f14 = -f21 + f2;
            f13 = f22 + f3;
            f12 = f21 + f2;
            f11 = f22 + f3;
        } else {
            float f32 = com.corrodinggames.rts.gameFramework.f.k(f31);
            float f33 = com.corrodinggames.rts.gameFramework.f.j(f31);
            float f34 = f21 * f32;
            float f35 = f22 * f32;
            float f36 = f21 * f33;
            float f37 = f22 * f33;
            f18 = -f34 + f37 + f2;
            f17 = -f36 - f35 + f3;
            f16 = f34 + f37 + f2;
            f15 = f36 - f35 + f3;
            f14 = -f34 - f37 + f2;
            f13 = -f36 + f35 + f3;
            f12 = f34 - f37 + f2;
            f11 = f36 + f35 + f3;
        }
        W.glTexCoord2f(f27, f28);
        W.glVertex3f(f18, f17, 0.0f);
        W.glTexCoord2f(f27, f28 + f30);
        W.glVertex3f(f14, f13, 0.0f);
        W.glTexCoord2f(f27 + f29, f28 + f30);
        W.glVertex3f(f12, f11, 0.0f);
        W.glTexCoord2f(f27 + f29, f28);
        W.glVertex3f(f16, f15, 0.0f);
        image.endUse();
        this.f.getColor().bind();
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, float f2, float f3, Paint paint) {
        this.b(e2, f2 - e2.t, f3 - e2.u, paint);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, float f2, float f3, Paint paint, float f4, float f5) {
        this.k();
        this.b(f2, f3);
        this.a(f5, f5);
        this.a(f4, f2, f3);
        this.c(e2, 0.0f, 0.0f, paint);
        this.l();
    }

    @Override
    public void b(com.corrodinggames.rts.gameFramework.m.e e2, float f2, float f3, Paint paint) {
        this.c(e2, f2, f3, paint);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, Rect rect, Paint paint) {
        this.a(e2, rect, paint, 0, 0, 0, 0);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, Rect rect, Paint paint, int n2, int n3, int n4, int n5) {
        aa.a((y)this, e2, rect, paint, n2, n3, n4, n5);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, RectF rectF, Paint paint, float f2, float f3, int n2, int n3) {
        aa.a((y)this, e2, rectF, paint, f2, f3, n2, n3);
    }

    @Override
    public void b(int n2) {
        if (l != this) {
            this.u();
        }
        this.b(false);
        this.w = null;
        this.f.setBackground(com.corrodinggames.rts.java.e.a(n2, e));
        this.f.clear();
    }

    @Override
    public void o() {
        if (l != this) {
            this.u();
        }
        this.w = null;
        this.f.clearAlphaMap();
    }

    @Override
    public void a(int n2, PorterDuff.Mode mode) {
        this.w = null;
        if (mode != PorterDuff.Mode.CLEAR) {
            this.b(n2);
            return;
        }
        this.b(n2);
        this.f.clearAlphaMap();
    }

    @Override
    public void a(String string, float f2, float f3, Paint paint, Paint paint2, float f4) {
        float f5 = this.b(string, paint);
        J.a(f2, f3, f2 + f5, f3 + (float)this.a(string, paint));
        com.corrodinggames.rts.gameFramework.f.a(J, f4);
        K.a(J);
        if (paint.j() == Paint$Align.b) {
            J.a(-(f5 / 2.0f), 0.0f);
        }
        this.a(J, paint2);
        this.a(string, com.corrodinggames.rts.java.e.K.a + f4, com.corrodinggames.rts.java.e.K.d - f4, paint);
    }

    boolean x() {
        if (!com.corrodinggames.rts.gameFramework.l.B().bQ.resizeFontWithUIScale) {
            return false;
        }
        if (this.L == 1.0f) {
            return false;
        }
        if (this.L < 1.0f) {
            return true;
        }
        return true;
    }

    @Override
    public void a(String string, float f2, float f3, Paint paint) {
        int n2;
        if (this.x()) {
            this.k();
            float f4 = 1.0f / this.L;
            this.a(f4, f4);
            f2 *= this.L;
            f3 *= this.L;
        }
        f2 *= this.T.d;
        f3 *= this.T.e;
        f2 += this.T.a;
        f3 += this.T.b;
        this.a(paint, string);
        int n3 = 0;
        if (paint.j() == Paint$Align.b) {
            n2 = this.f.getFont().getWidth(string);
            n3 -= n2 / 2;
        } else if (paint.j() == Paint$Align.c) {
            n2 = this.f.getFont().getWidth(string);
            n3 -= n2;
        }
        n2 = 0;
        int n4 = this.f.getFont().getLineHeight();
        int n5 = (int)(f2 + (float)n3);
        int n6 = (int)(f3 + (float)(n2 -= n4));
        this.f.drawString(string, (float)n5, (float)n6);
        if (this.x()) {
            this.l();
        }
    }

    @Override
    public void b(Rect rect, Paint paint) {
        this.G.a(rect);
        this.a(this.G, paint);
    }

    @Override
    public void a(RectF rectF, Paint paint) {
        this.b(paint);
        if (paint.d() == Paint$Style.a || paint.d() == Paint$Style.c) {
            TextureImpl.bindNone();
            W.glBegin(7);
            float f2 = rectF.a;
            float f3 = rectF.b;
            float f4 = rectF.c;
            float f5 = rectF.d;
            f2 *= this.T.d;
            f3 *= this.T.e;
            f4 *= this.T.d;
            f5 *= this.T.e;
            W.glVertex2f(f2 += this.T.a, f3 += this.T.b);
            W.glVertex2f(f4 += this.T.a, f3);
            W.glVertex2f(f4, f5 += this.T.b);
            W.glVertex2f(f2, f5);
            W.glEnd();
        } else {
            float f6 = rectF.a;
            float f7 = rectF.b;
            float f8 = rectF.b();
            float f9 = rectF.c();
            f6 *= this.T.d;
            f7 *= this.T.e;
            this.f.drawRect(f6 += this.T.a, f7 += this.T.b, f8 *= this.T.d, f9 *= this.T.e);
        }
    }

    @Override
    public void g() {
        this.e();
        M = null;
        if (this.j != null) {
            this.j.c();
        }
    }

    @Override
    public void h() {
        this.y();
        if (this.j != null) {
            this.j.d();
        }
        if (this.a && m != null) {
            this.v();
            m = null;
        }
        this.w = null;
        M = null;
        this.b = true;
        B = -1.0f;
        this.y = false;
    }

    @Override
    public void c(Rect rect, Paint paint) {
        this.o.a(rect.a, rect.b, rect.a + rect.c, rect.b + rect.d);
        this.b(this.o, paint);
    }

    @Override
    public void a(Rect rect) {
        if (rect != null) {
            this.T.f = new RectF(rect);
            this.T.f.a *= this.T.d;
            this.T.f.c *= this.T.d;
            this.T.f.b *= this.T.e;
            this.T.f.d *= this.T.e;
            this.T.f.a(this.T.a, this.T.b);
        } else {
            this.T.f = null;
        }
        this.b(false);
    }

    @Override
    public void a(RectF rectF) {
        if (rectF != null) {
            this.T.f = new RectF(rectF);
            this.T.f.a *= this.T.d;
            this.T.f.c *= this.T.d;
            this.T.f.b *= this.T.e;
            this.T.f.d *= this.T.e;
            this.T.f.a(this.T.a, this.T.b);
        } else {
            this.T.f = null;
        }
        this.b(false);
    }

    public void b(boolean bl) {
        RectF rectF = this.T.f;
        if (M == rectF && !bl) {
            return;
        }
        this.y();
        if (rectF != null) {
            W.glEnable(3089);
            W.glScissor((int)rectF.a, (int)((float)this.n() * this.L - rectF.d), (int)rectF.b(), (int)rectF.c());
        } else {
            W.glDisable(3089);
        }
        M = rectF;
    }

    @Override
    public void b(float f2, float f3, float f4, Paint paint) {
        f2 *= this.T.d;
        f3 *= this.T.e;
        f2 += this.T.a;
        f3 += this.T.b;
        f4 *= this.T.d;
        this.b(paint);
        if (paint.d() == Paint$Style.b) {
            int n2 = 40;
            if (f4 > 100.0f) {
                n2 = 60;
            }
            this.a(f2, f3, f4, n2);
        } else {
            this.f.fillOval(f2 - f4, f3 - f4, f4 * 2.0f, f4 * 2.0f);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, Paint paint) {
        float f5 = this.T.d;
        if (f4 * f5 < 25.0f && paint.d() == Paint$Style.b) {
            aa.a(this, f2, f3, f4, paint, f5);
            return;
        }
        this.b(f2, f3, f4, paint);
    }

    public FloatBuffer c(int n2) {
        if (this.N.capacity() < n2) {
            this.N = BufferUtils.createFloatBuffer((int)n2);
        }
        return this.N;
    }

    public FloatBuffer a(float[] fArray, int n2) {
        FloatBuffer floatBuffer = this.c(n2);
        floatBuffer.clear();
        floatBuffer.put(fArray, 0, n2);
        floatBuffer.flip();
        return floatBuffer;
    }

    public float[] d(int n2) {
        if (this.O.length < n2) {
            this.O = new float[n2];
        }
        return this.O;
    }

    @Override
    public void a(float[] fArray, int n2, int n3, Paint paint) {
        if (n3 == 0) {
            return;
        }
        boolean bl = true;
        if (Main.b) {
            bl = false;
        }
        float f2 = paint.g();
        float f3 = 1.0f;
        float f4 = 0.0f;
        if (f2 > 1.0f) {
            f3 += (f2 - 1.0f) * 0.5f;
            f4 += (f2 - 1.0f) * 0.5f;
        }
        float f5 = this.T.d;
        float f6 = this.T.e;
        float f7 = this.T.a;
        float f8 = this.T.b;
        if (bl) {
            float[] fArray2 = this.d(n3 * 4);
            int n4 = n3 * 4;
            int n5 = 0;
            for (int i2 = 0; i2 < n4; i2 += 8) {
                float f9 = fArray[n5];
                float f10 = fArray[n5 + 1];
                float f11 = f9 - f4;
                float f12 = f9 + f3;
                float f13 = f10 - f4;
                float f14 = f10 + f3;
                fArray2[i2 + 0] = f11;
                fArray2[i2 + 1] = f13;
                fArray2[i2 + 2] = f12;
                fArray2[i2 + 3] = f13;
                fArray2[i2 + 4] = f12;
                fArray2[i2 + 5] = f14;
                fArray2[i2 + 6] = f11;
                fArray2[i2 + 7] = f14;
                n5 += 2;
            }
            this.b(fArray2, 0, n3 * 4, paint);
        } else {
            this.b(paint);
            TextureImpl.bindNone();
            W.glBegin(7);
            int n6 = n2 + n3;
            for (int i3 = n2; i3 < n6; i3 += 2) {
                float f15 = fArray[i3];
                float f16 = fArray[i3 + 1];
                f15 *= f5;
                f16 *= f6;
                float f17 = (f15 += f7) - f4;
                float f18 = f15 + f3;
                float f19 = (f16 += f8) - f4;
                float f20 = f16 + f3;
                W.glVertex2f(f17, f19);
                W.glVertex2f(f18, f19);
                W.glVertex2f(f18, f20);
                W.glVertex2f(f17, f20);
            }
            W.glEnd();
        }
    }

    public void b(float[] fArray, int n2, int n3, Paint paint) {
        boolean bl = Main.a;
        if (bl) {
            GL11.glDisableClientState((int)32886);
        }
        this.b(paint);
        TextureImpl.bindNone();
        GL11.glEnableClientState((int)32884);
        FloatBuffer floatBuffer = this.a(fArray, n3);
        GL11.glVertexPointer((int)2, (int)0, (FloatBuffer)floatBuffer);
        GL11.glDrawArrays((int)7, (int)n2, (int)(n3 / 2));
        if (bl) {
            GL11.glEnableClientState((int)32886);
        }
    }

    public void a(float f2, float f3, float f4, int n2) {
        Graphics.setCurrent((Graphics)this.f);
        TextureImpl.bindNone();
        if (this.P != n2) {
            this.P = n2;
            this.Q = 6.283185f / (float)n2;
            this.R = (float)FastTrig.cos((double)this.Q);
            this.S = (float)FastTrig.sin((double)this.Q);
        }
        float f5 = this.R;
        float f6 = this.S;
        float f7 = f4;
        float f8 = 0.0f;
        boolean bl = true;
        X.start();
        ++n2;
        float f9 = f7 + f2;
        float f10 = f8 + f3;
        for (int i2 = 0; i2 < n2; ++i2) {
            X.vertex(f7 + f2, f8 + f3);
            float f11 = f7;
            f7 = f5 * f7 - f6 * f8;
            f8 = f6 * f11 + f5 * f8;
        }
        X.end();
    }

    @Override
    public void i() {
        this.z();
    }

    @Override
    public void j() {
        this.A();
    }

    @Override
    public void k() {
        this.z();
    }

    @Override
    public void l() {
        this.A();
    }

    @Override
    public void a(float f2, float f3, float f4) {
        this.T.c += f2;
        this.T.g = f3;
        this.T.h = f4;
    }

    public static void a(float f2, PointF pointF) {
        float f3 = com.corrodinggames.rts.gameFramework.f.j(f2);
        float f4 = com.corrodinggames.rts.gameFramework.f.k(f2);
        float f5 = pointF.a;
        float f6 = pointF.b;
        pointF.a = f4 * f6 - f3 * f5;
        pointF.b = f3 * f6 + f4 * f5;
    }

    @Override
    public void a(float f2, float f3) {
        this.T.d *= f2;
        this.T.e *= f3;
    }

    @Override
    public void a(float f2, float f3, float f4, float f5) {
        this.b(f4, f5);
        this.a(f2, f3);
        this.b(-f4, -f5);
    }

    @Override
    public void b(float f2, float f3) {
        this.T.a += f2 * this.T.d;
        this.T.b += f3 * this.T.e;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.m m2) {
        m2.a(this);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, Paint paint) {
        this.b(paint);
        f2 *= this.T.d;
        f3 *= this.T.e;
        f4 *= this.T.d;
        f5 *= this.T.e;
        this.f.drawLine(f2 += this.T.a, f3 += this.T.b, f4 += this.T.a, f5 += this.T.b);
    }

    @Override
    public void a(Paint paint) {
        this.a(paint, "", false);
    }

    @Override
    public void a(ae ae2) {
        if (this.a) {
            this.c(ae2);
            this.v();
            m = null;
        }
    }

    public void y() {
        this.f.flushBuffer();
    }

    @Override
    public void p() {
        this.f.flushBuffer();
        this.w = null;
        this.f.flush();
    }

    @Override
    public void q() {
        if (this.f != null) {
            this.f.destroy();
        }
        this.f = null;
    }

    @Override
    public int a(String string, Paint paint) {
        this.a(paint, string);
        int n2 = this.f.getFont().getLineHeight();
        if (this.x()) {
            n2 = (int)((float)n2 / this.L);
        }
        return n2;
    }

    @Override
    public int b(String string, Paint paint) {
        this.a(paint, string);
        int n2 = this.f.getFont().getWidth(string);
        if (this.x()) {
            n2 = (int)((float)n2 / this.L);
        }
        return n2;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e r() {
        return r;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.m.e e2, File file) {
        s s2 = this.e(e2);
        boolean bl = true;
        String string = "png";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ImageOut.write((Image)s2.C(), (String)"png", (OutputStream)bufferedOutputStream);
            bufferedOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        catch (SlickException slickException) {
            throw new RuntimeException(slickException);
        }
    }

    @Override
    public void a(Lock lock) {
    }

    @Override
    public void b(Lock lock) {
    }

    public void z() {
        this.U.add(this.T);
        g g2 = this.V.a == 0 ? new g() : (g)this.V.c();
        this.T.a(g2);
        this.T = g2;
    }

    public void A() {
        if (this.U.size() == 0) {
            throw new RuntimeException("tranform stack is empty");
        }
        this.V.add(this.T);
        this.T = (g)this.U.c();
        this.b(false);
    }

    @Override
    public float s() {
        return this.L;
    }

    @Override
    public /* synthetic */ y a(com.corrodinggames.rts.gameFramework.m.e e2) {
        return this.c(e2);
    }

    @Override
    public /* synthetic */ y b(com.corrodinggames.rts.gameFramework.m.e e2) {
        return this.d(e2);
    }

    static {
        A = new org.newdawn.slick.Color(0, 0, 0, 255);
        B = -1.0f;
        E = new ArrayList();
        I = new ag();
        H = new Paint();
        H.a(255, 255, 0, 0);
        H.a(Paint$Style.b);
        J = new RectF();
        K = new RectF();
        X = Renderer.getLineStripRenderer();
    }
}
