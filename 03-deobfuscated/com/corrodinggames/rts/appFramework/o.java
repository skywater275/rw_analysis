/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class o {
    String string;
    int int1 = 0;
    int int2 = 0;
    PlayerState testConfigField1 = null;  // 02b: game/n d (DialogHelper 幻觉名修正)
    PlayerState testConfigField2 = null;  // 02b: game/n e
    int int3;
    int g;

    o(String string) {
        this.string = string;
    }

    public void a(PlayerState n2, int n3) {  // 02b: a(game/n, int)
        if (n3 < this.int1 || this.testConfigField1 == null) {
            this.int1 = n3;
            this.testConfigField1 = n2;
            this.int3 = 1;
        } else if (n3 == this.int1) {
            ++this.int3;
        }
        if (n3 > this.int2 || this.testConfigField2 == null) {
            this.int2 = n3;
            this.testConfigField2 = n2;
            this.g = 1;
        } else if (n3 == this.int2) {
            ++this.g;
        }
    }

    public boolean a() {
        GlobalState l2 = GlobalState.B();
        if (this.int1 == this.int2) {
            return false;
        }
        if (this.testConfigField1 == null && this.testConfigField2 == null) {
            return false;
        }
        if (this.g == 1) {
            String string = "Warning: Uneven map - Player " + (this.testConfigField2.k + 1) + " on team " + this.testConfigField2.h() + ": " + this.string + " is " + this.int2 + " vs " + this.int1;
            l2.bS.h.a(null, string);
            return true;
        }
        String string = "Warning: Uneven map - " + this.g + " players including player " + (this.testConfigField2.k + 1) + " on team " + (this.testConfigField2.r + 1) + ": " + this.string + " is " + this.int2 + " vs " + this.int1;
        l2.bS.h.a(null, string);
        return true;
    }
}
