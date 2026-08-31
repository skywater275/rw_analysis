/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.animation;

import com.corrodinggames.rts.game.units.custom.animation.AnimationCurve;
import com.corrodinggames.rts.game.units.custom.animation.AnimationResourceCurve;
import com.corrodinggames.rts.game.units.custom.animation.AnimationActivationCurve;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.ModifierApplier;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public strictfp class AnimationVisibilityCurve
extends ModifierApplier {
    String a;
    CustomArrayList b = new CustomArrayList();  // 02b custom/b/g.java L12: utility.m b

    public AnimationVisibilityCurve(String string) {
        this.a = string;
    }

    @Override
    public void a(ModUnitRegistry l2) throws bo {
        if (this.a != null) {
            String[] stringArray;
            for (String string : stringArray = this.a.split(",")) {
                AnimationResourceCurve d2 = AnimationCurve.b(l2, string = string.trim());
                if (d2 == null) {
                    throw new bo("Failed to find decal: " + string);
                }
                this.b.add(d2);
            }
            this.a = null;
        }
    }

    public void a(CustomUnitType j2, float f2, float f3) {
        AnimationCurve.i.a(f2, f3);  // 02b custom/b/g.java L42: c.i.a (i=PointF)
        AnimationCurve.a(j2, 0.0f, AnimationActivationCurve.f, this.b, AnimationCurve.i);  // 02b L43: c.a(var1,0.0F,f.f,this.b,c.i)
    }
}
