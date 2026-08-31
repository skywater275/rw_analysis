/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.StatsPanel;
import com.corrodinggames.rts.gameFramework.GameObjectComparator;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.effects.EffectManager;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.ao;
import com.corrodinggames.rts.gameFramework.ui.BridgeUnit;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class UnitStateTracker {
    static CustomArrayList a = new CustomArrayList();
    static final BridgeUnit b = new BridgeUnit();

    public static ao a(long l) {
        Object[] objectArray = a.a();
        for (int i = UnitStateTracker.a.a - 1; i >= 0; --i) {
            ao ao2 = (ao)objectArray[i];
            if (ao2.a != l) continue;
            return ao2;
        }
        return null;
    }

    public static ao a(UnitInstance am2) {
        long l2 = am2.eh;
        ao ao2 = UnitStateTracker.a(l2);
        if (ao2 == null) {
            ao2 = new ao();
            ao2.a = am2.eh;
            ao2.b = am2.cE;
            ao2.c = am2.cF;
            ao2.d = GlobalState.B().bX.X;
            a.add(ao2);
        }
        return ao2;
    }

    public static void a(UnitInstance am2, ResourceComponent b2) {
        if (!GlobalState.B().bX.B) {
            return;
        }
        ao ao2 = UnitStateTracker.a(am2);
        ao2.b += b2.ammo;
        ao2.c = b2.hasZeroCost(ao2.c);
        if (!b2.customResources.c()) {
            ao2.e = com.corrodinggames.rts.game.units.custom.effects.EffectManager.b(ao2.e, b2.customResources);
        }
    }

    public static void b(UnitInstance am2, ResourceComponent b2) {
        if (!GlobalState.B().bX.B) {
            return;
        }
        ao ao2 = UnitStateTracker.a(am2);
        ao2.b -= b2.ammo;
        ao2.c = b2.hasZeroCost(ao2.c);
        if (!b2.customResources.c()) {
            ao2.e = com.corrodinggames.rts.game.units.custom.effects.EffectManager.a(ao2.e, b2.customResources);
        }
        if (UnitStateTracker.a.a > 0) {
            // empty if block
        }
    }

    public static boolean c(UnitInstance am2, ResourceComponent b2) {
        ao ao2 = UnitStateTracker.a(am2.eh);
        if (ao2 != null) {
            UnitStateTracker.b.player = am2.player;
            UnitStateTracker.b.cE = ao2.b;
            UnitStateTracker.b.cF = ao2.c;
            com.corrodinggames.rts.game.units.custom.effects.EffectManager f2 = b.df();
            b.a(ao2.e);
            boolean bl = b2.isEnabled((UnitInstance)b);
            b.a(f2);
            return bl;
        }
        return b2.isEnabled(am2);
    }

    public static boolean a(LogicBoolean logicBoolean, UnitType y2) {
        ao ao2 = UnitStateTracker.a(y2.eh);
        if (ao2 != null) {
            int n2 = y2.cE;
            int n3 = y2.cF;
            y2.cE = ao2.b;
            y2.cF = ao2.c;
            boolean bl = logicBoolean.read(y2);
            y2.cE = n2;
            y2.cF = n3;
            return bl;
        }
        return logicBoolean.read(y2);
    }

    public static void a() {
        if (UnitStateTracker.a.a > 0) {
            GlobalState.e("LagHiding: clearing: " + UnitStateTracker.a.a);
        }
        a.clear();
    }

    public static void a(UnitType y2, LineBuffer s2) {
        if (a.size() == 0) {
            return;
        }
        int n2 = GlobalState.B().bX.X;
        for (int i2 = a.size() - 1; i2 >= 0; --i2) {
            ao ao2 = (ao)a.get(i2);
            if (ao2.a == y2.eh) {
                a.remove(i2);
                break;
            }
            if (ao2.d >= n2 + 80) continue;
            a.remove(i2);
            break;
        }
    }
}
