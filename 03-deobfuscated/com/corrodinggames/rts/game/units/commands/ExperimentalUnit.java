/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.map.MapLayer;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.effects.GameHUD;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.io.IOException;

public strictfp abstract class ExperimentalUnit
extends com.corrodinggames.rts.game.units.UnitType {
    Texture previewTexture;
    public Rect sourceRect = new Rect();
    public Rect destRect = new Rect();
    public static Texture defaultIcon = null;
    public static Texture[] teamIconTextures = new Texture[10];
    int buildStep = 1;
    int animFrame = 0;

    public boolean hasOverlaySprite() {
        return false;
    }


    /* 覆写链 super.a 抛 IOException */
    public void readFromStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.buildStep);
        super.a(as2);  // 02b d/d L35: super.a(var1)
    }


    public void readFromStream(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        if (k2.b() >= 15) {
            int n2 = k2.f();
            this.setBuildStep(n2);
        }
        super.a(k2);  // 02b d/d L44: super.a(var1)
    }

    public static boolean readFromStream(UnitTypeHandle as2, float f2, float f3, com.corrodinggames.rts.game.PlayerState n2) {
        GlobalState l2 = GlobalState.B();
        UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)com.corrodinggames.rts.game.units.UnitInstance.a(as2);
        l2.bL.b(f2, f3);
        y2.eo = (float)l2.bL.T + y2.cZ();
        y2.ep = (float)l2.bL.U + y2.cZ();
        y2.b(n2);
        boolean bl = y2.c((com.corrodinggames.rts.game.PlayerState) null);
        return bl;
    }

    public void setBuildStep(int n2) {
        this.buildStep = n2;
    }


    public Texture d(int n2) {
        return null;
    }


    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return teamIconTextures[this.player.getTeamIndex()];
    }

    public static void initTextures() {
        GlobalState l2 = GlobalState.B();
        defaultIcon = l2.bO.a(R$drawable.unit_icon_building);
        teamIconTextures = com.corrodinggames.rts.game.PlayerState.a(defaultIcon);
    }

    public ExperimentalUnit(boolean bl) {
        super(bl);
        this.cg = -90.0f;
        this.bT = false;
    }


    public void f_() {
        this.bT = false;
    }

    public boolean onBuildComplete() {
        this.a(com.corrodinggames.rts.game.units.UnitState.d);
        return false;
    }


    public boolean e() {
        GlobalState l2 = GlobalState.B();
        l2.bU.a(this);
        if (this.cm < 1.0f) {
            this.a(com.corrodinggames.rts.game.units.UnitState.a);
            return false;
        }
        this.animFrame = 0;
        return this.onBuildComplete();
    }


    public Rect cd() {
        return this.destRect;
    }


    public Rect cc() {
        return this.sourceRect;
    }

    public static boolean readFromStream(UnitType y2, UnitTypeHandle as2, MovementTypeEnum ao2, int n2, int n3, int n4) {
        GlobalState l2 = GlobalState.B();
        com.corrodinggames.rts.game.map.MapEngine b2 = l2.bL;
        if (!b2.c(n2, n3)) {
            return false;
        }
        boolean bl = false;
        if (b2.placementCheck && l2.bs.N != null) {  // 02b d/d L118: var7.E
            if (!b2.ignorePlacementCheck && l2.bs.N[n2][n3] == 10) {  // 02b d/d L119: var7.G
                return false;
            }
            boolean bl2 = bl = l2.bs.N[n2][n3] >= 5;
        }
        if (ExperimentalUnit.readFromStream(y2, as2, ao2, n2, n3, bl)) {
            if (as2.p()) {
                com.corrodinggames.rts.game.map.MapLayer g2 = b2.e(n2, n3);
                return g2 != null && g2.isTileLayer;  // 02b d/d L129: var9.i
            }
            return !com.corrodinggames.rts.gameFramework.effects.GameHUD.a(l2.bs, n2, n3, n4);
        }
        return false;
    }

    public static boolean readFromStream(UnitType y2, UnitTypeHandle as2, MovementTypeEnum ao2, int n2, int n3, boolean bl) {
        return ExperimentalUnit.readFromStream(y2, as2, ao2, n2, n3, bl, null) == null;
    }

    public static String readFromStream(UnitType y2, UnitTypeHandle as2, MovementTypeEnum ao2, int n2, int n3, boolean bl, com.corrodinggames.rts.game.PlayerState n4) {
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
        if (as2 == com.corrodinggames.rts.game.units.UnitRegistry.d || ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.e) {
            if (!l2.bU.a(l2.bU.A, n2, n3)) {
                return null;
            }
            return "{3}";
        }
        com.corrodinggames.rts.game.map.MapLayer mapLayer = l2.bL.e(n2, n3);
        if (mapLayer != null && mapLayer.isTileLayer) {  // 02b d/d L158: var11.i
            if (as2.p()) {
                return null;
            }
            return "{0}";
        }
        if (ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.d) {
            return null;
        }
        if (ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.f) {
            if (!l2.bU.a(l2.bU.C, n2, n3)) {
                return null;
            }
            return "{0}";
        }
        if (ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.g) {
            if (!l2.bU.a(l2.bU.D, n2, n3)) {
                return null;
            }
            return "{0}";
        }
        if (ao2 == com.corrodinggames.rts.game.units.MovementTypeEnum.h) {
            if (!l2.bU.a(l2.bU.E, n2, n3)) {
                return null;
            }
            return "{0}";
        }
        if (l2.bU.a(l2.bU.airSolver, n2, n3, bl)) {  // 02b d/d L169: bU.z (z=airSolver)
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

    public static UnitInstance b(int n2, int n3) {
        GlobalState l2 = GlobalState.B();
        l2.bL.a(n2, n3);
        float f2 = l2.bL.T + l2.bL.selectedTileX;  // 02b d/d L191: T+p
        float f3 = l2.bL.U + l2.bL.selectedTileY;  // 02b d/d L192: U+q
        java.util.Iterator it2 = l2.cc.b(f2, f3, 0.0f).iterator();  // 02b d/d L193-205: 显式迭代+强转
        UnitInstance am2;
        do {
            if (!it2.hasNext()) {
                return null;
            }
            am2 = (UnitInstance) it2.next();
        } while (!am2.isFactoryBuilding() || am2.isDead || !am2.c(f2, f3, 0.0f));
        return am2;

    }


    public void readFromStream(int n2) {
    }

    public static UnitInstance createUnit(UnitTypeHandle as2) {
        if (as2 == null) {
            throw new RuntimeException("type is null");
        }
        return as2.a();
    }


    public boolean canPatrol() {
        return false;
    }


    public MovementTypeEnum getMovementType() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.a;
    }


    public boolean i() {
        return false;
    }


    public boolean isSelectable() {
        return false;
    }


    public float getBuildProgress() {
        return 0.0f;
    }


    public float A() {
        return 0.0f;
    }


    public boolean b_() {
        return false;
    }

    public Paint getStatusPaint() {
        int n2;
        GlobalState l2 = GlobalState.B();
        PorterDuffColorFilter porterDuffColorFilter = null;
        if (this.cm < 1.0f) {
            n2 = Color.a((int)(40.0f + this.cm * 200.0f), 140, 255, 140);
            porterDuffColorFilter = buildProgressFilter;
        } else {
            n2 = Color.a(255, 255, 255, 255);
        }
        if (this.cp) {
            if (this.cs) {
                n2 = Color.a(200, 20, 255, 20);
                porterDuffColorFilter = validSelectionFilter;
            }
            if (this.ct) {
                n2 = Color.a(200, 255, 20, 20);
                porterDuffColorFilter = invalidSelectionFilter;
            }
            if (this.cq) {
                n2 = Color.a(70, 70, 70, 245);
                porterDuffColorFilter = waypointFilter;
                if (this.ct) {
                    n2 = Color.a(70, 255, 20, 20);
                    porterDuffColorFilter = invalidSelectionFilter;
                }
            }
            if (this.cr) {
                n2 = Color.a(150, 100, 100, 100);
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
            bl = com.corrodinggames.rts.game.units.UnitRegistry.ag;
        }
        return this.a(n2, porterDuffColorFilter, bl);
    }


    public boolean setBuildProgress(float f2) {
        GlobalState l2 = GlobalState.B();
        int n2 = this.animFrame * this.es;
        int n3 = 0;
        RectF rectF = this.cF();
        dv.a(n2, n3, n2 + this.es, n3 + this.et);
        l2.bO.a(this.M, dv, rectF, this.getStatusPaint());
        return true;
    }


    public void d(float f2) {
        super.d(f2);  // 02b d/d L307: super.d(var1)
        if (this.previewTexture == null) {
            return;
        }
        GlobalState l2 = GlobalState.B();
        if (this.hasOverlaySprite()) {
            l2.bO.b(this.previewTexture, this.eo - (float)((int)(this.previewTexture.t + 0.1f)) - l2.cw, this.ep - (float)((int)(this.previewTexture.u + 0.1f)) - l2.cx, this.getStatusPaint());
        } else {
            int n2 = 0;
            int n3 = 0;
            RectF rectF = this.cF();
            dv.a(n2, n3, n2 + this.es, n3 + this.et);
            l2.bO.a(this.previewTexture, dv, rectF, this.getStatusPaint());
        }
    }


    public boolean bI() {
        return true;
    }
}

