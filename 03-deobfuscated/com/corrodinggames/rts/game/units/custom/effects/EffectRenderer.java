/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.DirectionConfig;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.effects.DataValue;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class EffectRenderer {
    public final CustomArrayList a = new CustomArrayList();

    public void a(LogicBoolean a2) {
        if (!this.a.contains(a2)) {
            this.a.add(a2);
        }
    }

    public void a(EffectManager f2, UnitInstance am2, double d) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            double d2 = e2.resourceTypeRef.a(am2);
            if (!(d2 < e2.amountValue * d)) continue;
            this.a(e2.resourceTypeRef);
        }
    }

    public void a(CustomActionBase b2, UnitInstance am2, double d) {
        if (!b2.k.c()) {
            this.a(b2.k, am2, d);
        }
        if (b2.b > 0 && am2.player.o < (double)b2.b * d) {
            this.a(LogicBoolean.D);
        }
    }

    public boolean a(EffectManager f2) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            DataValue e2 = (DataValue) objectArray[i];
            if (!this.a.contains(e2.resourceTypeRef)) continue;
            return true;
        }
        return false;
    }

    public boolean a(CustomActionBase b2) {
        if (b2.b > 0 && this.a.contains(LogicBoolean.D)) {
            return true;
        }
        return !b2.k.c() && this.a(b2.k);
    }

    public void a() {
        this.a.clear();
    }
}
