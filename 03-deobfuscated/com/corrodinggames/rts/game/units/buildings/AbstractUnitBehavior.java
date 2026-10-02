/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;
import com.corrodinggames.rts.game.units.BuildingBase;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitState;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import java.io.IOException;

public strictfp abstract class AbstractUnitBehavior
extends CustomUnitBase {
    float h;
    boolean i = false;
    float j;
    Boolean k;
    Boolean l;
    public static com.corrodinggames.rts.gameFramework.rendering.Texture m = null;
    public static Texture[] n = new Texture[10];

    public AbstractUnitBehavior(boolean bl) {
        super(bl);
    }

    @Override
    /* 覆写链 super.a 抛 IOException */
    public void a(OutputNetStream as2) throws IOException {
        as2.a(this.h);
        as2.a(this.i);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.h = k2.readFloat();
        this.i = k2.readBoolean();
        super.a(k2);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        return n[this.player.getTeamIndex()];
    }

    public static void K() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        m = l2.bO.a(R$drawable.unit_icon_air);
        n = com.corrodinggames.rts.game.PlayerState.a(m);
    }


    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.d;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.isDead) {
            if (this.eq > 0.0f) {
                this.h += 0.06f * f2;
                this.eq -= this.h * f2;
            } else {
                if (this.k == null) {
                    this.k = this.cK();
                }
                if (this.l == null) {
                    this.l = this.cJ();
                }
                if (!this.i) {
                    this.i = true;
                    if (this.k.booleanValue()) {
                        this.a(com.corrodinggames.rts.game.units.UnitState.a);
                        if (this.l.booleanValue()) {
                            com.corrodinggames.rts.gameFramework.GlobalState.B().bR.a(this.eo, this.ep, 0.0f, 0, 0.0f, 0.0f, this.cg);
                        }
                    } else {
                        this.a(com.corrodinggames.rts.game.units.UnitState.b);
                    }
                    this.h = 0.0f;
                } else if (this.k.booleanValue()) {
                    if (this.eq > -10.0f) {
                        this.h += 8.0E-4f * f2;
                        this.eq -= this.h * f2;
                        if (this.l.booleanValue()) {
                            this.j += f2;
                            if (this.j > 30.0f) {
                                this.j = 0.0f;
                                if (this.s_()) {
                                    GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                                    com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.b(this.eo, this.ep, this.eq, this.cg);
                                    if (e2 != null) {
                                        e2.P = 0.0f;
                                        e2.Q = -0.1f;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.eq = 0.0f;
                }
            }
            return;
        }
    }


    public boolean e() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (this.eq > -1.0f) {
            for (int j = 0; j < 3; ++j) {
                l2.bR.e(this.eo, this.ep, this.eq);
            }
        }
        return super.e();
    }
}
