/*
 * v19.133f: 02b game/j.java 简化直译 (桌面截图渲染层)
 */
package com.corrodinggames.rts.java;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.rendering.TextureManagerInterface;

public class ScreenshotLayer {  // 02b game/j.java
    public Texture a;  // 02b: m/e a
    TextureManagerInterface b;
    Paint c;
    Rect d;
    boolean e;

    public ScreenshotLayer() {  // 02b j() 构造
        this.c = new Paint();
        this.d = new Rect(-101, 0, 101, 0);
    }

    public void a() {  // 02b j.a() 简化 TODO
    }

    public void a(TextureManagerInterface var1) {  // 02b j.a(y) 简化 TODO
    }

    public void a(TextureManagerInterface var1, int var2, int var3, int var4) {  // 02b j.a(y,int,int,int) 简化 TODO
    }

    public void b() {  // 02b j.b() 简化 TODO
    }
}
