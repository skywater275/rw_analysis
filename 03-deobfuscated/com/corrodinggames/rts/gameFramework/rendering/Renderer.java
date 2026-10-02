/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 */
package com.corrodinggames.rts.gameFramework.rendering;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.rendering.Shader;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.DrawCommand;
import java.util.concurrent.locks.Lock;

public interface Renderer {
    public void a(boolean var1);

    public void a();  // 02b m/l.java L45

    public void a(Rect var1);

    public void a(RectF var1);

    public void a(Texture var1, float var2, float var3, Paint var4);

    public void a(Texture var1, Rect var2, Rect var3, Paint var4);

    public void a(Texture var1, Rect var2, RectF var3, Paint var4);

    public void a(float var1, float var2, float var3, Paint var4);

    public void a(int var1, PorterDuff.Mode var2);

    public void a(int var1);

    public void a(float var1, float var2, float var3, float var4, Paint var5);

    public void a(float[] var1, int var2, int var3, Paint var4);

    public void a(Rect var1, Paint var2);

    public void a(RectF var1, Paint var2);

    public void a(String var1, float var2, float var3, Paint var4);

    public void a(float var1, float var2, float var3);

    public void b();

    public void a(float var1, float var2);

    public void a(float var1, float var2, float var3, float var4);

    public void a(Texture var1);

    public void b(float var1, float var2);

    public void a(DrawCommand var1);

    public void a(Bitmap var1);

    public void a(Lock var1);

    public void b(Lock var1);

    public boolean a(Shader var1);

    boolean c();  // 02b m/l.java L17

}
