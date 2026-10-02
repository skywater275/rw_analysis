/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.actions;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionCondition;

import com.corrodinggames.rts.game.units.actions.UnitActionBase;
import com.corrodinggames.rts.game.units.UnitInstance;

public class b
extends UnitActionBase {
    public UnitActionBase bindingKey;
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase actionReference;
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase d;

    public b(UnitActionBase a2) {
        this.bindingKey = a2;
    }


    public boolean b(UnitInstance am2) {
        return this.bindingKey.isAffordable(am2);
    }


    public String c(UnitInstance am2) {
        return this.bindingKey.getDisabledReason(am2);
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return this.bindingKey.a(am2, bl);
    }


    public boolean d(UnitInstance am2) {
        return this.bindingKey.isBlocked(am2);
    }


    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase a() {
        if (this.actionReference != null) {
            return this.actionReference;
        }
        return this.bindingKey.a();
    }


    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase b() {
        if (this.d != null) {
            return this.d;
        }
        return this.bindingKey.b();
    }


    public void a(UnitInstance am2, UnitInstance am3) {
        this.bindingKey.a(am2, am3);
    }
}
