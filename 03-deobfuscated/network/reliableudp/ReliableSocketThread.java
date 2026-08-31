/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.TagFilter;

import network.reliableudp.core.AckPacket;
import network.reliableudp.core.ExtendedAckPacket;
import network.reliableudp.core.SynPacket;
import network.reliableudp.ReliableSocket;
import java.io.IOException;

class ReliableSocketThread
extends Thread {
    final /* synthetic */ ReliableSocket a;

    public ReliableSocketThread(ReliableSocket h2) {
        super("ReliableSocket");
        this.a = h2;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            network.reliableudp.core.Packet h2;
            while ((h2 = ReliableSocket.f(this.a)) != null) {
                if (h2 instanceof network.reliableudp.core.SynPacket) {
                    this.a.a((network.reliableudp.core.SynPacket) h2);
                } else if (h2 instanceof network.reliableudp.core.ExtendedAckPacket) {
                    ReliableSocket.a(this.a, (network.reliableudp.core.ExtendedAckPacket) h2);
                } else if (!(h2 instanceof network.reliableudp.core.AckPacket)) {
                    ReliableSocket.a(this.a, h2);
                }
                this.a.c(h2);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
