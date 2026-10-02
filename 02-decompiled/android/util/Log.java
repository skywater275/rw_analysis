/*
 * Decompiled with CFR 0.152.
 */
package android.util;

import android.util.Log$1;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Log {
    private static final ThreadLocal a = new Log$1();

    private Log() {
    }

    public static int a(String string, String string2) {
        return Log.a(0, 2, string, string2);
    }

    public static int b(String string, String string2) {
        return Log.a(0, 3, string, string2);
    }

    public static int c(String string, String string2) {
        return Log.a(0, 5, string, string2);
    }

    public static int a(String string, String string2, Throwable throwable) {
        return Log.a(0, 5, string, string2 + '\n' + Log.a(throwable));
    }

    public static native boolean isLoggable(String var0, int var1);

    public static int d(String string, String string2) {
        return Log.a(0, 6, string, string2);
    }

    public static int b(String string, String string2, Throwable throwable) {
        return Log.a(0, 6, string, string2 + '\n' + Log.a(throwable));
    }

    public static int c(String string, String string2, Throwable throwable) {
        return Log.a(0, string, string2, throwable, false);
    }

    static int a(int n, String string, String string2, Throwable throwable, boolean bl) {
        throw new RuntimeException("removed");
    }

    public static String a(Throwable throwable) {
        StackTraceElement[] stackTraceElementArray;
        StringWriter stringWriter = new StringWriter();
        for (StackTraceElement stackTraceElement : stackTraceElementArray = new Throwable().getStackTrace()) {
            stringWriter.write(stackTraceElement.toString() + "\n");
        }
        return stringWriter.toString();
    }

    public static int a(int n, int n2, String string, String string2) {
        Log.a(n, string, string2);
        return 0;
    }

    public static int a(int n, String string, String string2) {
        String string3 = ((SimpleDateFormat)a.get()).format(new Date());
        System.out.println(string3 + ": " + string2);
        return 0;
    }
}
