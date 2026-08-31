/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

public strictfp class ServerInfo {
    String a;
    long b;
    String c;

    public String a() {
        if (this.c != null) {
            return this.c;
        }
        return "Active ban";
    }

    public float b() {
        long l = System.currentTimeMillis();
        return (float)(this.b - l) / 1000.0f;
    }
}
