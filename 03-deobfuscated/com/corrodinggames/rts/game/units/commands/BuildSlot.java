/*
 * v19.115d: 02b units/d/d.java 闂堟瑦鈧焦鏂佺純顔筋梾閺屻儲鏌熷▔鏇㈠櫢瀵? * 闁挎氨鍋? 02b-decompiled/.../units/d/d.java L143-186 (FernFlower) + javap -c 鐎涙濡惍渚€鎼х拠? * 娓氭繆绂? GlobalState.bL(MapEngine).c/e/a, bU(PathFinder).a, UnitTypeHandle.p/q, be.a, MapLayer.isTileLayer
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.UnitRegistry;

public abstract class BuildSlot extends com.corrodinggames.rts.game.units.UnitType {  // 02b d/d.java L18: public abstract class d extends y


    com.corrodinggames.rts.gameFramework.rendering.Texture previewTexture;  // 02b d/d.java L20: m (寤洪€犻瑙堣创鍥? ExperimentalWaterUnit 寮曠敤)
    public Rect n = new Rect();  // 02b d/d.java: public Rect n (閸楁洑缍呴崠鍛纯閻? v19.132 鐞?
    public Rect o = new Rect();  // 02b d/d.java: public Rect o
    int r;  // 02b d/d.java: int r (package-private)
    int s;  // 02b d/d.java: int s (package-private)

    public BuildSlot(boolean bl) {  // 02b d/d.java L76-80
        super(bl);
        this.cg = -90.0f;
        this.bT = false;
    }

    public void R(int n2) {  // 02b d/d.java L58-60: 瀵ゆ椽鈧姵蝎缁涘楠?(閻欘剛鐝涢弬瑙勭《, 闂堢偠顩惄?
        this.r = n2;
    }

    @Override
    public float z() {  // 02b d/d.java L233-235
        return 0.0f;
    }

    @Override
    public float A() {  // 02b d/d.java L237-239
        return 0.0f;
    }

    @Override
    public boolean I() {  // 02b d/d.java L217-219
        return false;
    }

    @Override
    public MovementTypeEnum h() {  // 02b d/d.java L221-223: ao h() = ao.a
        return MovementTypeEnum.a;
    }

    public Paint f() {  // 02b d/d.java L245-294: 瀵ゆ椽鈧姵蝎缂佹ê鍩楅悽鑽ょ應 (閻欘剛鐝涢弬瑙勭《, 闂堢偠顩惄?
        GlobalState l2 = GlobalState.B();
        PorterDuffColorFilter colorFilter = null;
        int color;
        if (this.cm < 1.0f) {
            color = Color.a((int)(40.0f + this.cm * 200.0f), 140, 255, 140);
            colorFilter = aX;
        } else {
            color = Color.a(255, 255, 255, 255);
        }
        if (this.cp) {
            if (this.cs) {
                color = Color.a(200, 20, 255, 20);
                colorFilter = aY;
            }
            if (this.ct) {
                color = Color.a(200, 255, 20, 20);
                colorFilter = aZ;
            }
            if (this.cq) {
                color = Color.a(70, 70, 70, 245);
                colorFilter = ba;
                if (this.ct) {
                    color = Color.a(70, 255, 20, 20);
                    colorFilter = aZ;
                }
            }
            if (this.cr) {
                color = Color.a(150, 100, 100, 100);
            }
        }
        boolean bl = l2.bQ.renderAntiAlias;
        if (!this.dk()) {
            bl = false;
            if (l2.cX < 1.0f) {
                bl = true;
            }
        }
        if (this.co) {
            bl = UnitRegistry.ag;
        }
        return this.a(color, colorFilter, bl);
    }

    @Override
    public Texture d(int n2) {  // 02b d/d.java L62-64: 瀵ゆ椽鈧姵蝎缁惧湱鎮?(鐎涙劗琚弮蹇曟埛閹? 濞戝牓娅?d(int) 閹跺€熻杽)
        return null;
    }
    public boolean ds() {  // 02b d/d.java L29-31
        return false;
    }

    public boolean L() {  // 02b d/d.java L86-89 (a(ab.d) 閻樿埖鈧浇顔曠純顔界箒濮樻潙灏粻鈧崠?TODO)
        return false;
    }

    public boolean e() {  // 02b d/d.java L91-101 (PathFinder 濞夈劌鍞藉ǎ杈ㄦ寜閸栬櫣鐣濋崠?TODO)
        return false;
    }

    public void a(int n2) {  // 02b d/d.java L207
    }

    public boolean setTeamInternalById() {  // 03 鐠囶厺绠熼崥?(CustomUnitBase 閸氬本顑?return false)
        return false;
    }

    public boolean i() {  // 02b d/d.java L225-227
        return false;
    }

    public boolean Q() {  // 02b d/d.java L229-231
        return false;
    }

    public boolean bI() {  // 02b d/d.java L323-325
        return true;
    }

    public static Texture p;  // 02b d.d.p: 瀵よ櫣鐡氶崶鐐垼 (unit_icon_building)
    public static Texture[] q;  // 02b d.d.q: 闂冪喍绱為崣妯圭秼閸ョ偓鐖?

    public static void dt() {  // 02b d.d.dt(): 閸旂姾娴囧铏圭摎閸ョ偓鐖?
        GlobalState l2 = GlobalState.B();
        p = l2.bO.a(com.corrodinggames.rts.R$drawable.unit_icon_building);
        q = PlayerState.a(p);
    }

    public static UnitInstance g(UnitTypeHandle as2) {  // 02b d.d.g(as): 创建单位 (UnitInstance.a 铁证)
        if (as2 == null) {  // 02b d.d.g(as) 空类型检查
            throw new RuntimeException("type is null");
        }
        return UnitInstance.a(as2);  // 02b as.a() 閹恒儱褰?= am.a(as) 缂傛挸鐡ㄩ弻銉﹀
    }

    public static boolean a(UnitTypeHandle as2, float f2, float f3, PlayerState n2) {  // 02b d.d.a(as,f,f,n) L47-56: 娴ｅ秶鐤嗛崣顖涙杹缂冾喗顥呴弻?(AIStrategyNode.e 鐠嬪啰鏁?
        GlobalState l2 = GlobalState.B();
        UnitType unitType = (UnitType) UnitInstance.a(as2);
        l2.bL.b(f2, f3);
        unitType.eo = (float) l2.bL.scrollPixelX + unitType.cZ();
        unitType.ep = (float) l2.bL.scrollPixelY + unitType.cZ();
        unitType.b(n2);
        return unitType.c((PlayerState) null);
    }

    public static boolean a(UnitType y2, UnitTypeHandle as2, MovementTypeEnum ao2, int n2, int n3, int n4) {  // 02b d.d.a(y,as,ao,int,int,int) L111-137
        GlobalState l2 = GlobalState.B();
        MapEngine b2 = l2.bL;
        if (!b2.c(n2, n3)) {
            return false;
        }
        boolean bl = false;
        if (b2.tileHeight && l2.bs.N != null) {
            if (!b2.visibilityGrid && l2.bs.N[n2][n3] == 10) {
                return false;
            }
            bl = l2.bs.N[n2][n3] >= 5;
        }
        if (a(y2, as2, ao2, n2, n3, bl)) {
            if (as2.p()) {
                MapLayer g2 = b2.e(n2, n3);
                return g2 != null && g2.isTileLayer;
            }
            return !com.corrodinggames.rts.gameFramework.effects.GameHUD.a(l2.bs, n2, n3, n4);  // 02b d.a.a(n,int,int,int)
        }
        return false;
    }

    public static boolean a(UnitType y2, UnitTypeHandle as2, MovementTypeEnum ao2, int n2, int n3, boolean bl) {  // 02b d.d.a(y,as,ao,int,int,boolean) L139
        return a(y2, as2, ao2, n2, n3, bl, null) == null;
    }

    public static String a(UnitType y2, UnitTypeHandle as2, MovementTypeEnum ao2, int n2, int n3, boolean bl, PlayerState n4) {
        GlobalState l2 = GlobalState.B();
        if (!l2.bL.c(n2, n3)) {
            return "{0}";
        }
        be be2 = as2.q();
        if (be2 != null) {
            String string = be2.a(y2, n2, n3);
            if (string != null) {
                return string;
            }
        }
        if ((Object) as2 == (Object) WeaponTypeEnum.d || ao2 == MovementTypeEnum.e) {
            return l2.bU.a(l2.bU.A, n2, n3) ? "{3}" : null;
        }
        MapLayer mapLayer = l2.bL.e(n2, n3);
        if (mapLayer != null && mapLayer.isTileLayer) {
            return as2.p() ? null : "{0}";
        }
        if (ao2 == MovementTypeEnum.d) {
            return null;
        }
        if (ao2 == MovementTypeEnum.f) {
            return l2.bU.a(l2.bU.C, n2, n3) ? "{0}" : null;
        }
        if (ao2 == MovementTypeEnum.g) {
            return l2.bU.a(l2.bU.D, n2, n3) ? "{0}" : null;
        }
        if (ao2 == MovementTypeEnum.h) {
            return l2.bU.a(l2.bU.E, n2, n3) ? "{0}" : null;
        }
        if (l2.bU.a(l2.bU.airSolver, n2, n3, bl)) {
            boolean bl2 = false;
            if (n4 != null && !l2.bL.a(n2, n3, n4)) {
                bl2 = true;
            }
            if (!bl2) {
                return "{0}";
            }
        }
        return null;
    }
    // v19.115t 閹? 鐞涖儳宸? 02b d.d.b(int,int) L188-205 閻╃鐦?閳?閺嶈偐鍋ｉ埆鎺戝礋娴?(GlobalState.bL 閸︽澘娴橀崸鎰垼)
    public static UnitInstance b(int x, int y) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bL.a(x, y);
        float f2 = (float)(l2.bL.scrollPixelX + l2.bL.selectedTileX);
        float f3 = (float)(l2.bL.scrollPixelY + l2.bL.selectedTileY);
        java.util.Iterator iterator = l2.cc.b(f2, f3, 0.0f).iterator();
        UnitInstance am2;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            am2 = (UnitInstance)iterator.next();
        } while (!am2.bI() || am2.isDead || !am2.c(f2, f3, 0.0f));
        return am2;
    }

}
