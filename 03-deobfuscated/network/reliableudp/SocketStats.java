/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;

import network.reliableudp.ReliableSocket;

class SocketStats {
    private int bytesSent;
    private int bytesReceived;
    private int packetsSent;
    private int packetsReceived;
    private int packetsLost;

    public synchronized int a() {
        this.bytesSent = ReliableSocket.a(this.bytesSent);
        return this.bytesSent;
    }

    public synchronized int a(int n) {
        this.bytesSent = n;
        return this.bytesSent;
    }

    public synchronized int b(int n) {
        this.bytesReceived = n;
        return this.bytesReceived;
    }

    public synchronized int b() {
        return this.bytesReceived;
    }

    public synchronized void c() {
        ++this.packetsSent;
    }

    public synchronized int d() {
        return this.packetsSent;
    }

    public synchronized int e() {
        int n = this.packetsSent;
        this.packetsSent = 0;
        return n;
    }

    public synchronized void f() {
        ++this.packetsReceived;
    }

    public synchronized int g() {
        return this.packetsReceived;
    }

    public synchronized int h() {
        int n = this.packetsReceived;
        this.packetsReceived = 0;
        return n;
    }

    public synchronized void i() {
        ++this.packetsLost;
    }

    public synchronized int j() {
        return this.packetsLost;
    }

    public synchronized int k() {
        int n = this.packetsLost;
        this.packetsLost = 0;
        return n;
    }

    public synchronized void l() {
        this.packetsReceived = 0;
        this.packetsLost = 0;
        this.packetsSent = 0;
    }
}
