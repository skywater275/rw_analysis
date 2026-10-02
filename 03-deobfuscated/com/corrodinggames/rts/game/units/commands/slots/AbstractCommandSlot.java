/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.util.ArrayList;

public strictfp class AbstractCommandSlot
extends BuildActionSlot {
    static Texture a = null;
    static Texture b = null;
    static Texture c = null;
    static Texture[] d = new Texture[10];
    public static GameAction e = new AbstractCommandSlot$1(102);
    static ArrayList f = new ArrayList();

    @Override
    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return d[this.player.R()];
    }

    public static void b() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        a = l2.bO.a(R$drawable.anti_air_top);
        b = l2.bO.a(R$drawable.anti_air_top_l2);
        c = l2.bO.a(R$drawable.unit_icon_building_air_turrent);
        d = com.corrodinggames.rts.game.PlayerState.a(c);
    }

    public AbstractCommandSlot(boolean bl) {
        super(bl);
        this.hp = this.maxHp = 800.0f;
    }

    @Override
    public float m() {
        if (!this.j) {
            return 250.0f;
        }
        return 320.0f;
    }

    @Override
    public float b(int n2) {
        if (!this.j) {
            return 80.0f;
        }
        return 70.0f;
    }


    public float e(int n2) {
        if (!this.j) {
            return super.e(n2);
        }
        if (n2 == 2) {
            return 25.0f;
        }
        return super.e(n2);
    }

    @Override
    public PointF E(int n2) {
        if (!this.j || n2 == 0) {
            return super.E(n2);
        }
        float f2 = this.E() ? this.cg : this.cL[n2].turretAngle;  // 02b ap.a = turretAngle
        PointF pointF = this.G(n2);
        float f3 = pointF.a + com.corrodinggames.rts.gameFramework.GameUtils.k(f2 += n2 == 1 ? -30.0f : 30.0f) * 10.0f;
        float f4 = pointF.b + com.corrodinggames.rts.gameFramework.GameUtils.j(f2) * 10.0f;
        bg.a(f3, f4);
        return bg;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
        PointF pointF = this.E(n2);
        MovementController f2 = com.corrodinggames.rts.game.MovementController.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.t = 0.3f;
        f2.r = 6.0f;
        if (!this.j) {
            f2.ar = Color.a(255, 230, 230, 50);
            f2.U = 60.0f;
            f2.h = 220.0f;
        } else {
            f2.ar = Color.a(255, 230, 50, 50);
            f2.U = 60.0f;
            f2.h = 250.0f;
            f2.t = 0.5f;
            f2.r = 7.0f;
        }
        f2.P = (short)4;
        f2.x = 1.0f;
        f2.l = am2;
        f2.aH = false;
        f2.aI = 0.0f;
        f2.aJ = 0.0f;
        f2.aM = true;
        f2.aQ = true;
        f2.aG = true;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.GameUtils.c(-0.07f, 0.07f);
        l2.bM.a(SoundRegistry.m, 0.3f, f3, pointF.a, pointF.b);  // 02b a.java L101: a/e.m
        l2.bR.a(f2, -1118720);
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
    }

    @Override
    public UnitRegistry K() {
        if (this.j) {
            return com.corrodinggames.rts.game.units.UnitRegistry.T;
        }
        return com.corrodinggames.rts.game.units.UnitRegistry.g;
    }

    @Override
    public Texture d(int n2) {
        if (!this.j) {
            return a;
        }
        return b;
    }

    @Override
    public boolean af() {
        return true;
    }


    public boolean ag() {
        return false;
    }

    @Override
    public void s(float f2) {
        int n2 = 0;
        if (this.cL[n2].a()) {
            this.cL[n2].turretAngle += this.c(n2) * f2 * 0.1f;  // 02b a.java L110
        }
    }

    @Override
    public float g(int n2) {
        return 9.0f;
    }

    @Override
    public float c(int n2) {
        return 6.0f;
    }


    public float B() {
        return 1.0f;
    }


    public boolean u(int n2) {
        if (!this.j) {
            return super.u(n2);
        }
        if (n2 == 0) {
            return false;
        }
        return super.u(n2);
    }


    public int v(int n2) {
        if (!this.j) {
            return -1;
        }
        if (n2 == 1) {  // 02b a.java L148
            return 0;
        }
        if (n2 == 2) {
            return 0;
        }
        return -1;
    }


    public int bl() {
        return 3;
    }


    public boolean r(int n2) {
        return this.j || n2 <= 1;  // 02b a.java L156
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(e.N())) {  // 02b a.java L160: var1.j (BuilderUnit ActionId)
            this.a(2);
            this.W();
        }
    }


    @Override
    public ActionId cm() {  // 02b a.java L167-169: 返回 units.a.c = ActionId
        if (!this.j) {
            return e.N();
        }
        return GameAction.i;  // 02b s.i (s = units.a.s = GameAction)
    }

    @Override
    public void a(ArrayList arrayList) {
        arrayList.clear();
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {  // 02b a.java L176
            this.j = false;
        } else if (n2 == 2 && !this.j) {
            this.j = true;
            this.maxHp += 600.0f;
            this.hp += 600.0f;
        }
    }

    @Override
    public ArrayList N() {
        return f;
    }

    @Override
    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }

    static {
        f.add(e);  // 02b a.java L196: f.add(e)
    }
}
