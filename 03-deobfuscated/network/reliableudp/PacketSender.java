/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.TagFilter;

import network.reliableudp.ReliableSocket;
import java.io.IOException;
import java.util.ArrayList;

class PacketSender
implements Runnable {
    final /* synthetic */ ReliableSocket a;

    PacketSender(ReliableSocket h2, ReliableSocket$1 reliableSocket$1) {
        // T0 javap: 合成 2 参构造 (02b l/n/j/k (h, h$1))
        this(h2);
    }
    private PacketSender(ReliableSocket h2) {
        this.a = h2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        ArrayList arrayList = ReliableSocket.g(this.a);
        synchronized (arrayList) {
            for (network.reliableudp.core.Packet h2 : (java.util.Collection<network.reliableudp.core.Packet>) (java.util.Collection) ReliableSocket.g(this.a)) {
                try {
                    ReliableSocket.c(this.a, h2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            }
        }
    }
}
