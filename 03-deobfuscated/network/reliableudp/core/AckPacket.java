/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp.core;

import network.reliableudp.core.Packet;

public class AckPacket
extends Packet {
    protected AckPacket() {
    }

    public AckPacket(int n, int n2) {
        this.a(64, n, 6);
        this.a(n2);
    }

    @Override
    public String a() {
        return "ACK";
    }
}
