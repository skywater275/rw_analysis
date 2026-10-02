/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.j;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public strictfp class l {
    public String a;
    public ByteArrayInputStream b;
    public DataInputStream c;

    public l(byte[] byArray, boolean bl, boolean bl2) {
        this.b = new ByteArrayInputStream(byArray);
        InputStream inputStream = bl ? new BufferedInputStream(new GZIPInputStream(this.b)) : this.b;
        this.c = new DataInputStream(inputStream);
    }
}
