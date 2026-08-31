/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.TagFilter;

import network.reliableudp.core.NullPacket;
import network.reliableudp.ReliableSocket;
import java.io.IOException;
import java.util.ArrayList;

class NullPacketSender
implements Runnable {
    final /* synthetic */ ReliableSocket a;

    NullPacketSender(ReliableSocket h2, ReliableSocket$1 reliableSocket$1) {
        // T0 javap: 合成 2 参构造 (02b l/n/j/k (h, h$1))
        this(h2);
    }
    private NullPacketSender(ReliableSocket h2) {
        this.a = h2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        ArrayList arrayList = ReliableSocket.g(this.a);
        synchronized (arrayList) {
            block6: {
                if (ReliableSocket.g(this.a).isEmpty()) {
                    try {
                        ReliableSocket.b(this.a, new NullPacket(ReliableSocket.h(this.a).a()));
                    }
                    catch (IOException iOException) {
                        if (!ReliableSocket.h()) break block6;
                        iOException.printStackTrace();
                    }
                }
            }
        }
    }
}
