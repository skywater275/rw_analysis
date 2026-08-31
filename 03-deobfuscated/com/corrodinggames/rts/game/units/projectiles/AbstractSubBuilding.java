/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;
import com.corrodinggames.rts.game.units.BuildingBase;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.projectiles.AbstractBuildingBase;
import com.corrodinggames.rts.gameFramework.effects.SoundEffect;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp abstract class AbstractSubBuilding
extends AbstractBuildingBase {
    float l;
    public static Texture outputResource = null;
    public static Texture[] inputResources = new Texture[10];

    public AbstractSubBuilding(boolean bl) {
        super(bl);
    }

    @Override
    public Texture v() {
        if (this.player.k == -1) {
            return null;
        }
        if (this.dd()) {
            return AbstractBuildingBase.dO[this.player.getTeamIndex()];
        }
        return inputResources[this.player.getTeamIndex()];
    }

    public static void K() {
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        outputResource = l2.bO.a(R$drawable.unit_icon_hover);
        inputResources = com.corrodinggames.rts.game.PlayerState.a(outputResource);
    }

    @Override
    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.f;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.isDead) {
            return;
        }
        if (this.cK()) {
            if (this.cf > 0.0f) {
                this.l += f2;
            }
            if (this.l > 10.0f) {
                this.l = 0.0f;
                if (this.s_()) {
                    float f3;
                    GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
                    float f4 = this.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg) * 4.0f;
                    com.corrodinggames.rts.gameFramework.effects.HUDElement e2 = l2.bR.b(f4, f3 = this.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg) * 4.0f, 0.0f, com.corrodinggames.rts.gameFramework.effects.SoundEffect.a, false, com.corrodinggames.rts.gameFramework.effects.DrawLayer.b);
                    if (e2 != null) {
                        e2.aq = 0;
                        e2.ap = 13;
                        e2.ar = 1;
                        e2.r = true;
                        e2.E = 0.8f;
                        e2.W = 80.0f;
                        e2.V = 80.0f;
                        e2.P = -com.corrodinggames.rts.gameFramework.GameUtils.cosFast(this.cg) * 0.1f;
                        e2.Q = -com.corrodinggames.rts.gameFramework.GameUtils.sinFast(this.cg) * 0.1f;
                        e2.Y = com.corrodinggames.rts.gameFramework.GameUtils.c(-180.0f, 180.0f);
                    }
                }
            }
        }
    }
}
