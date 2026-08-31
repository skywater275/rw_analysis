/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.map.TileEntry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.UnitRegistry;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.MobileBuilderBase;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.platform.SoundRegistry;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.effects.DrawLayer;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;
import java.io.IOException;

public class ExperimentalSubUnit
extends MobileBuilderBase {
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] a = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] b = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    static com.corrodinggames.rts.gameFramework.rendering.Texture c = null;
    boolean d;
    boolean e;
    float f;
    static com.corrodinggames.rts.gameFramework.rendering.Texture g = null;
    static com.corrodinggames.rts.gameFramework.rendering.Texture[] h = new com.corrodinggames.rts.gameFramework.rendering.Texture[10];
    PointF i = new PointF();
    Rect j = new Rect();
    static GameAction k = new ExperimentalSubUnit$1(102);
    static ArrayList l = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) throws IOException {
        as2.a(this.d);
        as2.a(this.f);
        as2.a(this.e);
        as2.a(this.f);
        super.a(as2);
    }



    public com.corrodinggames.rts.gameFramework.rendering.Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return h[this.player.R()];  // 02b p.java L51: h[bX.R()]
    }

    public static void b() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        c = l2.bO.a(R$drawable.laser_defence_dead);
        com.corrodinggames.rts.gameFramework.rendering.Texture e2 = l2.bO.a(R$drawable.laser_defence);
        com.corrodinggames.rts.gameFramework.rendering.Texture e3 = l2.bO.a(R$drawable.laser_defence_t2);
        a = com.corrodinggames.rts.game.PlayerState.a(e2);
        b = com.corrodinggames.rts.game.PlayerState.a(e3);
        e2.n();
        e2 = null;
        e3.n();
        e3 = null;
        g = l2.bO.a(R$drawable.unit_icon_building_turrent);
        h = com.corrodinggames.rts.game.PlayerState.a(g);
    }


    public boolean L() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = c;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.p, 0.8f, this.eo, this.ep);
        return true;
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture d() {
        if (this.isDead) {
            return c;
        }
        if (this.player == null) {
            return a[a.length - 1];
        }
        if (!this.d) {
            return a[this.player.R()];
        }
        return b[this.player.R()];  // 02b p.java L80: b[bX.R()]
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture k() {
        return null;
    }

    public ExperimentalSubUnit(boolean bl) {
        super(bl);
        this.a(a[0], 2);
        this.f = 1.0f;
        this.ck = this.cj = 19.0f;
        this.hp = this.maxHp = 500.0f;
        this.M = a[a.length - 1];
        this.n.a(0, 0, 1, 1);  // 02b p L96
        this.o.a(0, 0, 1, 1);  // 02b p L97
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f3 = 4.0E-4f * f2;
        if (this.d) {
            f3 += 2.0E-4f * f2;
        }
        this.f = com.corrodinggames.rts.gameFramework.GameUtils.a(this.f, 1.0f, f3);
        if (this.f >= 1.0f) {
            this.e = false;
        }
        this.f -= f2;
        this.i.a(this.E(0));
        if (this.f > 0.0f && !this.e) {
            float f4 = !this.d ? 0.11f : 0.05f;
            if (ExperimentalSubUnit.a((UnitType) this, this.i.a, this.i.b, this.eq, this.m(), f4)) {  // 02b p 闈欐€?a(y,f,f,f,f,f)
                this.f = 3.0f;
            }
            if (this.f < 0.0f) {
                this.f = 0.0f;
                this.e = true;
            }
        }
        this.s = this.e ? 1 : 0;  // 02b: 1
    }

    public static boolean a(UnitType y2, float f2, float f3, float f4, float f5, float f6) {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        float f7 = f5 * f5;
        Object[] objectArray = com.corrodinggames.rts.game.MovementController.a.a();
        int n2 = com.corrodinggames.rts.game.MovementController.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.gameFramework.effects.HUDElement e2;
            float f8;
            MovementController f9 = (MovementController) objectArray[i2];  // 02b p L150: (f)var8[var9]
            if (f9.A || f9.C || !(f9.J > 7.0f) && (!(f9.J > 2.0f) || !(f9.t > 8.0f)) || f9.aS || !((f8 = (f9.eo - f2) * (f9.eo - f2) + (f9.ep - f3) * (f9.ep - f3)) < f7) || f9.eq < -1.0f) continue;
            boolean bl = false;
            if (f9.l != null && y2.player.d(f9.l.player)) {
                bl = true;
            }
            if (!bl && f9.j != null && y2.player.c(f9.j.player)) {
                bl = true;
            }
            if (!bl) continue;
            com.corrodinggames.rts.gameFramework.effects.HUDElement e3 = l2.bR.a(f2, f3, f4, f9.eo, f9.ep, f9.eq);
            if (e3 != null) {
                e3.W = e3.V = 10.0f;
            }
            if ((e2 = l2.bR.b(f2, f3, f4, SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c)) != null) {
                e2.P = 0.0f;
                e2.Q = 0.0f;
                e2.ap = 4;
                e2.W = e2.V = 39.0f;
                e2.r = true;
                e2.E = 1.3f;
                e2.G = 1.1f;
                e2.F = 0.7f;
            }
            f9.H -= 1.01f;
            if (f9.H <= 0.0f) {
                f9.d();
                e2 = l2.bR.b(f9.eo, f9.ep, f9.eq, SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.c);
                if (e2 != null) {
                    e2.P = 0.0f;
                    e2.Q = 0.0f;
                    e2.ap = 4;
                    e2.W = e2.V = 23.0f;
                    e2.r = true;
                    e2.E = 0.9f;
                    e2.G = 0.5f;
                    e2.F = 0.2f;
                }
                float f10 = 1.0f + com.corrodinggames.rts.gameFramework.GameUtils.c(-0.07f, 0.07f);
                l2.bM.a(com.corrodinggames.rts.gameFramework.platform.SoundRegistry.F, 0.2f, f10, f9.eo, f9.ep);
            }
            y2.cB -= f6;
            return true;
        }
        return false;
    }


    public PointF E(int n2) {
        bg.a(this.eo, this.ep - 13.0f);
        return bg;
    }

    @Override
    public void a(UnitInstance am2, int n2) {
    }

    @Override
    public float m() {
        if (!this.d) {
            return 160.0f;
        }
        return 210.0f;
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
        return com.corrodinggames.rts.game.units.UnitRegistry.y;
    }

    @Override
    public boolean l() {
        return false;
    }


    public float g(int n2) {
        return 1.0f;
    }


    public float bW() {
        if (this.f != 1.0f) {
            return this.f;
        }
        return super.bW();
    }


    public boolean bX() {
        return this.e;
    }


    public float bd() {
        return 1.0f;
    }

    @Override
    public void a(BuilderUnit j2) {
        if (j2.j.equals(k.N())) {
            com.corrodinggames.rts.game.PlayerState.b((UnitInstance) this);
            this.a(2);
            com.corrodinggames.rts.game.PlayerState.c(this);
            this.W();
        }
    }


    public ActionId cm() {
        if (!this.d) {
            return k.N();
        }
        return com.corrodinggames.rts.game.units.actions.GameAction.i;
    }


    public int V() {
        if (this.d) {
            return 2;
        }
        return 1;  // 02b: 1
    }


    public void a(int n2) {
        if (n2 == 1) {  // 02b: var2 == 1
            this.d = false;
        } else if (n2 == 2 && !this.d) {
            this.d = true;
            this.maxHp += 900.0f;
            this.hp += 900.0f;
        }
        this.S();
    }


    public ArrayList N() {
        return l;
    }


    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a((UnitInstance) this, f3);
    }


    public float cZ() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.tilePixelWidth;
    }


    public float da() {
        return com.corrodinggames.rts.gameFramework.GlobalState.B().bL.tilePixelHeight;
    }


    public float db() {
        return super.db() - 8.0f;
    }


    public /* synthetic */ UnitTypeHandle r() {
        return this.K();
    }

    static {
        l.add(k);
    }


    // v19.112d 琛ユ彃 (02b units/d/p.java)
    public void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        this.d = var1.e();
        this.cB = var1.readFloat();  // 02b p: g()
        this.e = var1.e();
        if(var1.b() >= 38) {
           this.f = var1.readFloat();  // 02b p: g()
        }

        super.a(var1);
   }
}
