/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.debug;
import com.corrodinggames.rts.gameFramework.platform.Sound;
import com.corrodinggames.rts.game.units.BuildingBase;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.WaterUnit;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class FactoryAction5
extends FactoryAction6 {
    boolean actionDef = false;
    boolean actionTarget = true;
    float actionValue = 0.0f;
    static com.corrodinggames.rts.gameFramework.rendering.Texture actionFlags = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture e = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture f = null;
    public static com.corrodinggames.rts.gameFramework.rendering.Texture g = null;
    public static com.corrodinggames.rts.gameFramework.rendering.Texture[] h = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] i = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    public static final GameAction j = new FactoryAction5$1(151);
    public static final GameAction k = new FactoryAction5$2(152);
    static ArrayList l = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.actionDef);
        as2.a(this.actionValue);
        super.a(as2);
    }

    @Override
    public void a(InputNetStream k2) {
        this.actionDef = k2.readBoolean();
        boolean bl = this.actionTarget = !this.Q();
        if (k2.b() >= 21) {
            this.actionValue = k2.readFloat();
        }
        this.L();
        super.a(k2);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return h[this.player.R()];
    }

    public static void b() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        e = l2.bO.a(R$drawable.attack_submarine);
        f = UnitInstance.a(e, e.m(), e.l());
        actionFlags = l2.bO.a(R$drawable.attack_submarine_dead);
        g = l2.bO.a(R$drawable.unit_icon_water);
        h = com.corrodinggames.rts.game.PlayerState.a(g);
        i = com.corrodinggames.rts.game.PlayerState.a(e);
    }


    public boolean F() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bQ.renderExtraShadows && this.eq >= 0.0f;
    }


    public float G() {
        return 0.0f;
    }


    public float H() {
        return 0.0f;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return actionFlags;
        }
        return i[this.player.R()];
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return f;
    }

    @Override
    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.e;
    }

    public UnitRegistry f() {
        return com.corrodinggames.rts.game.units.UnitRegistry.K;
    }

    @Override
    public boolean K() {
        return !this.Q();
    }

    public FactoryAction5(boolean bl) {
        super(bl);
        this.b(e);
        this.cj = 15.0f;
        this.ck = this.cj - 2.0f;
        this.hp = this.maxHp = 260.0f;
        this.M = e;
    }

    public void L() {
        if (!this.actionTarget) {
            this.S(1);
        } else {
            this.S(2);
        }
    }

    @Override
    public void s(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f3 = this.actionDef ? 1.0f : -8.0f;
        this.actionValue = com.corrodinggames.rts.gameFramework.GameUtils.c(this.eq - f3) > 2.0f ? com.corrodinggames.rts.gameFramework.GameUtils.a(this.actionValue, 0.08f, 0.003f * f2) : com.corrodinggames.rts.gameFramework.GameUtils.a(this.actionValue, 0.02f, 0.003f * f2);
        this.eq = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, f3, this.actionValue * f2);
        boolean bl = false;
        if (this.actionTarget && this.Q()) {
            this.actionTarget = false;
            this.L();
            bl = true;
        }
        if (!this.actionTarget && !this.Q()) {
            this.actionTarget = true;
            this.L();
            bl = true;
        }
        if (bl) {
            l2.bR.a(this.eo, this.ep, 0.0f, 0, 0.0f, 0.0f, this.cg);
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
    }


    public float m() {
        if (!this.Q()) {
            return 250.0f;
        }
        return 180.0f;
    }


    public float b(int n2) {
        return 170.0f;
    }


    public float e(int n2) {
        return 10.0f;
    }


    public float z() {
        if (!this.Q()) {
            return 0.8f;
        }
        return 0.45f;
    }


    public float A() {
        return 1.2f;
    }


    public float B() {
        return 0.06f;
    }


    public float c(int n2) {
        return 2.5f;
    }


    public float w(int n2) {
        return 0.08f;
    }


    public float C() {
        return 0.018f;
    }


    public float D() {
        return 0.1f;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d(int n2) {
        return null;
    }


    public boolean l() {
        return true;
    }

    @Override
    public boolean Q() {
        return this.eq < -1.0f;
    }


    public boolean ah() {
        return !this.Q();
    }


    public boolean ae() {
        return this.Q();
    }


    public boolean af() {
        return !this.Q();
    }


    public boolean ag() {
        if (!this.Q()) {
            return true;
        }
        return true;
    }


    public float q(int n2) {
        return 42.0f;
    }


    public void a(UnitInstance am2, int n2) {
        if (!this.Q()) {
            PointF pointF = this.E(n2);
            MovementController f2 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(255, 230, 230, 50);
            f2.U = 42.0f;
            f2.l = am2;
            f2.h = 190.0f;
            f2.t = 2.0f;
            f2.aH = true;
            f2.aM = true;
            f2.aQ = true;
            GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
            l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq, -1118720);
        } else {
            PointF pointF = this.E(n2);
            MovementController f3 = com.corrodinggames.rts.game.MovementController.a((UnitInstance) this, pointF.a, pointF.b, this.eq, n2);
            PointF pointF3 = this.K(n2);
            f3.K = pointF3.a;
            f3.L = pointF3.b;
            f3.ar = Color.a(255, 30, 30, 150);
            f3.x = 1.0f;
            f3.U = 42.0f;
            f3.l = am2;
            f3.h = 250.0f;
            f3.t = 1.3f;
            f3.aH = false;
            f3.aM = true;
            f3.aQ = true;
            GlobalState l3 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        }
    }


    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = actionFlags;
        this.S(0);
        this.bT = false;
        return true;
    }


    public void a(GameAction s2, boolean bl) {
        if (s2 == j) {
            this.actionDef = true;
        }
        if (s2 == k) {
            this.actionDef = false;
        }
    }


    public ArrayList N() {
        return l;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.f();
    }

    static {
        l.add(j);
        l.add(k);
    }
}
