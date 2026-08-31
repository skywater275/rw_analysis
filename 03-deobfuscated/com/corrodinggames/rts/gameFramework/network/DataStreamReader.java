/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.IOException;

public strictfp class DataStreamReader {
    public String a;
    public ByteArrayInputStream b;
    public DataInputStream c;

    /* 02b j/l.java: GZIPInputStream 构造抛 IOException (R8 移除 throws) */
    public DataStreamReader(byte[] byArray, boolean bl, boolean bl2) throws IOException {
        this.b = new ByteArrayInputStream(byArray);
        InputStream inputStream = bl ? new BufferedInputStream(new GZIPInputStream(this.b)) : this.b;
        this.c = new DataInputStream(inputStream);
    }
}
