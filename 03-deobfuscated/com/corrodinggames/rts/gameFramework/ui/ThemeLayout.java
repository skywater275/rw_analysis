/*
 * v19.133e ThemeColors 战役: 02b f/aj.java 直译 (主题布局渲染器)
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.Iterator;

public class ThemeLayout {  // 02b f/aj.java
    com.corrodinggames.rts.gameFramework.utility.CustomArrayList a;  // 02b: utility.m a
    Rect b;
    Paint c;
    Paint d;

    public void a(float var1, float var2) {  // 02b f/aj.java: a(float,float) 绘制
        GlobalState var3 = GlobalState.B();
        int var4 = 0;
        int var5 = TextFormatter.a(this.c);
        for (Iterator var6 = this.a.iterator(); var6.hasNext(); ++var4) {
            ak var7 = (ak) var6.next();
            int var8 = 0;
            af var9 = null;
            Iterator var10 = var7.a.iterator();
            while (var10.hasNext()) {
                af var11 = (af) var10.next();
                if (var9 != null) {
                    var8 += var9.a(this.c);
                }
                int var12 = (int) (var1 + (float) var8 + (float) this.b.d());
                var12 -= var7.b / 2;
                int var13 = (int) (var2 + (float) this.b.b + (float) (var5 / 2) + (float) (var4 * var5));
                if (!(var11 instanceof ThemeFontEntry)) {
                    if (var11 instanceof ThemeColorEntry) {
                        ThemeColorEntry var14 = (ThemeColorEntry) var11;
                        Texture var15 = var14.a;
                        var3.bO.a(var15, (float) var12, (float) var13 - (float) var15.q * var14.b, ThemeColors.accentColor, 0.0f, var14.b);
                    }
                    var9 = var11;
                } else {
                    ThemeFontEntry var16 = (ThemeFontEntry) var11;
                    Paint var17 = var16.b(this.c);
                    var3.bO.a(var16.d, (float) var12, (float) var13, var17);
                    var9 = var11;
                }
            }
        }
    }
}
