/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import java.io.BufferedReader;

abstract class PacketSerializer {
    String d;
    boolean e;
    int f;

    void s() {
    }

    abstract void a(BufferedReader var1, int var2, String var3);

    abstract void a();
}
