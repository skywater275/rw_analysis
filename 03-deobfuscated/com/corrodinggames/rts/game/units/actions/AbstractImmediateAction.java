/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;

public abstract class AbstractImmediateAction
extends GameAction {
    public AbstractImmediateAction(int n2) {
        super(n2);
    }

    public AbstractImmediateAction(String string) {
        super(string);
    }


    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    public int getResourceCost() {
        return 0;
    }

    public UnitRegistry K() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.a;
    }


    public ActionCategory f() {
        return ActionCategory.a;
    }


    public boolean g() {
        return false;
    }


    public boolean h() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.K();
    }
}
