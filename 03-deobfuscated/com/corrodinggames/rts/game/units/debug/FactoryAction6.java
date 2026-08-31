/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.debug;
import com.corrodinggames.rts.game.units.BuildingBase;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.io.IOException;

public strictfp abstract class FactoryAction6
extends CustomUnitBase {
    float m;
    float n;
    boolean o = false;
    public static Texture p = null;
    public static Texture[] q = new Texture[10];

    public FactoryAction6(boolean bl) {
        super(bl);
    }

    @Override
    public void a(OutputNetStream as2) throws IOException {
        as2.a(this.n);
        as2.a(this.o);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.n = k2.readFloat();
        this.o = k2.readBoolean();
        super.a(k2);
    }


    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return q[this.player.getTeamIndex()];
    }

    public static void M() {
        GlobalState l2 = GlobalState.B();
        p = l2.bO.a(R$drawable.unit_icon_water);
        q = com.corrodinggames.rts.game.PlayerState.a(p);
    }


    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.e;
    }


    public boolean cv() {
        return true;
    }

    public boolean K() {
        return true;
    }

    public void s(float f2) {
        float f3 = 0.0f;
        if (this.eq != f3) {
            this.eq = com.corrodinggames.rts.gameFramework.GameUtils.a(this.eq, f3, 0.2f * f2);
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead) {
            if (this.eq > -10.0f) {
                this.n += 0.002f * f2;
                this.eq -= this.n * f2;
            } else {
                this.eq = -10.0f;
                if (!this.o) {
                    this.o = true;
                }
            }
            return;
        }
        if (!this.bT() || this.isDead) {
            return;
        }
        this.s(f2);
        if (this.K()) {
            if (this.cf != 0.0f) {
                this.m += f2;
            }
            if (this.m > 10.0f) {
                this.m = 0.0f;
                if (this.s_()) {
                    float f3;
                    GlobalState l2 = GlobalState.B();
                    float f4 = this.cg + 180.0f;
                    if (this.cf < 0.0f) {
                        f4 += 180.0f;
                    }
                    if ((f3 = this.cj - 6.0f) < 4.0f) {
                        f3 = 4.0f;
                    }
                    float f5 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.k(f4) * f3;
                    float f6 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.j(f4) * f3;
                    l2.bR.b(f5, f6, 0.0f, f4);
                }
            }
        }
    }
}
