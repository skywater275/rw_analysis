/*
 * Decompiled with CFR 0.152.
 * 02 原稿: gameFramework/f$a.java (v19.108 重建)
 */
package com.corrodinggames.rts.gameFramework;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

strictfp class GameUtils$a
implements FileFilter {
    GameUtils$a() {
    }

    @Override
    public boolean accept(File file) {
        return file != null && Pattern.matches("cpu[0-9]+", file.getName());
    }
}
