/*
 * Decompiled with CFR 0.152.
 * 02 原稿: gameFramework/m/y.java (v19.109 javap 全量重建 — CFR 丢接口方法, jar 67 方法)
 *
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.rendering.OpenGLRenderer;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.Renderer;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;

public interface TextureManagerInterface {
    TextureManagerInterface b(Texture var1);
    TextureManagerInterface a(Texture var1);
    boolean a();
    void a(android.content.Context var1);
    void b();
    Renderer d();
    void a(Renderer var1);
    void a(OpenGLRenderer var1);
    Texture loadImageFromResource(int var1);
    Texture a(int var1);  // 02b m/y.java L35: a(int)→e 铁证 (v19.117 补: 全库 119 处调用点原名, loadImageFromResource 为语义改名)
    Texture a(int var1, boolean var2);
    Texture a(java.io.InputStream var1, boolean var2);
    Texture a(int var1, int var2, boolean var3);
    Texture b(int var1, int var2, boolean var3);
    void e();
    void a(Texture var1, float var2, float var3, float var4, Paint var5);  // 02b m/y.a(e,float,float,float,Paint) (D 为误名 v19.133f8)
    void A(Texture var1, android.graphics.Rect var2, float var3, float var4, float var5, android.graphics.Paint var6);
    void loadImageFromResource(Texture var1, android.graphics.Rect var2, android.graphics.Rect var3, android.graphics.Paint var4);
    void loadImageFromResource(Texture var1, android.graphics.Rect var2, android.graphics.RectF var3, android.graphics.Paint var4);
    void clearScreen(Texture var1, float var2, float var3, android.graphics.Paint var4);
    void loadImageFromResource(Texture var1, float var2, float var3, android.graphics.Paint var4, float var5, float var6);
    void a(Texture var1, float var2, float var3, android.graphics.Paint var4, float var5, float var6);  // 02b m/y.java L57: a(e,float,float,Paint,float,float) 铁证 (v19.133d 补缺)
    void a(Texture var1, float var2, float var3, android.graphics.Paint var4);  // 02b m/y.java L55: a(e,float,float,Paint) 铁证 (接口漏声明)
    void a(Texture var1, android.graphics.Rect var2, android.graphics.RectF var3, android.graphics.Paint var4);  // 02b m/y.java L53: a(e,Rect,RectF,Paint) 铁证 (接口漏声明)
    void b(Texture var1, float var2, float var3, android.graphics.Paint var4);
    void b(Texture var1, android.graphics.Rect var2, android.graphics.Rect var3, android.graphics.Paint var4);
    void a(Texture var1, android.graphics.Rect var2, android.graphics.Rect var3, android.graphics.Paint var4);  // 02b m/y.java L51: a(e,Rect,Rect,Paint) 铁证 (接口漏声明)
    void clearScreen(android.graphics.Rect var1, android.graphics.Paint var2);
    void a(boolean var1);
    void f();
    void a(Texture var1, android.graphics.Rect var2, android.graphics.Paint var3);
    void a(Texture var1, android.graphics.Rect var2, android.graphics.Paint var3, int var4, int var5, int var6, int var7);
    void a(Texture var1, android.graphics.RectF var2, android.graphics.Paint var3, float var4, float var5, int var6, int var7);
    void clearScreen(int var1);
    void a(java.lang.String var1, float var2, float var3, android.graphics.Paint var4, android.graphics.Paint var5, float var6);
    void a(java.lang.String var1, float var2, float var3, android.graphics.Paint var4);
    void b(android.graphics.Rect var1, android.graphics.Paint var2);
    void a(android.graphics.RectF var1, android.graphics.Paint var2);
    void g();
    void h();
    void c(android.graphics.Rect var1, android.graphics.Paint var2);
    void a(android.graphics.Rect var1);
    void a(android.graphics.RectF var1);
    void a(float var1, float var2, float var3, android.graphics.Paint var4);
    void b(float var1, float var2, float var3, android.graphics.Paint var4);
    void a(float[] var1, int var2, int var3, android.graphics.Paint var4);
    void i();
    void j();
    void k();
    void l();
    void a(float var1, float var2, float var3);
    void D(float var1, float var2);
    void a(float var1, float var2, float var3, float var4);
    void b(float var1, float var2);
    void a(DrawCommand var1);
    void a(float var1, float var2, float var3, float var4, android.graphics.Paint var5);
    int m();
    int n();
    void b(int var1);  // 02b m/y.java: b(int) (TileDrawer 绘制清屏) 铁证
    void a(int var1, int var2);
    void o();
    void a(android.graphics.Paint var1);
    void a(Shader var1);
    void p();
    void q();
    int a(java.lang.String var1, android.graphics.Paint var2);
    int b(java.lang.String var1, android.graphics.Paint var2);
    Texture r();
    void a(Texture var1, java.io.File var2);
    void a(java.util.concurrent.locks.Lock var1);
    void b(java.util.concurrent.locks.Lock var1);
    float s();

    void a(int n2, android.graphics.PorterDuff.Mode mode);  // v19.115w 补缺: javap y.a(int,PorterDuff$Mode) 铁证 (CFR 丢该方法)

    void a(float var1, float var2);  // 02b m/y.java L113

}
