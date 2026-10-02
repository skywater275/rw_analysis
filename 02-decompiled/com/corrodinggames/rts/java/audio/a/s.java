/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.t;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public final class s {
    public static final byte[] a = new byte[0];

    public static void a(InputStream inputStream, OutputStream outputStream) {
        s.a(inputStream, outputStream, new byte[4096]);
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] byArray) {
        int n;
        while ((n = inputStream.read(byArray)) != -1) {
            outputStream.write(byArray, 0, n);
        }
    }

    public static byte[] a(InputStream inputStream, int n) {
        t t2 = new t(Math.max(0, n));
        s.a(inputStream, t2);
        return ((ByteArrayOutputStream)t2).toByteArray();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }
}
