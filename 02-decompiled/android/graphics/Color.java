/*
 * Decompiled with CFR 0.152.
 */
package android.graphics;

import java.util.HashMap;
import java.util.Locale;

public class Color {
    private static final HashMap a = new HashMap();

    public static int a(int n) {
        return n >>> 24;
    }

    public static int b(int n) {
        return n >> 16 & 0xFF;
    }

    public static int c(int n) {
        return n >> 8 & 0xFF;
    }

    public static int d(int n) {
        return n & 0xFF;
    }

    public static int a(int n, int n2, int n3) {
        return 0xFF000000 | n << 16 | n2 << 8 | n3;
    }

    public static int a(int n, int n2, int n3, int n4) {
        return n << 24 | n2 << 16 | n3 << 8 | n4;
    }

    public static int a(String string) {
        if (string.charAt(0) == '#') {
            long l = Long.parseLong(string.substring(1), 16);
            if (string.length() == 7) {
                l |= 0xFFFFFFFFFF000000L;
            } else if (string.length() != 9) {
                throw new IllegalArgumentException("Unknown color");
            }
            return (int)l;
        }
        Integer n = (Integer)a.get(string.toLowerCase(Locale.ROOT));
        if (n != null) {
            return n;
        }
        throw new IllegalArgumentException("Unknown color");
    }

    static {
        a.put("black", -16777216);
        a.put("darkgray", -12303292);
        a.put("gray", -7829368);
        a.put("lightgray", -3355444);
        a.put("white", -1);
        a.put("red", -65536);
        a.put("green", -16711936);
        a.put("blue", -16776961);
        a.put("yellow", -256);
        a.put("cyan", -16711681);
        a.put("magenta", -65281);
        a.put("aqua", -16711681);
        a.put("fuchsia", -65281);
        a.put("darkgrey", -12303292);
        a.put("grey", -7829368);
        a.put("lightgrey", -3355444);
        a.put("lime", -16711936);
        a.put("maroon", -8388608);
        a.put("navy", -16777088);
        a.put("olive", -8355840);
        a.put("purple", -8388480);
        a.put("silver", -4144960);
        a.put("teal", -16744320);
    }
}
