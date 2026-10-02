/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;

import com.corrodinggames.rts.game.units.actions.AbstractCutsceneAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.KeyBinding;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class TeamChatAction
extends AbstractCutsceneAction {
    public TeamChatAction() {
        super("c__cut_chat");
    }


    @Override
    public String getLabel() {
        return "Team Chat";
    }


    @Override
    public String getDescription() {
        return "Send ActionPanel team chat message to your allies";
    }

    @Override
    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        l2.bS.g.n();
        return true;
    }

    @Override
    public KeyBinding M() {
        GlobalState l2 = GlobalState.B();
        return l2.bT.sendTeamChat;
    }
}
