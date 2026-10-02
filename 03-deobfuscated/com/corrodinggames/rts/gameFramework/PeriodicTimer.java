/*
 * Decompiled with CFR 0.152.
 * v19.117: 02b bl.java 直译 (GameRenderer 为幻觉名, am=UnitInstance; a 字段 utility.m=CustomArrayList)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.StatsCallback;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public strictfp class PeriodicTimer {
    CustomArrayList a = new CustomArrayList();  // 02b bl.java L8: utility.m a (WorldGenerator 为幻觉名)

    public void a(UnitInstance am2, UnitInstance am3) {  // 02b bl.java L11: a(am,am)
        if (this.a.a > 0) {
            java.util.Iterator iterator = this.a.iterator();
            while (iterator.hasNext()) {
                StatsCallback bk2 = (StatsCallback) iterator.next();
                bk2.a(am2, am3, null);
            }
        }
    }
}
