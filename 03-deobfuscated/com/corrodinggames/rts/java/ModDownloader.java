/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java;

import com.corrodinggames.rts.gameFramework.InputProvider;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.SlickToAndroidKeycodes;

public class ModDownloader
extends com.corrodinggames.rts.gameFramework.InputProvider {

    public boolean a(int n, int n2, boolean bl) {
        return super.a(n, n2, bl);
    }


    public boolean a(int n, int n2) {
        return false;
    }


    public int a() {
        return 0;
    }


    public float b(int n, int n2) {
        return 0.0f;
    }


    public String c(int n2, int n3) {
        return GlobalState.modifierMaskToString(n3) + SlickToAndroidKeycodes.a(n2);
    }
}
