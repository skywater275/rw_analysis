/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp.core;

import java.io.IOException;

import network.reliableudp.core.Packet;

public class DataPacket
extends Packet {
    private byte[] a;

    protected DataPacket() {
    }

    public DataPacket(int n, int n2, byte[] byArray, int n3, int n4) {
        this.a(64, n, 6);
        this.a(n2);
        this.a = new byte[n4];
        System.arraycopy(byArray, n3, this.a, 0, n4);
    }

    @Override
    public int b() {
        return this.a.length + super.b();
    }

    @Override
    public String a() {
        return "DAT";
    }

    public byte[] c() {
        return this.a;
    }

    @Override
    public byte[] d() {
        byte[] byArray = super.d();
        System.arraycopy(this.a, 0, byArray, 6, this.a.length);
        return byArray;
    }

    @Override
    public void a(byte[] byArray, int n, int n2) throws IOException {
        super.a(byArray, n, n2);
        this.a = new byte[n2 - 6];
        System.arraycopy(byArray, n + 6, this.a, 0, this.a.length);
    }
}
