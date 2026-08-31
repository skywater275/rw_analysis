/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.OSEnum;
import java.util.Locale;

public final class FileSystem {
    protected static OSEnum a;

    public static strictfp OSEnum a() {
        if (a == null) {
            String string = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            a = string.indexOf("mac") >= 0 || string.indexOf("darwin") >= 0 ? OSEnum.b : (string.indexOf("win") >= 0 ? OSEnum.a : (string.indexOf("nux") >= 0 ? OSEnum.c : OSEnum.d));
        }
        return a;
    }
}
