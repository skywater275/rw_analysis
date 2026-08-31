/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.gameFramework.network.NetworkPacket;

public class PacketHandler
extends NetworkPacket {
    public NetworkPacket f;
    public int g;

    public PacketHandler(int n, NetworkPacket au2) {
        super(175);
        this.g = n;
        this.f = au2;
    }
}
