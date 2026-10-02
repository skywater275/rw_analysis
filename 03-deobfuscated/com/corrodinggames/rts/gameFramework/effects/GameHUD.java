/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.effects;
import com.corrodinggames.rts.game.map.TileEntry;
import com.corrodinggames.rts.gameFramework.GameSaver;
import com.corrodinggames.rts.gameFramework.KeyCodeMapper;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.UniquePaint;
import com.corrodinggames.rts.gameFramework.utility.DequeList;
import java.util.Iterator;

public class GameHUD {
    float a;
    float b;
    public boolean c;  // 02b d.a.c
    public static GameHUD a(PlayerState n2, float f2, float f3) {  // 02b d.a.a(n,float,float): 附近建筑圈提示
        Iterator<GameHUD> iterator = w.iterator();
        while (iterator.hasNext()) {
            GameHUD a2 = iterator.next();
            if (a2.j == n2 && a2.n) {
                float f4 = GameUtils.a(a2.g, a2.h, f2, f3);
                UnitInstance am2 = UnitInstance.a(a2.d);
                float f5 = am2.cj + 1.0f;
                if (f5 < 20.0f) {
                    f5 = 20.0f;
                }
                if (f4 < f5 * f5) {
                    return a2;
                }
            }
        }
        return null;
    }

    public static boolean a(PlayerState n2, int n3, int n4, int n5) {  // 02b d.a.a(n,int,int,int): 蓝图格检查
        GlobalState l2 = GlobalState.B();
        l2.bL.a(n3, n4);
        float f2 = (float)(l2.bL.scrollPixelX + l2.bL.selectedTileX);
        float f3 = (float)(l2.bL.scrollPixelY + l2.bL.selectedTileY);
        y.a(f2, f3, f2 + 1.0f, f3 + 1.0f);
        return a(n2, y, n5);
    }

