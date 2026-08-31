/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.ChecksumCalculator;

public strictfp class ChecksumField {
    public String a;
    public long b;
    boolean c;
    final /* synthetic */ ChecksumCalculator d;  // 02b j/al.java: final ak d (ak=ChecksumCalculator)

    public ChecksumField(ChecksumCalculator ak2, String string) {
        this(ak2, string, true);
    }

    public ChecksumField(ChecksumCalculator ak2, String string, boolean bl) {
        this.d = ak2;
        this.a = string;
        this.c = bl;
        ak2.b.add(this);
    }
}
