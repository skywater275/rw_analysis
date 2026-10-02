/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.BuildingBase;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.game.units.CustomUnitBase;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp abstract class AbstractBuildingBase
extends CustomUnitBase {
    float dK;
    public static com.corrodinggames.rts.gameFramework.rendering.Texture dL = null;  // 02b e/j.java L11: m.e dL (SubBuildingType1 为幻觉名)
    public static com.corrodinggames.rts.gameFramework.rendering.Texture dM = null;
    public static Texture[] dN = new Texture[10];
    public static Texture[] dO = new Texture[10];

    public AbstractBuildingBase(boolean bl) {
        super(bl);
    }


    public com.corrodinggames.rts.gameFramework.rendering.Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        if (this.dd()) {
            return dO[this.player.getTeamIndex()];
        }
        return dN[this.player.getTeamIndex()];
    }

    public static void dt() {
        GlobalState l2 = GlobalState.B();
        dL = l2.bO.a(R$drawable.unit_icon_land);
        if (dL == null) {
            throw new RuntimeException("IMAGE_ICON is null");
        }
        dN = com.corrodinggames.rts.game.PlayerState.a(dL);  // 02b L31: n.a(m.e)
        dM = l2.bO.a(R$drawable.unit_icon_land_exp);
        if (dM == null) {
            throw new RuntimeException("IMAGE_ICON_EXP is null");
        }
        dO = com.corrodinggames.rts.game.PlayerState.a(dM);
    }

    @Override
    public void a(float f2) {
        float f3;
        super.a(f2);
        if (this.isDead) {
            f3 = 0.0f;
            if (this.cK()) {
                f3 = -10.0f;
            }
            if (this.eq > f3) {
                if (this.eq > 0.0f && this.dK < 0.4f) {
                    this.dK = 0.4f;
                }
                this.dK += 0.002f * f2;
                this.eq -= this.dK * f2;
                if (this.eq <= f3) {
                    this.eq = f3;
                }
            }
        }
        if (!this.bT() || this.isDead) {
            return;
        }
        if (!(this instanceof AbstractSubBuilding)) {
            f3 = 0.0f;
            if (this.eq < f3) {
                this.eq += 0.2f * f2;
                if (this.eq >= f3) {
                    this.eq = f3;
                }
            }
            if (this.eq > 0.0f) {
                this.dK += 0.03f * f2;
                if (this.eq < 0.0f) {
                    this.dK = com.corrodinggames.rts.gameFramework.GameUtils.b(this.dK, 0.2f);
                }
                this.eq -= this.dK * f2;
                if (this.eq <= 0.0f) {
                    if (this.eq < 0.0f) {
                        this.eq = 0.0f;
                    }
                    this.dK = 0.0f;
                }
            }
        }
    }


    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.b;
    }
}
