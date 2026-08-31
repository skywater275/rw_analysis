/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class BuildQueueAction
extends GameAction {
    UnitTypeHandle a;
    int b = 1;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        BuildQueueAction v2 = (BuildQueueAction) object;
        if (this.b != v2.b) {
            return false;
        }
        if (this.a != v2.a) {
            return false;
        }
        return super.equals(object);
    }

    public BuildQueueAction(UnitTypeHandle as2) {
        this(as2, 1, null);
    }

    public BuildQueueAction(UnitTypeHandle as2, int n2, Integer n3) {
        super("b_" + as2.v());
        UnitTypeHandle as3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.getCreditCost(as2);
        if (as3 != null) {
            as2 = as3;
            this.getDescription("b_" + as2.v());
        }
        if (n2 != 1) {
            this.getDescription(this.N() + "_" + n2);
        }
        this.a = as2;
        this.b = n2;
        if (n3 != null) {
            this.g = n3.intValue();
        }
    }

    @Override
    public UnitTypeHandle i() {
        return this.a;
    }

    @Override
    public UnitTypeHandle y() {
        return this.a;
    }

    @Override
    public int t() {
        return this.b;
    }

    @Override
    public String getDescription() {
        String string = this.i().f();
        boolean bl = false;
        boolean bl2 = true;
        UnitInstance am2 = UnitInstance.c(this.i());
        if (this.b != 1 && am2 instanceof UnitType) {
            ((UnitType) am2).a(this.b);
        }
        string = string + "\n\n" + com.corrodinggames.rts.gameFramework.ui.ActionPanel.a(am2, false, bl, bl2);
        if (this.b != 1 && am2 instanceof UnitType) {
            ((UnitType) am2).a(1);
        }
        return string;
    }

    @Override
    public String getLabel() {
        com.corrodinggames.rts.game.units.UnitTypeHandle as2 = this.i();
        String string = this.i().e();
        if (!(as2 instanceof com.corrodinggames.rts.game.units.custom.ModUnitRegistry)) {
            if (this.t() == 2) {
                string = string + " T-2";
            }
            if (this.t() == 3) {
                string = string + " T-3";
            }
        }
        return string;
    }


    @Override
    public int getResourceCost() {
        return this.B().a();
    }

    @Override
    public CustomActionBase B() {
        CustomActionBase b2 = this.h.isAffordable();
        if (b2 != null) {
            return b2;
        }
        return this.i().d(this.t());
    }

    @Override
    public CustomActionBase getSecondaryResourceComponent() {
        CustomActionBase b2 = this.h.isVisible();
        if (b2 != null) {
            return b2;
        }
        return this.i().B();
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    public ActionTargetType e() {
        return ActionTargetType.b;
    }


    public ActionCategory f() {
        return ActionCategory.e;
    }


    public boolean isBuildable() {
        return !this.i().C();
    }


    public boolean g(UnitInstance am2) {
        GlobalState l2 = GlobalState.B();
        if ((this.i() == UnitRegistry.D || this.i() == UnitRegistry.C) && l2.O() && l2.bX.ay.i) {
            return true;
        }
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }


    public boolean g() {
        return false;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean D() {
        return false;
    }

    @Override
    public float p(UnitInstance am2) {
        if (!(am2 instanceof UnitType)) {
            return -1.0f;
        }
        UnitType y2 = (UnitType) am2;
        UnitInstance am3 = y2.X();
        if (am3 != null && am3.cm < 1.0f && am3.r() == this.i()) {
            return am3.cm;
        }
        return -1.0f;
    }


    public boolean r(UnitInstance am2) {
        return this.h.a(am2, true);
    }

    @Override
    public boolean getLabel(UnitInstance am2) {
        return this.h.a(am2, false);
    }
}
