/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;

strictfp class ActionAddCredits
extends AbstractImmediateAction {
    public ActionAddCredits() {
        super("addCredits");
    }


    @Override
    public String getLabel() {
        return "Add credits";
    }


    @Override
    public String getDescription() {
        return "Add $10000 to this team";
    }


    public boolean h() {
        return true;
    }
}
