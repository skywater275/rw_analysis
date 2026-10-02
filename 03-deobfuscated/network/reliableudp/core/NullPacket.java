/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp.core;

import network.reliableudp.core.Packet;

public class NullPacket
extends Packet {
    protected NullPacket() {
    }

    public NullPacket(int n) {
        this.a(8, n, 6);
    }

    @Override
    public String a() {
        return "NUL";
    }
}
