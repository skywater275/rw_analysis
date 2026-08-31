/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.af;
import com.corrodinggames.rts.gameFramework.utility.ag;

public class ae {
    static Object a = new Object();
    static ag b = new ag();
    static af c;

    public static boolean a() {
        return GlobalState.at();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static af a(String string) {
        if (ag.i(string)) {
            return b;
        }
        if (ae.a() && (string.contains(".[saflink]/") || string.contains(".[saflink]\\") || string.endsWith(".[saflink]"))) {
            if (c == null) {
                Object object = a;
                synchronized (object) {
                    if (c == null) {
                        c = new ag();
                    }
                }
            }
            return c;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static af b(String string) {
        if (ae.a() && (string.contains(".[saflink]/") || string.contains(".[saflink]\\") || string.endsWith(".[saflink]"))) {
            if (c == null) {
                Object object = a;
                synchronized (object) {
                    if (c == null) {
                        c = new ag();
                    }
                }
            }
            return c;
        }
        return null;
    }

    public static void c(String string) {
        if (b != null && ag.i(string)) {
            b.k(string);
        }
    }

    public static void b() {
        if (b != null) {
            b.a();
        }
        if (c != null) {
            c.a();
        }
    }
}
