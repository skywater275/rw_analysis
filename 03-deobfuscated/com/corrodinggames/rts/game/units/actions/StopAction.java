/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.gameFramework.ui.ActionPanel;
import com.corrodinggames.rts.gameFramework.network.DebugPacketBuilder;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.io.IOException;

public class StopAction
extends GameAction {
    public boolean a;

    public StopAction(boolean bl) {
        super("c_5");
        this.g = -9990.0f;
        this.a = bl;
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    public int getResourceCost() {
        return 0;
    }


    public UnitTypeHandle n() {
        return null;
    }


    public UnitTypeHandle i() {
        return null;
    }

    public ActionTargetType e() {
        return ActionTargetType.i;
    }


    public ActionCategory f() {
        return ActionCategory.g;
    }


    public boolean g() {
        return false;
    }

    public com.corrodinggames.rts.game.units.UnitType K() {
        GlobalState l2 = GlobalState.B();
        UnitInstance[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2 instanceof com.corrodinggames.rts.game.units.UnitType)) continue;
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType)am2;
            if (!y2.cG) continue;
            return y2;
        }
        return null;
    }

    public boolean isEditorOrSpectator() {
        GlobalState l2 = GlobalState.B();
        com.corrodinggames.rts.game.units.UnitType y2 = this.K();
        if (y2 != null) {
            if (((Object)y2) instanceof ActionWrapper) {
                return true;
            }
            return l2.bs == y2.player;
        }
        return false;
    }


    public String d() {
        String string = "UnitInfo";
        GlobalState l2 = GlobalState.B();
        com.corrodinggames.rts.game.units.UnitType y2 = this.K();
        if (y2 != null) {
            if (((Object)y2) instanceof ActionWrapper) {
                return "Editor";
            }
            if (!this.a) {
                string = l2.bS.g.a((UnitInstance) y2, false);
            } else {
                PlayerState n2 = y2.player;
                string = l2.bS.g.a(n2);
            }
        }
        return string;
    }

    @Override
    public String getDescription() {
        if (this.a) {
            return "";
        }
        com.corrodinggames.rts.game.units.UnitType var1 = this.K();
        if (var1 != null) {
            GlobalState l2 = GlobalState.B();
            return l2.bS.g.a((UnitInstance) var1, false);
        }
        return "";
    }

    @Override
    public boolean h_() {
        return true;
    }

    @Override
    public String getLabel() {
        return "UnitInfo";
    }


    public String d(UnitInstance am2) {
        if (this.a) {
            return "";
        }
        if (am2 != null) {
            return am2.r().e();
        }
        return "UnitInfo";
    }

    @Override
    public boolean s() {
        if (this.a) {
            return !this.isEditorOrSpectator();
        }
        return true;
    }

    @Override
    public boolean u() {
        return !this.a;
    }

    @Override
    public boolean C() {
        return true;
    }

    public boolean isAggregateAction() {
        return true;
    }
}
