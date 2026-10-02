/*
 * Decompiled with CFR 0.152.
 */
package network.reliableudp;
import com.corrodinggames.rts.game.GameFlagImpl;

import network.reliableudp.ReliableServerSocket;
import network.reliableudp.ReliableClientSocket;
import network.reliableudp.ReliableSocket;
import network.reliableudp.ConnectionEventHandler;
import java.util.ArrayList;

class ServerConnectionListener
implements ConnectionEventHandler {
    final /* synthetic */ ReliableServerSocket a;

    ServerConnectionListener(ReliableServerSocket b2, ReliableServerSocket$1 reliableServerSocket$1) {
        // T0 javap: 合成 2 参构造 (02b f.java (b, b$1))
        this(b2);
    }
    private ServerConnectionListener(ReliableServerSocket b2) {
        this.a = b2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(ReliableSocket h2) {
        if (h2 instanceof ReliableClientSocket) {
            ArrayList arrayList = ReliableServerSocket.e(this.a);
            synchronized (arrayList) {
                while (ReliableServerSocket.e(this.a).size() > 50) {
                    try {
                        ReliableServerSocket.e(this.a).wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                ReliableServerSocket.e(this.a).add((ReliableClientSocket) h2);
                ReliableServerSocket.e(this.a).notify();
            }
        }
    }

    @Override
    public void b(ReliableSocket h2) {
    }

    @Override
    public void c(ReliableSocket h2) {
        if (h2 instanceof ReliableClientSocket) {
            ReliableServerSocket.a(this.a, ((ReliableClientSocket) h2).c());
        }
    }

    @Override
    public void d(ReliableSocket h2) {
        if (h2 instanceof ReliableClientSocket) {
            ReliableServerSocket.a(this.a, ((ReliableClientSocket) h2).c());
        }
    }

    @Override
    public void e(ReliableSocket h2) {
    }
}
