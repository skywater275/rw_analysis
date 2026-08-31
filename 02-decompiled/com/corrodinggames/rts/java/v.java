/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.ai;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;

public class v
extends ai {
    @Override
    public boolean a(int n, int n2, boolean bl) {
        return super.a(n, n2, bl);
    }

    @Override
    public boolean a(int n, int n2) {
        return false;
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public float b(int n, int n2) {
        return 0.0f;
    }

    @Override
    public String c(int n2, int n3) {
        return l.j(n3) + SlickToAndroidKeycodes.a(n2);
    }
}
