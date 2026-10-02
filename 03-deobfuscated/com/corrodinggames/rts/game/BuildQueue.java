/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.UnitManager;

public class BuildQueue {
    public static final UnitManager[] a = new UnitManager[0];
    UnitManager[] b = a;
    int c = 0;

    public boolean a(UnitManager p2) {  // 02b t.a(p): 追加 UnitManager
        UnitManager[] pArray = this.b;
        int n2 = this.c;
        if (n2 == pArray.length) {
            UnitManager[] pArray2 = new UnitManager[n2 + (n2 < 6 ? 12 : n2 >> 1)];
            System.arraycopy(pArray, 0, pArray2, 0, n2);
            pArray = pArray2;
            this.b = pArray2;
        }
        pArray[n2] = p2;
        this.c = n2 + 1;
        return true;
    }
}
