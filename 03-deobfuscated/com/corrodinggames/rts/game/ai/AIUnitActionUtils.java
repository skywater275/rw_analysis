/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.ai;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.actions.StopAction;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.WeaponTypeEnum;
import com.corrodinggames.rts.game.units.custom.actions.AutoFireMode;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.UnitType;
import java.util.AbstractList;
import java.util.ArrayList;

public class AIUnitActionUtils {  // 02b game/a/f.java (75 行真源) — v19.133f8 整写对照
    static boolean getRandomElement(UnitType y2) {  // 02b f.java L13-24: a(y)
        WeaponAction au2;
        boolean bl = false;
        if (y2.aq()) {
            bl = true;
        }
        if (!bl && (au2 = y2.ar()) != null && au2.d() == WeaponTypeEnum.g) {  // 02b L22: av.g
            bl = true;
        }
        return bl;
    }

    static boolean b(UnitType y2) {  // 02b f.java L26-34: b(y)
        boolean bl = false;
        if (y2.aq()) {
            bl = true;
        }
        return bl;
    }

    public static Object getRandomElement(AbstractList abstractList) {  // 02b f.java L36-41: a(AbstractList)
        int n2 = abstractList.size();
        if (n2 == 0) {
            return null;
        }
        return abstractList.get(com.corrodinggames.rts.gameFramework.GameUtils.a(0, n2 - 1));
    }

    public static boolean getRandomElement(UnitType y2, TeamTag g2) {  // 02b f.java L43-55: a(y,custom.g)
        UnitConfig h2;
        UnitTypeHandle as2 = y2.r();
        return as2 instanceof ModUnitRegistry && TeamTag.a(g2, h2 = ((ModUnitRegistry) as2).fv);  // 02b L47-52: custom.l.fv + g.a(g,h)
    }

    public static GameAction getRandomElement(AIStrategy a2, UnitType y2, AutoFireMode e2) {  // 02b f.java L57-74: a(a,y,custom.a.e)
        ArrayList arrayList = y2.N();
        ArrayList arrayList2 = a2.ap();
        for (GameAction s2 : (java.util.Collection<GameAction>) (java.util.Collection) arrayList) {
            if (s2.v(y2) != e2) continue;  // 02b L64: s.v(y)==var2
            arrayList2.add(s2);
        }
        if (arrayList2.size() > 0) {
            return (GameAction) getRandomElement((AbstractList) arrayList2);  // 02b L70: a((AbstractList)var4)
        }
        return null;
    }
}
