/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.h;
import java.util.Locale;

public final class g {
    protected static h a;

    public static strictfp h a() {
        if (a == null) {
            String string = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            a = string.indexOf("mac") >= 0 || string.indexOf("darwin") >= 0 ? h.b : (string.indexOf("win") >= 0 ? h.a : (string.indexOf("nux") >= 0 ? h.c : h.d));
        }
        return a;
    }
}
