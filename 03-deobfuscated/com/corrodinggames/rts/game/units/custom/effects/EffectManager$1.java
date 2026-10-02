/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects;

import com.corrodinggames.rts.game.units.custom.effects.DataValue;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import java.util.Comparator;

strictfp class EffectManager$1
implements Comparator {
    final /* synthetic */ EffectManager a;

    EffectManager$1(EffectManager f2) {
        this.a = f2;
    }

    public int a(DataValue e2, DataValue e3) {  // 02b f$1: a(e,e), 按元素 resourceTypeRef.x 排序
        if (e2.resourceTypeRef == null || e3.resourceTypeRef == null) {
            return 0;
        }
        return Float.compare(e2.resourceTypeRef.x, e3.resourceTypeRef.x);
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((DataValue)object, (DataValue)object2);
    }
}
