/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp.core;

import network.reliableudp.core.Packet;

public class ResetPacket
extends Packet {
    protected ResetPacket() {
    }

    @Override
    public String a() {
        return "RST";
    }
}
