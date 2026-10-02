/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

strictfp class f$a
implements FileFilter {
    f$a() {
    }

    @Override
    public boolean accept(File file) {
        return file != null && Pattern.matches("cpu[0-9]+", file.getName());
    }
}
