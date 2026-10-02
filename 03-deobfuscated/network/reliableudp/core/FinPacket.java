/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp.core;

import network.reliableudp.core.Packet;

public class FinPacket
extends Packet {
    protected FinPacket() {
    }

    public FinPacket(int n) {
        this.a(2, n, 6);
    }

    @Override
    public String a() {
        return "FIN";
    }
}
