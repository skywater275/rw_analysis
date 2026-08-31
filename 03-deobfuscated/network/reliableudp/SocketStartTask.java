/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.TagFilter;

import network.reliableudp.ReliableSocket;

class SocketStartTask
implements Runnable {
    final /* synthetic */ ReliableSocket a;

    SocketStartTask(ReliableSocket h2, ReliableSocket$1 reliableSocket$1) {
        // T0 javap: 合成 2 参构造 (02b l/n/j/k (h, h$1))
        this(h2);
    }
    private SocketStartTask(ReliableSocket h2) {
        this.a = h2;
    }

    @Override
    public void run() {
        ReliableSocket.j(this.a);
    }
}
