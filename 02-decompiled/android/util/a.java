/*
 * Decompiled with CFR 0.152.
 */
package android.util;

class a {
    static final boolean[] a = new boolean[0];
    static final int[] b = new int[0];
    static final long[] c = new long[0];
    static final Object[] d = new Object[0];

    static int a(int[] nArray, int n, int n2) {
        int n3 = 0;
        int n4 = n - 1;
        while (n3 <= n4) {
            int n5 = n3 + n4 >>> 1;
            int n6 = nArray[n5];
            if (n6 < n2) {
                n3 = n5 + 1;
                continue;
            }
            if (n6 > n2) {
                n4 = n5 - 1;
                continue;
            }
            return n5;
        }
        return ~n3;
    }
}
