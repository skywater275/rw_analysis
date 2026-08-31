/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

public class SellAction
extends GameAction {
    UnitTypeHandle a;
    ArrayList b = new ArrayList();
    int c = 0;
    boolean d;
    UnitType e = null;
    int f;

    public SellAction(UnitTypeHandle as2) {
        super("s_" + as2.v());
        this.g = -9999.0f;
        this.a = as2;
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    @Override
    public int getResourceCost() {
        return 0;
    }

    @Override
    public UnitTypeHandle i() {
        return this.a;
    }


    public ActionTargetType e() {
        return ActionTargetType.i;
    }


    public ActionCategory f() {
        if (GlobalState.at() && !com.corrodinggames.rts.gameFramework.ui.InGameUI.bO) {
            return ActionCategory.h;
        }
        return ActionCategory.g;
    }


    public boolean g() {
        return false;
    }

    @Override
    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (!bl) {
            if (l2.bS.q() == 1) {
                return false;
            }
            boolean bl2 = false;
            for (UnitInstance am3 : UnitInstance.bE) {
                if (!am3.cG || am3.r() == this.a) continue;
                l2.bS.l(am3);
                bl2 = true;
            }
            if (!bl2) {
                return false;
            }
        } else {
            for (UnitInstance am4 : UnitInstance.bE) {
                if (!am4.cG || am4.r() != this.a) continue;
                l2.bS.l(am4);
            }
        }
        return true;
    }

    @Override
    public String getDisplayString() {
        String string = "UnitInfo";
        GlobalState l2 = GlobalState.B();
        if (this.e instanceof Factory) {
            return "Editor";
        }
        string = "" + this.a.e() + " x" + this.c;
        return string;
    }

    @Override
    public String getLabel() {
        return "UnitInfo";
    }

    @Override
    public String getLabelForUnitTarget(UnitInstance am2) {
        if (this.e instanceof Factory) {
            return "Editor";
        }
        return this.a.e();
    }

    @Override
    public boolean h_() {
        return true;
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public String getDescription() {
        String string = "";
        if (this.e instanceof Factory) {
            return "";
        }
        if (this.d) {
            string = "(Left click to exclusively select / Right click to unselect)\n";
        }
        return string + this.a.f();
    }

    public void K() {
        GlobalState l2 = GlobalState.B();
        if (this.f == l2.bS.Y) {
            return;
        }
        this.f = l2.bS.Y;
        this.c = 0;
        this.d = false;
        this.e = null;
        UnitInstance[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG) continue;
            if (y2.r() == this.a) {
                ++this.c;
                if (this.e != null) continue;
                this.e = y2;
                continue;
            }
            this.d = true;
        }
    }


    public float getSortPriority() {
        return this.g - (float)this.c;
    }

    @Override
    public boolean G() {
        return true;
    }

    @Override
    public boolean isAlwaysVisible() {
        return true;
    }
}
