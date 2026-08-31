/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;
import com.corrodinggames.rts.game.map.MapEngine;

import android.graphics.Point;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.units.UnitTransform;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalc;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class h
extends PathCostLocator {  // 02b k/h.java L9: extends c
    PathCostCalc a;  // 02b L11: f a
    UnitTransform af = new UnitTransform();  // 02b L12: af b = new af()
    static Point point = new Point();

    public h(PathCostCalc f2) {
        this.a = f2;
    }


    public UnitTransform a(UnitInstance am2) {
        UnitTransform af2 = this.getaf(am2.eo, am2.ep);  // 02b L21: this.a(var1.eo, var1.ep)
        if (af2 == null) {
            return null;
        }
        UnitTransform af3 = this.getaf(af2.a, af2.b);  // 02b L22: this.a(var2.a, var2.b)
        if (af3 == null) {
            return af2;
        }
        UnitTransform af4 = this.getaf(af3.a, af3.b);  // 02b L25: this.a(var3.a, var3.b)
        if (af4 == null) {
            return af3;
        }
        return af4;
    }


    public void d(UnitInstance am2) {
        UnitTransform af2;  // 02b: af 类型
        float f2;
        if (this.a != null) {
            this.a.reset4();  // 02b L37: this.a.d() (03 名 reset4)
        }
        GlobalState l2 = GlobalState.B();
        float f3 = l2.cw;
        float f4 = l2.cx;
        UnitTransform af3 = this.e(am2);  // 02b L43: this.e(var1) (searchId 为幻觉名)
        if (af3 != null) {
            float f5 = af3.a;  // 02b L46: var6 = var5.a
            f2 = af3.b;  // 02b L47: var7 = var5.b
            PathCostCalc.c.b(-16776961);  // 02b L48: f.c.b (f=PathCostCalc 静态 Paint)
            l2.bO.a(am2.eo - f3, am2.ep - f4, f5 - f3, f2 - f4, PathCostCalc.c);  // 02b L49
            UnitTransform af4 = this.b(am2);  // 02b L50: this.b(var1) (af 为幻觉名)
            if (af4 != null) {
                PathCostCalc.c.b(-7829368);  // 02b L52: f.c.b
                l2.bO.a(f5 - f3, f2 - f4, af4.a - f3, af4.b - f4, PathCostCalc.c);  // 02b L53
            }
        }
        if ((af2 = this.a(am2)) != null) {  // 02b L57: this.a(var1) (getaf 单参为幻觉名)
            f2 = af2.a;  // 02b L59: var7 = var9.a
            float f6 = af2.b;  // 02b L60: var10 = var9.b
            PathCostCalc.c.b(-256);  // 02b L61: f.c.b
            l2.bO.a(am2.eo - f3, am2.ep - f4, f2 - f3, f6 - f4, PathCostCalc.c);  // 02b L62
        }
    }

    public UnitTransform e(UnitInstance am2) {
        return this.getaf(am2.eo, am2.ep);
    }


    public UnitTransform b(UnitInstance am2) {
        UnitTransform af2 = this.getaf(am2.eo, am2.ep);  // 02b L72
        if (af2 == null) {
            return null;
        }
        return this.getaf(af2.a, af2.b);  // 02b L73: this.a(var2.a, var2.b)
    }


    public void c(UnitInstance am2) {
    }

    public UnitTransform getaf(float f2, float f3) {
        if (this.a.b == null) {
            return null;
        }
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        int n2 = (int)(f2 * b2.float1);
        int n3 = (int)(f3 * b2.float2);
        if (!b2.c(n2, n3)) {
            return null;
        }
        byte by = this.a.reset(n2, n3);  // 02b L89: this.a.a(var5, var6) (03 名 reset)
        if (by == 0) {
            return null;
        }
        PathCostCalc.reset(by, point);  // 02b L93: f.a(var7, c) (03 静态 Point 字段名 point)
        int n4 = n2 - point.a;  // 02b L94: var8 = var5 - c.a (03 静态 Point 字段名 point)
        int n5 = n3 - point.b;  // 02b L95: var9 = var6 - c.b
        this.af.a = (float)(n4 * b2.tilePixelWidth + b2.selectedTileX);  // 02b L96: this.b.a = (float)(...)
        this.af.b = (float)(n5 * b2.tilePixelHeight + b2.selectedTileY);  // 02b L97: this.b.b
        return this.af;
    }
}
