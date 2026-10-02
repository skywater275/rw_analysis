/*
 * Decompiled with CFR 0.152.
 */
package android.graphics;

import com.a.a.a.a;

public class TemporaryBuffer {
    private static char[] a = null;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static char[] a(int n) {
        Class<TemporaryBuffer> clazz = TemporaryBuffer.class;
        synchronized (TemporaryBuffer.class) {
            char[] cArray = a;
            a = null;
            // ** MonitorExit[var2_1] (shouldn't be in output)
            if (cArray == null || cArray.length < n) {
                cArray = new char[com.a.a.a.a.b(n)];
            }
            return cArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void a(char[] cArray) {
        if (cArray.length > 1000) {
            return;
        }
        Class<TemporaryBuffer> clazz = TemporaryBuffer.class;
        synchronized (TemporaryBuffer.class) {
            a = cArray;
            // ** MonitorExit[var1_1] (shouldn't be in output)
            return;
        }
    }
}
