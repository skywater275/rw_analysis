/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform.net;

import com.corrodinggames.rts.platform.net.TestCase;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ExtraManager;

public class m
extends TestCase {
    public void a() {
        com.corrodinggames.rts.gameFramework.GlobalState.e("Unit Reference tests");
        com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.b;
        com.corrodinggames.rts.game.units.custom.CustomUnitType j2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j2.b(com.corrodinggames.rts.game.PlayerState.i);
        com.corrodinggames.rts.game.units.custom.CustomUnitType j3 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j3.b(com.corrodinggames.rts.game.PlayerState.i);
        j3.eo = 2.0f;
        com.corrodinggames.rts.game.units.custom.CustomUnitType j4 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j4.b(com.corrodinggames.rts.game.PlayerState.i);
        j4.eo = 3.0f;
        com.corrodinggames.rts.game.units.custom.CustomUnitType j5 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j5.b(com.corrodinggames.rts.game.PlayerState.i);
        j5.eo = 3.0f;
        j3.C(j4);
        j3.C(j5);
        com.corrodinggames.rts.game.units.custom.CustomUnitType j6 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j6.b(com.corrodinggames.rts.game.PlayerState.i);
        com.corrodinggames.rts.game.units.custom.CustomUnitType j7 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j7.b(com.corrodinggames.rts.game.PlayerState.i);
        com.corrodinggames.rts.game.units.custom.CustomUnitType j8 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.a(false, l2);
        j8.b(com.corrodinggames.rts.game.PlayerState.i);
        j2.bu = j6;
        j6.bv = j7;
        j4.bv = j7;
        j3.bu = j8;
        int n2 = 2;
        com.corrodinggames.rts.gameFramework.GlobalState.e("=== unit reference tests == (runs:" + n2 + ")");
        Long l3 = ExtraManager.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.custom.CustomUnitType j9 = j2;
            this.a(j9, this.a("self"), j9);
            this.a(j9, this.a("self.parent"), null);
            this.a("self.unknown", true);
            this.a(j9, this.a("self.parent"), null);
            this.a(j9, this.a("nullUnit"), null);
            this.a(j9, this.a("self.customTarget1"), j6);
            this.a(j9, this.a("self.customTarget1.customTarget2"), j7);
            this.a(j9, this.a("self.customTarget2"), null);
            this.a(j9, this.a("self.nullUnit"), null);
            this.a(j9, this.a("nullUnit.nullUnit"), null);
            this.a(j4, this.a("self.parent.customTarget1"), j8);
            this.a(j3, this.a("self.transporting(slot=0)"), j4);
            this.a(j3, this.a("self.transporting(SLOT=0)"), j4);
            this.a("self.transporting(MISS=0)", true);
            this.a(j3, this.a("self.transporting(slot=3)"), null);
            this.a(j3, this.a("self.transporting"), j4);
            this.a(j3, this.a("self.transporting(slot=0).customTarget2"), j7);
            this.a(j3, this.a("self.self.transporting(slot=0).customTarget2"), j7);
            this.a(j3, this.a("self.SELF.TRANsporting(slot=0).customTarget2"), j7);
            this.a(j3, this.a("self.SELF.transporting(slot=0).customTarget2"), j7);
            this.a(j9, this.a("self.nearestUnit(withinRange=500, withTag='test', relation='any')"));
            this.a("", true);
        }
        Long l4 = ExtraManager.a();
        double d2 = ExtraManager.a(l3, (long)l4);
        com.corrodinggames.rts.gameFramework.GlobalState.e("Took: " + d2);
    }

    public void a(String string, boolean bl2) {
        try {
            com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.b;
            UnitReference unitReference = UnitReference.parseSingleUnitReferenceBlock(l2, string);
        }
        catch (RuntimeException runtimeException) {
            if (runtimeException.getClass() != RuntimeException.class && runtimeException.getClass() != BooleanParseException.class) {
                throw new RuntimeException(runtimeException);
            }
            if (bl2) {
                com.corrodinggames.rts.gameFramework.GlobalState.e("(debug)assertCreateError: " + string + " expected-error:" + runtimeException.getMessage());
            }
            return;
        }
        throw new RuntimeException("assertCreateError got no error for: " + string);
    }

    public UnitReference a(String string) {
        try {
            com.corrodinggames.rts.game.units.custom.ModUnitRegistry l2 = com.corrodinggames.rts.game.units.custom.ModUnitRegistry.b;
            UnitReference unitReference = UnitReference.parseSingleUnitReferenceBlock(l2, string);
            if (unitReference == null) {
                throw new RuntimeException("Null when parsing [" + string + "]");
            }
            return unitReference;
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException("Error: " + runtimeException.getMessage() + " parsing [" + string + "]", runtimeException);
        }
    }

    public void a(com.corrodinggames.rts.game.units.UnitType y2, UnitReference unitReference, UnitInstance am2) {
        UnitInstance am3 = unitReference.get(y2);
        if (am3 != am2) {
            throw new RuntimeException("assertSame type expected:" + UnitInstance.A(am2) + " got: " + UnitInstance.A(am3));
        }
    }

    public void a(com.corrodinggames.rts.game.units.UnitType y2, UnitReference unitReference) {
        UnitInstance am2 = unitReference.get(y2);
    }
}
