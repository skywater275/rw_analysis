/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import java.util.Comparator;

strictfp final class Factory$15
implements Comparator {
    Factory$15() {
    }

    public int a(UnitTypeHandle as2, UnitTypeHandle as3) {
        Boolean bl;
        Boolean bl2;
        Boolean bl3;
        UnitInstance am2 = UnitInstance.isRenderable(as2);
        UnitInstance am3 = UnitInstance.isRenderable(as3);
        Boolean bl4 = am2.canAttackAir();
        int n2 = bl4.compareTo(bl3 = Boolean.valueOf(am3.canAttackAir()));
        if (n2 != 0) {
            return n2;
        }
        Boolean bl5 = as2.j();
        n2 = bl5.compareTo(bl2 = Boolean.valueOf(as3.j()));
        if (n2 != 0) {
            return n2;
        }
        Boolean bl6 = am2.hasSpawnedDeathEffect();
        n2 = bl6.compareTo(bl = Boolean.valueOf(am3.hasSpawnedDeathEffect()));
        if (n2 != 0) {
            return n2;
        }
        CustomActionBase b2 = as2.u();
        CustomActionBase b3 = as3.u();
        CustomActionBase b4 = as2.B();
        CustomActionBase b5 = as3.B();
        if (b4 != null) {
            b2 = CustomActionBase.a(b2, b4);
        }
        if (b5 != null) {
            b3 = CustomActionBase.a(b3, b5);
        }
        if ((n2 = b2.a(b3)) != 0) {
            return n2;
        }
        return 0;
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((UnitTypeHandle) object, (UnitTypeHandle) object2);
    }
}

