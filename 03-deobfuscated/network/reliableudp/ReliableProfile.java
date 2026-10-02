/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.UnitTypeCount;

import java.io.IOException;

public class ReliableProfile {
    public static final ReliableProfile a = new ReliableProfile();
    private int maxRetransmissions;
    private int maxCumulativeAcks;
    private int retransmissionTimeout;
    private int cumulativeAckTimeout;
    private int nullSegmentTimeout;
    private int maxResetAttempts;
    private int connectTimeout;
    private int connectionBufferSize;
    private int j;
    private int k;
    private int l;

    public ReliableProfile() {
        try {
            this.a(32, 32, 300, 70, 0, 3, 3, 3, 2000, 600, 300);
        }
        catch (IOException iOException) {
            throw new RuntimeException("IOException on ReliableSocketProfile default:" + iOException);
        }
    }

    public ReliableProfile(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) throws IOException {
        this.a(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11);
    }

    private void a(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) throws IOException {
        this.a("maxSendQueueSize", n, 1, 255);
        this.a("maxRecvQueueSize", n2, 1, 255);
        this.a("maxSegmentSize", n3, 22, 6535);
        this.a("maxOutstandingSegs", n4, 1, 255);
        this.a("maxRetrans", n5, 0, 255);
        this.a("maxCumulativeAcks", n6, 0, 255);
        this.a("maxOutOfSequence", n7, 0, 255);
        this.a("maxAutoReset", n8, 0, 255);
        this.a("nullSegmentTimeout", n9, 0, 65535);
        this.a("retransmissionTimeout", n10, 100, 65535);
        this.a("cumulativeAckTimeout", n11, 100, 65535);
        this.maxRetransmissions = n;
        this.maxCumulativeAcks = n2;
        this.retransmissionTimeout = n3;
        this.cumulativeAckTimeout = n4;
        this.nullSegmentTimeout = n5;
        this.maxResetAttempts = n6;
        this.connectTimeout = n7;
        this.connectionBufferSize = n8;
        this.j = n9;
        this.k = n10;
        this.l = n11;
    }

    public int a() {
        return this.retransmissionTimeout;
    }

    public int b() {
        return this.cumulativeAckTimeout;
    }

    public int c() {
        return this.nullSegmentTimeout;
    }

    public int d() {
        return this.maxResetAttempts;
    }

    public int e() {
        return this.connectTimeout;
    }

    public int f() {
        return this.connectionBufferSize;
    }

    public int g() {
        return this.j;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.maxRetransmissions).append(", ");
        stringBuilder.append(this.maxCumulativeAcks).append(", ");
        stringBuilder.append(this.retransmissionTimeout).append(", ");
        stringBuilder.append(this.cumulativeAckTimeout).append(", ");
        stringBuilder.append(this.nullSegmentTimeout).append(", ");
        stringBuilder.append(this.maxResetAttempts).append(", ");
        stringBuilder.append(this.connectTimeout).append(", ");
        stringBuilder.append(this.connectionBufferSize).append(", ");
        stringBuilder.append(this.j).append(", ");
        stringBuilder.append(this.k).append(", ");
        stringBuilder.append(this.l);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void a(String string, int n, int n2, int n3) throws IOException {
        if (n < n2 || n > n3) {
            throw new IOException(string + " out of range");
        }
    }
}
