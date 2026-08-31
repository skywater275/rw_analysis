/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.RallyPointAction;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.PathfindingUtils;
import java.util.ArrayList;
import java.io.IOException;

public strictfp class CommandCenter
extends MobileBuilderBase {
    static com.corrodinggames.rts.gameFramework.rendering.Texture commanderUnit = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] selectionIndex = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture lastCommandTick = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture commandTimeout = null;
    float isActiveCmdCenter;
    public float f;
    public float g;
    public int h;
    public float i;
    public float j;
    float k = 20.0f;
    int l = 0;


    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.isActiveCmdCenter);
        super.a(as2);
    }


    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream k2) {  // 02b e.java L36-39: a(j.k)
        this.isActiveCmdCenter = k2.readFloat();
        super.a(k2);
    }

    public static void getQueueCount() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        commanderUnit = l2.bO.a(R$drawable.base);
        lastCommandTick = l2.bO.a(R$drawable.base_dead);
        commandTimeout = l2.bO.a(R$drawable.base_back);
        selectionIndex = com.corrodinggames.rts.game.PlayerState.a(commanderUnit);
    }

    public UnitRegistry getUnitType() {
        return com.corrodinggames.rts.game.units.UnitRegistry.e;
    }


    public boolean L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        this.M = lastCommandTick;
        this.m = null;
        this.S(0);  // 02b S(int)
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.UnitState.d);  // 02b L59: a(ab.d)
        float f2 = this.eo;
        float f3 = this.ep;
        float f4 = 0.0f;
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.a(f2, f3, this.eq, Color.a(255, 255, 255, 255));
        if (e2 != null) {
            e2.G = 8.0f;
            e2.F = 5.0f;
            e2.E = 0.9f;
            e2.W = e2.V = 20.0f;
            e2.r = true;
        }
        l2.bR.b(com.corrodinggames.rts.gameFramework.effects.DrawLayer.e);
        e2 = l2.bR.c(f2, f3, f4, -1127220);
        if (e2 != null) {
            e2.G = 0.2f;
            e2.F = 2.0f;
            e2.ar = (short)2;
            e2.W = e2.V = 45.0f;
            e2.U = 0.0f;
        }
        float f5 = 40.0f;
        float f6 = 70.0f;
        l2.bR.a(this.eo, this.ep, this.eq, f5, f6);
        com.corrodinggames.rts.gameFramework.effects.DrawEffect.a(this.eo, this.ep);
        com.corrodinggames.rts.gameFramework.effects.DrawEffect.b((float)this.eo, (float)this.ep).a = 800.0f;
        return true;
    }


    public void a(int n2) {
    }


    public void S() {
        super.S();  // 02b e.java L96: super.S()
        this.m = this.isDead ? null : commandTimeout;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return lastCommandTick;
        }
        return selectionIndex[this.player.R()];  // 02b e.java L106: b[...]
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return null;
    }

    public CommandCenter(boolean bl) {
        super(bl);
        this.M = commanderUnit;
        this.m = commandTimeout;
        this.T(53);
        this.U(68);
        this.ck = this.cj = 30.0f;
        this.hp = this.maxHp = 4000.0f;
        this.S(3);  // 02b S(int)
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 2);
    }


    public RectF cF() {  // 02b e.java L128-132: cF() (旧误名 getSelectionBounds)
        RectF rectF = super.cF();
        rectF.a(6.0f, 0.0f);
        return rectF;
    }


    public void a(float f2) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        this.k = com.corrodinggames.rts.gameFramework.GameUtils.a(this.k, f2);
        if (this.k == 0.0f) {
            this.k = 5.0f;
            ++this.l;
            if (this.l > 6) {
                this.l = 0;
                this.k = 70.0f;
            }
            this.s = this.l <= 3 ? this.l : 6 - this.l;
        }
        this.f += f2;
        ++this.h;
        this.i += 10.0f;
        if (this.j > f2) {
            this.j = f2;
        }
        this.g += f2;
        this.isActiveCmdCenter += f2;
        if (this.isActiveCmdCenter > com.corrodinggames.rts.game.PlayerState.ap - 0.1f) {
            this.isActiveCmdCenter -= com.corrodinggames.rts.game.PlayerState.ap;
            this.player.b(this.cy() * (com.corrodinggames.rts.game.PlayerState.ap / com.corrodinggames.rts.game.PlayerState.ao));  // 02b L165
        }
    }


    public float cy() {
        return 18.0f;
    }


    public float q(int n2) {
        return 70.0f;
    }


    public void a(UnitInstance am2, int n2) {
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, this.eo, this.ep);
        PointF pointF = this.K(n2);  // 02b L181: K(var2)
        f2.K = pointF.a;
        f2.L = pointF.b;
        f2.ar = Color.a(255, 230, 230, 50);
        f2.U = this.q(n2);  // 02b L185: q(var2)
        f2.l = am2;
        f2.h = 180.0f;
        f2.t = 2.0f;
        f2.r = 5.0f;
        f2.aH = true;
        f2.aM = true;
        f2.aQ = true;
        f2.aG = true;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(f2, -1118720);
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.m, 0.8f, this.eo, this.ep);
    }

    @Override
    public float m() {
        return 280.0f;
    }

    @Override
    public float b(int n2) {
        return 70.0f;
    }

    @Override
    public float c(int n2) {
        return 999.0f;
    }


    public boolean b(int n2, float f2) {
        return false;
    }


    public boolean isBuilding() {
        return true;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new RallyPointAction());  // 02b units.a.o = RallyPointAction
        arrayList.add(new com.corrodinggames.rts.game.units.actions.UnitBuildAction(com.corrodinggames.rts.game.units.UnitRegistry.h, 1.0f));
    }


    public ArrayList N() {
        return this.getUnitType().a(this.V());
    }


    public float a(UnitInstance am2, float f2, MovementController f3) {  // 02b e.java L228: a(am,float,game/f)
        if (f2 > 2500.0f) {
            f2 = 2500.0f;
        }
        return super.a(am2, f2, f3);
    }


    public boolean isUnderConstruction() {
        return true;
    }


    public void e(float f2) {
        super.e(f2);  // 02b L241: super.e(var1)
        float f3 = this.m();  // 02b L242: m()
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public int getWidth() {
        return 20;
    }


    public int getHeight() {
        return 35;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.getUnitType();
    }
}
