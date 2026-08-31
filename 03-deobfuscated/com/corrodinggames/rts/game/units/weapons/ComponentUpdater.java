/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.weapons;
import com.corrodinggames.rts.game.units.actions.StopAction;

import com.corrodinggames.rts.game.units.weapons.UnitComponent;
import com.corrodinggames.rts.game.units.weapons.ComponentType;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.io.IOException;

public final class ComponentUpdater {
    public static void a(UnitType y2, float f2) {
        CustomArrayList m2 = y2.bp;
        if (m2 == null) {
            return;
        }
        GlobalState l2 = GlobalState.B();
        int n2 = l2.by;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            UnitComponent a2 = (UnitComponent) objectArray[i2];
            if (a2.a <= n2) {
                m2.remove(i2);
                continue;
            }
            a2.a(y2, f2);
        }
    }

    public static void a(UnitType y2, UnitComponent a2) {
        if (y2.bp == null) {
            y2.bp = new CustomArrayList();
        }
        if (y2.bp.size() > 1000) {
            y2.a("status effect limit reached");
            return;
        }
        y2.bp.add(a2);
    }

    /* 02b g/c.java L45: as2.e/UnitComponent.a 抛 IOException */
    public static void a(UnitType y2, OutputNetStream as2) throws IOException {
        CustomArrayList m2 = y2.bp;
        int n2 = m2 == null ? 0 : m2.size();
        as2.a((short)n2);
        if (n2 == 0) {
            return;
        }
        as2.e("s");
        Object[] objectArray = m2.a();
        for (int i2 = 0; i2 < m2.a; ++i2) {
            UnitComponent a2 = (UnitComponent) objectArray[i2];
            as2.a(a2.b());
            a2.a(y2, as2);
        }
        as2.a("s");
    }

    /* 02b g/c.java L69: 方法体抛 IOException (R8 移除 throws); 调用点 UnitType.deserializeFromStream 已 throws */
    public static void a(UnitType y2, InputNetStream k2) throws IOException {
        int n2 = k2.v();
        if (n2 <= 0) {
            y2.bp = null;
            return;
        }
        if (y2.bp == null) {
            y2.bp = new CustomArrayList();
        } else {
            y2.bp.clear();
        }
        CustomArrayList m2 = y2.bp;
        k2.b("s");
        for (int i2 = 0; i2 < n2; ++i2) {
            ComponentType b2 = (ComponentType) k2.b(ComponentType.class);  // 02b g/c.java L84: (b)var1.b(b.class)
            if (b2 == null) {
                throw new IOException("Unknown status effect type");
            }
            UnitComponent a2 = b2.a();
            a2.a(y2, k2);
            m2.add(a2);
        }
        k2.d("s");
    }
}
