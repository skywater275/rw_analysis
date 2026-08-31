/*
 * 02b l/a.java 直译 (设备信息/文件分享门面, 委托 PlatformBackend)
 */
package com.corrodinggames.rts.gameFramework.core;

import java.io.File;

public class PlatformExtension {

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
