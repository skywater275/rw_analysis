/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;

import com.corrodinggames.rts.game.units.custom.effects.DataValue;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import java.util.Comparator;

strictfp class ActionType$1
implements Comparator {
    final /* synthetic */ EffectRenderer a;

    ActionType$1(EffectRenderer f2) {
        this.a = f2;
    }

    public int a(DataValue e2, DataValue e3) {
        if (e2.resourceTypeRef == null || e3.resourceTypeRef == null) {
            return 0;
        }
        return Float.compare(e2.resourceTypeRef.x, e3.resourceTypeRef.x);
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((DataValue) object, (DataValue) object2);
    }
}
