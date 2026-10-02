/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bh;
import java.util.ArrayList;

public strictfp class bi
extends ArrayList {
    public int a(int n) {
        if (this.isEmpty()) {
            return 0;
        }
        int n2 = ((bh)this.get((int)0)).b;
        for (bh bh2 : this) {
            if (bh2.a > n) {
                return n2;
            }
            n2 = bh2.b;
        }
        return n2;
    }
}
