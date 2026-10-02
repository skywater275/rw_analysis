/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;
import com.corrodinggames.rts.gameFramework.ui.StatsPanel;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class ActionCooldown {
    UnitInstance a;
    GameAction b;
    float c;
    boolean d;
    boolean e;
    static CustomArrayList f = new CustomArrayList();

    void c() {
    }

    public static void a(UnitInstance am2, GameAction s2, boolean bl, boolean bl2) {
        ActionCooldown c2 = ActionCooldown.a(am2, s2, bl2);
        if (c2 == null) {
            c2 = new ActionCooldown();
            f.add(c2);
        }
        c2.a = am2;
        c2.b = s2;
        c2.c = 10.0f;
        c2.d = bl;
        c2.e = bl2;
    }

    public static ActionCooldown a(UnitInstance am2, GameAction s2, boolean bl) {
        for (ActionCooldown c2 : (java.util.Collection<ActionCooldown>) (java.util.Collection) f) {
            if (c2.a != am2 || c2.b != s2 || c2.e != bl) continue;
            return c2;
        }
        return null;
    }

    public static float b(UnitInstance am2, GameAction s2, boolean bl) {
        ActionCooldown c2 = ActionCooldown.a(am2, s2, bl);
        if (c2 != null) {
            float f = c2.c / 10.0f;
            if (c2.d) {
                f = -f;
            }
            return f;
        }
        return 0.0f;
    }

    public static void a(float f) {
        for (int i = ActionCooldown.f.size() - 1; i >= 0; --i) {
            ActionCooldown c2 = (ActionCooldown) ActionCooldown.f.get(i);
            c2.c -= f;
            if (!(c2.c <= 0.0f)) continue;
            ActionCooldown.f.remove(i);
        }
    }
}