    public static boolean a(PlayerState n2, RectF rectF, int n3) {  // 02b d.a.a(n,RectF,int): isTileRectOverBlueprint
        GlobalState l2 = GlobalState.B();
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        RectF rectF2 = A;
        Iterator iterator = w.iterator();
        while (iterator.hasNext()) {
            GameHUD a2 = (GameHUD) iterator.next();
            if (a2.j == n2 && a2.n && (n3 == -1 || n3 == a2.r)) {
                UnitInstance am2 = UnitInstance.a(a2.d);
                if (am2 == null) {
                    GlobalState.e("isTileRectOverBlueprint: Failed to get shared unit for: " + a2.d);
                    continue;
                }
                am2.eo = a2.g;
                am2.ep = a2.h;
                rectF2 = am2.a(b2, rectF2);
                if (GameUtils.a(rectF2, rectF)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(PlayerState n2, UnitType y2, int n3) {  // 02b d.a.a(n,y,int) L93-98: 单位矩形 vs 蓝图 (InGameUI blockout 用)
        GlobalState l2 = GlobalState.B();
        RectF rectF = y2.a(l2.bL, y);
        return a(n2, rectF, n3);
    }

    public static boolean a(UnitType y2, UnitType y3) {  // 02b d.a.a(y,y) L100-106: 两单位矩形相交 (InGameUI blockout 用)
        GlobalState l2 = GlobalState.B();
        RectF rectF = y2.a(l2.bL, y);
        RectF rectF2 = y3.a(l2.bL, z);
        return GameUtils.a(rectF, rectF2);
    }

    public boolean showHUD;
    public UnitTypeHandle d;
    public PlayerState e;
    public int f = 1;
    public float g;
    public float h;
    public boolean i;
    public PlayerState j;
    public boolean k;
    public int l;
    public int m;
    public boolean n;
    public UnitType o;
    boolean p = false;
    public boolean q = false;
    public int r;
    public float s;
    public float t = 0.04f;
    public boolean u;
    public UnitInstance v;
    public static DequeList w = new DequeList();
    static Point x = new Point();
    static RectF y = new RectF();
    static RectF z = new RectF();
    static RectF A = new RectF();
    Paint B = new Paint();
    static Paint C;
    static Paint D;
    static RectF E;

    public GameHUD() {
        w.add(this);
        w.a();
    }

    public boolean b() {
        if (this.n) {
            if (this.o == null || this.o.isDead) {
                return false;
            }
            if (!UnitRegistry.a(this.d, this.g, this.h, 0.0f, 0.0f, this.e)) {
                return false;
            }
        } else {
            if (this.v == null) {
                return false;
            }
            if (this.v.canMove()) {
                return false;
            }
        }
        return true;
    }

    public void c(float f2) {
        this.a += 1.0f;
        this.b += f2;
        boolean bl = false;
        this.s = GameUtils.a(this.s, this.t * f2);
        if (this.n) {
            if (this.a > 6.0f) {
                this.a = 0.0f;
                boolean bl2 = this.o.a(this.d, this.g, this.h);
                if (!this.p && bl2) {
                    this.p = true;
                }
                if (!bl2) {
                    if (this.p) {
                        bl = true;
                    } else if (this.b > 180.0f) {
                        bl = true;
                    }
                }
                if (!this.b()) {
                    bl = true;
                }
            }
        } else if (this.a > 2.0f && !this.b()) {
            bl = true;
        }
        if (bl) {
            this.showHUD = true;
            w.b(this);
        }
    }

    public void reset(float f2) {
        Rect rect;
        UnitInstance am2;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (l2.bs != this.j) {
            return;
        }
        if (!l2.cO.b(this.g, this.h)) {
            return;
        }
        if (this.q && !this.p) {
            return;
        }
        float f3 = 0.0f;
        float f4 = this.g;
        float f5 = this.h;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 1.0f;
        float f9 = 500.0f;
        boolean bl = false;
        boolean bl2 = false;
        if (this.n) {
            bl2 = true;
        } else {
            bl = true;
        }
        boolean bl3 = true;
        if (this.i) {
            bl3 = false;
        }
        if (bl2) {
            f3 = this.s;
            f3 = f3 <= 0.0f ? 0.0f : (this.s < 1.0f ? 1.0f - com.corrodinggames.rts.gameFramework.GameUtils.k(f3 * 90.0f) : 1.0f);
        }
        if (bl2 && this.s < 1.0f && (am2 = UnitInstance.c(this.d)) != null && am2.isFactoryBuilding() && (rect = am2.getHitboxRect()) != null) {
            E.a(rect);
            E.b *= (float)l2.bL.tilePixelHeight;
            E.d *= (float)l2.bL.tilePixelHeight;
            E.a *= (float)l2.bL.tilePixelWidth;
            E.c *= (float)l2.bL.tilePixelWidth;
            float f10 = (float)(l2.bL.selectedTileX - 3) + f3 * 5.0f;
            E.a(-(am2.getMapOriginX() - (float)l2.bL.selectedTileX), -(am2.getMapOriginY() - (float)l2.bL.selectedTileY));
            GameUtils.a(E, f10);
            float f11 = this.g - l2.cw;
            float f12 = this.h - l2.cx - f7;
            E.a(f11, f12);
            float f13 = 3.0f + f3 * 7.0f;
            Paint paint = C;
            if (this.s <= 0.0f) {
                paint = D;
            }
            l2.bO.a(E.a - f13, E.b, E.c + f13, E.b, paint);
            l2.bO.a(E.a - f13, E.d, E.c + f13, E.d, paint);
            l2.bO.a(E.a, E.b - f13, E.a, E.d + f13, paint);
            l2.bO.a(E.c, E.b - f13, E.c, E.d + f13, paint);
        }
        float f14 = 0.0f;
        if (bl2) {
            f14 -= 10.0f * f3;
        }
        UnitRegistry.a(this.d, f4, f5 + f14, f6, f7, this.e, f8, f9, bl, bl2, this.f, bl3, null);
    }

    static {
        E = new RectF();
        C = new UniquePaint();
        C.a(90, 0, 0, 255);
        C.a(Paint$Style.b);
        C.a(2.0f);
        D = new UniquePaint();
        D.a(40, 0, 0, 255);
        D.a(Paint$Style.b);
        D.a(2.0f);
    }

    public static void a() {  // javap: d.a 静态无参 a() (02b 体缺失)
    }


    public static void a(float f2) {  // 02b d.a.a(float) L62-69: 帧更新全部 HUD 元素
        Iterator iterator = w.iterator();
        while (iterator.hasNext()) {
            GameHUD a2 = (GameHUD) iterator.next();
            a2.c(f2);
        }
        w.a();
    }


    public static void b(float f2) {  // 02b d.a.b(float) L71-78
        Object[] objectArray = w.b();
        for (int i2 = 0; i2 < w.size(); ++i2) {
            GameHUD a2 = (GameHUD) objectArray[i2];
            a2.d(f2);
        }
    }

    public void d(float f2) {  // 02b d.a.d(float) L214-289: 附近建筑箭头绘制 (简化空实现, 渲染细节后续补)
    }

}
