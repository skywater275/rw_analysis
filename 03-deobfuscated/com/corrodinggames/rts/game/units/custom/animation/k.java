/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;
import com.corrodinggames.rts.game.units.custom.RangeValue;

import android.graphics.PointF;
import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.game.units.custom.animation.AnimationCurveBase;
import com.corrodinggames.rts.game.units.custom.ModUnitLoader;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit;
import com.corrodinggames.rts.game.units.UnitType;

public strictfp class k
extends AnimationCurveBase {
    public static final AnimationCurveBase a = new k();  // 02b custom/b/k.java L12: static final a a
    static final PointF b = new PointF();

    @Override
    public void b(CustomUnitType j2, float f2) {
        ModUnitRegistry l2 = j2.x;
        int n2 = j2.bl();
        for (int i2 = 0; i2 < n2; ++i2) {
            ModUnitLoader bn2 = l2.fQ[i2];
            if (bn2.aj != null && j2.cB > 0.0f && !j2.v) {
                float f3 = bn2.aj.floatValue();
                b.a(j2.E(i2));
                float f4 = j2.m();
                if (bn2.ab < 99999.0f) {
                    f4 = bn2.ab;
                }
                if (ExperimentalSubUnit.a((UnitType)j2, k.b.a, k.b.b, j2.eq, f4, f3)) {  // 02b custom/b/k.java L30: units/d/p.a (p=ExperimentalSubUnit)
                    // empty if block
                }
                if (j2.cB < 0.0f) {
                    j2.cB = 0.0f;
                    j2.v = true;
                }
            }
            if (bn2.ak == null) continue;
            k.a(j2, bn2);
        }
    }

    public static void a(CustomUnitType j2, ModUnitLoader bn2) {
        if (!j2.a(bn2)) {
            return;
        }
        float f2 = bn2.al;
        float f3 = bn2.am;
        float f4 = bn2.an;
        MovementController f5 = null;  // 02b custom/b/k.java L52: game.f var5 (f=MovementController)
        UnitConfig h2 = bn2.ak;  // 02b L53: custom.h var6 (h=UnitConfig)
        Object[] objectArray = MovementController.a.a();
        int n2 = MovementController.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            float f6;
            float f7;
            MovementController f8 = (MovementController) objectArray[i2];  // 02b L58: (game.f)var7[var8]
            if (f8.aE == null || !(f8.eq > f4) || !TeamTag.a(f8.aE, h2) || !((f7 = GameUtils.a(j2.eo, j2.ep, f8.eo, f8.ep)) < f3 * f3) || !((f6 = GameUtils.a(j2.eo, j2.ep, f8.n, f8.o)) < f2 * f2) && !(f2 < 0.0f) || f8.j != null && (f8.j.player.d(j2.player) || f8.j.player == j2.player) || f8.h <= 0.0f || k.a(f8)) continue;
            f5 = f8;
        }
        if (f5 != null) {
            j2.b(bn2);
            MovementController f9 = j2.a(null, f5.eo, f5.ep, bn2.e, null, 0);  // 02b L72: game.f var13 = var0.a(...)
            f9.aC = true;
            f9.q = f5;
        }
    }

    public static boolean a(MovementController f2) {  // 02b L80: a(game.f)
        Object[] objectArray = MovementController.a.a();
        int n2 = MovementController.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            MovementController f3 = (MovementController) objectArray[i2];
            if (f3 == f2 || f3.q != f2) continue;
            return true;
        }
        return false;
    }
}
