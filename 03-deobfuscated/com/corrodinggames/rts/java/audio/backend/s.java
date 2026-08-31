/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.java.audio.backend;

import com.corrodinggames.rts.java.audio.backend.t;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class s {
    public static final byte[] a = new byte[0];

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        a(inputStream, outputStream, new byte[4096]);
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] byArray) throws IOException {
        int n;
        while ((n = inputStream.read(byArray)) != -1) {
            outputStream.write(byArray, 0, n);
        }
    }

    public static byte[] a(InputStream inputStream, int n) throws IOException {
        t t2 = new t(Math.max(0, n));
        a(inputStream, t2);
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
