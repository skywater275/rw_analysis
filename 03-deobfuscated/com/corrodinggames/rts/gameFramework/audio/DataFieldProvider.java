/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.audio;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import java.util.ArrayList;
public enum DataFieldProvider {
    a("none"),
    b("income"),
    c("armyValue"),
    d("buildingValue"),
    e("totalValue"),
    f("credits");

    private DataFieldProvider(String string) {
    }


    public int a(PlayerState n2) {
        switch (this) {
            default: {
                return 0;
            }
            case b: {
                int n3 = n2.getScaledTotalUnits();
                ArrayList arrayList = com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.f();
                for (LogicBoolean a2 : (java.util.Collection<LogicBoolean>) (java.util.Collection) arrayList) {  // 02b g/f L33-38: custom/e/a (LogicBoolean)
                    if (a2.d()) {
                        float f2 = a2.b();
                        if (f2 != 0.0f) {
                            n3 = (int)((float)n3 + f2 * (float)n2.b(a2));
                        }
                    }
                }
                return n3;
            }
            case c: {
                return n2.T.n;
            }
            case d: {
                return n2.T.o;
            }
            case e: {
                return n2.T.n + n2.T.o;
            }
            case f: 
        }
        return (int)n2.o;
    }
}
