/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;
import com.corrodinggames.rts.gameFramework.core.PlatformBackend;

import com.corrodinggames.rts.gameFramework.network.PlayerConnect;

public strictfp class NetworkPacket {
    public PacketDecoder connection = null;
    public int packetLength;
    public byte[] packetData;
    public int packetType = -1;
    public boolean isSystemPacket;

    public NetworkPacket(int n) {
        this.packetLength = n;
    }
}
