/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.GlobalState;

public class InputProvider {
    public boolean a(int n2, int n3, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (!l2.a(n3, bl)) {
            return false;
        }
        return l2.h(n2);
    }

    public boolean a(int n2, int n3) {
        return false;
    }

    public int a() {
        return 0;
    }

    public float b(int n2, int n3) {
        return 0.0f;
    }

    public String c(int n2, int n3) {
        return "<abstract>";
    }
}
