/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer$1;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer$2;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer$3;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer$4;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer$5;
import com.corrodinggames.rts.gameFramework.ui.UnitRenderer$6;
import com.corrodinggames.rts.gameFramework.GlobalState;
import java.util.ArrayList;

abstract class UnitRenderer {
    static UnitRenderer a = new UnitRenderer$1();
    static UnitRenderer b = new UnitRenderer$2();
    static UnitRenderer c = new UnitRenderer$3();
    static UnitRenderer d = new UnitRenderer$4();
    static UnitRenderer e = new UnitRenderer$5();
    static UnitRenderer f = new UnitRenderer$6();

    void al() {
    }



    UnitRenderer() {
    }



    public abstract boolean a(UnitType var1);



    public static void a(ArrayList arrayList, UnitRenderer al2, UnitRenderer al3) {
        UnitType y2;
        UnitType y3;
        GlobalState l2 = GlobalState.B();
        if (l2.bS.q() != 1) {
            arrayList.clear();
        }
        if ((y3 = l2.bS.t()) != null) {
            if (al2.a(y3) || al3 != null && al3.a(y3)) {
                if (!arrayList.contains(y3)) {
                    arrayList.add(y3);
                }
            } else {
                arrayList.clear();
            }
        }
        if ((y2 = UnitRenderer.a(arrayList, al2)) == null && al3 != null) {
            y2 = UnitRenderer.a(arrayList, al3);
        }
        if (y2 == null) {
            arrayList.clear();
            if (y3 != null) {
                arrayList.add(y3);
            }
            if ((y2 = UnitRenderer.a(arrayList, al2)) == null && al3 != null) {
                y2 = UnitRenderer.a(arrayList, al3);
            }
        }
        if (y2 != null) {
            l2.bS.y();
            l2.bS.j(y2);
            l2.b(y2.eo, y2.ep);
            arrayList.add(y2);
        }
    }



    public static UnitType a(ArrayList arrayList, UnitRenderer al2) {
        GlobalState l2 = GlobalState.B();
        UnitType y2 = null;
        float f2 = -1.0f;
        for (UnitInstance am2 : UnitInstance.bE) {  // 02b units/am L?: bE 单位注册表 (v19.133f4 修正)
            UnitType y3;
            if (!(am2 instanceof UnitType) || !l2.bS.m(y3 = (UnitType)am2) || !al2.a(y3) || arrayList.contains(y3)) continue;
            float f3 = com.corrodinggames.rts.gameFramework.GameUtils.a(l2.cy + l2.cI, l2.cz + l2.cJ, y3.eo, y3.ep);
            if (y2 != null && !(f3 < f2)) continue;
            f2 = f3;
            y2 = y3;
        }
        return y2;
    }
}
