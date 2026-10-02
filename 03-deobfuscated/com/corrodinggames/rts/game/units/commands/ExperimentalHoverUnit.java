/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class ExperimentalHoverUnit
extends MobileBuilderBase {
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] buttonTextures = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture b = null;
    boolean isSelected;
    int slotIndex;
    float alphaValue;
    static com.corrodinggames.rts.gameFramework.rendering.Texture f = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] g = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    PointF h = new PointF();
    Rect i = new Rect();
    static GameAction j = new ExperimentalHoverUnit$1(145);
    static GameAction k = new ExperimentalHoverUnit$2(144);
    static ArrayList l = new ArrayList();


    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.isSelected);
        as2.a(this.slotIndex);
        super.a(as2);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {
        this.isSelected = k2.e();
        if (k2.b() >= 30) {
            this.slotIndex = k2.f();
        }
        super.a(k2);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return g[this.player.R()];
    }

    public static void updateState() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        b = l2.bO.a(R$drawable.antinuke_launcher_dead);
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.a(R$drawable.antinuke_launcher);
        buttonTextures = com.corrodinggames.rts.game.PlayerState.a(e2);
        e2.n();
        f = l2.bO.a(R$drawable.unit_icon_building_turrent);
        g = com.corrodinggames.rts.game.PlayerState.a(f);
    }


    public boolean L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.h);
        return true;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return b;
        }
        return buttonTextures[this.player.getTeamIndex()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return null;
    }


    public void a(int n2) {
    }

    public ExperimentalHoverUnit(boolean bl) {
        super(bl);
        this.M = buttonTextures[buttonTextures.length - 1];
        this.b(this.M);
        this.ck = this.cj = 24.0f;
        this.hp = this.maxHp = 2800.0f;
        this.n.a(-1, -1, 1, 1);  // 02b n (sourceRect 幻觉)
        this.o.a(-1, -1, 1, 1);  // 02b o (destRect 幻觉)
    }


    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        if (this.slotIndex > 0) {
            MovementController f3 = null;
            this.alphaValue = com.corrodinggames.rts.gameFramework.GameUtils.a(this.alphaValue, f2);
            if (this.alphaValue == 0.0f) {
                this.alphaValue = 15.0f;
                java.util.Iterator iterator = com.corrodinggames.rts.game.MovementController.a.iterator();  // 02b c.java L103-106: 显式迭代+强转
                while (iterator.hasNext()) {
                    com.corrodinggames.rts.game.MovementController f4 = (com.corrodinggames.rts.game.MovementController) iterator.next();
                    float f5;
                    if (!f4.D || !(f4.eq > 50.0f)) continue;
                    float f6 = 2200.0f;
                    float f7 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f4.eo, f4.ep);
                    if (!(f7 < f6 * f6) || !((f5 = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eo, this.ep, f4.n, f4.o)) < 1000000.0f) || f4.j != null && (f4.j.player.d(this.player) || f4.j.player == this.player) || this.a(f4)) continue;
                    f3 = f4;
                }
            }
            if (f3 != null) {
                this.b(f3);
            }
        }
    }

    public boolean a(com.corrodinggames.rts.game.MovementController f2) {
        Object[] objectArray = com.corrodinggames.rts.game.MovementController.a.a();
        int n2 = com.corrodinggames.rts.game.MovementController.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            MovementController f3 = (com.corrodinggames.rts.game.MovementController) objectArray[i2];
            if (f3 == f2 || f3.q != f2) continue;
            return true;
        }
        return false;
    }

    public void b(com.corrodinggames.rts.game.MovementController f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.slotIndex <= 0) {
            return;
        }
        --this.slotIndex;
        int n2 = 0;
        PointF pointF = this.E(n2);
        MovementController f3 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        f3.S(10);
        f3.P = (short)10;
        f3.R = 0;
        f3.x = 1.0f;
        f3.aC = true;
        f3.q = f2;
        f3.h = 99999.0f;
        f3.t = 0.2f;
        f3.r = 6.5f;
        f3.ar = Color.a(255, 80, 60, 180);
        f3.U = 600.0f;
        f3.aH = true;
        f3.aM = true;
        f3.aQ = true;
        f3.C = true;
        f3.aI = 80.0f;
        f3.aJ = 100.0f;
        f3.aL = 2.0f;
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.d(pointF.a, pointF.b, 0.0f, -1);
        if (e2 != null) {
            e2.G = 0.5f;
            e2.F = 2.1f;
            e2.ar = 2;
            e2.W = e2.V = 90.0f;
            e2.U = 0.0f;
        }
        float f4 = 1.5f;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.D, 0.15f, f4, pointF.a, pointF.b);
    }


    public PointF E(int n2) {
        bg.a(this.eo, this.ep - 9.0f);
        return bg;
    }


    public void a(UnitInstance am2, int n2) {
    }

    @Override
    public float m() {
        return 1000.0f;
    }

    @Override
    public float c(int n2) {
        return 4.0f;
    }


    public boolean b(int n2, float f2) {
        return false;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }

    public UnitRegistry K() {
        return com.corrodinggames.rts.game.units.UnitRegistry.D;
    }

    @Override
    public boolean l() {
        return false;
    }


    public float g(int n2) {
        return 1.0f;
    }

    @Override
    public float bV() {
        return super.bV();
    }

    public void M() {
        ++this.slotIndex;
    }


    public void a(BuilderUnit j2) {
        if (j2.j.equals(k.N())) {
            this.M();
        }
    }


    public com.corrodinggames.rts.game.units.actions.ActionId cm() {
        if (this.slotIndex < 4) {
            return k.N();
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public boolean ck() {
        return false;
    }


    public ArrayList N() {
        return l;
    }


    public void e(float f2) {
        super.e(f2);
    }


    public void O() {
    }


    public void cb() {
        float f2 = 990.0f;
        boolean bl = false;
        boolean bl2 = true;
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f2, bl, bl2);
    }


    public boolean a(com.corrodinggames.rts.gameFramework.GlobalState l2) {
        if (this.cG) {
            return true;
        }
        return super.a(l2);
    }


    public float a(UnitInstance am2, float f2, com.corrodinggames.rts.game.MovementController f3) {
        if (f2 > 2600.0f) {
            f2 = 2600.0f;
        }
        return super.a(am2, f2, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }

    static {
        l.add(j);
        l.add(k);
    }
}
