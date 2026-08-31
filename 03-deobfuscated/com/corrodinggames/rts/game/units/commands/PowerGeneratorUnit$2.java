/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.PowerGeneratorUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class PowerGeneratorUnit$2
extends AbstractBuildAction {
    PowerGeneratorUnit$2(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/h$2.java 对应方法
        return Localization.a("units.fabricator.upgrade.descriptionT3", new Object[0]);
    }


    public String getLabel() {  // 02b d/h$2.java 对应方法
        return Localization.a("units.fabricator.upgrade.nameT3", new Object[0]);
    }


    public int getResourceCost() {  // 02b c() = getResourceCost
        return UnitRegistry.J.c(3);
    }

    @Override
    public float K() {
        return 2.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        PowerGeneratorUnit h2 = (PowerGeneratorUnit) am2;
        if (h2.r != 2 || h2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        PowerGeneratorUnit h2 = (PowerGeneratorUnit) am2;
        return h2.r == 2;
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return ActionCategory.c;
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
