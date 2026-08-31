/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit;

public strictfp class CustomGroundUnit
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {
    public CustomGroundUnit() {
        super(ExperimentalGroundUnit.h.a());  // 02b d/b.java: super(a.h.a()) (a=units/d/a=ExperimentalGroundUnit)
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/b.java 对应方法
        return "-Allows factory to build Tech 2 units";
    }


    public String getLabel() {  // 02b d/b.java 对应方法
        return com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.actions.upgradeT2", new Object[0]);
    }


    public int getResourceCost() {  // 02b d/b.java 对应方法
        return UnitRegistry.c.c(2);
    }


    public float K() {
        return 4.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalGroundUnit a2 = (ExperimentalGroundUnit) am2;
        if (a2.f != 1 || a2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);  // 02b d/b L37: super.a(var1,var2)
    }

    public UnitRegistry L() {
        return null;
    }


    public com.corrodinggames.rts.game.units.actions.ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.c;  // 02b d/b L46: a.t.c
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}

