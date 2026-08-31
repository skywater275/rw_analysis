/*
 * Decompiled with CFR 0.152.
 */
package com.a.a.a;

public class a {
    private static Object[] a = new Object[0];
    private static Object[] b = new Object[73];

    public static int a(int n) {
        for (int i = 4; i < 32; ++i) {
            if (n > (1 << i) - 12) continue;
            return (1 << i) - 12;
        }
        return n;
    }

    public static int b(int n) {
        return com.a.a.a.a.a(n * 2) / 2;
    }

    public static int c(int n) {
        return com.a.a.a.a.a(n * 4) / 4;
    }
}
