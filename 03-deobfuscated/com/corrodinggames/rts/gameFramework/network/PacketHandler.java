/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.Packet;

public class PacketHandler
extends Packet {
    public Packet f;
    public int g;

    public PacketHandler(int n, Packet au2) {
        super(175);
        this.g = n;
        this.f = au2;
    }
}
