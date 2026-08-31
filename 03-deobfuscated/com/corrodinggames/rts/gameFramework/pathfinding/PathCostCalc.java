/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.pathfinding;
import com.corrodinggames.rts.game.map.MapEngine;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.TMXMapLoader;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.pathfinding.PathNode;
import com.corrodinggames.rts.gameFramework.pathfinding.TerrainCost;
import com.corrodinggames.rts.gameFramework.pathfinding.PathFinder;
import java.util.LinkedList;

public class PathCostCalc
extends PathCostCalculator {
    PathCostLocator a = new h(this);  // 02b k/f.java L16: c a = new h(this)
    TerrainCost b;  // 02b k/f.java: 字段 b 类型 g (TerrainCost); L960 修复
    static Paint c = new Paint();
    static Point d = new Point();

    public PathCostCalc(PathFinder l2, boolean bl) {
        super(l2, bl);
    }


    public PathCostLocator reset(UnitInstance am2) {  // 02b L26: public c a(am)
        if (this.reset() != null) {
            return this.a;
        }
        return null;
    }


    public LinkedList reset() {
        return super.a();  // 02b L31: super.a()
    }


    public boolean isEnabled() {
        return true;
    }


    public boolean reset(PathCostCalculator k2) {
        if (this == k2) {
            return true;
        }
        if (!(k2 instanceof PathCostCalc)) {
            return false;
        }
        PathCostCalc f2 = (PathCostCalc) k2;
        if (this.l != f2.l || this.m != f2.m) {
            return false;
        }
        return this.o == f2.o;
    }


    protected boolean isEnabled2() {
        return this.x != null;
    }

    public final byte reset(int n, int n2) {
        if (this.b == null) {
            return -1;
        }
        return this.b.a(n, n2);
    }

    public static final void reset(byte by, Point point) {
        int n = 0;
        int n2 = 0;
        if ((by = (byte)(by - 1)) == 0) {
            ++n;
        }
        if (by == 1) {
            ++n;
            ++n2;
        }
        if (by == 2) {
            ++n2;
        }
        if (by == 3) {
            ++n2;
            --n;
        }
        if (by == 4) {
            --n;
        }
        if (by == 5) {
            --n;
            --n2;
        }
        if (by == 6) {
            --n2;
        }
        if (by == 7) {
            --n2;
            ++n;
        }
        point.a = n;
        point.b = n2;
    }

    public void reset4() {
        int n2;
        int n3;
        int n4;
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        MapEngine b2 = l2.bL;
        Rect rect = new Rect();
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = l2.cA;
        float f5 = l2.cB;
        TMXMapLoader e2 = l2.bL.groundLayer;
        int n5 = (int)(f2 * b2.float1 - 1.0f);
        if (n5 < 0) {
            n5 = 0;
        }
        if ((n4 = (int)(f3 * b2.float2 - 1.0f)) < 0) {
            n4 = 0;
        }
        if ((n3 = (int)((f2 + f4) * b2.float1 + 1.0f)) > b2.mapHeight - 1) {
            n3 = b2.mapHeight - 1;
        }
        if ((n2 = (int)((f3 + f5) * b2.float2 + 1.0f)) > b2.tileWidth - 1) {
            n2 = b2.tileWidth - 1;
        }
        boolean bl = false;
        for (int i2 = n5; i2 < n3 + 1; ++i2) {
            for (int i3 = n4; i3 < n2 + 1; ++i3) {
                com.corrodinggames.rts.game.map.MapLayer g2 = e2.a(i2, i3);
                if (g2 == null) continue;
                int n6 = i2 * b2.tilePixelWidth;
                int n7 = i3 * b2.tilePixelHeight;
                rect.a(n6, n7, n6 + b2.tilePixelWidth, n7 + b2.tilePixelHeight);
                rect.a((int)(-f2), (int)(-f3));
                boolean bl2 = rect.b((int)(l2.bS.x / l2.cX), (int)(l2.bS.y / l2.cX));
                int n8 = 50;
                int n9 = 0;
                int n10 = 0;
                n8 = n8 == -1 ? 255 : (n8 *= 2);
                n9 = n9 == -1 ? 255 : (n9 *= 2);
                if (n10 == -1) {
                    n10 = 255;
                } else {
                    if (n10 != 0) {
                        n10 += 30;
                    }
                    n10 *= 2;
                }
                c.a(128, n8, n9, n10);  // 02b L168: c.a (静态 Paint 字段)
                byte by = this.reset(i2, i3);  // 02b L169: this.a(var14, var15) (reset4 为幻觉名)
                reset(by, d);  // 02b L170: a(var23, d) 自身静态
                float f6 = (float)(n6 + b2.selectedTileX) - f2;
                float f7 = (float)(n7 + b2.selectedTileY) - f3;
                l2.bO.a(f6, f7, f6 + (float)(d.a * (b2.tilePixelWidth - 3)) + 1.0f, f7 + (float)(d.b * (b2.tilePixelHeight - 3)) + 1.0f, c);  // 02b L173: d.a/d.b (静态 Point 字段)
                if (!bl2) continue;
            }
        }
    }
}
