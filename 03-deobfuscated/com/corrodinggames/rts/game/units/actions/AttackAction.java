/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitFlag;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.Command;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class AttackAction
extends GameAction {
    int a;
    UnitFlag b;

    public AttackAction() {
        super("c_7");
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    @Override
    public int getResourceCost() {
        return 0;
    }

    public UnitRegistry n() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.k;
    }


    public ActionCategory f() {
        return ActionCategory.a;
    }


    public boolean g() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Attack Mode";
    }

    @Override
    public String getLabel() {
        UnitFlag a2 = this.q();
        if (a2 != null) {
            return a2.name();
        }
        return "NA";
    }

    @Override
    public boolean h_() {
        return false;
    }


    public void c(UnitInstance am2) {
        GlobalState l2 = GlobalState.B();
        UnitFlag a2 = this.r();
        UnitFlag a3 = this.getDescription(a2);
        PlayerState n2 = null;
        n2 = am2.player;
        Command e2 = l2.cf.b(n2);  // 02b d.java L64: gameFramework/e var6
        for (UnitInstance am3 : UnitInstance.bE) {
            if (!(am3 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am3;
            if (!y2.cG) continue;
            e2.a(y2);
        }
        e2.a(a3);
        this.a = l2.bS.Y;
        this.b = a3;
    }

    public UnitFlag getDescription(UnitFlag a2) {  // 02b d.java L82: a(units.a)
        return a2 == UnitFlag.b ? UnitFlag.e : (a2 == UnitFlag.b ? UnitFlag.f : UnitFlag.b);
    }

    public UnitFlag q() {
        GlobalState l2 = GlobalState.B();
        UnitFlag a2 = this.r();
        this.a = l2.bS.Y;
        this.b = a2;
        return a2;
    }

    public UnitFlag r() {
        GlobalState l2 = GlobalState.B();
        if (this.a == l2.bS.Y && this.b != null) {
            return this.b;
        }
        UnitFlag a2 = null;
        boolean bl = false;
        boolean bl2 = false;
        for (UnitInstance am2 : UnitInstance.bE) {
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG) continue;
            if (a2 == null || a2 == y2.P) {
                a2 = y2.P;
                continue;
            }
            a2 = UnitFlag.g;  // 02b d.java L110: units.a.g
        }
        return a2;
    }

    @Override
    public boolean getLabel(UnitInstance am2) {
        return true;
    }


    public String d() {
        return this.getLabel();
    }

    @Override
    public boolean s() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.n();
    }
}
