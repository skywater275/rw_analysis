/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.graphics.Point;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.units.commands.BuildSlot;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.commands.ExperimentalUnit;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class ScreenUtils {
    static Point a = new Point();

    public static Point a(int n2, int n3, int n4) {
        GlobalState l2 = GlobalState.B();
        int n5 = n4;
        l2.bL.a((float)n2, (float)n3);
        int n6 = l2.bL.scrollPixelX;
        int n7 = l2.bL.scrollPixelY;
        Point point = null;
        float f2 = -1.0f;
        for (int i2 = n6 - n5; i2 <= n6 + n5; ++i2) {
            for (int i3 = n7 - n5; i3 <= n7 + n5; ++i3) {
                MapLayer g2;
                if (!l2.bL.c(i2, i3) || (g2 = l2.bL.e(i2, i3)) == null || !g2.isTileLayer) continue;
                UnitInstance am2 = BuildSlot.b(i2, i3);
                if (am2 != null && !am2.canMove()) {
                    am2 = null;
                }
                if (am2 != null) continue;
                float f3 = GameUtils.a((float)n6, (float)n7, (float)i2, (float)i3);
                if (f2 != -1.0f && !(f2 > f3)) continue;
                l2.bL.a(i2, i3);
                a.a(l2.bL.scrollPixelX, l2.bL.scrollPixelY);
                point = a;
                f2 = f3;
            }
        }
        return point;
    }
}
