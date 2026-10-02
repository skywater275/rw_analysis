/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.core;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;

import com.corrodinggames.rts.gameFramework.core.FilePickerCallback;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;
import java.io.File;

public class PerformanceMonitor {
    static PlatformBackend a = new PlatformBackend();

    public static String a() {
        return a.a();
    }

    public static boolean b() {
        return a.b();
    }

    public static void a(File file) {
        a.a(file);
    }

    public static void a(FilePickerCallback b2) {
        a.a(b2);
    }

    public static float c() {
        return a.c();
    }
}
