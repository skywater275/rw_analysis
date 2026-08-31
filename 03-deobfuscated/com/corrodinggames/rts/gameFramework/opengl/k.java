/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.opengl.BlendMode;
import com.corrodinggames.rts.gameFramework.opengl.GLObject;
import com.corrodinggames.rts.gameFramework.opengl.ShaderSource;
import com.corrodinggames.rts.gameFramework.opengl.TransformCallback;
import com.corrodinggames.rts.gameFramework.opengl.TextureIdProvider;
import com.corrodinggames.rts.gameFramework.opengl.LineStyle;

public interface k {
    public TextureIdProvider a();

    public void b();

    public void c();

    public void a(float var1, float var2, float var3, LineStyle var4, ShaderSource var5);

    public void a(float var1, float var2, float var3, float var4, LineStyle var5, ShaderSource var6);

    public void a(GLObject var1, int var2, int var3, int var4, int var5, BlendMode var6, TransformCallback var7);

    public boolean a(GLObject var1);

    public void b(GLObject var1);

    public void c(GLObject var1);

    public void d();

    public void d(GLObject var1);

    public void a(GLObject var1, int var2, int var3, int var4);

    public void a(GLObject var1, Bitmap var2, int var3);

    public void a(GLObject var1, int var2, int var3, Bitmap var4, int var5, int var6);

    public void a(int var1, int var2, int var3, int var4);

    public void a(String var1, float var2, float var3, Paint var4);

    public void a(float[] var1, int var2, int var3, LineStyle var4, ShaderSource var5);

    public void e();

    public void f();

    public void a(Bitmap var1);
}
