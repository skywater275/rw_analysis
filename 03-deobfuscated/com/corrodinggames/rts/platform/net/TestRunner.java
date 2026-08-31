/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.platform.net;

import com.corrodinggames.rts.platform.net.TestGameLogic;
import com.corrodinggames.rts.platform.net.TestLogicBoolean;
import com.corrodinggames.rts.platform.net.TestMath;
import com.corrodinggames.rts.platform.net.TestFilesystem;
import com.corrodinggames.rts.platform.net.TestPerformance;
import com.corrodinggames.rts.platform.net.k;
import com.corrodinggames.rts.platform.net.m;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class TestRunner {
    public void a() {
        GlobalState.e("Running unit tests");
        new TestLogicBoolean().a();
        new TestMath().a();
        new k().a();
        new TestFilesystem().a();
        new TestGameLogic().a();
        new TestPerformance().a();
        new m().a();
    }

    public static void a(boolean bl) {
        if (!bl) {
            throw new RuntimeException("Asset failed");
        }
    }

    public static void b(boolean bl) {
        if (bl) {
            throw new RuntimeException("Asset failed");
        }
    }

    public static void a(int n2, int n3) {
        if (n2 != n3) {
            throw new RuntimeException("Asset failed (int):" + n2 + "!=" + n3);
        }
    }

    public static void a(float f2, float f3) {
        if (com.corrodinggames.rts.gameFramework.GameUtils.c(f2 - f3) > 0.001f) {
            throw new RuntimeException("Asset failed (float):" + f2 + "!=" + f3);
        }
    }

    public static void a(String string, String string2) {
        if (!string.equals(string2)) {
            throw new RuntimeException("Asset failed:" + string + "!=" + string2);
        }
    }

    public static void b(String string, String string2) {
        GlobalState.e("assertEqualDebug:'" + string + "' vs '" + string2 + "'");
        TestRunner.a(string, string2);
    }

    public static void c(String string, String string2) {
        Float f2 = Float.valueOf(Float.parseFloat(string));
        Float f3 = Float.valueOf(Float.parseFloat(string2));
        TestRunner.a(f2.floatValue(), f3.floatValue());
    }

    public static void a(Object object, Object object2) {
        if (object != object2) {
            throw new RuntimeException("Asset failed:" + object + "!=" + object2);
        }
    }


    public static void dumpUnitTypes() {
        com.corrodinggames.rts.gameFramework.GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        if (com.corrodinggames.rts.game.units.UnitRegistry.ae == null) {
            l2.b("dumpUnitTypes: ae == null");
            return;
        }
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.units.UnitRegistry.ae.size(); ++i2) {
            com.corrodinggames.rts.game.units.UnitTypeHandle as2 = (com.corrodinggames.rts.game.units.UnitTypeHandle) com.corrodinggames.rts.game.units.UnitRegistry.ae.get(i2);
            com.corrodinggames.rts.game.units.UnitInstance am2 = com.corrodinggames.rts.game.units.UnitInstance.a(as2);
            if (!(am2 instanceof com.corrodinggames.rts.game.units.UnitType)) {
                continue;
            }
            com.corrodinggames.rts.game.units.UnitType y2 = (com.corrodinggames.rts.game.units.UnitType) am2;
            l2.e("DUMP_UNIT:" + as2.i() + " eo=" + y2.eo + " ep=" + y2.ep + " aO=" + y2.aO + " ax=" + y2.ax + " cg=" + y2.cg + " cs=" + y2.cs + " eh=" + y2.eh + " eq=" + y2.eq + " l=" + y2.l);
        }
        l2.e("dumpUnitTypes done");
    }
}
